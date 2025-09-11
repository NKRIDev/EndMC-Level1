
# ZonePlugin

**Minecraft Spigot plugin for managing custom zones with player entry and exit detection.**

---

## Features

- Create and delete cuboid zones using in-game position selections.
- Real-time detection of player entries and exits within zones.
- Custom Bukkit events (`ZoneEnterEvent` and `ZoneLeaveEvent`) to react to zone changes.
- Automatic zone saving in JSON files (`/datas/<zone_name>.json`).
- Easy-to-use "magic axe" item to select positions.

---

## Commands

| Command                | Description                                             | Permission     |
|------------------------|---------------------------------------------------------|----------------|
| `/zone wand`           | Gives a magic axe to select two positions (left/right click). | `zone.selector` |
| `/zone create <name>`  | Creates a zone with the specified name.                 | `zone.admin`   |
| `/zone list`           | Displays a list of created zones.                        | `zone.admin`   |
| `/zone remove <name>`  | Deletes an existing zone.                                | `zone.admin`   |
| `/zone info <name>`    | Provides information about a zone.                       | `zone.admin`   |

---

## Permissions

- `zone.selector` : Allows the player to use the magic axe for zone selection.
- `zone.admin` : Allows access to all `/zone` commands.

---

## Custom Events

The plugin provides two custom Bukkit events you can listen to in your own plugin:

- **ZoneEnterEvent**: Called when a player enters a zone.
- **ZoneLeaveEvent**: Called when a player leaves a zone.

```java
public class ZoneEnterEvent extends Event {
    public Player getPlayer();
    public Zone getZone();
}

public class ZoneLeaveEvent extends Event {
    public Player getPlayer();
    public Zone getZone();
}
