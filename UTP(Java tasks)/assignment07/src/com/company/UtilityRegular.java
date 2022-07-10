package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class UtilityRegular {
    public static boolean checkDirectory(File dir){
        if(dir.isDirectory() || dir.canRead() || dir.exists()){
            return true;
        }
        else return false;
    }

    public static List<File> search(File dir, String searchParam, FilePredicate predicate) throws Exception {
        if(!checkDirectory(dir)) return null;
        try{
            Path root = dir.toPath();
            List<File> files = Files
                    .walk(root)
                    .filter(path -> {
                        try {
                            return predicate.test(path, searchParam);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .map(path -> path.toFile())
                    .filter(File::isFile)
                    .collect(Collectors.toList());
            return files;
        }catch (Throwable exception){
            throw new Exception(exception);
        }
    }

    public static List<File> searchByName(File dir, String name) throws Exception {
        try{
            return search(dir, name, UtilityRegular::searchByNamePredicate);
        } catch (Throwable exception) {
            throw new Exception(exception);
        }
    }

    public static List<File> searchByContent(File dir, String content) throws Exception {
        try{
            return search(dir, content, UtilityRegular::searchByContentPredicate);
        } catch (Throwable exception) {
            throw new Exception(exception);
        }
    }

    public static boolean searchByNamePredicate(Path path, String name){
        return path.toFile().getName().contains(name);
    }

    public static boolean searchByContentPredicate(Path path, String content) throws Exception {
        try{
            File file = path.toFile();
            if(file.isDirectory()){
                return false;
            }
            return contains(file, content);
        }catch (Throwable exception){
            throw new Exception(exception);
        }
    }

    public static boolean contains (File file, String content) throws Exception{
        InputStream inputStream = new FileInputStream(file);
        return UtilityInputStream.contains(inputStream, content, file.length());
    }
}
