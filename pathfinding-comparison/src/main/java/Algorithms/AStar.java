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
 * A* algorithm for heuristically assisted pathfinding.
 *
 * The A* algorithm is used for pathfinding in a graph. It differs from Dijkstra
 * in that it uses a heuristic function to assist it for deciding what direction
 * to search next. In this implementation, the heuristic is based solely on the
 * Manhattan distance between the current point and the goal point. This ensures
 * that the heuristic will remain admissible, meaning that it will not
 * over-estimate the distance to the goal point at any point. This in turn
 * ensures that we will always find the shortest route, although in terms of
 * algorithm effectiveness this may not be the most optimal solution.
 *
 * @author Jonkke
 */
public class AStar {

    int startX;
    int startY;
    private long lastRunNanoTime; // Running time of the algorithm is saved in this variable
    private int totalCost;
    private int cellsTraversed;
    private int searchedCells;

    /**
     * Shortest route search using A* algorithm. Input the map that the search
     * is to be run on, and the starting x and y coordinates (x1,y1), then the
     * target x and y coordinates (x2,y2).
     *
     * @param map
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param markSearched If true, mark searched map cells with special
     * materials
     * @param useCrossProduct Dictates whether or not to use cross product
     * tie-breaking (results in more straight-looking paths)
     * @return Ordered list of map cells that make up the shortest path. If
     * either start or target is a non-searchable cell (e.g. wall), or a route
     * does not exist, then an empty list will be returned.
     */
    public MapCellList findShortestPath(Map map, int x1, int y1, int x2, int y2, boolean markSearched, boolean useCrossProduct) {
        this.lastRunNanoTime = 0;
        this.totalCost = 0;
        this.cellsTraversed = 0;
        this.searchedCells = 0;
        long startTime = System.nanoTime();
        this.startX = x1;
        this.startY = y1;
        if (!map.getCell(x1, y1).isTraversable() || !map.getCell(x2, y2).isTraversable()) {
            return new MapCellList(); // Start or end of route blocked by wall => empty list
        }
        boolean routeFound = false;
        MapCell start = map.getCell(x1, y1);
        MapCell target = map.getCell(x2, y2);
        start.costFromStart = 0;
        MapCellBinaryHeap mcbh = new MapCellBinaryHeap();
        mcbh.add(start);
        while (!mcbh.isEmpty()) {
            MapCell cell = mcbh.poll();
            if (cell == target) {
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
                int currentCost = mce.to.costFromStart;
                int updatedCost = cell.costFromStart + mce.cost;
                if (!mce.to.isTested) {
                    mce.to.costFromStart = updatedCost;
                    double h = useCrossProduct
                            ? getManhattanDistance(mce.to.x, mce.to.y, target.x, target.y) + getCrossProduct(mce.to.x, mce.to.y, target.x, target.y)
                            : getManhattanDistance(mce.to.x, mce.to.y, target.x, target.y);
                    mce.to.priority = updatedCost + h;
                    mcbh.add(mce.to);
                    if (markSearched) {
                        mce.to.material = Material.CANDIDATE;
                    }
                } else if (updatedCost < currentCost) {
                    mce.to.costFromStart = updatedCost;
                    double h = useCrossProduct
                            ? getManhattanDistance(mce.to.x, mce.to.y, target.x, target.y) + getCrossProduct(mce.to.x, mce.to.y, target.x, target.y)
                            : getManhattanDistance(mce.to.x, mce.to.y, target.x, target.y);
                    mce.to.priority = updatedCost + h;
                    MapCell temp = mcbh.poll(); // update binary heap by removing and readding smallest MapCell
                    mcbh.add(temp);
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
            return route; // Route not found => empty list
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

    private double getManhattanDistance(int x1, int y1, int x2, int y2) {
        int xDiff = Math.abs(x2 - x1);
        int yDiff = Math.abs(y2 - y1);
        int dist = (xDiff + yDiff);
        return dist * 0.98;
    }

    private double getCrossProduct(int x1, int y1, int x2, int y2) {
        int currX = x1 - x2;
        int currY = y1 - y2;
        int fromStartX = this.startX - x2;
        int fromStartY = this.startY - y2;
        int cross = Math.abs(currX * fromStartY - fromStartX * currY);
        return cross * 0.05;
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
