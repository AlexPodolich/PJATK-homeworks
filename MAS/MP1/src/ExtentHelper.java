import java.io.*;

public class ExtentHelper {
    public static String EXTENT_NAME = "save.dat";
    public static void saveAllClassExtents() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXTENT_NAME))){
            Dormitory.saveExtent(oos);
            Employee.saveExtent(oos);
            Resident.saveExtent(oos);
            Room.saveExtent(oos);
        }
    }

    public static void loadAllClassExtents() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXTENT_NAME))){
            Dormitory.loadExtent(ois);
            Employee.loadExtent(ois);
            Resident.loadExtent(ois);
            Room.loadExtent(ois);
        }
    }
}
