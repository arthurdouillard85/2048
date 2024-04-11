package com.example.a2048;

import java.io.Serializable;

public class EndGame implements Serializable {

    private String nomJoueur;

    private Integer score;

    private Integer bestTile;

    public EndGame(String nomJoueur, Integer score, Integer bestTile) {
        this.nomJoueur = nomJoueur;
        this.score = score;
        this.bestTile = bestTile;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getBestTile() {
        return bestTile;
    }
}
