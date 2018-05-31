import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Draw {


    public static void draw(List<Node> list) {

        List<Node> listCopyWithIds = new ArrayList<>(list);
        insertIds(listCopyWithIds);

        String begin = "<html>\n" +
                "<head>\n" +
                "    <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/vis/4.21.0/vis.min.js\"></script>\n" +
                "    <link href=\"https://cdnjs.cloudflare.com/ajax/libs/vis/4.21.0/vis.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "        #mynetwork {\n" +
                "            width: 800px;\n" +
                "            height: 800px;\n" +
                "            border: 1px solid lightgray;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"mynetwork\"></div>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    // create an array with nodes\n" +
                "    var nodes = new vis.DataSet([";


        String nodes = "";
        for (Node node : listCopyWithIds) {
            nodes += "{id: " + node.id + ", label: '" + node.name + "'},\n";
        }
        nodes += "  ]);";


        // add edges
        String edges = "\n" +
                "    // create an array with edges\n" +
                "    var edges = new vis.DataSet([";

        for (Node node : listCopyWithIds) {
            if (node.nextNode != null) {
                edges += " {from: "+ node.id+", to: "+ node.nextNode.id +", arrows:'to'},\n";
            }
        }
        edges += "  ]);";


        String end = "\n" +
                "    // create a network\n" +
                "    var container = document.getElementById('mynetwork');\n" +
                "\n" +
                "    // provide the data in the vis format\n" +
                "    var data = {\n" +
                "        nodes: nodes,\n" +
                "        edges: edges\n" +
                "    };\n" +
                "    var options = {};\n" +
                "\n" +
                "    // initialize your network!\n" +
                "    var network = new vis.Network(container, data, options);\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";


        String complete = begin + nodes + edges + end;

        try {
            FileWriter fileWriter = new FileWriter("index2.html");
            fileWriter.write(complete);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertIds(List<Node> list) {
        for (Node node : list) {
            node.id = Integer.parseInt(node.name.substring(1));
//            System.out.println(node.name + ":" + node.id);
        }


    }


}
