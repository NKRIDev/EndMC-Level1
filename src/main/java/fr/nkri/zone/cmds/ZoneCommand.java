package fr.nkri.zone.cmds;

import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.japi.cmds.ICommand;
import fr.nkri.japi.cmds.interfaces.Command;
import fr.nkri.japi.utils.JUtils;
import fr.nkri.zone.managers.ZoneManager;
import fr.nkri.zone.managers.selectors.models.AreaSelector;
import org.bukkit.entity.Player;

public class ZoneCommand extends ICommand {

    private final ZoneManager zoneManager;
    public ZoneCommand(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    //Note: pas de permission pour faciliter le test du plugin
    @Override
    @Command(name = "zone", isConsole = false)
    public boolean onCommand(CommandArguments args) {
        final Player player = args.getPlayer();

        if(args.length() == 0){
            showHelp(player);
            return false;
        }

        final String subCommand = args.getArgs(0).toLowerCase();

        /**
         * Checking the arguments to verify that the command is working correctly
         */
        if ((subCommand.equalsIgnoreCase("create") || subCommand.equalsIgnoreCase("remove") || subCommand.equalsIgnoreCase("info"))
                && args.length() < 2) {
            player.sendMessage(JUtils.color("&cUtilisation: /zone " + subCommand + " <nom>"));
            return false;
        }

        /**
         * Execute command
         */
        switch (subCommand) {
            //Give item
            case "wand":
                break;

            //Zone arrays
            case "list":
                break;

            //Create zone
            case "create":
                if (args.length() < 2) {
                    player.sendMessage(JUtils.color("&cUtilisation: /zone create <nom>"));
                    return false;
                }

                final String createName = args.getArgs(1);
                final AreaSelector areaSelector = this.zoneManager.getSelectorManager().getSelector(player.getUniqueId());

                if (!areaSelector.canCreate()){
                    player.sendMessage(JUtils.color("&cVous devez sélectionner une zone !"));
                    return false;
                }
                player.sendMessage(JUtils.color("&eOK !, Pos 1 (x : "+areaSelector.getSelectorFirst().getBlockX()+" - y: "+areaSelector.getSelectorFirst().getBlockY()+") - Pos 2 (x : "+areaSelector.getSelectorSecond().getBlockX()+" - y: "+areaSelector.getSelectorSecond().getBlockY()+")"));

                //TODO : create zone with NAME
                return true;

            //Remove zone
            case "remove":
                if (args.length() < 2) {
                    player.sendMessage(JUtils.color("&cUtilisation: /zone remove <nom>"));
                    return false;
                }

                final String removeName = args.getArgs(1);
                break;

            //Zone info
            case "info":
                if (args.length() < 2) {
                    player.sendMessage(JUtils.color("&cUtilisation: /zone info <nom>"));
                    return false;
                }

                final String infoName = args.getArgs(1);
                break;
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
