package fr.nkri.zone.managers.selectors;

import fr.nkri.zone.managers.selectors.models.AreaSelector;
import fr.nkri.zone.managers.selectors.models.PosSelector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SelectorManager {

    /**
     * Zone selection management
     * @key player UUID
     * @value block pos data
     */
    private final Map<UUID, AreaSelector> selectors;

    /**
     * Manages the area definition part
     */
    public SelectorManager() {
        this.selectors = new HashMap<>();
    }

    /**
     * Create or set the 1st selected position
     * @param uuid player UUID, clicker
     * @param selector selected block
     */
    public void setFirstPos(final UUID uuid, final PosSelector selector){
        final AreaSelector areaSelector = getSelector(uuid);
        if(areaSelector == null){
            this.selectors.put(uuid, new AreaSelector(selector, null));
        }
        else{
            areaSelector.setSelectorFirst(selector);
        }
    }

    /**
     * Create or set the 2nd selected position
     * @param uuid player UUID, clicker
     * @param selector selected block
     */
    public void setSecondPos(final UUID uuid, final PosSelector selector){
        final AreaSelector areaSelector = getSelector(uuid);
        if(areaSelector == null){
            this.selectors.put(uuid, new AreaSelector(null, selector));
        }
        else{
            areaSelector.setSelectorSecond(selector);
        }
    }

    /**
     * Retrieve a player's selection
     * @param uuid player UUID
     * @return selection
     */
    public AreaSelector getSelector(final UUID uuid){
        return this.selectors.get(uuid);
    }

    /**
     * Removes a player's selection
     * @param uuid player UUID
     */
    public void removeSelector(final UUID uuid){
        this.selectors.remove(uuid);
    }
}
