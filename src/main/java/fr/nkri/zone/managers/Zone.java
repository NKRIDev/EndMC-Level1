package fr.nkri.zone.managers;

import fr.nkri.japi.utils.areas.Area;
import lombok.Getter;

@Getter
public class Zone {

    private final String name;
    private final Area area;

    /**
     * Represents an area
     *
     * @param name area name
     * @param area arena cuboid
     */
    public Zone(final String name, final Area area) {
        this.name = name;
        this.area = area;
    }
}
