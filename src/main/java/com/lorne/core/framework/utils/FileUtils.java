package com.lorne.core.framework.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void copyFile(String path, String topath) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
            return;
        }
        File tf = new File(topath);
        tf.getParentFile().mkdirs();
        InputStream inStream = new FileInputStream(f);
        FileOutputStream fs = new FileOutputStream(tf);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inStream.read(buffer)) != -1) {
            fs.write(buffer, 0, length);
        }
        fs.flush();
        fs.close();
        inStream.close();
    }

    public static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.isFile())
                file.getParentFile().mkdirs();
            else
                file.mkdirs();
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            String[] children = file.list();
            for (int i = 0; i < children.length; i++) {
                deleteFile(file.getPath() + "/" + children[i]);
            }
        }
        file.delete();
    }

    public static String getFileType(String path) {
        String name = "";
        try {
            if (path != null) {
                name = new File(path).getName();
            }
            if (!name.equals("") && name.contains(".")) {
                return name.split("\\.")[name.split("\\.").length - 1];
            }
            return "";
        } catch (Exception e) {
        }
        return name;
    }

    public static String getFileName(String path) {
        String fileName = "";

        if (path != null) {
            fileName = new File(path).getName();
        }
        if (fileName != null) {
            if (fileName.contains("/")) {
                fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
                if (fileName.contains(".")) {
                    fileName = fileName.substring(0, fileName.indexOf("."));
                }
            } else {
                if (fileName.contains(".")) {
                    fileName = fileName.substring(0, fileName.indexOf("."));
                }
            }

        }
        return fileName;
    }


    public static String readTextFile(String path) throws Exception {
        String lines = "";
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        InputStreamReader inputStreamReader = new InputStreamReader(
                fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines += line + "\n";
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        return lines;
    }


    public static List<String> readFileText2List(String path) throws Exception {
        List<String> lines = new ArrayList<String>();
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        InputStreamReader inputStreamReader = new InputStreamReader(
                fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        return lines;
    }


    public static void writeString2File(String filepath, String data)
            throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(
                filepath));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(data);
        if (bufferedWriter != null) {
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        if (outputStreamWriter != null) {
            outputStreamWriter.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }

    public static void writeString2File(String filepath, String data,
                                        boolean append) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(
                filepath), append);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(data);
        if (bufferedWriter != null) {
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        if (outputStreamWriter != null) {
            outputStreamWriter.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }

}
