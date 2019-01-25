# Project definition

This project will be used to implement and compare at least two (possibly more) pathfinding algorithms in a map (represented as a two-dimensional array of nodes), that can be used to find the shortest route between two nodes (or points on a map). The graph / map can also be weighted. In practice, this means that crossing from one node to another may be more expensive in some areas of a map than others. For example, if we imagine that the map is part of a game world, we could add a water area that is slower to cross than a land area or a bridge.

In addition to the underlying algorithms and data structures, the intention is to crete a graphical user interface, in which the user can either load ready-made map from a file (such as those found at the [Moving AI Lab](https://movingai.com/benchmarks/grids.html)) or create randomly generated maps, that can be used to test and illustrate the progression and resulting routes of the different pathfinding algorithms. The maps can also be drawn by hand, so that the user can create corner cases to see how the different algorithms perform in those. The main goal is to compare the speed, accuracy and overall effectiveness of the tested algorithms in various situations.

## Used algorithms and data structures

At least Dijkstra's algorithm and the more advanced A* algorithm (possibly with the jump-point search optimization) will be implemented and tested. All the necessary underlying data structures will be created from scratch as well, such as classes for maps, map nodes, routes on a map (may be weighted) and the like. Basic data structures, such as lists or stack/queue implementations will be created as well, as necessary.

## Time complexity & Space complexity

The target time complexities for the algorithms will be those calculated for Dijkstra and A* algorithms (possibly others with their own respective time complexities...). That is, the worst-case performance of O(E + V\*log(V)) for Dijkstra, and the same for A\* (in the case that every node on the map needs to be searched).

The space requirement will be O(E) for storing the map in memory. The algorithms themselves will also need to store the map in memory for processing (list of node edges in a heap for Dijkstra, for example).

## Input / Output

### Input
 * Maps can be loaded in to the app from text files

### Output
 * Graphical user interface / CLI interface will simulate the progression of an algorithm
 * Diagnostic data and benchmark results will be outputted

## Conclusion

The above forms the basis for the project. This page may still live, and illustrative diagrams may be added later on.
