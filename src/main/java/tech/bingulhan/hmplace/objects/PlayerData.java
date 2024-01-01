package tech.bingulhan.hmplace.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class PlayerData {

    private static List<PlayerData> playerDataList = new ArrayList<>();

    public static Optional<PlayerData> getPlayerDataByName(String playerName) {
        return playerDataList.stream().filter(pd -> pd.getPlayerName().equals(playerName)).findFirst();
    }

    public static void addPlayerData(PlayerData data) {
        playerDataList.add(data);
    }

    public static void removePlayerData(String playerName) {
        Optional<PlayerData> data = getPlayerDataByName(playerName);
        if (data.isPresent()) {
            playerDataList.remove(data.get());
        }
    }

    public static void registerPlayerData(String playerName){
        if (!playerDataList.stream().anyMatch(pd -> pd.getPlayerName().equals(playerName))) {
            playerDataList.add(new PlayerData(playerName));
        }
    }

    private String playerName;
    @Setter
    private boolean isBlockReplace = true;

    @Setter
    private PlayerReplaceTask task;

    public PlayerData(String playerName) {
        this.playerName = playerName;
        task = new PlayerReplaceTask(this);
    }


}
