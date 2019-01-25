/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Map;
import domain.MapCell;
import java.util.ArrayList;

/**
 *
 * @author Jonkke
 */
public class Main {

    public static void main(String[] args) {
        Map map = new Map(10, 10, 0.10, 1337);
        Dijkstra d = new Dijkstra();
        ArrayList<MapCell> route = d.findShortest(map, 0, 0, 9, 9);
        for (MapCell mc : route) {
            mc.material = 9;
        }
        System.out.println(map.toString());
    }

}
