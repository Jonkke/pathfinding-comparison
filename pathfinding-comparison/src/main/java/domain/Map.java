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

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * This class represents a testable map. It holds constructor methods for
 * generating an empty map or a random map, and a method for generating a map
 * from a file. Map can also be presented in ASCII form using the toString
 * method.
 *
 * @author Jonkke
 */
public class Map {

    private MapCell[][] cells; // y,x

    public Map(MapCell[][] cells) {
        this.cells = cells;
        generateEdges();
    }

    /**
     * Generate a map from file.
     *
     * This method allows the generation of maps from a file. It has been
     * designed to support game maps that can be found on movingai.com site.
     *
     * It expects the map to be in the following format: First four lines:
     *
     * type octile height x width y map
     *
     * The rest will be an ASCII grid representing a map. Upper-left corner is
     * at (0,0). The ASCII maps consist of the following characters:
     *
     * . - passable terrain G - passable terrain
     *
     * @ - out of bounds O - out of bounds T - trees (unpassable) S - swamp
     * (passable from regular terrain) W - water (traversable, but not passable
     * from terrain)
     *
     * @param path relative path to the map file
     */
    public Map(String path) {
        String rawMap = "";
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            rawMap = new String(encoded, "utf-8");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        Scanner sc = new Scanner(rawMap);
        sc.nextLine();
        int height = Integer.parseInt(sc.nextLine().split(" ")[1]);
        int width = Integer.parseInt(sc.nextLine().split(" ")[1]);
        sc.nextLine();

        this.cells = new MapCell[height][width];
        int y = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int x = 0;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                Material mat = c == '.' ? Material.EMPTY
                        : c == 'G' ? Material.EMPTY
                                : c == '@' ? Material.WALL
                                        : c == 'O' ? Material.WALL
                                                : c == 'S' ? Material.EMPTY
                                                        : c == 'W' ? Material.EMPTY : Material.WALL;
                this.cells[y][x] = new MapCell(x, y, mat);
                x++;
            }
            y++;
        }
        sc.close();
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
                this.cells[y][x] = new MapCell(x, y, Material.EMPTY);
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
                    this.cells[y][x] = new MapCell(x, y, Material.WALL);
                } else {
                    this.cells[y][x] = new MapCell(x, y, Material.EMPTY);
                }
            }
        }
        generateEdges();
    }

    // This method will resolve the connections between map cells
    private void generateEdges() {
        for (int y = 0; y < this.cells.length; y++) {
            for (int x = 0; x < this.cells[y].length; x++) {
                if (this.cells[y][x].material == Material.WALL) {
                    continue;
                }
                if (x > 0 && this.cells[y][x - 1].material == Material.EMPTY) {
                    this.cells[y][x].edges[0] = new MapCellEdge(this.cells[y][x], this.cells[y][x - 1], 1);
                }
                if (y > 0 && this.cells[y - 1][x].material == Material.EMPTY) {
                    this.cells[y][x].edges[1] = new MapCellEdge(this.cells[y][x], this.cells[y - 1][x], 1);
                }
                if (x < this.cells[y].length - 1 && this.cells[y][x + 1].material == Material.EMPTY) {
                    this.cells[y][x].edges[2] = new MapCellEdge(this.cells[y][x], this.cells[y][x + 1], 1);
                }
                if (y < this.cells.length - 1 && this.cells[y + 1][x].material == Material.EMPTY) {
                    this.cells[y][x].edges[3] = new MapCellEdge(this.cells[y][x], this.cells[y + 1][x], 1);
                }
            }
        }
    }

    public MapCell getCell(int x, int y) {
        return this.cells[y][x];
    }

    /**
     * @return ASCII representation of the map
     */
    @Override
    public String toString() {
        StringBuilder asciiMap = new StringBuilder();
        for (int y = 0; y < this.cells.length; y++) {
            for (int x = 0; x < this.cells[y].length; x++) {
                String m = this.cells[y][x].material == Material.EMPTY ? "."
                        : this.cells[y][x].material == Material.WALL ? "X"
                                : this.cells[y][x].material == Material.SEARCHED ? "s"
                                        : this.cells[y][x].material == Material.CANDIDATE ? "c"
                                                : this.cells[y][x].material == Material.ROUTE ? "*" : "?";
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
