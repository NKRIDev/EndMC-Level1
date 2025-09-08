package fr.nkri.zone.cmds;

import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.japi.cmds.ICommand;
import fr.nkri.japi.cmds.interfaces.Command;
import fr.nkri.japi.utils.JUtils;
import fr.nkri.zone.managers.ZoneManager;
import fr.nkri.zone.managers.models.AreaSelector;
import org.bukkit.entity.Player;

public class ZoneCommand extends ICommand {

    private final ZoneManager zoneManager;
    public ZoneCommand(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    @Override
    @Command(name = "zone", isConsole = false)
    public boolean onCommand(CommandArguments args) {
        final Player player = args.getPlayer();

        //TODO : juste pour tester l'area selector
        if(args.getArgs(0).equalsIgnoreCase("create")){
            final AreaSelector areaSelector = this.zoneManager.getSelector(player.getUniqueId());

            if (!areaSelector.canCreate()){
                player.sendMessage(JUtils.color("&cVous devez sélectionner une zone !"));
                return false;
            }
            player.sendMessage(JUtils.color("&eOK !, Pos 1 (x : "+areaSelector.getSelectorFirst().getBlockX()+" - y: "+areaSelector.getSelectorFirst().getBlockY()+") - Pos 2 (x : "+areaSelector.getSelectorSecond().getBlockX()+" - y: "+areaSelector.getSelectorSecond().getBlockY()+")"));

        }

        return false;
    }
}
