package com.example.a2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game2048 {
    private Tile[][] grid;
    private int size;
    private Random random;

    public Game2048(int size) {
        this.size = size;
        grid = new Tile[size][size];
        random = new Random();
        // Initialiser la grille avec des tuiles vides
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Tile(0, i, j);
            }
        }
        // Placez deux tuiles au hasard au début du jeu
        addRandomTile();
        addRandomTile();
    }

    public void moveLeft() {
        boolean moved = false;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Tile tile = grid[row][col];
                if (tile.getValue() != 0) {
                    // Déplacer la tuile autant que possible vers la gauche
                    moved |= moveTile(tile, row, col, -1, 0);
                }
            }
        }
        if (moved) {
            addRandomTile();
        }
    }

    private boolean moveTile(Tile tile, int row, int col, int dRow, int dCol) {
        int newRow = row + dRow;
        int newCol = col + dCol;
        if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size) {
            return false;
        }
        if (grid[newRow][newCol].getValue() == 0) {
            // Déplacer la tuile
            grid[newRow][newCol].setValue(tile.getValue());
            grid[row][col].setValue(0);
            return moveTile(grid[newRow][newCol], newRow, newCol, dRow, dCol);
        } else if (grid[newRow][newCol].getValue() == tile.getValue()) {
            // Fusionner les tuiles de même valeur
            grid[newRow][newCol].setValue(tile.getValue() * 2);
            grid[row][col].setValue(0);
            return true;
        }
        return false;
    }

    private void addRandomTile() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col].getValue() == 0) {
                    emptyTiles.add(grid[row][col]);
                }
            }
        }
        if (!emptyTiles.isEmpty()) {
            Tile tile = emptyTiles.get(random.nextInt(emptyTiles.size()));
            tile.setValue(random.nextInt(2) == 0 ? 2 : 4);
        }
    }

    public boolean isGameOver() {
        // Vérifier s'il existe des mouvements possibles ou s'il reste des tuiles vides
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col].getValue() == 0) {
                    return false;
                }
                if ((col < size - 1 && grid[row][col].getValue() == grid[row][col + 1].getValue()) ||
                        (row < size - 1 && grid[row][col].getValue() == grid[row + 1][col].getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    // Ajouter des méthodes pour d'autres mouvements (droite, haut, bas) si nécessaire

    // Getters et Setters
}
