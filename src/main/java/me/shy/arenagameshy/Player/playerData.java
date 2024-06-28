package me.shy.arenagameshy.Player;

public class playerData {
    private String id;
    private int round;
    private String difficulty;

    public playerData(String id, int round, String difficulty) {
        this.id = id;
        this.round = round;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public int getRound() {
        return round;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
