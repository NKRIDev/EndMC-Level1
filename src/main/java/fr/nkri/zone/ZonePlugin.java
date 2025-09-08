package fr.nkri.zone;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ZonePlugin extends JavaPlugin {

    /**
     * Instance of the main class
     */
    @Getter
    private static ZonePlugin INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
    }
}
