package me.shy.arenagameshy.Game;

import me.shy.arenagameshy.Item.ItemCreate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChoseLevel {
    public static String difficulty;

    public static String getDifficulty(){
        return difficulty;
    }

    public static void openDifficultySelection(Player player) {
        Inventory difficultyInventory = Bukkit.createInventory(null, 9, "난이도 설정");

        ItemStack easyItem = createDifficultyItem(Material.GREEN_WOOL, "쉬움");
        ItemStack normalItem = createDifficultyItem(Material.YELLOW_WOOL, "보통");
        ItemStack hardItem = createDifficultyItem(Material.RED_WOOL, "어려움");

        difficultyInventory.setItem(2, easyItem);
        difficultyInventory.setItem(4, normalItem);
        difficultyInventory.setItem(6, hardItem);

        player.openInventory(difficultyInventory);
    }

    private static ItemStack createDifficultyItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static void handleInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("난이도 설정")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                String itemName = clickedItem.getItemMeta().getDisplayName();

                switch (itemName) {
                    case "쉬움":
                        setDifficulty(player, "Easy");
                        difficulty = "Easy";
                        break;
                    case "보통":
                        setDifficulty(player, "Normal");
                        difficulty = "Normal";
                        break;
                    case "어려움":
                        setDifficulty(player, "Hard");
                        difficulty = "Hard";
                        break;
                }
                player.closeInventory();
                ItemCreate.startingItems(player);
            }
        }
    }

    private static void setDifficulty(Player player, String difficulty) {
        player.sendMessage("난이도가 " + difficulty + "(으)로 설정되었습니다.");
    }
}
