package nl._xxprodudexx_.staffmode.listener;

import nl._xxprodudexx_.staffmode.StaffMode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class StaffModeListeners implements Listener {

    @EventHandler
    public void on(BlockBreakEvent e) {
        if (StaffMode.getAPI().hasStaffMode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(BlockPlaceEvent e) {
        if (StaffMode.getAPI().hasStaffMode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(InventoryClickEvent e) {
        if (StaffMode.getAPI().hasStaffMode((Player) e.getWhoClicked())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (StaffMode.getAPI().hasStaffMode((Player) e.getEntity())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (StaffMode.getAPI().hasStaffMode((Player) e.getDamager())) {
                e.setCancelled(true);
            }
        }
        if (e.getEntity() instanceof Player) {
            if (StaffMode.getAPI().hasStaffMode((Player) e.getEntity())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            if (StaffMode.getAPI().hasStaffMode((Player) e.getEntity())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(PlayerLevelChangeEvent e) {
        if (StaffMode.getAPI().hasStaffMode(e.getPlayer())) {
            e.getPlayer().setLevel(e.getOldLevel());
        }
    }

    @EventHandler
    public void on(PlayerPickupItemEvent e) {
        if (StaffMode.getInstance().hasStaffMode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }


    public void setStaffMode(Player p) {
        StaffMode.getInstance().staffmodePlayers.add(p);
        p.setAllowFlight(true);
        p.setFlying(true);
        vanish(p);
        StaffMode.getInstance().getArmorContents().put(p, p.getEquipment().getArmorContents());
        StaffMode.getInstance().getItemContents().put(p, p.getInventory().getContents());
        StaffMode.getInstance().getLocations().put(p, p.getLocation());
        p.getInventory().clear();
        p.getInventory().remove(p.getInventory().getHelmet());
        p.getInventory().remove(p.getInventory().getChestplate());
        p.getInventory().remove(p.getInventory().getLeggings());
        p.getInventory().remove(p.getInventory().getBoots());
        p.getEquipment().clear();
    }

    public void removeStaffMode(Player p) {
        StaffMode.getInstance().staffmodePlayers.remove(p);
        if (!(p.getGameMode() == GameMode.CREATIVE)) {
            p.setFlying(false);
            p.setAllowFlight(false);
        }
        unvanish(p);
        p.getInventory().setContents(StaffMode.getInstance().getItemContents().get(p));
        p.getInventory().setArmorContents(StaffMode.getInstance().getArmorContents().get(p));
        StaffMode.getInstance().getArmorContents().remove(p);
        if (!p.hasPermission("staffmode.admin")) {
            p.teleport(StaffMode.getInstance().getLocations().get(p));
        }
        StaffMode.getInstance().getLocations().remove(p);
    }

    private void vanish(Player p) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!StaffMode.getInstance().hasStaffMode(all)) {
                all.hidePlayer(p);
            }
        }
    }

    private void unvanish(Player p) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.showPlayer(p);
        }
    }
}
