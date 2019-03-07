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

import domain.Map;
import domain.MapCell;
import domain.Material;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import java.awt.event.MouseMotionListener;

/**
 *
 * @author Jonkke
 */
public class MapCanvas extends JPanel implements MouseMotionListener, MouseListener {

    GUI parentGui;
    ArrayList<Object> shapes = new ArrayList();
    Random r = new Random();
    int rows, columns;
    int height = 600;
    int width = 800;
    int cellHeight = 1;
    int cellWidth = 1;
    Map map;

    public void setNewMap(Map map, int width, int height) {
        this.map = map;
        this.rows = map.cells.length;
        this.columns = map.cells[0].length;
        this.height = height;
        this.width = width;
        this.cellHeight = this.height / this.rows;
        this.cellWidth = this.width / this.rows;
        setBackground(Color.WHITE);
    }

    public MapCanvas(GUI gui, int columns, int rows) {
        this.parentGui = gui;
        this.addMouseMotionListener(this);
        this.columns = columns;
        this.rows = rows;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
    }

    public MapCanvas(GUI gui, Map map, int width, int height) {
        this.parentGui = gui;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        setNewMap(map, width, height);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(this.width, this.height));
    }
    
    public void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < columns - 1; i++) {
            int xPos = (this.width / columns) * (i + 1);
            g.drawLine(xPos, 0, xPos, this.height);
        }
        for (int i = 0; i < rows - 1; i++) {
            int yPos = (this.height / rows) * (i + 1);
            g.drawLine(0, yPos, this.width, yPos);
        }
    }

    public void drawMap(Graphics g) {
        MapCell[][] cells = this.map.cells;
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                MapCell mc = cells[y][x];
                if (mc.material == Material.WALL) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * this.cellWidth, y * this.cellHeight, this.cellWidth, this.cellHeight);
                } else if (mc.material == Material.SWAMP) {
                    g.setColor(new Color(40, 40, 255));
                    g.fillRect(x * this.cellWidth, y * this.cellHeight, this.cellWidth, this.cellHeight);
                } else if (mc.material == Material.WATER) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * this.cellWidth, y * this.cellHeight, this.cellWidth, this.cellHeight);
                } else if (mc.material == Material.SEARCHED) {
                    g.setColor(Color.GRAY);
                    g.fillRect(x * this.cellWidth, y * this.cellHeight, this.cellWidth, this.cellHeight);
                } else if (mc.material == Material.CANDIDATE) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(x * this.cellWidth, y * this.cellHeight, this.cellWidth, this.cellHeight);
                }
            }
        }
    }

    public void drawRoute(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        MapCell[][] cells = this.map.cells;
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                MapCell mc = cells[y][x];
                if (mc.material == Material.ROUTE) {
                    g.setColor(Color.RED);
                    g.fillRect(x * this.cellWidth, y * this.cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawRoute(g);
    }

    public void update() {
        Graphics g = getGraphics();
        paintComponent(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.parentGui.setNodePos(e.getX() / cellWidth, e.getY() / cellHeight);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
