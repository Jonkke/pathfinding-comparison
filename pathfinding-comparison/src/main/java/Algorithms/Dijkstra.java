package Algorithms;

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
import DataStructures.MapCellList;
import DataStructures.MapCellBinaryHeap;
import domain.Map;
import domain.MapCell;
import domain.MapCellEdge;
import domain.Material;

/**
 * Dijkstra's algorithm for shortest path. This class is a container for the
 * Dijkstra's algorithm, which can be used to find the shortest possible route
 * between two nodes in a graph, provided there are no edges width negative
 * weights anywhere in the graph. In this implementation, the algorithm will
 * find the shortest path in a grid-like map consisting of x*y cells.
 *
 * @author Jonkke
 */
public class Dijkstra {

    private long lastRunNanoTime; // Running time of the algorithm is saved in this variable
    private int totalCost;
    private int cellsTraversed;
    private int searchedCells;

    /**
     * Shortest route search using Dijkstra's algorithm. Input the map that the
     * search is to be run on, and the starting x and y coordinates (x1,y1),
     * then the target x and y coordinates (x2,y2).
     *
     * @param map
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param markSearched If true, mark searched map cells with special
     * materials
     * @return Ordered list of map cells that make up the shortest path. If
     * either start or target is a non-searchable cell (e.g. wall), or a route
     * does not exist, then an empty list will be returned.
     */
    public MapCellList findShortestPath(Map map, int x1, int y1, int x2, int y2, boolean markSearched) {
        long startTime = System.nanoTime();
        this.lastRunNanoTime = 0;
        this.totalCost = 0;
        this.cellsTraversed = 0;
        this.searchedCells = 0;
        if (!map.getCell(x1, y1).isTraversable() || !map.getCell(x2, y2).isTraversable()) {
            return new MapCellList(); // Start or end of route blocked by wall => empty list
        }
        MapCell start = map.getCell(x1, y1);
        start.costFromStart = 0;
        boolean routeFound = false;
        MapCellBinaryHeap mcbh = new MapCellBinaryHeap();
        mcbh.add(start);
        while (!mcbh.isEmpty()) {
            MapCell cell = mcbh.poll();
            if (cell == map.getCell(x2, y2)) {
                routeFound = true;
                break;
            }
            if (cell.isTested || cell.material == Material.WALL) {
                continue;
            }
            cell.isTested = true;
            this.searchedCells++;
            if (markSearched) {
                cell.material = Material.SEARCHED;
            }
            for (MapCellEdge mce : cell.edges) {
                if (mce == null) {
                    continue;
                }
                int currentDistance = mce.to.costFromStart;
                int updatedDistance = cell.costFromStart + mce.cost;
                if (updatedDistance < currentDistance) {
                    mce.to.costFromStart = updatedDistance;
                    mce.to.priority = updatedDistance;
                    mcbh.add(mce.to);
                    if (markSearched) {
                        mce.to.material = Material.CANDIDATE;
                    }
                }
            }
        }
        this.lastRunNanoTime = System.nanoTime() - startTime;

        // If a route was found, trace it back from the target and return traversed cells as list
        MapCellList route = new MapCellList();
        this.totalCost = 0;
        this.cellsTraversed = 0;
        if (!routeFound) {
            this.lastRunNanoTime = 0;
            return route; // Route not fonud => empty list
        }
        MapCell curr = map.getCell(x2, y2);
        MapCell last = null;
        while (curr != map.getCell(x1, y1)) {
            this.cellsTraversed++;
            int shortestDist = Integer.MAX_VALUE;
            MapCell shortest = null;
            for (MapCellEdge mce : curr.edges) {
                if (mce == null) {
                    continue;
                }
                if (mce.to != last && mce.to.costFromStart < shortestDist) {
                    shortestDist = mce.to.costFromStart;
                    shortest = mce.to;
                    this.totalCost += mce.cost;
                }
            }
            last = curr;
            route.add(curr);
            curr = shortest;
        }
        route.add(curr);

        return route;
    }

    /**
     * Return the length of time it took to complete the last route search. The
     * duration of the last run is measured from the point in time where the
     * findShortestPath method was called to the point where are shortest path
     * was found. Building of the list of cells on the route is NOT included in
     * this measurement.
     */
    public long getLastRunTimeNanos() {
        return this.lastRunNanoTime;
    }

    /**
     * @return total cost of traversed cells
     */
    public int getTotalCost() {
        return this.totalCost;
    }

    /**
     * @return amount of cells traversed
     */
    public int getCellsTraversed() {
        return this.cellsTraversed;
    }

    /**
     * @return return the amount of cells searched during the algorithm
     */
    public int getSearchedCells() {
        return this.searchedCells;
    }

}
