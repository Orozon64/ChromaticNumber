import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static ArrayList<GraphNode> nodes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner myScn = new Scanner(System.in);
        boolean should_stop = false;
        int nodekey;
        do{
            System.out.println("Chcesz dodać nowy wierzchołek (wpisz 0) czy dodać nową krawędź do istniejącego wierzchołka (wpisz 1)?");
            int action_response = Integer.parseInt(myScn.nextLine());
            switch (action_response){
                case 0:
                    System.out.println("Podaj klucz wierzchołka");
                    nodekey = Integer.parseInt(myScn.nextLine());
                    GraphNode n1 = new GraphNode(nodekey);
                    nodes.add(n1);
                    Connect(n1);
                    break;
                case 1:
                    System.out.println("Podaj klucz wierzchołka");
                    nodekey = Integer.parseInt(myScn.nextLine());
                    GraphNode node_with_new_con = new GraphNode(-99);
                    for (GraphNode gn: nodes){
                        if (gn.key == nodekey){
                            node_with_new_con = gn;
                        }
                    }
                    if(node_with_new_con.key != -99){
                        Connect(node_with_new_con);
                    }

            }

            System.out.println("Chcesz dalej modyfikować graf? 0 - tak, 1 - nie");
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
        System.out.println("Liczba chromatyczna tego grafu to " + GraphColoring());
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


    static int GraphColoring(){ //Użyjemy zmodyfikowanej wersji algorytmu zachłannego. Jeśli jakiś kolor jest już w grafie - użyjemy go.

        color_list.add("red");
        color_list.add("blue");
        color_list.add("white");
        color_list.add("black");
        color_list.add("orange");
        color_list.add("green");
        int chromatic_num = 0;
        ArrayList<String> colors_in_graph = new ArrayList<>();
        colors_in_graph.add("no_color");
        ArrayList<String> colors_of_neighbors = new ArrayList<>();
        int node_index = 0;
        for(GraphNode gn : nodes){

            if(node_index == 0){
                String node_color = color_list.get(0);
                gn.color = node_color;

                colors_in_graph.add(node_color);
                color_list.remove(node_color);
                chromatic_num++;
                System.out.println("Liczba chromatyczna wzrasta dla wierzchołka o kluczu " + gn.key);
            }
            else{
                for(GraphEdge ge : gn.edges){
                    colors_of_neighbors.add(ge.nodeToConnect.color);
                }
                if(!HasCommonElements(colors_in_graph, colors_of_neighbors)){
                    gn.color = colors_in_graph.get(0);

                }
                else {
                    String node_color = color_list.get(0);
                    gn.color = node_color;

                    colors_in_graph.add(node_color);
                    color_list.remove(node_color);
                    chromatic_num++;
                    System.out.println("Liczba chromatyczna wzrasta dla wierzchołka o kluczu " + gn.key);
                }
            }
            node_index++; //pozostaw to na dole
            colors_of_neighbors.clear();
        }
        return chromatic_num;
    }

    static boolean HasCommonElements(ArrayList<String> ar1, ArrayList<String> ar2){
        boolean result = false;
        int max = 0;
        if(ar1.size() >= ar2.size()){
            max = ar2.size();
        }
        else {
            max = ar1.size();
        }
        Collections.sort(ar1);
        Collections.sort(ar2);
        for(int i = 0; i <= max - 1; i++){
            String element1 = ar1.get(i);
            String element2 = ar2.get(i);
            if(element1.equals(element2)){
                result = true;
            }
        }
        return result;
    }
}
