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
 * This class represents an edge between two map cells.
 * The edge may have a cost (or "weight") that can be used
 * to make some parts of the map slower to navigate (rough
 * terrain, water...)
 * 
 * @author Jonkke
 */
public class MapCellEdge {
    public MapCell from;
    public MapCell to;
    public int cost;
    
    public MapCellEdge(MapCell from, MapCell to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
