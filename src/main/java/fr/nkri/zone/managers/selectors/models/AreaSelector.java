package fr.nkri.zone.managers.selectors.models;

import fr.nkri.japi.utils.areas.Area;
import lombok.Getter;
import lombok.Setter;

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
     * Sets the 1st position
     * @param blockX X position of the clicked block
     * @param blockY Y position of the clicked block
     */
    public void posFirst(final double blockX, final double blockY) {
        final PosSelector selectorFirst = new PosSelector(blockX, blockY);
        this.selectorFirst = selectorFirst;
    }

    /**
     * Sets the 2nd position
     * @param blockX X position of the clicked block
     * @param blockY Y position of the clicked block
     */
    public void posSecond(final double blockX, final double blockY) {
        final PosSelector selectorSecond = new PosSelector(blockX, blockY);
        this.selectorSecond = selectorSecond;
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
}
