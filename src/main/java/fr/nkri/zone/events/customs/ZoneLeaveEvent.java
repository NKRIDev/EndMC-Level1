package fr.nkri.zone.events.customs;

import fr.nkri.zone.managers.Zone;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class ZoneLeaveEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final Zone zone;

    /**
     * Call when a player leave a zone
     * @param player Outgoing player
     * @param zone Area in which the player exits
     */
    public ZoneLeaveEvent(final Player player, final Zone zone){
        this.player = player;
        this.zone = zone;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
