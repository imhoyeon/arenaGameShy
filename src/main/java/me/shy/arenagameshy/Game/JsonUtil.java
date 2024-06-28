package me.shy.arenagameshy.Game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.shy.arenagameshy.Player.playerData;
import me.shy.arenagameshy.Round.RoundManager;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path path = Path.of("D:\\Projects\\java\\arenaGameShy\\src\\main\\resources\\record.json");

    public static void recordPlayer(Player player) {
        List<playerData> playerDataList = loadPlayerData();

        int round = RoundManager.getCurrentRound();
        String difficulty = ChoseLevel.getDifficulty();

        String id = player.getName();
        playerData newPlayerData = new playerData(id, round, difficulty);
        playerDataList.add(newPlayerData);

        savePlayerData(playerDataList);
    }

    private static List<playerData> loadPlayerData() {
        List<playerData> playerDataList = new ArrayList<>();

        try {
            if (Files.exists(path)) {
                try (Reader reader = new FileReader(path.toFile())) {
                    playerData[] dataArray = gson.fromJson(reader, playerData[].class);
                    if (dataArray != null) {
                        for (playerData data : dataArray) {
                            playerDataList.add(data);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playerDataList;
    }

    private static void savePlayerData(List<playerData> playerDataList) {
        try (Writer writer = new FileWriter(path.toFile())) {
            gson.toJson(playerDataList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void playerRecordOut(Player player, String id) {
        try {
            if (Files.exists(path)) {
                String json = Files.readString(path);
                playerData[] dataArray = gson.fromJson(json, playerData[].class);
                if (dataArray != null) {
                    int totalRoundsEasy = 0;
                    int totalRoundsNormal = 0;
                    int totalRoundsHard = 0;
                    int maxRoundEasy = 0;
                    int maxRoundNormal = 0;
                    int maxRoundHard = 0;
                    int countEasy = 0;
                    int countNormal = 0;
                    int countHard = 0;

                    for (playerData data : dataArray) {
                        if (data.getId().equalsIgnoreCase(id)) {
                            switch (data.getDifficulty()) {
                                case "Easy":
                                    maxRoundEasy = Math.max(maxRoundEasy, data.getRound());
                                    totalRoundsEasy += data.getRound();
                                    countEasy++;
                                    break;
                                case "Normal":
                                    maxRoundNormal = Math.max(maxRoundNormal, data.getRound());
                                    totalRoundsNormal += data.getRound();
                                    countNormal++;
                                    break;
                                case "Hard":
                                    maxRoundHard = Math.max(maxRoundHard, data.getRound());
                                    totalRoundsHard += data.getRound();
                                    countHard++;
                                    break;
                            }
                        }
                    }

                    if (countEasy > 0) {
                        player.sendMessage("쉬움 최고 라운드: " + maxRoundEasy + ", 평균 라운드: " + (double)(totalRoundsEasy / countEasy));
                    } else {
                        player.sendMessage("쉬움 라운드 기록 없음");
                    }
                    if (countNormal > 0) {
                        player.sendMessage("보통 최고 라운드: " + maxRoundNormal + ", 평균 라운드: " + (double)(totalRoundsNormal / countNormal));
                    } else {
                        player.sendMessage("보통 라운드 기록 없음");
                    }
                    if (countHard > 0) {
                        player.sendMessage("어려움 최고 라운드: " + maxRoundHard + ", 평균 라운드: " + (double)(totalRoundsHard / countHard));
                    } else {
                        player.sendMessage("어려움 라운드 기록 없음");
                    }
                    return;
                }
                player.sendMessage("플레이어 " + id + "의 데이터를 찾을 수 없습니다.");
            } else {
                player.sendMessage("기록 파일이 존재하지 않습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
