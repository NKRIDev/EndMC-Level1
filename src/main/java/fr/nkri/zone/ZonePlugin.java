package fr.nkri.zone;

import fr.nkri.japi.JAPI;
import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.zone.cmds.ZoneCommand;
import fr.nkri.zone.events.AreaSelectorEvent;
import fr.nkri.zone.managers.ZoneManager;
import fr.nkri.zone.managers.datas.ZoneData;
import fr.nkri.zone.managers.runnables.ZoneRunnable;
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
    private ZoneData zoneData;

    /**
     * Runnalbe that detects the entry and
     * exit of players in the zones
     */
    private ZoneRunnable zoneRunnable;

    @Override
    public void onEnable() {
        INSTANCE = this;

        //init managers
        this.zoneManager = new ZoneManager();

        //load data
        this.zoneData = new ZoneData(this, zoneManager);
        this.zoneData.loadZones();

        //Start 'zone runnable'
        this.zoneRunnable = new ZoneRunnable(zoneManager);
        this.zoneRunnable.runTaskTimer(this, 20L, 20L);

        //register events
        JAPI.getInstance().registerListeners(new AreaSelectorEvent(zoneManager));

        //register cmds
        JAPI.getInstance().registerCommand(new ZoneCommand(zoneManager));
    }

    @Override
    public void onDisable() {
        //save zones
        this.zoneData.saveZones();

        //stop zone runnable
        if(this.zoneRunnable != null) {
            this.zoneRunnable.cancel();
        }
    }
}
