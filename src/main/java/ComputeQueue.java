import apps.Task;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ComputeQueue<ATask extends Task> {

    BlockingDeque<ATask> queue = new LinkedBlockingDeque<>();
    RemoteQueue<ATask> rq;
    int counter=0;
    int agg=0;

    void setQueue(RemoteQueue rq){this.rq = rq;}
    public void addTaskToQueue(ArrayList<ATask> tasks){
        queue.addAll(tasks);
    }

    public synchronized void addTaskAfterFetching(ATask task){
        counter--;
        queue.addLast(task);
    }

    public int run() {
        try {

            while (counter > 0 || !queue.isEmpty()) {
                ATask task = queue.takeFirst();
                task.compute();
                if (task.allDone()) {
                    agg = agg + task.getAgg();
                } else {
                    counter++;
                    this.rq.addTaskToQueue(task);
                }
            }
            return agg;

    }catch(Exception e){
        System.out.println("Compute queue Died");
        System.out.println(e);
        }
        return 0;
    }
}
