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
package UI;

import domain.AStar;
import domain.Dijkstra;
import domain.Map;
import domain.MapCell;
import domain.Material;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import javax.swing.*;

/**
 *
 * @author Jonkke
 */
public class UI {
    MapCanvas mapCanvas;
    
    public void buildWindow() {
        Map map = new Map("./maps/arena2.map");
        Dijkstra d = new Dijkstra();
        AStar as = new AStar();
        BiConsumer<Integer, Integer> updateDest = (x, y) -> {
            map.resetMap();
            ArrayList<MapCell> route2 = as.findShortestPath(map, 100, 8, x, y, null);
            for (MapCell cell : route2) {
                cell.material = Material.ROUTE;
            }
            mapCanvas.repaint();
        };
        mapCanvas = new MapCanvas(map, 4, 4, updateDest);

        JFrame frame = new JFrame();
        frame.add(mapCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

//        Runnable updateFn = () -> mc.repaint();
//        ArrayList<MapCell> route = as.findShortestPath(map, 100, 8, 220, 190, updateFn);
//        for (MapCell cell : route) {
//            cell.material = Material.ROUTE;
//        }

//        map.resetMap();
//        route = d.findShortestPath(map, 100, 8, 220, 190, updateFn);
//        for (MapCell cell : route) {
//            cell.material = Material.ROUTE;
//        }
//        mc.repaint();
    }

}
