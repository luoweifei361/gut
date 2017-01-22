//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.lufei.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.lufei.IoKit;
import org.lufei.IoKit.CopyListener;

public class Tool {
    public Tool() {
    }

    public static String call(File workDir,String showLog, String... cmd) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        processBuilder.directory(workDir);
        showLog= showLog==null?workDir.getName():showLog;
        System.out.println(String.format("==============%s===================",showLog ));
        Process process = processBuilder.start();
        int retCode = process.waitFor();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InputStream outIs = process.getInputStream();
        InputStream errIs = process.getErrorStream();

        String log;
        try {
            IoKit.copyStream(outIs, bos, (CopyListener) null);
            IoKit.copyStream(errIs, bos, (CopyListener) null);
            log = new String(bos.toByteArray());
        } finally {
            IoKit.closeSilently(outIs);
            IoKit.closeSilently(errIs);
        }

        System.out.print(log);
        return log;
    }

    public static List<File> repoDirList(String path) {
        LinkedList repoDirList = new LinkedList();
        File file = new File(path, ".git");
        if (file.exists() && file.isDirectory()) {
            repoDirList.add(new File(path));
        }

        File[] var7 = (new File(path)).listFiles();
        int var3 = var7.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            File file1 = var7[var4];
            if (file1.isDirectory()) {
                File childGitDir = new File(file1, ".git");
                if (childGitDir.exists() && childGitDir.isDirectory()) {
                    repoDirList.add(file1);
                }
            }
        }

        return repoDirList;
    }

    public static List<String> file2Line(String file) {


        List<String> lines = new ArrayList<>();

        try {
            FileReader in = new FileReader(file);
            BufferedReader reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println(file + "文件不存在!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(file + "读取失败!");
            e.printStackTrace();
        }
        return lines;
    }

    public static void call(String[] option) {
        String debugPath = System.getProperty("debug.path");
        String path = debugPath != null ? debugPath : ".";
        List<File> files = Tool.repoDirList(path);
        String[] desc = new String[option.length + 1];
        desc[0] = "git";
        System.arraycopy(option, 0, desc, 1, option.length);
        for (File file : files) {
            try {
                Tool.call(file.getAbsoluteFile(),null, desc);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
