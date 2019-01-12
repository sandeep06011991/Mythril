package mythril;
import obj.Vertex;

public class DefaultGraph extends Graph{

    int size = 4;

    int[][] adjList=new int[][]{
            {1,2,3},
            {0,2},
            {0,1},
            {0}
    };

    @Override
    Vertex getVertexId(int vid) {
        assert vid<size;
        return new Vertex(vid,adjList[vid]);
    }

    @Override
    int getSize() {
        return size;
    }


}
