package Project;

import java.io.*;

public class ExtentHelper {
    public static String EXTENT_NAME = "save.dat";
    public static void saveAllClassExtents() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXTENT_NAME))){
            ObjectPlus.saveExtent(oos);
        }
    }

    public static void loadAllClassExtents() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXTENT_NAME))){

            ObjectPlus.loadExtent(ois);
        }
    }
}
