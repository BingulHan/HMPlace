package tech.bingulhan.hmplace.bll;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;

public class GameManager {

    public void resetInventory(Player player) {

        player.getInventory().clear();

        player.getEquipment().setHelmet(null);
        player.getEquipment().setChestplate(null);
        player.getEquipment().setLeggings(null);
        player.getEquipment().setBoots(null);

        player.setFoodLevel(20);
        player.setHealth(20);


        for (XMaterial material : XMaterial.values()) {
            if (material.name().contains("WOOL")) {
                player.getInventory().addItem(material.parseItem());
            }
        }
    }
}
