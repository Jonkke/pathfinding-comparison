# Week 4

Used hours during week 4: 20 hours

## Events of week 4
 * More research
 * Basic GUI created, still needs work
 * Maps from MovingAILab site can now be imported into the app
 * Improved A* algorithm (added cross-product tie breaking), still needs some work
 * Wrote binary heap implementation to replace Java's PriorityQueue
 * Wrote new tests for binary heap
 
## Problems

Still been busy with other courses and stuff. Basic functionality of the app is almost done, but still needs some work. I have had some trouble getting the heuristic to work properly on A*, it still searches unnecessarily wide area in some cases (been trying to build tie-breaking into it). Have not had time to look into jump-point search, I think I'll leave it as extra thing if I have time. GUI does not have many functions yet, and I have not decided how to build those. Documentation and tests still bit lacking.

## Questions

Is it okay if my own base data structures are not generic? Currently my binary heap only supports the MapCell class, since nothing else is stored into it. Another question, is it allowed to use some Java classes in my algorithm classes to help drawing on GUI? I was trying to create a callback hook of some sort, that would allow me to update GUI as the algorithm progresses. Still need to work on that.

## Next week

TODO:
* Base data structures:
  * ArrayList
* Fix A* searching too deep
* Improve GUI
* More tests
* Focus on documentation and start looking into performance testing
* Refactoring & commenting as necessary

