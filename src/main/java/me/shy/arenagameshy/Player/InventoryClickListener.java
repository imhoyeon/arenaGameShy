package me.shy.arenagameshy.Player;

import me.shy.arenagameshy.Game.ChoseLevel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ChoseLevel.handleInventoryClick(event);
    }
}
