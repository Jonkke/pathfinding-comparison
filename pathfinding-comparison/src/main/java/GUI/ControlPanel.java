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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Jonkke
 */
public class ControlPanel extends JPanel implements ActionListener {

    GUI parentGui;

    int panelWidth;
    JLabel loadMapLabel;
    JLabel nodeSelectLabel;
    JLabel algoSelectLabel;
    JLabel aStarSettings;
    JLabel routeInfo;
    JButton loadMapBtn;
    JButton startPosBtn;
    JButton endPosBtn;
    JButton dijkstraBtn;
    JButton aStarBtn;
    JButton searchedAreaBtn;
    JFileChooser fileChooser;
    JCheckBox useCrossProductTieBreaking;

    // Route info labels
    JLabel startAndEndCells;
    JLabel routeCellsTraversed;
    JLabel routeTotalCost;
    JLabel routeTimeMs;
    JLabel cellsSearched;

    JLabel divisor;

    public ControlPanel(GUI parentGui) {
        this.parentGui = parentGui;
        panelWidth = 300;

        this.setPreferredSize(new Dimension(panelWidth, 200));

        // Divisor thingy
        this.divisor = new JLabel(" ");
        this.divisor.setPreferredSize(new Dimension(panelWidth, 100));
        this.divisor.setHorizontalAlignment(SwingConstants.CENTER);

        // Map loading
        this.loadMapLabel = new JLabel("Load map");
        this.loadMapLabel.setPreferredSize(new Dimension(panelWidth, 20));
        this.loadMapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.loadMapLabel);

        this.loadMapBtn = new JButton("From file...");
        this.loadMapBtn.setPreferredSize(new Dimension(panelWidth - 30, 30));
        this.loadMapBtn.setActionCommand("LoadMap");
        this.loadMapBtn.addActionListener(this);
        this.add(this.loadMapBtn);

