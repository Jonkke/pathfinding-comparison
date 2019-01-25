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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonkke
 */
public class MapTest {

    Map map;

    @Test
    public void testEmptyMap() {
        map = new Map(5, 5);
        assertEquals(
                ". . . . .\n"
                + ". . . . .\n"
                + ". . . . .\n"
                + ". . . . .\n"
                + ". . . . .\n",
                map.toString());
    }

    @Test
    public void testRandomMap() {
        map = new Map(5, 5, 0.40, 1337);
        assertEquals(
                ". . . . X\n"
                + ". X X . .\n"
                + "X . X X .\n"
                + "X . X . .\n"
                + ". . . X .\n",
                map.toString());
    }

}
