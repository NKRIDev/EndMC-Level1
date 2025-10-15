package fr.nkri.zone.cmds;

import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.japi.cmds.ICommand;
import fr.nkri.japi.cmds.interfaces.Command;
import fr.nkri.japi.utils.JUtils;
import fr.nkri.japi.utils.areas.Area;
import fr.nkri.japi.utils.items.ItemBuilder;
import fr.nkri.zone.managers.Zone;
import fr.nkri.zone.managers.ZoneManager;
import fr.nkri.zone.managers.selectors.models.AreaSelector;
import net.minecraft.server.v1_8_R3.ItemBucket;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static sun.audio.AudioPlayer.player;

public class ZoneCommand extends ICommand {

    private final ZoneManager zoneManager;
    public ZoneCommand(final ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    @Override
    @Command(name = "zone", permissionNode = "zone.admin", isConsole = false)
    public boolean onCommand(final CommandArguments args) {
        final Player player = args.getPlayer();

        if(args.length() == 0){
            showHelp(player);
            return false;
        }

        final String subCommand = args.getArgs(0).toLowerCase();

        /**
         * Checking the arguments to verify that the command is working correctly
         */
        if((subCommand.equalsIgnoreCase("create") || subCommand.equalsIgnoreCase("remove") || subCommand.equalsIgnoreCase("info"))
                && args.length() < 2){
            player.sendMessage(JUtils.color("&cUtilisation: /zone " + subCommand + " <nom>"));
            return false;
        }

        /**
         * Execute command
         */
        switch (subCommand) {
            //Give item
            case "wand":
                if (JUtils.isInventoryFull(player)){
                    player.sendMessage(JUtils.color("&cFaite de la place dans votre inventaire pour récupérer l'item."));
                    return false;
                }

                final String stackName = "&6&lHâche magique";
                final ItemStack stack = new ItemBuilder(Material.WOOD_AXE).setName(JUtils.color(stackName)).toItemStack();

                player.getInventory().addItem(stack);
                player.sendMessage(JUtils.color("&aVous venez de reçevoir la %stack% &r&a!")
                        .replace("%stack%", JUtils.color(stackName)));
                return true;

            //Zone arrays
            case "list":
                player.sendMessage(JUtils.color("&eListes des zones disponibles :"));
                player.sendMessage(JUtils.color("&6%zones%")
                        .replace("%zones%", this.zoneManager.getAllZones()));
                return true;

            //Create zone
            case "create":
                if(args.length() < 2){
                    player.sendMessage(JUtils.color("&cUtilisation: /zone create <nom>"));
                    return false;
                }

                final String createName = args.getArgs(1);

                /**
                 * recovery of positions selected by the player
                 */
                final AreaSelector areaSelector = this.zoneManager.getSelectorManager().getSelector(player.getUniqueId());

                if(areaSelector == null){
                    player.sendMessage(JUtils.color("&cVous devez sélectionner une zone."));
                    return false;
                }

                if(!areaSelector.canCreate()){
                    player.sendMessage(JUtils.color("&cVous devez sélectionner une zone !"));
                    return false;
                }

                if(this.zoneManager.zoneExists(createName)){
                    player.sendMessage(JUtils.color("&cUne zone portant ce nom existe déjà."));

                    //Removes a player's selection
                    this.zoneManager.getSelectorManager().removeSelector(player.getUniqueId());
                    return false;
                }

                /**
                 * Create zone
                 */

                //Create area object
                final Area area = areaSelector.getArea();

                //add in map
                this.zoneManager.createZone(createName, area);
                player.sendMessage(JUtils.color("&aVous venez de créer la zone %name% avec succès !")
                        .replace("%name%", createName));

                /**
                 * Removed player selection when creating a zone
                 */
                this.zoneManager.getSelectorManager().removeSelector(player.getUniqueId());
                return true;

            //Remove zone
            case "remove":
                if(args.length() < 2){
                    player.sendMessage(JUtils.color("&cUtilisation: /zone remove <nom>"));
                    return false;
                }

                final String removeName = args.getArgs(1);

                /**
                 * Check if zone doesn't exist
                 */
                if(!this.zoneManager.zoneExists(removeName)){
                    player.sendMessage(JUtils.color("&cLa zone %name% n'existe pas, impossible de la supprimer.")
                            .replace("%name%", removeName));
                    return false;
                }

                this.zoneManager.removeZone(removeName);
                player.sendMessage(JUtils.color("&aVous venez de supprimer la zone %name% avec succès !")
                        .replace("%name%", removeName));
                return true;

            //Zone info
            case "info":
                if(args.length() < 2){
                    player.sendMessage(JUtils.color("&cUtilisation: /zone info <nom>"));
                    return false;
                }

                final String infoName = args.getArgs(1);

                /**
                 * Area recovery
                 */
                final Zone zone = this.zoneManager.getZone(infoName);

                /**
                 * Check if zone doesn't exist
                 */
                if(zone == null){
                    player.sendMessage(JUtils.color("&cLa zone %name% n'existe pas, impossible de la supprimer.")
                            .replace("%name%", infoName));
                    return false;
                }

                /**
                 * NOTE: Je ne sais pas quoi mettre pour les informations de la zone.
                 */
                player.sendMessage(JUtils.LINE);
                player.sendMessage(JUtils.color("&eInformation sur la zone &6%name% &e:")
                        .replace("%name%", zone.getName()));
                player.sendMessage(JUtils.color("&7..."));
                player.sendMessage(JUtils.LINE);
                break;

                //Or else
            default:
                showHelp(player);
                return false;
        }

        return false;
    }

    /**
     * Player help
     * @param player : target who received message
     */
    private void showHelp(final Player player) {
        player.sendMessage(JUtils.LINE);
        player.sendMessage(JUtils.color("&6&lCommandes - Zone:"));
        player.sendMessage(JUtils.color("&e/zone wand §8: §7donne un item pour sélectionner deux positions (click gauche/droit)."));
        player.sendMessage(JUtils.color("&e/zone create <nom> §8: §7crée une zone."));
        player.sendMessage(JUtils.color("&e/zone list §8: §7affiche la liste de zones créer."));
        player.sendMessage(JUtils.color("&e/zone remove <nom> §8: §7supprime une zone existante."));
        player.sendMessage(JUtils.color("&e/zone info <nom> §8: §7donne des informations sur la zone."));
        player.sendMessage(JUtils.LINE);
    }
}
