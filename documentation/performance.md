# Performance testing documentation

This document covers a short overview on the performances of Dijkstra and A* algorithm, and how they compare to each other. All data below is dervied from the GUI of the pathfinding-comparison application. Several different maps have been tested, and under each map's name a short summary can be found describing the start and end points of the route, how many cells were traversed in total, what is the total cost of the route, how many cells were searched in total and of course, the time it took to complete the route search (in milliseconds).

## Tested maps
All tested maps can be found within the project directory.

Format of individual runs: start and end positions: (x1,y1)->(x2,y2), cells traversed, total cost, total cells searched, time in ms. DI=Dijkstra, A*=AStar

### Empty map, 1024 x 1024
Run 1 (DI): (4,3)->(1015,1017), 2025, 3039, 1048468, 229ms
Run 1 (A*): (4,3)->(1015,1017), 2025, 3039, 1042879, 309ms
Run 2 (DI): (4,3)->(992,13), 998, 1008, 505553, 100ms
Run 2 (A*): (4,3)->(992,13), 998, 1008, 17807, 5ms

### arena2.map
Run 1 (DI): (92,5)->(23,97), 277, 398, 17753, 4ms
Run 1 (A*): (92,5)->(23,97), 277, 398, 5505, 3ms
Run 2 (DI): (92,5)->(279,178), 372, 516, 23868, 10ms
Run 2 (A*): (92,5)->(279,178), 372, 516, 7188, 4ms

### brc202d.map
Run 1 (DI): (119,51)->(468,469), 1013, 1439, 41195, 14ms
Run 1 (A*): (119,51)->(468,469), 1013, 1439, 30632, 7ms
Run 2 (DI): (98, 259)->(251,398), 1110, 1750, 43081, 10ms
Run 2 (A*): (98, 259)->(251,398), 1110, 1750, 28844, 6ms

### brc000d.map
Run 1 (DI): (21,52)->(68,132), 423, 574, 27235, 5ms
Run 1 (A*): (21,52)->(68,132), 423, 574, 18229, 4ms
Run 2 (DI): (35, 78)->(211,62), 228, 269, 18433, 4ms
Run 2 (A*): (35, 78)->(211,62), 228, 269, 4150, 2ms

### dustwallowkeys.map \*
Run 1 (DI): (85,87)->(439, 403), 728, 1387, 181570, 35ms
Run 1 (A*): (85,87)->(439, 403), 728, 1279, 97138, 25ms

\*maps with water materials cause some oddities with this implementation, causing Dijkstra to find paths with greater than optimal costs.

In general, it can be seen that the A* algorithm is generally more effective both time-wise and memory-wise (considering how many cells need to be searched). However, the difference in completion time is not necessarily very drastic, even though the amount of searched cells could be substantially lower. This may be in part due to implementation details, but the difference is still clearly visible.
