package com.example.a2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game2048 {
    private Tile[][] grid;
    private int size;
    private Random random;
    private Integer score = 0;

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
        addRandomTile(2);
        addRandomTile(2);
    }

    public void moveLeft() {
        boolean moved = false;
        for (int col = 0; col < size; col++) {
            for (int row = size - 1; row >= 0; row--) {
                Tile tile = grid[row][col];
                if (tile.getValue() != 0) {
                    int newCol = col - 1;
                    if (newCol >= 0) {
                        if (grid[row][newCol].getValue() == 0 || grid[row][newCol].getValue() == tile.getValue()) {
                            // Déplacer la tuile autant que possible vers la gauche
                            moveTile(tile, row, col, 0, -1);
                            moved = true;
                        }
                    }
                    // Déplacer la tuile autant que possible vers la gauche
                    moveTile(tile, row, col, 0, -1);
                }
            }
        }
        if (moved) {
            addRandomTile();
        }
    }

    public void moveRight() {
        boolean moved = false;
        for (int col = size - 1; col >= 0; col--) {
            for (int row = size - 1; row >= 0; row--) {
                Tile tile = grid[row][col];
                if (tile.getValue() != 0) {
                    int newCol = col + 1;
                    if (newCol < size) {
                        if (grid[row][newCol].getValue() == 0 || grid[row][newCol].getValue() == tile.getValue()) {
                            // Déplacer la tuile autant que possible vers la droite
                            moveTile(tile, row, col, 0, 1);
                            moved = true;
                        }
                    }
                }
            }
        }
        if (moved) {
            addRandomTile();
        }
    }

    public void moveUp() {
        boolean moved = false;
        for (int row = 0; row < size; row++) {
            for (int col = size - 1; col >= 0; col--) {
                Tile tile = grid[row][col];
                if (tile.getValue() != 0) {
                    int newRow = row - 1;
                    if (newRow >= 0) {
                        if (grid[newRow][col].getValue() == 0 || grid[newRow][col].getValue() == tile.getValue()) {
                            // Déplacer la tuile autant que possible vers le haut
                            moveTile(tile, row, col, -1, 0);
                            moved = true;
                        }
                    }
                    // Déplacer la tuile autant que possible vers le haut
                    moveTile(tile, row, col, -1, 0);
                }
            }
        }
        if (moved) {
            addRandomTile();
        }
    }

    public void moveDown() {
        boolean moved = false;
        for (int row = size - 1; row >= 0; row--) {
            for (int col = size - 1; col >= 0; col--) {
                Tile tile = grid[row][col];
                if (tile.getValue() != 0) {
                    int newRow = row + 1;
                    if (newRow < size) {
                        if (grid[newRow][col].getValue() == 0 || grid[newRow][col].getValue() == tile.getValue()) {
                            // Déplacer la tuile autant que possible vers le bas
                            moveTile(tile, row, col, 1, 0);
                            moved = true;
                        }
                    }
                }
            }
        }
        if (moved) {
            addRandomTile();
        }
    }

    private void moveTile(Tile tile, int row, int col, int dRow, int dCol) {
        int newRow = row + dRow;
        int newCol = col + dCol;
        if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size) {
            return ;
        }
        if (grid[newRow][newCol].getValue() == 0) {
            // Déplacer la tuile
            grid[newRow][newCol].setValue(tile.getValue());
            grid[row][col].setValue(0);
            moveTile(grid[newRow][newCol], newRow, newCol, dRow, dCol);
        } else if (grid[newRow][newCol].getValue() == tile.getValue()) {
            // Augmenter le score
            score += tile.getValue() * 2;
            // Fusionner les tuiles de même valeur
            grid[newRow][newCol].setValue(tile.getValue() * 2);
            grid[row][col].setValue(0);
        }
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
            tile.setValue(random.nextInt(5) == 0 ? 4 : 2);
        }
    }

    private void addRandomTile(int value) {
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
            tile.setValue(value);
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

    public void setGrid(Tile[][] grid){
        this.grid = grid;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Random getRandom() {
        return random;
    }

    public int getScore(){
        return score;
    }
}

