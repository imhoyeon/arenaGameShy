package me.shy.arenagameshy.Player;

import me.shy.arenagameshy.ArenaGameShy;
import me.shy.arenagameshy.Game.GameManager;
import me.shy.arenagameshy.Monster.MonsterManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerDeathListener implements Listener {

    private final ArenaGameShy plugin;

    public PlayerDeathListener(ArenaGameShy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.sendMessage("사망하셨습니다.");
        if (ArenaGameShy.isPlayerInArena(player)) {
            MonsterManager.clearArenaMonsters();
            GameManager.endGame(player);
        }
    }
}
