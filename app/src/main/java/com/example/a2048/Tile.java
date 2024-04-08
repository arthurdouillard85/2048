package com.example.a2048;

public class Tile {
        private int value;
        private int row;
        private int col;

        public Tile(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
}
