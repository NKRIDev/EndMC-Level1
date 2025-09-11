package fr.nkri.zone.managers;

import com.google.gson.annotations.Expose;
import fr.nkri.japi.utils.areas.Area;
import lombok.Getter;

@Getter
public class Zone {

    @Expose(serialize = true, deserialize = true)
    private final String name;

    @Expose(serialize = true, deserialize = true)
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
