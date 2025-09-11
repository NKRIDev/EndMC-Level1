package fr.nkri.zone.managers.datas;

import fr.nkri.japi.JAPI;
import fr.nkri.japi.logs.Logs;
import fr.nkri.japi.logs.enums.LogsType;
import fr.nkri.japi.utils.json.FileUtils;
import fr.nkri.zone.managers.Zone;
import fr.nkri.zone.managers.ZoneManager;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ZoneData {

    private final File zoneDir;
    private final ZoneManager zoneManager;

    /**
     * ZoneData for saving/loading a single zone area.
     * @param main Plugin main class
     * @param zoneManager manager zone
     */
    public ZoneData(final Plugin main, final ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
        this.zoneDir = new File(main.getDataFolder(), "datas");
    }

    /**
     * Load zone from its JSON file
     */
    public void loadZones(){
        if(!zoneDir.exists()){
            return;
        }

        for(File file : zoneDir.listFiles()){
            if(file.isFile()){
                final String json = FileUtils.loadContent(file);
                final Zone zone = JAPI.getInstance().deserialize(json, Zone.class);

                if(zone != null){
                    this.zoneManager.getZones().putIfAbsent(zone.getName(), zone);
                    Logs.sendLog("[Zone-DATA]",
                            "Successfully recovered zone %name%".replace("%name%", zone.getName()),
                            LogsType.SUCCES);
                }
                else{
                    Logs.sendLog("[Zone-DATA]",
                            "Unable to load file : %name%".replace("%name%", file.getName()),
                            LogsType.ERROR);
                }
            }
        }
    }

    /**
     * Save zone to a JSON file
     */
    public void saveZones(){
        for(Zone zone : this.zoneManager.getZones().values()){
            final File file = new File(zoneDir, zone.getName() + ".json");
            final String json = JAPI.getInstance().serialize(zone);

            FileUtils.save(file, json);
            Logs.sendLog("[Zone-DATA]",
                    "Zone %name% saved successfully".replace("%name%", zone.getName()),
                    LogsType.SUCCES);
        }
    }
}
