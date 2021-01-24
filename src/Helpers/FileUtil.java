package Helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtil {

    public void writeObject(Object object, String fileName){
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Object readObject(String fileName){
        Object object = null;
        try (FileInputStream streamIn = new FileInputStream(fileName);
             ObjectInputStream objectinputstream = new ObjectInputStream(streamIn)) {
            object = objectinputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }




}
