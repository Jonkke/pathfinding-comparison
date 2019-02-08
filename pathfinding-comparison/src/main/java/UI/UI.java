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
 * The user interface. This class defines the user interface for the pathfinding
 * comparison app. In it, user can load .map files from the file system, switch
 * between algorithms and view the progression and end result of these
 * algorithms on a map.
 *
 * @author Jonkke
 */
public class UI {

    MapCanvas mapCanvas;
    int next = 0;

    /**
     * This method will build and display a new UI window that can be used to
     * control the application.
     */
    public void buildUIWindow() {
//        Map map = new Map("./maps/arena2.map");
        Map map = new Map("./maps/brc000d.map");
//        Map map = new Map(300, 300, 0.1, 2134);
        Dijkstra d = new Dijkstra();
        AStar as = new AStar();
        mapCanvas = new MapCanvas(map, 4, 4);

        BiConsumer<Integer, Integer> updateDest = (x, y) -> {
            this.next = this.next == 0 ? 1 : 0;
            map.resetMap();
//            ArrayList<MapCell> route2 = this.next == 0 ? d.findShortestPath(map, 100, 25, x, y, null) : as.findShortestPath(map, 100, 25, x, y, true, null);
              ArrayList<MapCell> route2 = as.findShortestPath(map, 100, 25, x, y, true, null);
            for (MapCell cell : route2) {
                cell.material = Material.ROUTE;
            }
            mapCanvas.repaint();
        };

        this.mapCanvas.setUpdateHook(updateDest);

        JFrame frame = new JFrame();
        frame.add(mapCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
