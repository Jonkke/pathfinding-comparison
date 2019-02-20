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
package GUI;

import Algorithms.AStar;
import Algorithms.Dijkstra;
import domain.Map;
import domain.MapCell;
import DataStructures.MapCellList;
import domain.Material;
import java.awt.BorderLayout;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * The graphical user interface. This class defines the user interface for the
 * pathfinding comparison app. In it, user can load .map files from the file
 * system, switch between algorithms and view the progression and end result of
 * these algorithms on a map.
 *
 * @author Jonkke
 */
public class GUI {

    ControlPanel controlPanel;
    MapCanvas mapCanvas;
    GUIState state;

    /**
     * This method will build and display a new UI window that can be used to
     * control the application.
     */
    public void buildUIWindow() {
        this.state = new GUIState();
        controlPanel = new ControlPanel(state);
//        Map map = new Map("./maps/arena2.map");
        Map map = new Map("./maps/brc000d.map");
//        Map map = new Map(300, 300, 0.1, 2134);
        Dijkstra d = new Dijkstra();
        AStar as = new AStar();
        mapCanvas = new MapCanvas(map, 3, 3);
        
//        Consumer<String> changeMap = (path) -> {
//            this.map = new Map(path);
//        };

        BiConsumer<Integer, Integer> updateDest = (x, y) -> {
            if (this.state.nodeToSet == 1) {
                this.state.startX = x;
                this.state.startY = y;
            } else {
                this.state.endX = x;
                this.state.endY = y;
            }
            map.resetMap();
            MapCellList route;
            if (state.algo == 1) {
                route = d.findShortestPath(map, this.state.startX, this.state.startY, this.state.endX, this.state.endY, null);
            } else {
                route = as.findShortestPath(map, this.state.startX, this.state.startY, this.state.endX, this.state.endY, false, null);
            }
            
            for (int i = 0; i < route.size(); i++) {
                MapCell cell = route.get(i);
                cell.material = Material.ROUTE;
            }
            mapCanvas.repaint();
        };

        this.mapCanvas.setUpdateHook(updateDest);

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(mapCanvas, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    class GUIState {
        int nodeToSet; // 1=start, 2=end
        int startX;
        int startY;
        int endX;
        int endY;
        int algo; // 1=Dijkstra, 2=A*
        String mapFilePath;
        
        public GUIState() {
            this.nodeToSet = 1;
            this.algo = 1;
        }
        
        public void setMapFilePath(String path) {
            this.mapFilePath = path;
        }
    }

}
