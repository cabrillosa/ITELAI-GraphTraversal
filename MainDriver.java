public class MainDriver {
    public static void main(String[]args){
        GraphTraversal gt = new GraphTraversal();

            gt.addPlace("arad", 366);
            gt.addPlace("bucharest", 0);
            gt.addPlace("craiova", 160);
            gt.addPlace("dobreta", 242);
            gt.addPlace("eforie", 161);
            gt.addPlace("fagaras", 178);
            gt.addPlace("giurgiu", 77);
            gt.addPlace("hirsova", 151);
            gt.addPlace("Iasi", 226);
            gt.addPlace("lugoj", 244);
            gt.addPlace("mehadia", 241);
            gt.addPlace("neamt", 234);
            gt.addPlace("oradea", 380);
            gt.addPlace("pitesti", 98);
            gt.addPlace("RV", 193);
            gt.addPlace("sibiu", 253);
            gt.addPlace("timisoara", 329);
            gt.addPlace("urziceni", 80);
            gt.addPlace("vaslui", 199);
            gt.addPlace("zerind", 374);


            gt.connect("arad", "zerind", 75);
            gt.connect("arad", "timisoara", 118);
            gt.connect("arad", "sibiu", 140);

            gt.connect("zerind", "oradea", 71);

            gt.connect("oradea", "sibiu", 151);

            gt.connect("sibiu", "fagaras", 99);
            gt.connect("sibiu", "RV", 80);

            gt.connect("fagaras", "bucharest", 211);

            gt.connect("bucharest", "pitesti", 101);
            gt.connect("bucharest", "giurgiu", 90);
            gt.connect("bucharest", "urziceni", 85);

            gt.connect("urziceni", "vaslui", 142);
            gt.connect("urziceni", "hirsova", 98);

            gt.connect("hirsova", "eforie", 86);

            gt.connect("vaslui", "Iasi", 92);

            gt.connect("Iasi", "neamt", 87);

            gt.connect("pitesti", "RV", 97);
            gt.connect("pitesti", "craiova", 138);

            gt.connect("craiova", "RV", 146);
            gt.connect("craiova", "dobreta", 120);

            gt.connect("dobreta", "mehadia", 75);

            gt.connect("mehadia", "lugoj", 70);

            gt.connect("lugoj", "timisoara", 111);

            System.out.println("GBFS result:");
            gt.greedyBestFirstSearch("arad", "bucharest");
            
            System.out.println("A* result:");
            gt.aStar("arad", "bucharest");



    }
}
