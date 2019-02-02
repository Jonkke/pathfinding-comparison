/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Map;
import domain.MapCell;
import domain.Material;
import java.util.ArrayList;

/**
 *
 * @author Jonkke
 */
public class Main {

    public static void main(String[] args) {
        Map map = new Map(25, 25, 0.05, 1337);
        Dijkstra d = new Dijkstra();
        AStar as = new AStar();
        ArrayList<MapCell> route = as.findShortestPath(map, 12, 12, 0, 12);
        for (MapCell mc : route) {
            mc.material = Material.ROUTE;
        }
        System.out.println(map.toString());
    }

}
