package mythril;
import mythril.apps.Task;

import java.util.ArrayList;

public class Driver<ATask extends Task> {

    ComputeQueue cq;
    RemoteQueue mq;

    Driver(Graph graph){
        cq = new ComputeQueue();
        this.mq = new RemoteQueue(graph,cq);
        cq.setQueue(mq);
    }

    public int run(ArrayList<ATask> seedTasks){
//      FIXME: Iterate over Graph and start tasks
        this.mq.start();
        cq.addTaskToQueue(seedTasks);
        return cq.run();
    }
}
