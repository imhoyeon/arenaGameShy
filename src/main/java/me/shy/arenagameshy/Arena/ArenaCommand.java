package me.shy.arenagameshy.Arena;

import me.shy.arenagameshy.Game.GameManager;
import me.shy.arenagameshy.Game.JsonUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArenaCommand extends BukkitCommand {

    public ArenaCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("올바른 명령어: /arena <id> | start | stop");
            return false;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "start":
                GameManager.startGame(player);
                player.sendMessage("아레나가 시작되었습니다!");
                return true;
            case "stop":
                GameManager.endGame(player);
                player.sendMessage("아레나가 종료되었습니다!");
                return true;
            default:
                JsonUtil.playerRecordOut(player, subCommand);
                return true;
        }
    }

}
