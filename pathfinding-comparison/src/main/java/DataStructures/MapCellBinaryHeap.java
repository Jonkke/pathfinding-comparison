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
package DataStructures;

import domain.MapCell;

/**
 * This class is a container for the minimum binary heap data structure. In
 * short, it functions like Java's default PriorityQueue data structure,
 * allowing fast retrieval of the smallest element in the heap, in O(1) time.
 * Removing and adding of new elements can be completed in O(log n) time. This
 * implementation is not generic, and can only handle MapCell classes as it's
 * elements.
 *
 * @author Jonkke
 */
public class MapCellBinaryHeap {

    private MapCell[] heap;
    private int lastFreeIndex;

    public MapCellBinaryHeap() {
        this.heap = new MapCell[16];
        this.lastFreeIndex = 1;
    }

    /**
     * Add a new MapCell object to this minimum binary heap
     *
     * @param mc
     */
    public void add(MapCell mc) {
        this.heap[this.lastFreeIndex] = mc;
        int i = lastFreeIndex;
        while ((i / 2) > 0 && this.heap[i].priority < this.heap[i / 2].priority) {
            MapCell temp = this.heap[i / 2];
            this.heap[i / 2] = this.heap[i];
            this.heap[i] = temp;
            i = i / 2;
        }
        this.lastFreeIndex++;
        if (this.lastFreeIndex > this.heap.length - 1) {
            doubleHeapMem();
        }
    }

    /**
     * Return and remove from heap the MapCell object at the head of this binary
     * heap.
     *
     * @return MapCell
     */
    public MapCell poll() {
        MapCell first = this.heap[1];
        if (this.lastFreeIndex > 1) {
            this.lastFreeIndex--;
            this.heap[1] = this.heap[this.lastFreeIndex];
            this.heap[lastFreeIndex] = null;
        }
        int i = 1;
        MapCell curr = this.heap[i];
        MapCell leftChild = this.heap.length > i * 2 ? this.heap[i * 2] : null;
        MapCell rightChild = this.heap.length > i * 2 + 1 ? this.heap[i * 2 + 1] : null;
        while (leftChild instanceof MapCell) {
            if (rightChild instanceof MapCell
                    && rightChild.priority < curr.priority
                    && rightChild.priority < leftChild.priority) {
                this.heap[i] = rightChild;
                this.heap[i * 2 + 1] = curr;
                i = i * 2 + 1;
            } else if (leftChild.priority < curr.priority) {
                this.heap[i] = leftChild;
                this.heap[i * 2] = curr;
                i = i * 2;
            } else {
                break;
            }
            curr = this.heap[i];
            leftChild = this.heap.length > i * 2 ? this.heap[i * 2] : null;
            rightChild = this.heap.length > i * 2 + 1 ? this.heap[i * 2 + 1] : null;
        }
        if (this.lastFreeIndex < this.heap.length / 2) {
            this.splitHeapMem();
        }
        return first;
    }

    /**
     * Return, but do not remove the MapCell object at the head of this binary
     * heap.
     *
     * @return MapCell
     */
    public MapCell peek() {
        return this.heap[1];
    }

    /**
     * Returns true if empty, false if not
     */
    public boolean isEmpty() {
        return this.heap[1] == null;
    }

    private void doubleHeapMem() {
        MapCell[] newHeap = new MapCell[2 * this.heap.length];
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }

    private void splitHeapMem() {
        int splitLength = this.heap.length / 2;
        MapCell[] newHeap = new MapCell[splitLength];
        for (int i = 0; i < splitLength; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }
}
