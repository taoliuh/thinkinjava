package me.sonavie;

import java.io.*;
import java.util.concurrent.Semaphore;

/**
 * Created by liutao on 15/04/2019.
 */
public class NumberPrinter {

    private static final int MAX_PRINT_COUNT = 10;

    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);
        Semaphore s4 = new Semaphore(0);
        Thread thread1 = new Thread(new NumberGenerator(s1, s2, '1'));
        Thread thread2 = new Thread(new NumberGenerator(s2, s3, '2'));
        Thread thread3 = new Thread(new NumberGenerator(s3, s4, '3'));
        Thread thread4 = new Thread(new NumberGenerator(s4, s1, '4'));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    static class NumberGenerator implements Runnable {

        private Semaphore semaphore;
        private Semaphore nextSemaphore;
        private char printChar;

        public NumberGenerator(Semaphore semaphore, Semaphore nextSemaphore, char printChar) {
            this.semaphore = semaphore;
            this.nextSemaphore = nextSemaphore;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_PRINT_COUNT; i++) {
                try {
                    semaphore.acquire();
                    write("A.txt", getNextChar(printChar, 0));
                    write("B.txt", getNextChar(printChar, 1));
                    write("C.txt", getNextChar(printChar, 2));
                    write("D.txt", getNextChar(printChar, 3));
                    nextSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private String getNextChar(char printChar, int offset) {
            int number = Integer.valueOf(String.valueOf(printChar));
            if (number + offset < 5) {
                return String.valueOf(number + offset % 5);
            } else {
                return String.valueOf((number + offset) / 5 + (number + offset) % 5);
            }
        }

        private void write(String fileName, String ch) {
            try {
                File file = getFile("NumberPrinter/out/", fileName);
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
                writer.write(ch);
                writer.flush();
            } catch (Exception e) {
                System.out.println("write file failed!" + e.getMessage());
            }
        }

        private File getFile(String directory, String fileName) throws IOException {
            File dir = new File(directory);
            if (!dir.exists()) {
                boolean success = dir.mkdirs();
                if (!success) {
                    throw new IOException("create directory failed!");
                }
            }
            File file = new File(dir, fileName);
            if (!file.exists()) {
                boolean success = file.createNewFile();
                if (!success) {
                    throw new IOException("create file failed!");
                }
            }
            return file;
        }
    }
}
