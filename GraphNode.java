import java.util.ArrayList;
import java.util.Scanner;

public class GraphNode {
    int key;
    ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();
    String color;
    public GraphNode(int id){
        key = id;
    }

}