import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<GraphNode> nodes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner myScn = new Scanner(System.in);
        boolean should_stop = false;
        do{
            System.out.println("Podaj id wierzchołka");
            int nodekey = Integer.parseInt(myScn.nextLine());
            GraphNode n1 = new GraphNode(nodekey);
            nodes.add(n1);
            Connect(n1);
            System.out.println("Dodać jescze jakieś węzły? 0 - tak, 1 - nie");
            int continue_response = Integer.parseInt(myScn.nextLine());
            switch (continue_response){
                case 0:
                    break;
                case 1:
                    should_stop = true;
                    break;

            }
        }while (!should_stop);

        for (GraphNode no: nodes){
            System.out.println("Obecny węzeł ma klucz równy " + no.key);
            System.out.println("Jest powiązany z:");
            for(GraphEdge ge: no.edges){
                System.out.println("Węzłem o kluczu " + ge.nodeToConnect.key + " połączeniem o wadze " + ge.weight);
            }
        }
    }
    public static void Connect(GraphNode con_node){
        Scanner myScn = new Scanner(System.in);
        boolean connector_exists = false;
        System.out.println("Podaj wagę połączenia");
        int edge_weight = Integer.parseInt(myScn.nextLine());
        System.out.println("Podaj id wierzchołka, z jakim chcesz połączyć ten wierzchołek.");
        int connector_id = Integer.parseInt(myScn.nextLine());
        for(GraphNode gn : nodes){
            if(gn.key == connector_id){
                GraphEdge myedge = new GraphEdge(edge_weight, gn);
                con_node.edges.add(myedge);
                GraphEdge con_edge = new GraphEdge(edge_weight, con_node);
                gn.edges.add(con_edge);
                connector_exists = true;
            }

        }
        if(!connector_exists){
            GraphNode con = new GraphNode(connector_id);
            nodes.add(con);
            GraphEdge myedge = new GraphEdge(edge_weight, con);
            con_node.edges.add(myedge);
            GraphEdge con_edge = new GraphEdge(edge_weight, con_node);
            con.edges.add(con_edge);
        }

    }
    static ArrayList<String> color_list = new ArrayList<>();


    static void GraphColoring(){
        color_list.add("red");
        color_list.add("blue");
        color_list.add("white");
        color_list.add("black");
        color_list.add("orange");
        color_list.add("green");
        int n = nodes.size();
        ArrayList<String> colors_in_graph = new ArrayList<>();
        for(GraphNode gn : nodes){
            int node_index = 0;
            if(node_index == 0){
                String node_color = color_list.get(0);
                gn.color = node_color;

                colors_in_graph.add(node_color);
                color_list.remove(node_color);
            }
        }
    }
}