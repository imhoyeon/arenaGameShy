package me.shy.arenagameshy.Item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCreate {

    public static void startingItems(Player player){
        Inventory inv = Bukkit.createInventory(null, 9, "Starting Items");

        ItemStack itemIronSword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta itemIronSwordMeta = itemIronSword.getItemMeta();
        itemIronSwordMeta.setDisplayName("아레나 지급용 검");
        List<String> itemIronSwordMetaLore = new ArrayList<>();
        itemIronSwordMetaLore.add("아이템을 들고 F키를 누르면");
        itemIronSwordMetaLore.add("화염구를 발사 할 수 있습니다.");
        itemIronSwordMetaLore.add("쿨타임 30초");
        itemIronSwordMeta.setLore(itemIronSwordMetaLore);
        itemIronSword.setItemMeta(itemIronSwordMeta);

        ItemStack itemIronHelmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta itemIronHelmetMeta = itemIronHelmet.getItemMeta();
        itemIronHelmetMeta.setDisplayName("아레나 지급용 헬멧");
        itemIronHelmet.setItemMeta(itemIronHelmetMeta);

        ItemStack itemHealing = new ItemStack(Material.RED_DYE, 5);
        ItemMeta itemHealingMeta = itemHealing.getItemMeta();
        itemHealingMeta.setDisplayName("재생의 문양");
        List<String> itemHealingMetaLore = new ArrayList<>();
        itemHealingMetaLore.add("아이템을 들고 F키를 누르면");
        itemHealingMetaLore.add("재생 효과를 얻을 수 있습니다.");
        itemHealingMeta.setLore(itemHealingMetaLore);
        itemHealing.setItemMeta(itemHealingMeta);



        inv.setItem(2, itemIronSword);
        inv.setItem(4, itemHealing);
        inv.setItem(6, itemIronHelmet);

        player.openInventory(inv);
    }

    public static void rewardItems(Player player, int currentRound){
        Inventory inv2 = Bukkit.createInventory(null, 9, "보상");
        switch (currentRound%3) {
            case 0:
                ItemStack itemResistance = new ItemStack(Material.BLACK_DYE, 3);
                ItemMeta itemResistanceMeta = itemResistance.getItemMeta();
                itemResistanceMeta.setDisplayName("방어의 문양");
                List<String> itemResistanceMetaLore = new ArrayList<>();
                itemResistanceMetaLore.add("아이템을 들고 F키를 누르면");
                itemResistanceMetaLore.add("저항 효과를 얻을 수 있습니다.");
                itemResistanceMeta.setLore(itemResistanceMetaLore);
                itemResistance.setItemMeta(itemResistanceMeta);

                ItemStack itemIronChestPlate = new ItemStack(Material.IRON_CHESTPLATE, 1);
                ItemMeta itemIronChestPlateMeta = itemIronChestPlate.getItemMeta();
                itemIronChestPlateMeta.setDisplayName("아레나 지급용 갑옷");
                itemIronChestPlate.setItemMeta(itemIronChestPlateMeta);

                inv2.setItem(3, itemResistance);
                inv2.setItem(5, itemIronChestPlate);
                break;
            case 1:
                if (currentRound == 4){
                    ItemStack itemNetheriteSword = new ItemStack(Material.NETHERITE_SWORD, 1);
                    ItemMeta itemNetheriteSwordMeta = itemNetheriteSword.getItemMeta();
                    itemNetheriteSwordMeta.setDisplayName("짱짱검");
                    List<String> itemNetheriteSwordMetaLore = new ArrayList<>();
                    itemNetheriteSwordMetaLore.add("아이템을 들고 F키를 누르면");
                    itemNetheriteSwordMetaLore.add("화염구를 발사 할 수 있습니다.");
                    itemNetheriteSwordMetaLore.add("쿨타임 5초");
                    itemNetheriteSwordMeta.setLore(itemNetheriteSwordMetaLore);
                    itemNetheriteSword.setItemMeta(itemNetheriteSwordMeta);
                    inv2.setItem(4, itemNetheriteSword);
                } else {
                ItemStack itemStrength = new ItemStack(Material.GRAY_DYE, 3);
                ItemMeta itemStrengthMeta = itemStrength.getItemMeta();
                itemStrengthMeta.setDisplayName("힘의 문양");
                List<String> itemStrengthMetaLore = new ArrayList<>();
                itemStrengthMetaLore.add("아이템을 들고 F키를 누르면");
                itemStrengthMetaLore.add("힘 효과를 얻을 수 있습니다.");
                itemStrengthMeta.setLore(itemStrengthMetaLore);
                itemStrength.setItemMeta(itemStrengthMeta);

                ItemStack itemIronLeggings = new ItemStack(Material.IRON_LEGGINGS, 1);
                ItemMeta itemIronLeggingsMeta = itemIronLeggings.getItemMeta();
                itemIronLeggingsMeta.setDisplayName("아레나 지급용 바지");
                itemIronLeggings.setItemMeta(itemIronLeggingsMeta);

                inv2.setItem(3, itemStrength);
                inv2.setItem(5, itemIronLeggings);
                }
                break;
            case 2:
                ItemStack itemHealthBoost = new ItemStack(Material.PINK_DYE, 3);
                ItemMeta itemHealthBoostMeta = itemHealthBoost.getItemMeta();
                itemHealthBoostMeta.setDisplayName("체력 강화의 문양");
                List<String> itemHealthBoostMetaLore = new ArrayList<>();
                itemHealthBoostMetaLore.add("아이템을 들고 F키를 누르면");
                itemHealthBoostMetaLore.add("체력 강화 효과를 얻을 수 있습니다.");
                itemHealthBoostMeta.setLore(itemHealthBoostMetaLore);
                itemHealthBoost.setItemMeta(itemHealthBoostMeta);

                ItemStack itemIronBoots = new ItemStack(Material.IRON_BOOTS, 1);
                ItemMeta itemIronBootsMeta = itemIronBoots.getItemMeta();
                itemIronBootsMeta.setDisplayName("아레나 지급용 신발");
                itemIronBoots.setItemMeta(itemIronBootsMeta);

                inv2.setItem(3, itemHealthBoost);
                inv2.setItem(5, itemIronBoots);
                break;
            }
            player.openInventory(inv2);
        }

    }
