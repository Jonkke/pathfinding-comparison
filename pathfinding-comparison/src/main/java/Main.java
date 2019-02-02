/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Map;
import domain.MapCell;
import domain.Material;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Jonkke
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        
//        Map map = new Map(25, 25, 0.05, 1337);
        Map map =  new Map("./maps/arena2.map");
        Dijkstra d = new Dijkstra();
        AStar as = new AStar();
        ArrayList<MapCell> route = as.findShortestPath(map, 100, 8, 110, 80);
        for (MapCell mc : route) {
            mc.material = Material.ROUTE;
        }
        System.out.println(map.toString());
    }

}
