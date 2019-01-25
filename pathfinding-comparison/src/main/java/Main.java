/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Map;

/**
 *
 * @author Jonkke
 */
public class Main {

    public static void main(String[] args) {
        Map map = new Map(5, 5, 0.40, 1337);
        System.out.println(map.toString());
    }

}
