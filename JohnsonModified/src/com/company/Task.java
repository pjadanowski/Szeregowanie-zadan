package com.company;

import java.util.List;

public class Task {


    String name;
    int d1;
    int d2;
    int d3;

    int t1, t2;
    int last;

    public Task(String name, int d1, int d2, int d3) {
        this.name = name;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;

        this.t1 = this.d1 +this.d2;
        this.t2 = this.d2 +this.d3;
    }


    public static void print(List<Task> list) {
        list.forEach(t -> System.out.println(t.name + ", t1:" + t.t1+ ", t2:" + t.t2));
    }

}
