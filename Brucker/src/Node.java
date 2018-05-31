
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Node {

    int id;
    String name;
    int d;
//    int d_k;
    int d_s = -9999;
    int L;
    int C;

    Node nextNode = null;


    public Node(String name, int d, Node nextNode) {
        this.name = name;
        this.d = d;
        this.nextNode = nextNode;
    }

    List<Node> next = new ArrayList<>();
    List<Node> prev = new ArrayList<>();


    public Node findPrev( List<Node> list){
        List<Node> result = new ArrayList<>();

//        Predicate<Node> prev = n -> (n.nextNode.equals(this));
//        list.stream().filter(prev).forEach(n -> result.add(n));

        for (Node node : list) {
            if (node.nextNode != null && node.nextNode.equals(this) ) {
                return node;
            }
        }

        return  null;
    }

    public static Optional<Node> findLast(List<Node> list) {
        Predicate<Node> last = n -> (n.nextNode == null);
        Optional<Node> result = list.stream().filter(last).findFirst();

        return  result;
    }


    @Override
    public String toString() {
        return String.format("%3s", this.name);
//        return this.name;
    }
}
