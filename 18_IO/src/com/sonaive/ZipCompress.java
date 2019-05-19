package com.sonaive;

import java.io.*;
import java.util.zip.*;

/**
 * Created by liutao on 2/23/16.
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream("test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("A test of java zipping");
        for (String arg : args) {
            System.out.println("Writing file " + arg);
            BufferedReader reader = new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = reader.read()) != -1) {
                out.write(c);
            }
            reader.close();
            out.flush();
        }
        out.close();
        System.out.println("CheckSum: " + csum.getChecksum().getValue());
        System.out.println("Reading file");
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
        ZipInputStream zi = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(zi);
        ZipEntry ze;
        while ((ze = zi.getNextEntry()) != null) {
            System.out.println("Reading file " + ze);
            int x;
            while ((x = bis.read()) != -1) {
                System.out.write(x);
            }
        }
        if (args.length == 1) {
            System.out.println("CheckSum: " + csumi.getChecksum().getValue());
        }
        bis.close();
    }
}
