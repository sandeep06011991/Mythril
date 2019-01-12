package mythril;
import mythril.apps.Task;
import obj.Vertex;

import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class RemoteQueue<ATask extends Task> extends Thread{

    BlockingDeque<ATask> toFetch = new LinkedBlockingDeque<>();
    ConcurrentHashMap<Integer,int[]> remoteVertexToPartitionMap = new ConcurrentHashMap<>();
    final int pc = 2;
    TaskPartition[] partitions = new TaskPartition[pc];
    Graph graph;

    RemoteQueue(Graph graph,ComputeQueue computeQueue){
        this.graph = graph;
        for(int i=0;i<partitions.length;i++){
            partitions[i] = new TaskPartition(computeQueue);
        }
        new PartitionContainer(partitions).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                flowVertexesToPartitions();
            }
        }).start();
    }
    // FixMe: come up with an elegant kill switch
    public void addTaskToQueue(ATask task){
        toFetch.addLast(task);
    }

    Random random = new Random();
    @Override
    public void run() {
        try{
        while(true){
            ATask task = toFetch.take();
            while(true)
            {   int choice= random.nextInt(pc);
                if(partitions[choice].tryLock()){
                    for(int rv: task.toFetch()){
                        if(!remoteVertexToPartitionMap.containsKey(rv)){
                            remoteVertexToPartitionMap.put(rv,new int[]{0,0});
                        }
                        int[] ar = remoteVertexToPartitionMap.get(rv);
                        ar[choice] = 1;
                    }
                    partitions[choice].tasks.add(task);
                    partitions[choice].unlock();
                    break;
                }
            }
        }
        }catch(Exception e){
            System.out.println("Remote Queue Failure");
            System.out.println(e);
        }
    }

//  Seperate Thread which flows chunks into partitions
    public void flowVertexesToPartitions(){
        while(true){
            Enumeration<Integer> key = remoteVertexToPartitionMap.keys();
//          Fixme: clean up hashmap
            while(key.hasMoreElements()){
                int k = key.nextElement();
                int[] ar = remoteVertexToPartitionMap.get(k);
                Vertex v = graph.getVertexId(k);
                for(int i=0;i<pc;i++){
                    if(ar[i]==1){
                        partitions[i].vertexes.add(v);
                        ar[i]=0;
                    }
                }
            }
        }
    }
}
