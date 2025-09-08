package fr.nkri.zone.managers;

import fr.nkri.zone.managers.models.AreaSelector;
import fr.nkri.zone.managers.models.PosSelector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ZoneManager {

    /**
     * Instance of area manager
     */
    private static ZoneManager INSTANCE;

    /**
     * Zone selection management
     * @key player UUID
     * @value block pos data
     */
    private final Map<UUID, AreaSelector> selectors;

    public ZoneManager(){
        INSTANCE = this;
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
}
