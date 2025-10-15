package fr.nkri.zone.events;

import fr.nkri.japi.utils.JUtils;
import fr.nkri.zone.events.customs.ZoneEnterEvent;
import fr.nkri.zone.events.customs.ZoneLeaveEvent;
import fr.nkri.zone.managers.Zone;
import fr.nkri.zone.managers.ZoneManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ZoneEvent implements Listener {

    private final ZoneManager zoneManager;

    /**
     * Listen to the whole part of the zones
     * @param zoneManager manage zone
     */
    public ZoneEvent(final ZoneManager zoneManager){
        this.zoneManager = zoneManager;
    }

    //Player enter in zone
    @EventHandler
    public void onPlayerEnterZone(final ZoneEnterEvent event){
        final Player player = event.getPlayer();
        final Zone zone = event.getZone();

        player.sendMessage(JUtils.color("&aVous venez de &e&lrentrer &r&adans la zone %name%")
                .replace("%name%", zone.getName()));
    }

    //Player leave zone
    @EventHandler
    public void onPlayerQuitZone(final ZoneLeaveEvent event){
        final Player player = event.getPlayer();
        final Zone zone = event.getZone();

        player.sendMessage(JUtils.color("&cVous venez de &e&lsortir &r&cde la zone %name%")
                .replace("%name%", zone.getName()));
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event){
        /**
         * Delete to make cache space
         */
        this.zoneManager.getPlayersInZone().remove(event.getPlayer().getUniqueId());
    }
}
