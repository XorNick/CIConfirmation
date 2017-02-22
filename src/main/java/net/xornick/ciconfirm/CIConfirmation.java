package net.xornick.ciconfirm;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class CIConfirmation extends JavaPlugin {

    private ArrayList<UUID> confirmed = new ArrayList<>();

    @Override
    public void onEnable() {
        confirmed.clear();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("clearinventory")) {
            if (confirmed.contains(player.getUniqueId())) {
                player.getInventory().clear();
                player.sendMessage(ChatColor.GREEN + "Your inventory has successfully been cleared.");
                return true;
            } else {
                confirmed.add(player.getUniqueId());
                player.sendMessage(ChatColor.GREEN + "You just performed the command /clearinventory. If you are sure about this, please repeat the command.");
                return true;
            }
        }
        return false;
    }
}
