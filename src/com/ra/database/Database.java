package com.ra.database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database{
    public static File createFile(String fileName) {
        File dataDir = new File("data");
        if(!dataDir.exists()){
            dataDir.mkdir();
        }
        File data = new File("data/"+fileName);

        if(!data.exists()){
            try {
                data.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }

    public static void saveToFile(File fileName, List<?> list){

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(list);
        } catch (IOException exception){
            System.out.println("co loi khi them moi");
        }
    }
    // them phuong thuc doc file và lấy về danh sách sinh viên
    public static List<?> getAllToFile(File fileName){
        List<?> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            list = (List<?>) inputStream.readObject();
        } catch (EOFException ignored) {

        } catch (IOException e) {
            System.err.println("Loi khi doc file");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
