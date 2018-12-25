package apps;


import obj.Vertex;
import java.util.HashMap;

/* Interface extended by all applications such as Triangle Counting, Clique.
* Features:
* 1. Compute function should be able to write tasks to disk between steps.
* 2. Compute: returns when it  gets needs a remote vertex or has finished computation */
public abstract class Task {

//  each step of execution of tasks
    int step;
    HashMap<Integer, Vertex[]> adjList;
    int[] toFetch;

    abstract void Task(int vertexID);

//  Runs till it either encounters a remove vertex or is finished
    abstract void compute();

    abstract boolean allDone();

    abstract int getAgg();

    abstract int[] toFetch();
}
