/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import mythril.apps.TC;
import org.junit.Test;

import java.util.ArrayList;

public class AppTest {

    @Test public void testTC() {
        DefaultGraph graph = new DefaultGraph();
        Driver driver = new Driver(graph);
        ArrayList<TC> seeds = new ArrayList<>();
        for(int i=0;i<graph.size;i++){
            seeds.add(new TC(graph.getVertexId(i)));
        }
        int agg = driver.run(seeds);
        System.out.println("Total Aggregate returned" + agg);
    }
}
