package tech.bingulhan.hmplace.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.bingulhan.hmplace.HMPlace;

public class CMDSetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.isOp()) {
                HMPlace.getInstance().getConfig().set("settings.spawn", player.getLocation());
                HMPlace.getInstance().saveConfig();
                player.sendMessage(ChatColor.GREEN+"Spawn ayarlandı!");
            }

        }

        return true;
    }
}
