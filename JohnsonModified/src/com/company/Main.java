package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {

        List<Task> taskList = init();

        List<Task> n1List = N1(taskList);
//        Task.print(n1List);

        List<Task> n2List = N2(taskList);

        // array join
        List<Task> n12List = new ArrayList<>(n1List);
        n12List.addAll(n2List);
//        Task.print(n2List);
//        System.out.println(Arrays.deepToString(new List[]{taskList.n}));

        Task[][] tasks = setTasksIntoMachines(n12List);
        printSchedule(tasks);
        printAxis();

        Draw.draw(tasks);
    }


    public static List<Task> init() {
        List<Task> taskList = new ArrayList<>();

        taskList.add(new Task("Z1", 4, 1, 3));
        taskList.add(new Task("Z2", 3, 3, 5));
        taskList.add(new Task("Z3", 5, 2, 4));
        taskList.add(new Task("Z4", 6, 1, 4));
        taskList.add(new Task("Z5", 3, 2, 4));

        return taskList;
    }


    public static List<Task> N1(List<Task> list) {

        List<Task> N1 = new ArrayList<>();
        // wez min z t1, t2
        for (Task task : list) {
            if (task.t1 <= task.t2) {
                // wrzuc do N1 te ktorych t1 jest wieksze
                N1.add(task);
            }
        }

        // posortuj je niemalejaco
        N1.sort((o1, o2) -> o1.t1 - o2.t1);

        return N1;
    }

    public static List<Task> N2(List<Task> list) {

        List<Task> N2 = new ArrayList<>();
        // wez max z t1, t2
        for (Task task : list) {
            if (task.t1 > task.t2) {
                // wrzuc do N2 te ktorych t2 jest mniejsze od t1
                N2.add(task);
            }
        }

        // posortuj je nierosnaco
        N2.sort((o1, o2) -> o2.t2 - o1.t2);

        return N2;
    }

    public static int J = 0; // ile razy wrzucic zadanie
    public static int C = 0; // pomocnicza do indeksu

    private static Task[][] setTasksIntoMachines(List<Task> list) {

        Task[][] schedule = new Task[3][30];

        for (int i = 0; i < list.size(); i++) {
            while (list.get(i).d1 > J) {
                schedule[0][C + J++] = list.get(i);
            }
            C += J;
            list.get(i).last = C;
            J = 0;
        }
        C = 0;
        // dla maszyny 2
        for (int i = 0; i < list.size(); i++) {
            while (C < list.get(i).last) {
                schedule[1][C++] = new Task("  ", 0, 0, 0);
            }
            while (list.get(i).d2 > J) {
                schedule[1][C + J++] = list.get(i);
            }
            C += J;
            list.get(i).last = C;
            J = 0;
        }
        C = 0;

        // dla maszyny 3
        for (int i = 0; i < list.size(); i++) {
            while (C < list.get(i).last) {
                schedule[2][C++] = new Task("  ", 0, 0, 0);
            }
            while (list.get(i).d3 > J) {
                schedule[2][C + J++] = list.get(i);
            }
            C += J;
            J = 0;
        }

        return schedule;
    }


    private static void printSchedule(Task[][] schedule) {
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                String name = schedule[i][j] != null ? schedule[i][j].name : "";
                System.out.print(name + " ");
            }
            System.out.println();
        }
    }

    private static void printAxis() {

        for (int i = 1; i < 30; i++) {
            System.out.printf("%2d ", i);
        }
    }
}


