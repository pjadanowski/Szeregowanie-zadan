import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static final int M = 4;

    // liczniki do umieszczania w maszynach
    public static int I = 0;
    public static int J = 0;

    public static List<Node> listcopy;

    public static void main(String[] args) {

        Node[][] P = new Node[M][12];
        for (int j = J; j < P[0].length; j++) {
            for (int i = I; i < P.length; i++) {
                P[i][j] = new Node("   ", 0, null);
            }
        }

        List<Node> list = init2();
        algorithm(list, P);
        L(listcopy, P);

        findL_max(listcopy);
//        listcopy.forEach(s -> {
//            System.out.println(s.name + " " + s.L);
//        });

//        printArray(P);
        Draw.draw(listcopy);
    }


    public static List<Node> init() {
        List<Node> list = new ArrayList<>();
        Node Z1 = new Node("Z1", 16, null);
        Node Z2 = new Node("Z2", 20, Z1);
        Node Z3 = new Node("Z3", 4, Z1);
        Node Z4 = new Node("Z4", 3, Z3);
        Node Z5 = new Node("Z5", 15, Z2);
        Node Z6 = new Node("Z6", 14, Z1);
        Node Z7 = new Node("Z7", 17, Z6);
        Node Z8 = new Node("Z8", 6, Z7);
        Node Z9 = new Node("Z9", 6, Z8);
        Node Z10 = new Node("Z10", 4, Z9);
        Node Z11 = new Node("Z11", 10, Z5);
        Node Z12 = new Node("Z12", 8, Z5);
        Node Z13 = new Node("Z13", 9, Z12);
        Node Z14 = new Node("Z14", 7, Z13);
        Node Z15 = new Node("Z15", 10, Z11);
        Node Z16 = new Node("Z16", 9, Z15);
        Node Z17 = new Node("Z17", 10, Z16);
        Node Z18 = new Node("Z18", 8, Z17);
        Node Z19 = new Node("Z19", 2, Z18);
        Node Z20 = new Node("Z20", 3, Z19);
        Node Z21 = new Node("Z21", 6, Z18);
        Node Z22 = new Node("Z22", 5, Z21);
        Node Z23 = new Node("Z23", 4, Z22);
        Node Z24 = new Node("Z24", 11, Z5);
        Node Z25 = new Node("Z25", 12, Z24);
        Node Z26 = new Node("Z26", 9, Z25);
        Node Z27 = new Node("Z27", 10, Z26);
        Node Z28 = new Node("Z28", 8, Z27);
        Node Z29 = new Node("Z29", 7, Z28);
        Node Z30 = new Node("Z30", 5, Z29);
        Node Z31 = new Node("Z31", 3, Z29);
        Node Z32 = new Node("Z32", 5, Z31);

        list.add(Z1);
        list.add(Z2);
        list.add(Z3);
        list.add(Z4);
        list.add(Z5);
        list.add(Z6);
        list.add(Z7);
        list.add(Z8);
        list.add(Z9);
        list.add(Z10);
        list.add(Z11);
        list.add(Z12);
        list.add(Z13);
        list.add(Z14);
        list.add(Z15);
        list.add(Z16);
        list.add(Z17);
        list.add(Z18);
        list.add(Z19);
        list.add(Z20);
        list.add(Z21);
        list.add(Z22);
        list.add(Z23);
        list.add(Z24);
        list.add(Z25);
        list.add(Z26);
        list.add(Z27);
        list.add(Z28);
        list.add(Z29);
        list.add(Z30);
        list.add(Z31);
        list.add(Z32);


        return list;
    }


    public static List<Node> init2() {
        List<Node> list = new ArrayList<>();
        Node Z2 = new Node("Z2", 20, null);
        Node Z1 = new Node("Z1", 16, Z2);
        Node Z5 = new Node("Z5", 4, null);
        Node Z3 = new Node("Z3", 6, Z5);
        Node Z4 = new Node("Z4", 2, Z5);

        list.add(Z1); list.add(Z2); list.add(Z3); list.add(Z4); list.add(Z5);
        return  list;
    }

    public static void algorithm(List<Node> list, Node[][] P) {

        checkCorrect(list);

        // find last node
        Node last = null;
        Optional<Node> lastOp = Node.findLast(list);
        if (lastOp.isPresent()) {
            last = lastOp.get();
            last.d_s = 1 - last.d;
        }


        for (int i = 0; i < list.size(); i++) {
            for (Node node : list) {
                if (node.nextNode != null && !node.equals(last)) {
                    if (node.nextNode.d_s != -9999) { //tzn został ustawiony
                        // policz d_s jako max{1+d_s.next, 1-d}
                        node.d_s = Math.max(1 + node.nextNode.d_s, 1 - node.d);
                    }
                }
            }
        }
//        listcopy = list;
        listcopy = new ArrayList<>(list);
        while (list.size() > 0) {
//        for (int i = 0; i < 20; i++) {
            List<Node> withoutPrev = findWithoutPrev(list);
            putIntoMachines(list, withoutPrev, P, M);
        }

    }

    private static IllegalArgumentException checkCorrect(List<Node> list) {
        // new list to store nodes one by one
        List<Node> listCopy = new ArrayList<>();

        for (Node node : list) {
            if (node.nextNode != null) {
                if (listCopy.contains(node.nextNode)) {
                    return new IllegalArgumentException("Wykryto cyklicznosc");
                }
                listCopy.add(node.nextNode);
            }
        }

        return null;
    }


    // znajdz te bez poprzednikow i posortuj je wg d_s
    public static List<Node> findWithoutPrev(List<Node> list) {
        List<Node> result = new ArrayList<>();

        // znajdz te ktore nie maja poprzednikow
        for (Node node : list) {
            if (node.findPrev(list) == null) {
                result.add(node);
            }
        }
        // posortuj
        result.sort((o1, o2) -> o2.d_s - o1.d_s);
//        result.forEach(s -> System.out.println(s.name + " " + s.d_s));
        int min = Math.min(result.size(), M);

//        if (result.size() >= M){
//            return result.subList(0,M);
//        } else{
//            for (int i = 0; i < M - result.size(); i++) {
//                    result.add(new Node("   ", 0, null));
//            }
//        }

        return result.subList(0, min);
//        return result;
    }


    public static void putIntoMachines(List<Node> list, List<Node> result, Node[][] P, int m) {
        // umiesc w maszynach
        int k = 0;

        while (k < result.size()) {
            try {
                P[I][J] = result.get(k); //wrzuc do tablicy
            } catch (IndexOutOfBoundsException e) {
            }
            result.get(k).C = J;
            list.remove(result.get(k)); // usun obiekt z listy
            k++;
            I++;
        }

        I = 0;
        J++;


        printArray(P);
    }

    public static void printArray(Node[][] P) {
        Stream.of(P).map(Arrays::toString).forEach(System.out::println);
        System.out.println();
    }

    public static void L(List<Node> list, Node[][] P) {
        for (Node node : list) {
            for (int i = 0; i < P.length; i++) {
                for (int j = 0; j < P[0].length; j++) {
                    if (node.name.equals(P[i][j].name)) {
                        node.L = j + 1 - node.d;
                    }
                }
            }

        }
    }

    public static void findL_max(List<Node> list) {
        Optional<Node> max = list.stream().max((o1, o2) -> o1.L - o2.L);
        System.out.println("L_max*=" + max.get().L);
    }

}
