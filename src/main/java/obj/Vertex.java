package obj;

public class Vertex {

    final int vid;
    final int[] neightbours;

    public Vertex(int vid, int[] neighbours){
        this.vid = vid;
        this.neightbours = neighbours;
    }

    int getVertexId(){
        return vid;
    }

    int[] getNeighbours(){
        return this.neightbours;
    }
}
