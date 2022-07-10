package com.company;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class UtilityJar {
    public static List<String> searchByName(File file, String name) throws Exception {
        if(!file.isFile()){
            return null;
        }
        JarFile jar = null;
        try{
            jar = new JarFile(file);
            return search(jar, name, entry -> searchByNamePredicate(entry, name));
        }catch (Throwable exception){
            throw new Exception(exception);
        } finally {
            if (jar != null){
                try{
                    jar.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<String> searchByContent(File file, String content) throws Exception {
        if(!file.isDirectory()){
            return null;
        }
        try {
            JarFile jar = new JarFile(file);
            List<String> fileNames = search(jar, content, entry -> {
                try {
                    return searchByContentPredicate(entry, jar, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            });
            jar.close();
            return fileNames;
        }catch (Throwable exception){
            throw new Exception(exception);
        }
    }

    private static List<String> search(JarFile jar, String name, Predicate<? super JarEntry> predicate) throws Exception {
        try {
            List<String> fileNames = jar.stream().filter(predicate).map(JarEntry::getName).collect(Collectors.toList());
            jar.close();
            return fileNames;
        }catch (Throwable exception){
            throw new Exception(exception);
        }
    }

    private static boolean searchByNamePredicate(JarEntry entry, String name){
        return entry.getName().contains(name);
    }

    private static boolean searchByContentPredicate(JarEntry entry, JarFile jar, String content) throws Exception {
        try {
            InputStream inputStream = jar.getInputStream(entry);
            return UtilityInputStream.contains(inputStream, content, entry.getSize());
        }catch (Throwable exception){
            throw new Exception(exception);
        }
    }

}