        // Start / end node selection
        this.nodeSelectLabel = new JLabel("Select node");
        this.nodeSelectLabel.setPreferredSize(new Dimension(panelWidth, 20));
        this.nodeSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.nodeSelectLabel);

        this.startPosBtn = new JButton("Start");
        this.startPosBtn.setActionCommand("SetStartPos");
        this.startPosBtn.addActionListener(this);
        this.startPosBtn.setBackground(Color.lightGray);
        this.endPosBtn = new JButton("End");
        this.endPosBtn.setActionCommand("SetEndPos");
        this.endPosBtn.addActionListener(this);

        this.add(startPosBtn);
        this.add(endPosBtn);

        // Algo selection
        this.algoSelectLabel = new JLabel("Select algorithm");
        this.algoSelectLabel.setPreferredSize(new Dimension(panelWidth, 20));
        this.algoSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.algoSelectLabel);

        this.dijkstraBtn = new JButton("Dijkstra");
        this.dijkstraBtn.setActionCommand("Dijkstra");
        this.dijkstraBtn.addActionListener(this);
        this.dijkstraBtn.setBackground(Color.lightGray);
        this.aStarBtn = new JButton("A*");
        this.aStarBtn.setActionCommand("Astar");
        this.aStarBtn.addActionListener(this);

        this.add(dijkstraBtn);
        this.add(aStarBtn);

        // Show | hide searched area switch
        this.searchedAreaBtn = new JButton("Show searched");
        this.searchedAreaBtn.setActionCommand("SwitchSearched");
        this.searchedAreaBtn.addActionListener(this);
        this.searchedAreaBtn.setPreferredSize(new Dimension(panelWidth / 2, 25));
        this.add(searchedAreaBtn);

        // A* specific settings
        this.aStarSettings = new JLabel("A* settings");
        this.aStarSettings.setPreferredSize(new Dimension(panelWidth, 20));
        this.aStarSettings.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.aStarSettings);

        this.useCrossProductTieBreaking = new JCheckBox("Use tie-breaking");
        this.useCrossProductTieBreaking.setSelected(false);
        this.useCrossProductTieBreaking.setActionCommand("SetCPTieBreaking");
        this.useCrossProductTieBreaking.addActionListener(this);
        this.add(this.useCrossProductTieBreaking);

        // Divisor
        this.add(this.divisor);

        // Route info
        this.routeInfo = new JLabel("Route Info:");
        this.routeInfo.setPreferredSize(new Dimension(panelWidth, 20));
        this.routeInfo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.routeInfo);

        this.routeCellsTraversed = new JLabel("No route to display...");
        this.routeCellsTraversed.setPreferredSize(new Dimension(panelWidth, 20));
        this.routeCellsTraversed.setHorizontalAlignment(SwingConstants.CENTER);
        this.startAndEndCells = new JLabel("");
        this.startAndEndCells.setPreferredSize(new Dimension(panelWidth, 20));
        this.startAndEndCells.setHorizontalAlignment(SwingConstants.CENTER);
        this.routeTotalCost = new JLabel("");
        this.routeTotalCost.setPreferredSize(new Dimension(panelWidth, 20));
        this.routeTotalCost.setHorizontalAlignment(SwingConstants.CENTER);
        this.routeTimeMs = new JLabel("");
        this.routeTimeMs.setPreferredSize(new Dimension(panelWidth, 20));
        this.routeTimeMs.setHorizontalAlignment(SwingConstants.CENTER);
        this.cellsSearched = new JLabel("");
        this.cellsSearched.setPreferredSize(new Dimension(panelWidth, 20));
        this.cellsSearched.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(startAndEndCells);
        this.add(routeCellsTraversed);
        this.add(routeTotalCost);
        this.add(routeTimeMs);
        this.add(cellsSearched);

        // File chooser popup
        this.fileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        Color defBtnColor = UIManager.getColor("Button.background");
        if (ac.equals("SetStartPos")) {
            this.startPosBtn.setBackground(Color.lightGray);
            this.endPosBtn.setBackground(defBtnColor);
            this.parentGui.nodeToSet = 1;
        } else if (ac.equals("SetEndPos")) {
            this.startPosBtn.setBackground(defBtnColor);
            this.endPosBtn.setBackground(Color.lightGray);
            this.parentGui.nodeToSet = 2;
        } else if (ac.equals("Dijkstra")) {
            this.dijkstraBtn.setBackground(Color.lightGray);
            this.aStarBtn.setBackground(defBtnColor);
            this.parentGui.activeAlgorithm = 1;
            this.parentGui.findAndDrawRoute();
        } else if (ac.equals("Astar")) {
            this.dijkstraBtn.setBackground(defBtnColor);
            this.aStarBtn.setBackground(Color.lightGray);
            this.parentGui.activeAlgorithm = 2;
            this.parentGui.findAndDrawRoute();
        } else if (ac.equals("SwitchSearched")) {
            this.parentGui.switchSearchedVisibility();
            if (this.searchedAreaBtn.getText().equals("Show searched")) {
                this.searchedAreaBtn.setText("Hide searched");
            } else {
                this.searchedAreaBtn.setText("Show searched");
            }
        } else if (ac.equals("SetCPTieBreaking")) {
            this.parentGui.crossProductTieBreaking = this.useCrossProductTieBreaking.isSelected();
            this.parentGui.findAndDrawRoute();
        } else if (ac.equals("LoadMap")) {
            int returnVal = this.fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();
                this.parentGui.newMap(file.getAbsolutePath());
            }
        }
    }

    public void updateRouteInfo(int[] startEndPos, int cellsTraversed, int totalCost, int timeMs, int cellsSearched) {
        this.startAndEndCells.setText("Start: (" + startEndPos[0] + "," + startEndPos[1] + "), End: (" + startEndPos[2] + "," + startEndPos[3] + ")");
        this.routeCellsTraversed.setText("Cells traversed: " + cellsTraversed);
        this.routeTotalCost.setText("Total cost: " + totalCost);
        this.routeTimeMs.setText("Time in ms: " + timeMs);
        this.cellsSearched.setText("Cells searched: " + cellsSearched);
    }

}
