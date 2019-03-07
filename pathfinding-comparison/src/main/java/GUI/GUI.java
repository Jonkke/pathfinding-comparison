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
    Map map;
    Dijkstra dijkstra;
    AStar astar;

    int nodeToSet; // 1=start, 2=end
    int startX;
    int startY;
    int endX;
    int endY;
    int activeAlgorithm; // 1=Dijkstra, 2=A*
    boolean crossProductTieBreaking = false;
    boolean showSearched; // if true, draw searched area

    public GUI() {
        controlPanel = new ControlPanel(this);
        map = new Map(1024, 1024);
        mapCanvas = new MapCanvas(this, map, 1024, 1024);
        this.nodeToSet = 1;
        this.activeAlgorithm = 1;
        this.showSearched = false;
        this.dijkstra = new Dijkstra();
        this.astar = new AStar();
    }

    private void resetNodes() {
        this.startX = 0;
        this.startY = 0;
        this.endX = 0;
        this.endY = 0;
    }

    /**
     * This method will build and display a new UI window that can be used to
     * control the application.
     */
    public void buildUIWindow() {
        JScrollPane scrollPane = new JScrollPane(mapCanvas);
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Replace current map
     *
     * @param path Path to new map
     */
    public void newMap(String path) {
        map.resetMap();
        resetNodes();
        map.mapFromFilePath(path);
        mapCanvas.setNewMap(map, 1024, 1024);
        mapCanvas.repaint();
        mapCanvas.revalidate();
    }

    /**
     * Switch if the map canvas shows searched cells for the route
     */
    public void switchSearchedVisibility() {
        this.showSearched = !this.showSearched;
        findAndDrawRoute();
    }

    /**
     * Force new pathfind algorithm run and update map canvas and control panel
     * values according to it's results
     */
    public void findAndDrawRoute() {
        this.map.resetMap();
        MapCellList route;
        int cellsTraversed = 0;
        int totalCost = 0;
        int timeMs = 0;
        int cellsSearched = 0;
        if (this.activeAlgorithm == 1) {
            route = this.dijkstra.findShortestPath(map, this.startX, this.startY, this.endX, this.endY, this.showSearched);
            timeMs = (int) (this.dijkstra.getLastRunTimeNanos() / 1000000);
            totalCost = this.dijkstra.getTotalCost();
            cellsTraversed = this.dijkstra.getCellsTraversed();
            cellsSearched = this.dijkstra.getSearchedCells();
        } else {
            route = this.astar.findShortestPath(map, this.startX, this.startY, this.endX, this.endY, this.showSearched, this.crossProductTieBreaking);
            timeMs = (int) (this.astar.getLastRunTimeNanos() / 1000000);
            totalCost = this.astar.getTotalCost();
            cellsTraversed = this.astar.getCellsTraversed();
            cellsSearched = this.astar.getSearchedCells();
        }

        for (int i = 0; i < route.size(); i++) {
            MapCell cell = route.get(i);
            cell.material = Material.ROUTE;
        }
        int[] startEnd = {startX, startY, endX, endY};
        this.controlPanel.updateRouteInfo(startEnd, cellsTraversed, totalCost, timeMs, cellsSearched);
        this.mapCanvas.repaint();
        this.mapCanvas.revalidate();
    }

    /**
     * Set new node position. nodeToSet determines if we are setting the
     * starting or end position, 1 is start, 2 is end
     *
     * @param x
     * @param y
     */
    public void setNodePos(int x, int y) {
        if (this.nodeToSet == 1) {
            this.startX = x;
            this.startY = y;
        } else {
            this.endX = x;
            this.endY = y;
        }
        findAndDrawRoute();
    }

}
