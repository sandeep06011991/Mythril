Start: 2:00
Single node task based Triangle Counting.

classes:

GraphServer


Questions:

Start Graph Server 
Load seed Tasks into ComputeQueue


Graph Server -> holds the graph from which I can read adj list of vertexes. 

TaskComputeQueue->
-----------processes a Task


TaskPartition contains list of tasks, Associated adj lists. 
Partition consists of all task partitions. 

##TODO 

1. Tasks are serailizable and can be written to disk. 
2. Tasks partitions have to be made serializable.
2. Multi partition Tasks. 

## Order of things.

Fix Task Queue
Than fix compute queue
Add profilers for memory consumption and bottlenecks measurements.
