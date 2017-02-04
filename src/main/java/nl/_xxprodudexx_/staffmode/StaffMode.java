package nl._xxprodudexx_.staffmode;

import nl._xxprodudexx_.staffmode.api.StaffModeAPI;
import nl._xxprodudexx_.staffmode.cmd.StaffmodeCommand;
import nl._xxprodudexx_.staffmode.listener.StaffModeListeners;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <UHC - Made by _xXProDudeXx_>
 * Copyright (C) <2016>  <_xXProDudeXx_>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class StaffMode extends JavaPlugin implements StaffModeAPI {

    private static StaffMode main;
    private static StaffModeAPI api;
    private StaffModeListeners event = new StaffModeListeners();
    public List<Player> staffmodePlayers = new ArrayList<Player>();
    private Map<Player, ItemStack[]> armorContents = new HashMap<Player, ItemStack[]>();
    private Map<Player, ItemStack[]> itemContents = new HashMap<Player, ItemStack[]>();
    private Map<Player, Location> locations = new HashMap<Player, Location>();

    @Override
    public void onEnable() {
        main = this;
        api = this;

        getCommand("staffmode").setExecutor(new StaffmodeCommand());
        Bukkit.getPluginManager().registerEvents(new StaffModeListeners(), this);

    }

    @Override
    public void onDisable() {
        main = null;
        api = null;
    }

    public static StaffMode getInstance() {
        return main;
    }

    public static StaffModeAPI getAPI() {
        return api;
    }

    public final boolean hasStaffMode(Player p) {
        return staffmodePlayers.contains(p);
    }

    public final void setStaffMode(Player p) {
        event.setStaffMode(p);
    }

    public final void removeStaffMode(Player p) {
        event.removeStaffMode(p);
    }

    public Map<Player, ItemStack[]> getArmorContents() {
        return armorContents;
    }

    public Map<Player, ItemStack[]> getItemContents() {
        return itemContents;
    }

    public Map<Player, Location> getLocations() {
        return locations;
    }
}

