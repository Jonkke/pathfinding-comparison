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

/**
 * A class representing a single cell in a map. 
 * 
 * Map cells are the building blocks of the map, and all movement happens between them.
 * A map cell holds a list of edges to all adjacent cells (left,up,right,down)
 * that are accessible (i.e. empty or "navigable" cells). The cell material
 * dictates if the cell is accessible, and also if the movement is constrained
 * in it (it could be a water cell, perhaps?).
 * 
 * @author Jonkke
 */

public class MapCell implements Comparable<MapCell> {
    public int x, y;
    public Material material;
    public MapCellEdge[] edges; // left, top, right, bottom
    public int costFromStart; // Helper variable for determining distance from starting point
    public double priority;
    public boolean isTested;
    
    public MapCell(int x, int y, Material material) {
        this.x = x;
        this.y = y;
        this.material = material;
        this.edges = new MapCellEdge[4];
        this.costFromStart = Integer.MAX_VALUE;
        this.isTested = false;
    }
    
    public boolean isTraversable() {
        return this.material != Material.WALL;
    }

    @Override
    public int compareTo(MapCell c) {
        return Double.compare(this.priority, c.priority);
    }
    
}
