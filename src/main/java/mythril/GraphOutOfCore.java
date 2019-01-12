package mythril;
import java.io.*;
import java.util.ArrayList;

// FixMe: Efficient Indexer
public class GraphOutOfCore {
    int limit = 100000000;
    void indexGraph(String filename) throws FileNotFoundException, IOException {
        ArrayList<String> ls = new ArrayList<String>();
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        for(int i=0;i<limit;i++){
            ls.add(br.readLine());
        }
    }
    public static void main(String args[]) throws Exception{
        new GraphOutOfCore().indexGraph("dataset/com-orkut.ungraph.txt");
    }
}

