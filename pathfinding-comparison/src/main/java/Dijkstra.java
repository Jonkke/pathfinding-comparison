
import domain.Map;
import domain.MapCell;
import domain.MapCellEdge;
import java.util.PriorityQueue;
import java.util.ArrayList;

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

/**
 * Dijkstra's algorithm for shortest path.
 * This class is a container for the Dijkstra's algorithm, which can be used
 * to find the shortest possible route between two nodes in a graph, provided
 * there are no edges width negative weights anywhere in the graph. In this
 * implementation, the algorithm will find the shortest path in a grid-like
 * map consisting of x*y cells.
 * 
 * @author Jonkke
 */
public class Dijkstra {
    
    /**
     * Shortest route search using Dijkstra's algorithm.
     * Input the map that the search is to be run on, and the starting
     * x and y coordinates (x1,y1), then the target x and y coordinates (x2,y2).
     * 
     * @param map
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return Ordered list of map cells that make up the shortest path. If either
     * start or target is a non-searchable cell (e.g. wall), or a route does not exist,
     * then an empty list will be returned.
     */
    public ArrayList<MapCell> findShortestPath(Map map, int x1, int y1, int x2, int y2) {
        if (!map.getCell(x1, y1).isTraversable() || !map.getCell(x2, y2).isTraversable()) {
            return new ArrayList<MapCell>(); // Start or end of route blocked by wall => empty list
        }
        MapCell start = map.getCell(x1, y1);
        start.weight = 0;
        boolean routeFound = false;
        PriorityQueue<MapCell> pq = new PriorityQueue();
        pq.add(start);
        while (!pq.isEmpty()) {
            MapCell cell = pq.poll();
            if (cell == map.getCell(x2, y2)) {
                routeFound = true;
            }
            if (cell.isTested) continue;
            cell.isTested = true;
            for (MapCellEdge mce : cell.edges) {
                if (mce == null) continue;
                int currentDistance = mce.to.weight;
                int updatedDistance = cell.weight + mce.cost;
                if (updatedDistance < currentDistance) {
                    mce.to.weight = updatedDistance;
                    pq.add(mce.to);
                }
            }
        }
        
        // If a route was found, trace it back from the target and return traversed cells as list
        ArrayList<MapCell> route = new ArrayList();
        if (!routeFound) return route; // Route not fonud => empty list
        MapCell curr = map.getCell(x2, y2);
        while (curr != map.getCell(x1, y1)) {
            int shortestDist = Integer.MAX_VALUE;
            MapCell shortest = null;
            MapCell last = null;
            for (MapCellEdge mce : curr.edges) {
                if (mce == null) continue;
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
    
}
