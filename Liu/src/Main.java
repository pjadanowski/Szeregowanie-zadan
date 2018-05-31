import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    private static int time = 18;

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();

        taskList = initial();
        setDE(taskList);
        algorithm(taskList);

        return_L_max(taskList);
//        taskList.forEach(t -> System.out.println(t.name + " " + t.dE));

        // zrob kopie taskList dla rysunku
        List<Task> taskList_drawing = initial();
        setDE(taskList_drawing);
        try {
            draw(taskList_drawing);
            System.out.println("tu");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ex");
        }
    }

    private static List<Task> initial() {

        List<Task> taskList = new ArrayList<>();
        Task Z1 = new Task("Z1", 1, 3, 4, 0);
        Task Z2 = new Task("Z2", 2, 2, 6, 4);
        Task Z3 = new Task("Z3", 3, 2, 8, 2);
        Task Z4 = new Task("Z4", 4, 1, 15, 5);
        Task Z5 = new Task("Z5", 5, 4, 10, 6);
        Task Z6 = new Task("Z6", 6, 1, 20, 15);
        Task Z7 = new Task("Z7", 7, 2, 25, 13);

        Z1.next.add(Z3);
        Z1.next.add(Z5);
        Z1.next.add(Z7);
        Z2.next.add(Z4);
        Z2.next.add(Z5);
        Z2.next.add(Z6);
        Z2.next.add(Z7);
        Z3.next.add(Z5);
        Z3.next.add(Z7);
        Z4.next.add(Z5);
        Z4.next.add(Z6);
        Z4.next.add(Z7);
        Z5.next.add(Z7);
        Z6.next.add(Z7);

        taskList.add(Z1);
        taskList.add(Z2);
        taskList.add(Z3);
        taskList.add(Z4);
        taskList.add(Z5);
        taskList.add(Z6);
        taskList.add(Z7);

        return taskList;
    }

    private static void setDE(List<Task> taskList) {
        for (Task task : taskList) task.setdE();
    }


    private static void algorithm(List<Task> taskList) {

        for (int i = 0; i < time; i++) {

            List<Task> whichCanList = whichCan(taskList, i);
            List<Task> whichCanListSorted = whichCanSorted(whichCanList);

            if (whichCanListSorted.size() > 0)
            {
                Task task = whichCanListSorted.get(0);
                // znajdz index pod ktorym task na liscie
                int index = taskList.indexOf(task);

                System.out.print(task.name + " ");
                taskList.get(index).p = --taskList.get(index).p;
                // zmien started na true
                taskList.get(index).started = true;
                // zapisz index pod ktorym ostatecznie zakonczyl zadanie
                task.L = i - task.dE + 1;
            } else
                System.out.print("_" + " ");



        }
    }

    private static List<Task> whichCan(List<Task> taskList, int time) {
        List<Task> list = new ArrayList<>();

        for (Task task : taskList) {
            if ( task.r <= time && task.prev(taskList).size() == 0  && task.p > 0)
                list.add(task);

            else if(task.r <= time && task.prev(taskList).size() > 0 && task.p > 0){

                boolean b = true;

                for (Task t: task.prev(taskList)) {
                    if (!t.started)
                        b = false;
                }
                if (b)
                    list.add(task);
            }
        }

        return list;
    }

    private static List<Task> whichCanSorted(List<Task> taskList) {
        taskList.sort(Comparator.comparingInt(o -> o.dE));
        return taskList;
    }

    private static void return_L_max(List<Task> taskList) {
        Optional<Task> max = taskList.stream().max((o1, o2) -> o1.L - o2.L);

//        System.out.print("\nL_i: ");
//        taskList.forEach(t -> System.out.print(t.name + ":" + t.L + " " ));
        System.out.println("\nL_max*="+max.get().L);
    }



    private static int findWholeTime(List<Task> taskList) {
        int time = 0;
        int max = 0;
        for (Task task : taskList) {
            max = Math.max(task.r, max);
        }
        time = max + 10;

        return time;
    }



    private static void draw(List<Task> taskList) throws IOException {

        Files.deleteIfExists(Paths.get("rysunek.html"));

        File file = new File("rysunek.html");
        FileWriter fout = new FileWriter(file, true);

        fout.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n");



        for (int i = 0; i < time; i++) {

            List<Task> whichCanList = whichCan(taskList, i);
            List<Task> whichCanListSorted = whichCanSorted(whichCanList);

            if (whichCanListSorted.size() > 0)
            {
                Task task = whichCanListSorted.get(0);
                // znajdz index pod ktorym task na liscie
                int index = taskList.indexOf(task);

                fout.append("<svg width=\"110\" height=\"180\">\n" +
                        "  <rect x=\"5\" y=\"20\" width=\"100\" height=\"100\"\n" +
                        "  style=\"fill:#c6ffcb;stroke:#77ea81;stroke-width:2;fill-opacity:0.9;stroke-opacity:0.99\" />\n" +
                        "    <text x=\"35\" y=\"70\" fill=\"#227c2a\">Zad " + task.name.substring(1,2) + "</text>\n" +
                        "    <text x=\"0\" y=\"140\" fill=\"#227c2a\">"+ i +"</text>" +
                        "</svg>");

                taskList.get(index).p = --taskList.get(index).p;
                // zmien started na true
                taskList.get(index).started = true;
                // zapisz index pod ktorym ostatecznie zakonczyl zadanie
                task.L = i - task.dE + 1;
            } else
                fout.append("<svg width=\"110\" height=\"180\">\n" +
                        "  <rect x=\"5\" y=\"20\" width=\"100\" height=\"100\"\n" +
                        "  style=\"fill:#c6ffcb;stroke:#77ea81;stroke-width:2;fill-opacity:0.02;stroke-opacity:0.15\" />\n" +
                        "    <text x=\"45\" y=\"70\" fill=\"red\">    X</text>\n" +
                        "    <text x=\"0\" y=\"140\" fill=\"#227c2a\">"+ i +"</text>" +
                        "</svg>");



        }



        fout.append("\n" +
                "\n" +
                "</body>\n" +
                "</html>\n");
        fout.close();
    }

}
