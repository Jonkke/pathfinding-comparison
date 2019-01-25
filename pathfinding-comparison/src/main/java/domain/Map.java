/*
 * Copyright (C) 2019 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package domain;

import java.util.Random;

/**
 *
 * This class represents a testable map. It holds constructor methods for generating an
 * empty map or a random map, and a method for generating a map from file will
 * be added as well in the future. Map can also be presented in ASCII form using
 * the toString method.
 *
 * @author Jonkke
 */
public class Map {

    MapCell[][] cells; // y,x

    public Map(MapCell[][] cells) {
        this.cells = cells;
        generateEdges();
    }

    /**
     *
     * Generate an empty map with this method.
     *
     * @param width
     * @param height
     */
    public Map(int width, int height) {
        this.cells = new MapCell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.cells[y][x] = new MapCell(0);
            }
        }
        generateEdges();
    }

    /**
     * Generate a random map with custom seed and a probability factor that
     * regulates the wall occurrence.
     *
     * @param width
     * @param height
     * @param wallOdds Controls the occurrence of random wall pieces
     * @param seed
     */
    public Map(int width, int height, double wallOdds, int seed) {
        Random r = new Random(seed);
        this.cells = new MapCell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (r.nextDouble() < wallOdds) {
                    this.cells[y][x] = new MapCell(1);
                } else {
                    this.cells[y][x] = new MapCell(0);
                }
            }
        }
        generateEdges();
    }

    // This method will resolve the connections between map cells
    private void generateEdges() {
        for (int y = 0; y < this.cells.length; y++) {
            for (int x = 0; x < this.cells[y].length; x++) {
                if (this.cells[y][x].material == 1) {
                    continue;
                }
                if (x > 0 && this.cells[y][x - 1].material == 0) {
                    this.cells[y][x].edges[0] = new MapCellEdge(this.cells[y][x], this.cells[y][x - 1], 1);
                }
                if (y > 0 && this.cells[y - 1][x].material == 0) {
                    this.cells[y][x].edges[1] = new MapCellEdge(this.cells[y][x], this.cells[y - 1][x], 1);
                }
                if (x < this.cells[y].length - 1 && this.cells[y][x + 1].material == 0) {
                    this.cells[y][x].edges[2] = new MapCellEdge(this.cells[y][x], this.cells[y][x + 1], 1);
                }
                if (y < this.cells.length - 1 && this.cells[y + 1][x].material == 0) {
                    this.cells[y][x].edges[3] = new MapCellEdge(this.cells[y][x], this.cells[y + 1][x], 1);
                }
            }
        }
    }

    /**
     * @return ASCII representation of the map
     */
    @Override
    public String toString() {
        StringBuilder asciiMap = new StringBuilder();
        for (int y = 0; y < this.cells.length; y++) {
            for (int x = 0; x < this.cells[y].length; x++) {
                String m = this.cells[y][x].material == 0 ? "." : "X";
                asciiMap.append(m);
                if (x < this.cells[y].length - 1) {
                    asciiMap.append(" ");
                }
            }
            asciiMap.append("\n");
        }
        return asciiMap.toString();
    }

}
