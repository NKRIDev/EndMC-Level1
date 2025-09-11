package fr.nkri.zone.events;

import fr.nkri.japi.utils.JUtils;
import fr.nkri.zone.managers.ZoneManager;
import fr.nkri.zone.managers.selectors.models.PosSelector;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class AreaSelectorEvent implements Listener {

    private final ZoneManager zoneManager;

    /**
     * Manage the input speed of the zone selector (pos1 and pos2)
     * @param zoneManager manage zone
     */
    public AreaSelectorEvent(final ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        /*
        the player must have an axe in hand to create a selector
         */
        if(e.getItem() == null || e.getItem().getType() != Material.WOOD_AXE) {
            return;
        }

        if(!e.getItem().hasItemMeta()){
            return;
        }

        final ItemMeta meta = e.getItem().getItemMeta();
        if(meta == null || !meta.hasDisplayName()){
            return;
        }

        if (!meta.getDisplayName().equals(JUtils.color("&6&lHâche magique"))){
            return;
        }

        final Player player = e.getPlayer();
        final Block clickedBlock = e.getClickedBlock();

        if(clickedBlock == null) {
            return;
        }

        if(!player.hasPermission("zone.selector")){
            player.sendMessage(JUtils.color("&cErreur : vous n'avez pas la permission d'effectuer cette action."));
            return;
        }

        if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
            //Create a pos1
            final PosSelector pos1 = new PosSelector(clickedBlock.getLocation());
            this.zoneManager.getSelectorManager().setFirstPos(player.getUniqueId(), pos1);

            e.setCancelled(true);
            player.sendMessage(JUtils.color("&7[&6&lSELECTOR&7&l] &r&aPos 1 définit en X: %x% - Y: %y%")
                    .replace("%x%", String.valueOf(pos1.getBlockX()))
                    .replace("%y%", String.valueOf(pos1.getBlockY())));
        }
        else if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            //Create a pos2
            final PosSelector pos2 = new PosSelector(clickedBlock.getLocation());
            this.zoneManager.getSelectorManager().setSecondPos(player.getUniqueId(), pos2);

            e.setCancelled(true);
            player.sendMessage(JUtils.color("&7[&6&lSELECTOR&7&l] &r&aPos 2 définit en X: %x% - Y: %y%")
                    .replace("%x%", String.valueOf(pos2.getBlockX()))
                    .replace("%y%", String.valueOf(pos2.getBlockY())));
        }
    }
}
