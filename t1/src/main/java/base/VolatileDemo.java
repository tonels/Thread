package base;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 常情况下，在一个线程调用 run() 方法（在 Runnable 开启的线程），在另一个线程调用 stop() 方法。 如果 第一行 中缓冲区的
 * active 值被使用，那么在 第二行 的 active 值为 false 时循环不会停止。 但是以上代码中我们使用了 volatile 修饰
 * active，所以该循环会停止。
 */
public class VolatileDemo {

    public static Hashtable<String, Integer> num = new Hashtable<>();

    public static class Model {
        Integer a;
        Integer b;
    }

    public Model model;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void init() {
        model.a = 1;
        model.b = 2;
    }

    public void change() {
        model.a = 3;
        model.b = model.a;
    }

    public void print() {
        String key = model.a.toString() + model.b.toString();
        if (num.keySet().contains(key)) {
            num.put(key, num.get(key) + 1);
        } else {
            num.put(key, 1);
        }
    }

    public static void test1() {
        final VolatileDemo test = new VolatileDemo();
        VolatileDemo.Model model = new VolatileDemo.Model();
        model.a = 1;
        model.b = 2;
        test.setModel(model);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        ExecutorService fixedThreadPool1 = Executors.newFixedThreadPool(1);
        ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        test.change();
                    }
                }
            });
            fixedThreadPool1.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        test.init();
                    }
                }
            });
            fixedThreadPool2.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        test.print();
                    }
                }
            });
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Map.Entry entry : num.entrySet()) {
            System.out.println("key:" + entry.getKey() + "--value:" + entry.getValue());
        }
    }

    public static void main(String[] args) {
        test1();
    }
}