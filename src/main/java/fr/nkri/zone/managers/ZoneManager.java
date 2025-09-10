package fr.nkri.zone.managers;

import fr.nkri.japi.utils.JUtils;
import fr.nkri.japi.utils.areas.Area;
import fr.nkri.zone.managers.selectors.SelectorManager;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ZoneManager {

    /**
     * Instance of area manager
     */
    @Getter
    private static ZoneManager INSTANCE;

    /**
     * Manages the area definition part
     */
    private final SelectorManager selectorManager;

    /**
     * lists of created zones associated with
     * their unique identifiers
     *
     * @key zone name
     * @value zone object
     */
    private final Map<String, Zone> zones;

    public ZoneManager(){
        INSTANCE = this;
        this.selectorManager = new SelectorManager();
        this.zones = new HashMap<>();
    }

    /**
     * Returns the zone object by name
     *
     * @param name zone name
     * @return zone object
     */
    public Zone getZone(final String name){
        return this.zones.get(name);
    }

    /**
     * Create a new area
     *
     * @param name area name
     * @param area area cuboid
     */
    public void createZone(final String name, final Area area){
        this.zones.putIfAbsent(name, new Zone(name, area));
    }

    /**
     * Removes the area from the map
     * @param name zone name
     */
    public void removeZone(final String name){
        this.zones.remove(name);
    }

    /**
     * Returns whether the zone exists or not
     *
     * @param name zone name
     * @return True or false whether the area exists or not
     */
    public boolean zoneExists(final String name){
        return this.zones.containsKey(name);
    }

    /**
     * Retrieves a String containing all the zone names
     *
     * @return the list of zone names in display format for the player
     */
    public String getAllZones(){
        if(this.zones.isEmpty()){
            return JUtils.color("&cAuncune zone disponible.");
        }

        //NOTE : additione tout les nom des zones et les sépares par une ,
        return String.join(", ", this.zones.keySet());
    }
}
