package tech.bingulhan.hmplace;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import tech.bingulhan.hmplace.bll.GameManager;
import tech.bingulhan.hmplace.cmd.CMDSetSpawn;
import tech.bingulhan.hmplace.listeners.PlayerListeners;
import tech.bingulhan.hmplace.objects.PlayerData;

public final class HMPlace extends JavaPlugin {

    @Getter
    private static HMPlace instance;

    @Getter
    private int currentWaitDelay;


    @Getter
    private String reloadMessage;

    @Getter
    private String blockChangeMessage;

    @Getter
    private String succesMessage;


    @Override
    public void onEnable() {
        instance = this;


        Bukkit.getLogger().info("Bu eklenti BingulHan tarafından yılbaşına özel revizyondan geçirilip paylaşılmıştır iyi kullanmalar dileğiyle. Github @BingulHan");
        //Listeners
        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);

        //Commands
        getCommand("setspawn").setExecutor(new CMDSetSpawn());

        getConfig().options().copyDefaults(true);
        saveConfig();

        GameManager gameManager = new GameManager();
        for (Player player : getServer().getOnlinePlayers()) {
            PlayerData.registerPlayerData(player.getName());
            gameManager.resetInventory(player);
        }

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new HMPlaceHook().register();
        }

        currentWaitDelay = getConfig().getInt("settings.delay");
        reloadMessage = getConfig().getString("settings.messages.reload-message");
        blockChangeMessage = getConfig().getString("settings.messages.block-change");
        succesMessage = getConfig().getString("settings.messages.success");



    }

    @Override
    public void onDisable() {

    }
}
