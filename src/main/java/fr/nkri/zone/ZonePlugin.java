package fr.nkri.zone;

import fr.nkri.zone.managers.ZoneManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ZonePlugin extends JavaPlugin {

    /**
     * Instance of the main class
     */
    @Getter
    private static ZonePlugin INSTANCE;

    /**
     * Init managers
     */
    private ZoneManager zoneManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        //init managers
        this.zoneManager = new ZoneManager();
    }

    @Override
    public void onDisable() {
    }
}
