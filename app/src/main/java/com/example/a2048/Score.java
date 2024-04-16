package com.example.a2048;

import java.io.Serializable;

public class Score implements Serializable {

    private String pseudo;

    private int score;

    private int bestTile;

    public Score() {}

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBestTile() {
        return bestTile;
    }

    public void setBestTile(int bestTile) {
        this.bestTile = bestTile;
    }
}
