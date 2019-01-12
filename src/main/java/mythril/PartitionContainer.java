package mythril;
import mythril.apps.Task;

import java.util.concurrent.TimeUnit;

public class PartitionContainer<ATask extends Task> extends Thread{

    TaskPartition[] tps;

    PartitionContainer(TaskPartition[] tps){
        this.tps = tps;
    }

    @Override
    public void run() {
//      FixMe: and put this into the compute queue
        try{
            while(true){
                for(TaskPartition tp:tps){
                    if(tp.tryLock()){
                        tp.mapVertexesToTasks();
                        tp.unlock();
                    }
                }
                TimeUnit.SECONDS.sleep(1);
            }
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Partition Container was interrupted and will exit");
        }
    }
}
