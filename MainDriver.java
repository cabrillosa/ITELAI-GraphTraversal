public class MainDriver {
    public static void main(String[]args){
        GraphTraversal gt = new GraphTraversal();
        gt.addPlace("A");
        gt.addPlace("B");
        gt.addPlace("C");
        gt.addPlace("D");
        gt.addPlace("E");
        gt.addPlace("F");
        gt.addPlace("G");
        gt.addPlace("H");

        gt.connect("A", "B");
        gt.connect("A", "C");
        gt.connect("A", "D");
        gt.connect("B", "E");
        gt.connect("C", "F");
        gt.connect("D", "G");
        gt.connect("E", "H");
        // gt.connect("F", "H");

        gt.breadthFirstSearch("A", "H");
        // gt.addPlace("Cebu");
        // gt.addPlace("Talisay");
        // gt.addPlace("Minglanilla");
        // gt.addPlace("Naga");
        // gt.addPlace("Mandaue");
        // gt.addPlace("Cordova");
        // gt.addPlace("Ubos sa 2nd bridge");


        // gt.connect("cebu", "talisay");
        // gt.connect("cebu", "Mandaue");
        // gt.connect("Ubos sa 2nd bridge", "Mandaue");
        // gt.connect("Ubos sa 2nd bridge", "Cordova");
        // gt.connect("cebu", "Cordova");
        // gt.connect("Minglanilla", "talisay");
        // gt.connect("Minglanilla", "Naga");

        // gt.displayAdjacencyList();

        // System.out.println("BFS");
        // gt.breadthFirstSearch("cebu", "Naga");

        // System.out.println("DFS");
        // gt.depthFirstSearch("cebu","Naga");
    }
}
