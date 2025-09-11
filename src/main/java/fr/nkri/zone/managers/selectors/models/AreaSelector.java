package fr.nkri.zone.managers.selectors.models;

import fr.nkri.japi.utils.areas.Area;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class AreaSelector {

    private PosSelector selectorFirst, selectorSecond;
    private Area area;

    /**
     * Position the blocks to form a cuboid
     *
     * @param selectorFirst 1st position of the block click with the left click by the player
     * @param selectorSecond 2nd position of the block click with the right click by the player
     */
    public AreaSelector(final PosSelector selectorFirst, final PosSelector selectorSecond) {
        this.selectorFirst = selectorFirst;
        this.selectorSecond = selectorSecond;
        this.area =null;
    }

    /**
     * Know if we can create an arena, if the
     * 2 selectors are correctly selected
     *
     * @return True then we can create a zone, otherwise false
     */
    public boolean canCreate(){
        return this.selectorFirst != null && this.selectorSecond != null;
    }

    /**
     * Create the arena object from the 2 positions
     * @return the Area object or null if it could not be created
     */
    public Area getArea(){
        if(!canCreate()){
            return null;
        }

        final Location locationFirst = this.selectorFirst.getLocation();
        final Location locationSecond = this.selectorSecond.getLocation();

        return new Area(locationFirst, locationSecond);
    }
}
