package nl._xxprodudexx_.staffmode.cmd;

import nl._xxprodudexx_.staffmode.StaffMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffmodeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("The console cannot use this command!");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("staffmode")) {
            if (p.hasPermission("staffmode.use")){
                if (StaffMode.getAPI().hasStaffMode(p) == true) {
                    StaffMode.getAPI().removeStaffMode(p);
                    p.sendMessage("§7Your StaffMode has been §cdisabled!");
                    p.sendMessage("§7You are now §cvisible!");
                    return true;
                } else {
                    StaffMode.getAPI().setStaffMode(p);
                    p.sendMessage("§7Your StaffMode has been §aenabled!");
                    p.sendMessage("§7You are now §ainvisible!");
                    return true;
                }
            } else {
                p.sendMessage("§cYou don't have permission to execute this command!");
                return true;
            }
        }

        return true;
    }
}
