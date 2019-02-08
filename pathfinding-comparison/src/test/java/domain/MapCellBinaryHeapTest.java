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
package domain;

import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jonkke
 */
public class MapCellBinaryHeapTest {
    MapCellBinaryHeap mcbh;
    
    @Before
    public void init() {
        mcbh = new MapCellBinaryHeap();
    }

    @Test
    public void testAddToBinaryHeap() {
        MapCell mc1 = new MapCell(0, 0, Material.EMPTY);
        MapCell mc2 = new MapCell(0, 1, Material.EMPTY);
        MapCell mc3 = new MapCell(0, 2, Material.EMPTY);
        mc1.priority = 0.7;
        mc2.priority = 0.3;
        mc3.priority = 3.4;
        mcbh.add(mc1);
        mcbh.add(mc2);
        mcbh.add(mc3);
        assertEquals(mcbh.poll(), mc2);
        assertEquals(mcbh.poll(), mc1);
        assertEquals(mcbh.poll(), mc3);
    }

    @Test
    public void testPollFromEmptyHeap() {
        assertEquals(mcbh.poll(), null);
    }
    
    @Test
    public void testPeekFromEmptyHeap() {
        assertEquals(mcbh.peek(), null);
    }
    
    @Test
    public void bigTest() {
        Random r =  new Random();
        int halfN = 100000;
        for (int i = 0; i < halfN; i++) {
            MapCell mc = new MapCell(0,0,Material.EMPTY);
            mc.priority = r.nextDouble()*5 + 0.3;
            mcbh.add(mc);
        }
        MapCell first = new MapCell(0,0,Material.EMPTY);
        first.priority = 0.1;
        mcbh.add(first);
        MapCell last = new MapCell(0,0,Material.EMPTY);
        last.priority = 6.0;
        mcbh.add(last);
        for (int i = 0; i < halfN; i++) {
            MapCell mc = new MapCell(0,0,Material.EMPTY);
            mc.priority = r.nextDouble()*5 + 0.3;
            mcbh.add(mc);
        }
        assertEquals(mcbh.poll(), first); // Make sure the one with priority 0.1 is first to come out
        
        for (int i = 0; i < halfN*2; i++) {
            mcbh.poll();
        }
        assertEquals(mcbh.poll(), last);
        
    }

}
