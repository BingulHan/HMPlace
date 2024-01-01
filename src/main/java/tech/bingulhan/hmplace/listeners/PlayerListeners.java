package tech.bingulhan.hmplace.listeners;

import com.cryptomorin.xseries.XMaterial;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tech.bingulhan.hmplace.HMPlace;
import tech.bingulhan.hmplace.bll.GameManager;
import tech.bingulhan.hmplace.objects.PlayerData;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event) {
        PlayerData.registerPlayerData(event.getPlayer().getName());

        new GameManager().resetInventory(event.getPlayer());

        HMPlace.getInstance().getServer().getScheduler().runTaskLater(HMPlace.getInstance(), () -> {

            if (HMPlace.getInstance().getConfig().isSet("settings.spawn")) {
                event.getPlayer().teleport(((Location) HMPlace.getInstance().getConfig().get("settings.spawn")));
            }

        }, 2);


        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event) {
        PlayerData.removePlayerData(event.getPlayer().getName());
        event.setQuitMessage(null);
    }


    @EventHandler
    public void onSpawnMob(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamagePlayer(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }



    @EventHandler
    public void onPlayerInteractBlockEvent(PlayerInteractEvent event) {

        try {
            if (event.getItem()!=null) {
                if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

                    event.setCancelled(true);

                    PlayerData data = PlayerData.getPlayerDataByName(event.getPlayer().getName()).get();


                    Block block = event.getClickedBlock();
                    XMaterial material = XMaterial.matchXMaterial(event.getItem());

                    event.getPlayer().setItemInHand(material.parseItem());

                    if (material.name().contains("WOOL")) {

                        if (data.isBlockReplace()) {
                            data.getTask().runTaskTimer(HMPlace.getInstance(), 0L, 20L);
                            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', HMPlace.getInstance().getBlockChangeMessage()));
                            block.setType(material.parseMaterial());
                            block.setData(material.getData());
                        }else{
                            String message = HMPlace.getInstance().getReloadMessage();
                            message = StringUtils.replace(message, "%s", ""+data.getTask().getDelay());

                            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                        }


                    }
                }

                if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    event.setCancelled(true);

                    PlayerData data = PlayerData.getPlayerDataByName(event.getPlayer().getName()).get();


                    Block block = event.getClickedBlock();
                    XMaterial material = XMaterial.matchXMaterial(event.getItem());

                    event.getPlayer().setItemInHand(material.parseItem());

                    if (material.name().contains("WOOL")) {

                        if (data.isBlockReplace()) {
                            data.getTask().runTaskTimer(HMPlace.getInstance(), 0L, 20L);event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', HMPlace.getInstance().getBlockChangeMessage()));
                            block.setType(material.parseMaterial());
                            block.setData(material.getData());
                        }else{
                            String message = HMPlace.getInstance().getReloadMessage();
                            message = StringUtils.replace(message, "%s", ""+data.getTask().getDelay());

                            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                        }


                    }
                }
            }
        }catch (Exception exception)  {

        }
    }


}
