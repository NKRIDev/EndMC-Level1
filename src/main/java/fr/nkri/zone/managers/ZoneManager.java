package fr.nkri.zone.managers;

import fr.nkri.zone.managers.selectors.SelectorManager;
import lombok.Getter;

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

    public ZoneManager(){
        INSTANCE = this;
        this.selectorManager = new SelectorManager();
    }
}
