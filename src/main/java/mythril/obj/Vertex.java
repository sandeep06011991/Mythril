package obj;

public class Vertex {

    final int vid;
    final int[] neightbours;

    public Vertex(int vid, int[] neighbours){
        this.vid = vid;
        this.neightbours = neighbours;
    }

    public int getVertexId(){
        return vid;
    }

    public int[] getNeighbours(){
        return this.neightbours;
    }
}
