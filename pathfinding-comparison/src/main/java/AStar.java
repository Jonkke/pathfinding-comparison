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

import java.util.ArrayList;
import java.util.PriorityQueue;
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
     * @return Ordered list of map cells that make up the shortest path. If
     * either start or target is a non-searchable cell (e.g. wall), or a route
     * does not exist, then an empty list will be returned.
     */
    public ArrayList<MapCell> findShortestPath(Map map, int x1, int y1, int x2, int y2) {
        if (!map.getCell(x1, y1).isTraversable() || !map.getCell(x2, y2).isTraversable()) {
            return new ArrayList<>(); // Start or end of route blocked by wall => empty list
        }
        boolean routeFound = false;
        MapCell start = map.getCell(x1, y1);
        MapCell target = map.getCell(x2, y2);
        start.weight = 0;
        PriorityQueue<MapCell> pq = new PriorityQueue();
        pq.add(start);
        while (!pq.isEmpty()) {
            MapCell cell = pq.poll();
            if (cell == target) {
                routeFound = true;
                break;
            }
            if (cell.isTested) continue;
            cell.isTested = true;
            cell.material = Material.SEARCHED;
//            System.out.println(map.toString()); // remove
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException ie) {
//                System.out.println(ie);
//            }

            for (MapCellEdge mce : cell.edges) {
                if (mce == null) {
                    continue;
                }
                int currentCost = mce.to.weight;
                int updatedCost = cell.weight + mce.cost;
                if (updatedCost < currentCost) {
                    mce.to.weight = updatedCost + getManhattanDistance(mce.to.x, mce.to.y, target.x, target.y);
                    pq.add(mce.to);
                    mce.to.material = Material.CANDIDATE;
                }
            }
        }

        // If a route was found, trace it back from the target and return traversed cells as list
        ArrayList<MapCell> route = new ArrayList();
        if (!routeFound) {
            return route; // Route not fonud => empty list
        }
        MapCell curr = map.getCell(x2, y2);
        while (curr != map.getCell(x1, y1)) {
            double shortestDist = Integer.MAX_VALUE;
            MapCell shortest = null;
            MapCell last = null;
            for (MapCellEdge mce : curr.edges) {
                if (mce == null) {
                    continue;
                }
                if (mce.to != last && mce.to.weight < shortestDist) {
                    shortestDist = mce.to.weight;
                    shortest = mce.to;
                }
            }
            last = curr;
            route.add(curr);
            curr = shortest;
        }
        route.add(curr);

        return route;
    }

    private int getManhattanDistance(int x1, int y1, int x2, int y2) {
        int xDiff = Math.abs(x2 - x1);
        int yDiff = Math.abs(y2 - y1);
        return xDiff + yDiff;
    }
}
