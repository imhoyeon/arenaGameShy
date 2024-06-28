package me.shy.arenagameshy.Item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ItemUseListener implements Listener {
    private Map<UUID, Long> cooldowns = new HashMap<>();
    private long defaultCooldownTime = 15 * 1000;

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        ItemStack handItem = player.getInventory().getItemInMainHand();

        if (handItem.hasItemMeta()) {
            ItemMeta itemMeta = handItem.getItemMeta();
            if (itemMeta != null && itemMeta.hasDisplayName()) {
                String itemName = itemMeta.getDisplayName();

                switch (itemName) {
                    case "아레나 지급용 검":
                        Fireball(player, defaultCooldownTime);
                        break;
                    case "짱짱검":
                        Fireball(player, 5 * 1000);
                        break;
                    case "재생의 문양":
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                        player.sendMessage("재생의 문양을 사용해 재생효과를 얻었습니다!");
                        handItem.setAmount(handItem.getAmount() - 1);
                        break;
                    case "힘의 문양":
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
                        player.sendMessage("힘의 문양을 사용해 힘효과를 얻었습니다!");
                        handItem.setAmount(handItem.getAmount() - 1);
                        break;
                    case "체력 강화의 문양":
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 200, 1));
                        player.sendMessage("체력 강화의 문양을 사용해 체력 강화효과를 얻었습니다!");
                        handItem.setAmount(handItem.getAmount() - 1);
                        break;
                }
            }
        }
    }

    private void Fireball(Player player, long cooldownTime) {
        UUID playerId = player.getUniqueId();
        if (cooldowns.containsKey(playerId)) {
            long currentTime = System.currentTimeMillis();
            long lastUsedTime = cooldowns.get(playerId);
            long timeLeft = cooldownTime - (currentTime - lastUsedTime);

            if (timeLeft > 0) {
                player.sendMessage("화염구는 " + (timeLeft / 1000) + "초 후에 다시 사용할 수 있습니다.");
                return;
            }
        }

        player.launchProjectile(Fireball.class);
        player.sendMessage("화염구를 발사했습니다!");
        cooldowns.put(playerId, System.currentTimeMillis() + cooldownTime);

        new BukkitRunnable() {
            @Override
            public void run() {
                cooldowns.remove(playerId);
                player.sendMessage("화염구가 다시 사용 가능합니다!");
            }
        }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("arenagameshy")), cooldownTime / 50); // tick 기준으로 지연 시간 설정
    }
}
