package me.shy.arenagameshy;

import me.shy.arenagameshy.Arena.ArenaCommand;
import me.shy.arenagameshy.Arena.ArenaManager;
import me.shy.arenagameshy.Item.ItemUseListener;
import me.shy.arenagameshy.Monster.MonsterManager;
import me.shy.arenagameshy.Player.InventoryClickListener;
import me.shy.arenagameshy.Player.InventoryCloseListener;
import me.shy.arenagameshy.Player.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class ArenaGameShy extends JavaPlugin {
    private ArenaManager arena;
    private static Set<Player> playersInArena;

    @Override

    public void onEnable() {
        arena = new ArenaManager();
        playersInArena = new HashSet<>();
        Bukkit.getLogger().warning("Arena 플러그인 활성화");
        Bukkit.getCommandMap().register("shooter", new ArenaCommand("arena"));
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemUseListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new MonsterManager(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().warning("Arena 플러그인 비활성화");
    }

    public static void addPlayerToArena(Player player) {
        playersInArena.add(player);
    }

    public static void removePlayerFromArena(Player player) {
        playersInArena.remove(player);
    }

    public static boolean isPlayerInArena(Player player) {
        return playersInArena.contains(player);
    }
}
