package mythril;
import obj.Vertex;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class GraphInMemory extends Graph {

    int[] offsets;
    int[] edgesList;
    int nodes;
    int edges;

    GraphInMemory(String filename) throws IOException {
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//      Process nodes and offsets
//      Intial template
//      # Undirected graph: ../../data/output/orkut.txt
//      # Orkut
//      # Nodes: 3072441 Edges: 117185083
//      # FromNodeId	ToNodeId
        br.readLine();
        br.readLine();
        String[] meta = br.readLine().split(" ");
        nodes = Integer.parseInt(meta[2]);
        edges = Integer.parseInt(meta[4]);
        offsets = new int[nodes+1];
        edgesList = new int[edges];
        br.readLine();
        String curr;
        String[] temp;
        int ep = 0; int a,b;int prev = 0;
//        int np = 0;
        while((curr = br.readLine())!=null){
            temp = curr.split("\t");
            a = Integer.parseInt(temp[0]);
            b = Integer.parseInt(temp[1]);
            edgesList[ep] = b;
            while(a!=prev){
                offsets[prev] = ep;
                prev++;
            }
            ep++;
        }
        while(prev!=nodes){
            offsets[prev] = ep;
            prev++;
        }
    }

    @Override
    Vertex getVertexId(int vertexID) {
        int size = offsets[vertexID] - offsets[vertexID-1];
        if(size==0)return new Vertex(vertexID,null);
        int[] adj = new int[size];
        for(int i=0;i<size;i++){
            adj[i]= edgesList[offsets[vertexID-1]+i];
        }
        return new Vertex(vertexID,adj);
    }

    @Override
    int getSize() {
        return nodes;
    }

    public static void main(String args[]) throws Exception{
        GraphInMemory g = new GraphInMemory("dataset/com-amazon.ungraph.txt");
        Vertex v = g.getVertexId(3);
        assert g.getVertexId(3).getNeighbours()==null;
        assert g.getVertexId(5).getNeighbours().length ==4;
        v = g.getVertexId(548411);
        v = g.getVertexId(548391);
    }
}
