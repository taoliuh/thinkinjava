import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 1/8/16.
 */

class TaskA implements Runnable {

    private boolean flag = false;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
            setFlag(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean getFlag() {
        return flag;
    }

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class TaskB implements Runnable {

    private TaskA task;

    public TaskB(TaskA task) {
        this.task = task;
    }

    @Override
    public void run() {
        long curTime = System.currentTimeMillis();
        while (true) {
            if (task.getFlag()) {
                task.setFlag(false);
                long newTime = System.currentTimeMillis();
                System.out.printf("Wait time %d ms\n", newTime - curTime);
                break;
            }
        }
    }
}

class TaskC implements Runnable {
    private boolean flag = false;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
            setFlag(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean getFlag() {
        return flag;
    }

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class TaskD implements Runnable {

    private TaskC task;

    public TaskD(TaskC task) {
        this.task = task;
    }

    @Override
    public void run() {
        long curTime = System.currentTimeMillis();
        try {
            waitForTaskC();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (task.getFlag()) {
            task.setFlag(false);
            long newTime = System.currentTimeMillis();
            System.out.printf("Wait time %d ms\n", newTime - curTime);
        }
    }

    private synchronized void waitForTaskC() throws InterruptedException {
        while (!task.getFlag()) {
            wait();
        }
    }
}

public class Ex22 {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        TaskA task = new TaskA();
        exec.execute(task);
        exec.execute(new TaskB(task));
        exec.shutdown();

        ExecutorService exec2 = Executors.newCachedThreadPool();
        TaskC taskC = new TaskC();
        TaskD taskD = new TaskD(taskC);
        exec2.execute(taskC);
        exec2.execute(taskD);
        exec2.shutdown();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (taskD) {
            taskD.notifyAll();
        }
    }
}
