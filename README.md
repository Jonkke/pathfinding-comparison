# pathfinding-comparison

A project conducted as part of a university course. Used to test pathfinding algorithm effectiveness on a grid-like 2D map.

## Setup & Usage instructions

This project uses Java 8 and Gradle 5. Make sure you have those installed. Then clone this repository.
To build the project, `cd` to the "pathfinding-comparison" folder, then run `gradle build`. This will create a .jar file in build/libs folder, that you can use to run the application.

The usage of the app itself is quite straightforward. You simply load a map (or use the default empty map), then pick start and end points on the map, and the currently selected algorithm will find the shortest available path. You can choose to show or hide the searched area, and for A* algorithm you can choose to use a cross product tie-breaking extension (not 100% working, as it may not give optimal route). The details of the last search are shown in the control panel.

There are some maps included with the project that you can try, or you can download new ones from the [Moving AI Labs 2D Pathfinding Benchmarks site](https://movingai.com/benchmarks/grids.html). Drawing maps is currently not supported.

## Documentation

### [Project definition](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/definition.md)
### [Implementation](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/implementation.md)
### [Testing document](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/testing_document.md)
### [Performance documentation](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/performance.md)

Most of the project (GUI is not, as it's not part of the core functionality) is javadoc compatible. You can generate javadoc with `gradle javadoc` in the "pathfinding-comparison" folder.

## Weekly reports

 * [Weekly report #1](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/Weekly_report_1.md)
 * [Weekly report #2](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/Weekly_report_2.md)
 * [Weekly report #3](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/Weekly_report_3.md)
 * [Weekly report #4](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/Weekly_report_4.md)
 * [Weekly report #5](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/Weekly_report_5.md)
 * [Weekly report #6](https://github.com/Jonkke/pathfinding-comparison/blob/master/documentation/Weekly_report_6.md)
