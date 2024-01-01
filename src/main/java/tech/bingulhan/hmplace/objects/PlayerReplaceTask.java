package tech.bingulhan.hmplace.objects;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import tech.bingulhan.hmplace.HMPlace;

@Getter
public class PlayerReplaceTask extends BukkitRunnable {

    private int delay = HMPlace.getInstance().getCurrentWaitDelay();

    private PlayerData data;
    public PlayerReplaceTask(PlayerData data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.setBlockReplace(false);
        delay--;

        if (delay == 0) {
            data.setBlockReplace(true);
            delay = HMPlace.getInstance().getCurrentWaitDelay();
            Bukkit.getPlayer(data.getPlayerName()).sendMessage(ChatColor.translateAlternateColorCodes('&', HMPlace.getInstance().getSuccesMessage()));

            data.setTask(new PlayerReplaceTask(data));
            cancel();
        }
    }
}
