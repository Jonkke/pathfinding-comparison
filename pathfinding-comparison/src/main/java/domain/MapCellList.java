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

/**
 * This class implements a list for storing map cells, and an interface for it.
 * It is simply a replacement for the default ArrayList data structure for Java,
 * only this one is not generic but only used for map cells. Behind the scenes,
 * it uses a regular array, which will grow and shrink as the cells are added
 * to, or removed from the list.
 * 
 * Note: Removing elements from the end will be much faster than removing them
 * from the beginning or the middle, as the underlying array will always need
 * to be rearranged in those cases. If removing MapCells from list with a loop,
 * all objects can be removed in O(log n) time if all are removed from the end, 
 * while removing all objects from any other position other than the curren last
 * index will result in a time complexity of O(n^2).
 *
 * @author Jonkke
 */
public class MapCellList {

    private MapCell[] mcArr;
    private int size;
    private int maxSize;

    public MapCellList() {
        this.mcArr = new MapCell[1];
        this.size = 0;
        this.maxSize = 1;
    }

    /**
     * Add a new MapCell instance to this list.
     *
     * @param mc MapCell to be added
     */
    public void add(MapCell mc) {
        if (this.size >= this.maxSize) {
            this.maxSize *= 2;
            MapCell[] newMcArr = new MapCell[this.maxSize];
            copyMcArrTo(newMcArr);
            this.mcArr = newMcArr;
        }
        this.mcArr[size] = mc;
        this.size++;
    }

    public MapCell remove(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        MapCell mc = this.mcArr[i];
        this.mcArr[i] = null;
        this.size--;
        int newMaxSize = this.size < (this.maxSize / 2) ? (this.maxSize / 2) : this.maxSize;
        if (i != this.size || newMaxSize != this.maxSize) {
            if (newMaxSize < this.maxSize) {
                this.maxSize /= 2;
            }
            MapCell[] newMcArr = new MapCell[newMaxSize];
            copyMcArrTo(newMcArr);
            this.mcArr = newMcArr;
        }
        return mc;
    }

    /**
     * Return MapCell from index i in this list. If there is no MapCell in such
     * index, return null.
     *
     * @param i Index to get MapCell from
     * @return MapCell at index i
     */
    public MapCell get(int i) {
        if (i < 0 || i >= this.size || !(this.mcArr[i] instanceof MapCell)) {
            return null;
        }
        return this.mcArr[i];
    }

    /**
     * @return current size of this list
     */
    public int size() {
        return this.size;
    }

    /**
     * Copies the current map cell array to a new one. Also rearrange so that
     * the new array will not have gaps between existing MapCells.
     */
    private void copyMcArrTo(MapCell[] newMcArr) {
        for (int i = 0, i2 = 0; i < this.mcArr.length; i++, i2++) {
            if (!(this.mcArr[i] instanceof MapCell)) {
                i2--;
                continue;
            }
            newMcArr[i2] = this.mcArr[i];
        }
    }
}
