package me.shy.arenagameshy.Monster;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import me.shy.arenagameshy.Arena.ArenaManager;
import me.shy.arenagameshy.Round.RoundManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MonsterManager implements Listener {
    private static final MythicMob spade = MythicBukkit.inst().getAPIHelper().getMythicMob("SpadeSoldier");
    private static final MythicMob heart = MythicBukkit.inst().getAPIHelper().getMythicMob("HeartSoldier");
    private static final List<ActiveMob> activeMobs = new ArrayList<>();
    private static int mobCount;
    public static void spawnMonster(int currentRound, int intDifficulty) {
        mobCount = 0;
        Location location = ArenaManager.center.clone();
        location.add(5, 1, 0);

        for (int i = 0; i < currentRound; i++) {
            ActiveMob heartMob = heart.spawn(BukkitAdapter.adapt(location), currentRound + intDifficulty);
            mobCount++;
            activeMobs.add(heartMob);
        }

        for (int i = 0; i < currentRound; i++) {
            ActiveMob spadeMob = spade.spawn(BukkitAdapter.adapt(location), currentRound + intDifficulty);
            mobCount++;
            activeMobs.add(spadeMob);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        @NotNull Player player = Objects.requireNonNull(event.getEntity().getKiller());
        mobCount--;
        ActiveMob activeMob = findActiveMob(event.getEntity());
        if (activeMob != null) {
            activeMobs.remove(activeMob);
        }
        if ( mobCount == 0){
            RoundManager.endRound(player);
        }
    }

    private ActiveMob findActiveMob(Entity entity) {
        for (ActiveMob activeMob : activeMobs) {
            if (activeMob.getEntity().equals(entity)) {
                return activeMob;
            }
        }
        return null;
    }

    public static void clearArenaMonsters() {
        for (ActiveMob activeMob : activeMobs) {
            activeMob.getEntity().remove();
        }
        activeMobs.clear();
    }
}
