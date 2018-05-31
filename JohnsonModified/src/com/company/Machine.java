package com.company;

import java.util.ArrayList;
import java.util.List;

public class Machine {


    int id;
    List<Task> tasks = new ArrayList<>();

    public Machine(int id) {
        this.id = id;
    }
}
