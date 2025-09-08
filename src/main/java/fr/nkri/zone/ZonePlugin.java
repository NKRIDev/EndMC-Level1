package fr.nkri.zone;

import fr.nkri.japi.JAPI;
import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.zone.cmds.ZoneCommand;
import fr.nkri.zone.events.AreaSelectorEvent;
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

        //register events
        JAPI.getInstance().registerListeners(new AreaSelectorEvent(zoneManager));

        //register cmds
        JAPI.getInstance().registerCommand(new ZoneCommand(zoneManager));
    }

    @Override
    public void onDisable() {
    }
}
