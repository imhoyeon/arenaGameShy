package me.shy.arenagameshy.Game;


import me.shy.arenagameshy.Arena.ArenaManager;
import me.shy.arenagameshy.ArenaGameShy;
import me.shy.arenagameshy.Monster.MonsterManager;
import me.shy.arenagameshy.Round.RoundManager;
import org.bukkit.entity.Player;

import static me.shy.arenagameshy.Game.ChoseLevel.difficulty;
import static me.shy.arenagameshy.Round.RoundManager.currentRound;

public class GameManager {

    public static void startGame(Player player) {
        ArenaManager.createMap(player);
        ArenaGameShy.addPlayerToArena(player);
        ChoseLevel.openDifficultySelection(player);
    }

    public static void endGame(Player player){
        MonsterManager.clearArenaMonsters();
        ArenaManager.deleteWorldBorder(player);
        JsonUtil.recordPlayer(player);
        player.sendMessage("게임이 종료되었습니다.");
        player.sendMessage("난이도는 " + difficulty + "이고 마지막 라운드는 "+ currentRound +"라운드 입니다.");
        ArenaManager.resetArena();
        RoundManager.RoundClear();
        ArenaGameShy.removePlayerFromArena(player);
    }
}
