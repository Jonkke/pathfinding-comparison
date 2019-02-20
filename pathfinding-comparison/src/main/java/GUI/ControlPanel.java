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

import GUI.GUI.GUIState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
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

    int panelWidth;
    GUIState state;
    JLabel loadMapLabel;
    JLabel nodeSelectLabel;
    JLabel algoSelectLabel;
    JButton loadMapBtn;
    JButton startPosBtn;
    JButton endPosBtn;
    JButton dijkstraBtn;
    JButton aStarBtn;
    JFileChooser fileChooser;

    public ControlPanel(GUIState state) {
        panelWidth = 200;
        this.state = state;

        this.setPreferredSize(new Dimension(panelWidth, 200));

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

        // File chooser
        this.fileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        Color defBtnColor = UIManager.getColor("Button.background");
        if (ac.equals("SetStartPos")) {
            this.startPosBtn.setBackground(Color.lightGray);
            this.endPosBtn.setBackground(defBtnColor);
            this.state.nodeToSet = 1;
        } else if (ac.equals("SetEndPos")) {
            this.startPosBtn.setBackground(defBtnColor);
            this.endPosBtn.setBackground(Color.lightGray);
            this.state.nodeToSet = 2;
        } else if (ac.equals("Dijkstra")) {
            this.dijkstraBtn.setBackground(Color.lightGray);
            this.aStarBtn.setBackground(defBtnColor);
            this.state.algo = 1;
        } else if (ac.equals("Astar")) {
            this.dijkstraBtn.setBackground(defBtnColor);
            this.aStarBtn.setBackground(Color.lightGray);
            this.state.algo = 2;
        } else if (ac.equals("LoadMap")) {
            int returnVal = this.fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();
                this.state.setMapFilePath(file.getAbsolutePath());
            }
        }
    }

}
