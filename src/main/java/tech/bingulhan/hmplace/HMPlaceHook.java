package tech.bingulhan.hmplace;



import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.bingulhan.hmplace.objects.PlayerData;

public class HMPlaceHook extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "BingulHan";
    }

    @Override
    public String getIdentifier() {
        return "rplace";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }


    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        PlayerData data = PlayerData.getPlayerDataByName(player.getName()).get();
        if (data.isBlockReplace()) {
            return ChatColor.GREEN + "Kullanılabilir";
        } else {
            return ChatColor.RED + "" + data.getTask().getDelay()+ " Saniye";
        }
    }
}
