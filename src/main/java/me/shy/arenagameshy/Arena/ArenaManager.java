package me.shy.arenagameshy.Arena;

import me.shy.arenagameshy.Game.GameManager;
import me.shy.arenagameshy.Monster.MonsterManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ArenaManager {
    private static final Map<Location, Material> originalBlocks = new HashMap<>();
    public static Location center;

    public static void createMap(Player player) {
        World world = player.getWorld();
        center = player.getLocation();
        center.add(0,-1,0);
        int size = 30;
        setWorldBorder(world, center, size);
        generateArena(world, center, size);
    }

    public static void deleteWorldBorder(Player player) {
        World world = player.getWorld();
        Location center = new Location(world, 0,0,0);
        int size = 59999968;
        setWorldBorder(world, center, size);
    }

    private static void setWorldBorder(World world, Location center, int size) {
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(center);
        worldBorder.setSize(size);
    }

    private static void generateArena(World world,Location center, int size) {
        int halfSize = size / 2;
        Material material = Material.POLISHED_DIORITE;

        int minX = center.getBlockX() - halfSize;
        int maxX = center.getBlockX() + halfSize;
        int minZ = center.getBlockZ() - halfSize;
        int maxZ = center.getBlockZ() + halfSize;
        int y = center.getBlockY();

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                Location loc = new Location(world, x, y, z);
                Block block = world.getBlockAt(loc);
                originalBlocks.put(loc, block.getType());
                block.setType(material);
            }
        }
    }

    public static void resetArena() {
        for (Map.Entry<Location, Material> entry : originalBlocks.entrySet()) {
            Location loc = entry.getKey();
            Material originalMaterial = entry.getValue();
            loc.getBlock().setType(originalMaterial);
        }
        originalBlocks.clear();
    }
}
