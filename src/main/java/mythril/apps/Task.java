package mythril.apps;

import obj.Vertex;
import java.util.ArrayList;
import java.util.HashMap;

/* Interface extended by all applications such as Triangle Counting, Clique.
* Features:
* 1. Compute: function should be able to write tasks to disk between steps.
* 2. Compute: returns when it  gets needs a remote vertex or has finished computation
* Regarding remote vertex.
* 1. During a step: add a vertex in next step.
* 2. Before fetch: get an int[] of all vertices to be fetched
* 3. After fetch: */
public abstract class Task {

//  each step of execution of tasks
    int step = 0;
    int id;

    ArrayList<Integer> toFetch = new ArrayList<>();
    HashMap<Integer, ArrayList<Vertex>> exploredVertices = new HashMap<>();

// FixME: Analyze memory profile for each task
// FixMe: Convert int arrays to fixed size
    Task(Vertex vid){
        this.id = vid.getVertexId();
        ArrayList<Vertex> t = new ArrayList<>();
        t.add(vid);
        exploredVertices.put(step,t);
    }
//  Runs till it either encounters a remove vertex or is finished
    abstract public void compute();

    abstract public boolean allDone();

    abstract public int getAgg();

    public ArrayList<Integer> toFetch() {
      return toFetch;
    }

    public void refreshToFetch(){
        toFetch.clear();
    }

    public void addToFetch(int i){
        toFetch.add(i);
    }

    public void removeFromFetch(Vertex vid){
        if(!exploredVertices.containsKey(step)){
            exploredVertices.put(step,new ArrayList<>());
        }
        for(Integer obj:toFetch){
            if(obj.intValue() ==vid.getVertexId()){
                exploredVertices.get(step).add(vid);
                toFetch.remove(obj);
                return;
            }
        }
    }
}
