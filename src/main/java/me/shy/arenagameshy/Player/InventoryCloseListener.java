package me.shy.arenagameshy.Player;

import me.shy.arenagameshy.ArenaGameShy;
import me.shy.arenagameshy.Game.GameManager;
import me.shy.arenagameshy.Round.RoundManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class InventoryCloseListener implements Listener {

    public InventoryCloseListener(ArenaGameShy arenaGameShy) {
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equals("Starting Items")||event.getView().getTitle().equals("보상")) {
            player.sendMessage("10초뒤에 라운드가 시작됩니다.");
            new BukkitRunnable() {
                @Override
                public void run() {
                    RoundManager.startRound();
                }
            }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("arenagameshy")), 200);
        }
        }
    }
