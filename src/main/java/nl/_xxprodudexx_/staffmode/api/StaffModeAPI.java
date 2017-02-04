package nl._xxprodudexx_.staffmode.api;

import org.bukkit.entity.Player;

public interface StaffModeAPI {

    boolean hasStaffMode(Player p);

    void setStaffMode(Player p);

    void removeStaffMode(Player p);

}
