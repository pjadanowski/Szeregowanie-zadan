import java.util.*;
import java.util.function.Predicate;

public class Task  {

    String name;
    int id;
    int p;
    int d;
    int r;
    int dE;
    int L;
    boolean started = false;

    List<Task> next = new ArrayList<>();

    public Task(String name, int id, int p, int d, int r) {
        this.name = name;
        this.id = id;
        this.p = p;
        this.d = d;
        this.r = r;
    }

    public Task(String name, int p, int d, int r, Task... next) {
        this.name = name;
        this.p = p;
        this.d = d;
        this.r = r;
        Collections.addAll(this.next, next);
    }


    public void setdE(){
        int min = this.d;
        for (Task task: this.next) {
            min = Math.min(min, task.d);
        }
        this.dE = min;
    }


    public List<Task> prev(List<Task> list){
        List<Task> result = new ArrayList<>();
        Predicate<Task> prev =  t -> t.next.contains(this);
        list.stream().filter(prev).forEach(result::add);
        return result;
    }

//    @Override
//    public int compare(Task t1, Task t2) {
//        return t1.dE.;
//    }


//    public List<Task> dependent(List<Task> list, Task task){
//        List<Task> dependents = new ArrayList<>();
//        for (Task n: task.next) {
//            while(n.next.size() > 0)
//                dependents.add(n.next);
//        }
//    }
}
