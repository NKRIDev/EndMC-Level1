package fr.nkri.zone.managers.runnables;

import fr.nkri.zone.managers.Zone;
import fr.nkri.zone.managers.ZoneManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ZoneRunnable extends BukkitRunnable {

    /**
     * Manage zone
     */
    private final ZoneManager zoneManager;

    /**
     * Check if players have entered or exited an area
     *
     * @param zoneManager manage zone
     */
    public ZoneRunnable(final ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            final UUID uuid = player.getUniqueId();

            /**
             * Current list of areas the player is in
             */
            final Set<String> currentZones = new HashSet<>();
            for(Zone zone : this.zoneManager.getZones().values()){
                if(zone.getArea().isInArea(player)){
                    currentZones.add(zone.getName());
                }
            }

            /**
             * Previous lsit
             */
            final Set<String> previousZones = this.zoneManager.getPlayersInZone().getOrDefault(uuid, new HashSet<>());

            /**
             * Detection of entries
             */
            for(String zoneName : currentZones){
                if(!previousZones.contains(zoneName)){
                    player.sendMessage("§eEntrer dans la zone: " + zoneName);
                }
            }

            /**
             * Exit detection
             */
            for(String zoneName : previousZones){
                if(!currentZones.contains(zoneName)){
                    player.sendMessage("§cSortie de la zone: " + zoneName);
                }
            }

            /**
             * Update map
             */
            this.zoneManager.getPlayersInZone().put(uuid, currentZones);
        }
    }
}
