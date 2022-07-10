package com.company;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UtilityZip {
    public static List<String> searchByName(File file, String name) throws Exception {
        if(!file.isFile()){
            return null;
        }
        try {
            ZipFile zipFile = new ZipFile(file);
            List<String> fileNames = zipFile.stream().filter(zipEntry -> searchByNamePredicate(zipEntry, name)).map(ZipEntry::getName).collect(Collectors.toList());
            zipFile.close();
            return fileNames;
        }catch (Throwable exception){
            throw new Exception(exception);
        }
    }

    public static List<String> searchByContent(File file, String content) throws Exception {
        if(!file.isDirectory()){
            return null;
        }
        try {
            ZipFile zipFile = new ZipFile(file);
            List<String> fileNames = zipFile.stream().filter(zipEntry -> {
                try {
                    return searchByContentPredicate(zipEntry, zipFile, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }).map(zipEntry -> zipEntry.getName()).collect(Collectors.toList());
            zipFile.close();
            return fileNames;
        } catch (Throwable exception){
            throw new Exception(exception);
        }
    }

    private static boolean searchByNamePredicate(ZipEntry entry, String content){
        return entry.getName().contains(content);
    }

    private static boolean searchByContentPredicate(ZipEntry entry, ZipFile zipFile, String content) throws Exception {
        try {
            InputStream inputStream = zipFile.getInputStream(entry);
            return UtilityInputStream.contains(inputStream, content, entry.getSize());
        } catch (Throwable exception){
            throw new Exception(exception);
        }
    }
}
