package ru.itis.inf301.lab2_8;

public class MainPrintTask {
    public static void main(String[] args) throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println(n);

        final String data = "Вы также можете сопоставлять правила с помощью метода HTTP. Это удобно при авторизации с помощью предоставленных разрешений, например, при получении привилегии read или write.";

/*
        Thread thread1 = new Thread(new PrintTask(data));
        Thread thread2 = new Thread(new PrintTask(data));
*/

/*
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < data.length(); ++i) {
                System.out.print(data.charAt(i));
            }
            System.out.println();
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < data.length(); ++i) {
                System.out.print(data.charAt(i));
            }
            System.out.println();
        });
*/
        Thread thread1 = new PrintTaskThread(data);
        Thread thread2 = new PrintTaskThread(data);
/*
        thread1.setDaemon(true);
        thread2.setDaemon(true);
*/
        long start = System.nanoTime();
        thread1.start();

        thread2.start();
        thread1.join();
        thread2.join();
        long end = System.nanoTime();
        System.out.println(end - start);

        //
    }
}
