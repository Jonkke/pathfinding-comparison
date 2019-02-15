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

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jonkke
 */
public class MapCellListTest {
    
    MapCellList mcl;
    
    @Before
    public void init() {
        this.mcl = new MapCellList();
    }
    
    @Test
    public void testEmptyListSizeIsZero() {
        assertEquals(mcl.size(), 0);
    }
    
    @Test
    public void testRemoveNonExistentElement() {
        assertEquals(mcl.remove(0), null);
        assertEquals(mcl.remove(123), null);
        assertEquals(mcl.remove(-123), null);
    }
    
    @Test
    public void testAddNewElement() {
        MapCell mc = new MapCell(10, 10, Material.EMPTY);
        mcl.add(mc);
        assertEquals(mcl.get(0), mc);
    }
    
    @Test
    public void testAddRemoveNewElement() {
        MapCell mc = new MapCell(10, 10, Material.EMPTY);
        mcl.add(mc);
        assertEquals(mcl.size(), 1);
        assertEquals(mcl.remove(0), mc);
        assertEquals(mcl.get(0), null);
        assertEquals(mcl.size(), 0);
    }
    
    @Test
    public void testRemoveFromMiddle() {
        MapCell m1 = new MapCell(1, 1, Material.EMPTY);
        MapCell m2 = new MapCell(2, 2, Material.EMPTY);
        MapCell m3 = new MapCell(3, 3, Material.EMPTY);
        MapCell m4 = new MapCell(4, 4, Material.EMPTY);
        MapCell m5 = new MapCell(5, 5, Material.EMPTY);
        mcl.add(m1);
        mcl.add(m2);
        mcl.add(m3);
        mcl.add(m4);
        mcl.add(m5);
        assertEquals(mcl.remove(2), m3);
        assertEquals(mcl.remove(2), m4);
        assertEquals(mcl.remove(0), m1);
        assertEquals(mcl.remove(0), m2);
        assertEquals(mcl.remove(0), m5);
    }
    
    @Test
    public void testRemoveAndAddCombined() {
        MapCell m1 = new MapCell(1, 1, Material.EMPTY);
        MapCell m2 = new MapCell(2, 2, Material.EMPTY);
        MapCell m3 = new MapCell(3, 3, Material.EMPTY);
        MapCell m4 = new MapCell(4, 4, Material.EMPTY);
        MapCell m5 = new MapCell(5, 5, Material.EMPTY);
        MapCell m6 = new MapCell(5, 5, Material.EMPTY);
        MapCell m7 = new MapCell(5, 5, Material.EMPTY);
        mcl.add(m1);
        mcl.add(m2);
        mcl.add(m3);
        assertEquals(mcl.remove(1), m2);
        mcl.add(m4);
        mcl.add(m5);
        assertEquals(mcl.remove(1), m3);
        assertEquals(mcl.remove(2), m5);
        mcl.add(m6);
        mcl.add(m7);
        assertEquals(mcl.remove(0), m1);
        assertEquals(mcl.remove(0), m4);
        assertEquals(mcl.remove(0), m6);
        assertEquals(mcl.remove(0), m7);
    }
    
}
