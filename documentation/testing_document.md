# Testing documentation

This document describes the various ways this project has been / will be tested.

## Unit tests
A suite of unit tests has been written for the core application and the supporting custom data structures. These unit tests are not yet completed and will still be improved before the final version.

## Components

### GUI
No individual testing has been done on the GUI, as this is not part of the core functionality. Has been manually tested while building it...

### Data structures
A small but sufficient suite of unit test has been written for the MapCellBinaryHeap and MapCellList data structures.

### Map classes
So far, only the main Map class has some unit tests, with likely some more to follow. Some unit tests will be written for the supporting map components MapCell and MapCellEdge. They have also been verified to be working correctly with the main algorithms and GUI components that utilize them.

### Algorithms
Dijkstra and AStar algorithm also have some basic unit tests written for them. Possibly more to come.

## Performance testing
Performance testing has not been implemented yet. It will be usable from within the graphical user interface.

## Code & testing coverage
Still to be implemented...
