package mythril;
import mythril.apps.Task;
import obj.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public class TaskPartition extends ReentrantLock{

    ArrayList<Task> tasks = new ArrayList<>();

    ArrayList<Vertex> vertexes = new ArrayList<>();

    ComputeQueue<Task> computeQueue;

    public TaskPartition(ComputeQueue computeQueue){
        this.computeQueue = computeQueue;
    }

    void mapVertexesToTasks(){
        HashMap<Integer,ArrayList<Task>> map = new HashMap<>();
        if(vertexes.size()==0)return;
        for(Task task:tasks){
            for(int j:task.toFetch()){
                if(map.containsKey(j)){
                    map.get(j).add(task);
                }else{
                    ArrayList<Task> n = new ArrayList<>();
                    n.add(task);
                    map.put(j,n);
                }
            }
        }
//      This will be made sequential access
        for(Vertex v: vertexes){
            if(map.containsKey(v.getVertexId())){
                ArrayList<Task> targetTasks = map.get(v.getVertexId());
                for(Task task: targetTasks){
                    task.removeFromFetch(v);
                }
            }
        }
//      At the end dump loaded tasks to compute Queue
        Iterator<Task> it = tasks.iterator();
        while(it.hasNext()){
            Task task = it.next();
            if(task.toFetch().size()!=0)continue;
            it.remove();
            computeQueue.addTaskAfterFetching(task);
         }
    }
}
