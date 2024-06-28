package me.shy.arenagameshy.Round;

import me.shy.arenagameshy.Item.ItemCreate;
import me.shy.arenagameshy.Monster.MonsterManager;
import org.bukkit.entity.Player;

public class RoundManager {
    public static int intDifficulty;
    public static int currentRound = 1;
    public static void startRound() {
        MonsterManager.spawnMonster(currentRound, intDifficulty);

    }

    public static void endRound(Player player){
        player.sendMessage(currentRound + "라운드 클리어, 보상을 지급해드리겠습니다.");
        ItemCreate.rewardItems(player,currentRound);
        currentRound++;
    }

    public static int getCurrentRound(){
        return currentRound;
    }

    public static void RoundClear(){
        currentRound = 1;
    }


}