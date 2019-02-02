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

import domain.Map;
import domain.MapCell;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonkke
 */
public class AStarTest {

    Map map;
    AStar as = new AStar();

    @Test
    public void testRouteExists() {
        map = new Map(5, 5, 0.63, 1337);
        ArrayList<MapCell> path = as.findShortestPath(map, 0, 0, 4, 4);
        assertEquals(path.size(), 9);
    }

    @Test
    public void testRouteDoesNotExist() {
        map = new Map(5, 5, 0.64, 1337);
        ArrayList<MapCell> path = as.findShortestPath(map, 0, 0, 4, 4);
        assertEquals(path.size(), 0);
    }

    @Test
    public void testRouteStartBlocked() {
        map = new Map(5, 5, 0.63, 1337);
        ArrayList<MapCell> path = as.findShortestPath(map, 1, 1, 4, 4);
        assertEquals(path.size(), 0);
    }

    @Test
    public void testRouteEndBlocked() {
        map = new Map(5, 5, 0.63, 1337);
        ArrayList<MapCell> path = as.findShortestPath(map, 0, 0, 3, 4);
        assertEquals(path.size(), 0);
    }

}