package fr.nkri.zone.managers.selectors.models;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Getter
public class PosSelector {

    private final double blockX, blockY, blockZ;
    private final String world;

    /**
     * Coordinate of one of the blocks clicked by the player
     *
     * @param location block location
     */
    public PosSelector(final Location location) {
        this.blockX = location.getBlockX();
        this.blockY = location.getBlockY();
        this.blockZ = location.getBlockZ();
        this.world = location.getWorld().getName();
    }

    /**
     * Retrieves the position as a Bukkit Location
     * @return location object
     */
    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), blockX, blockY, blockZ);
    }
}
