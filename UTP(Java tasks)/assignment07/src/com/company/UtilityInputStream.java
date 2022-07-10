package com.company;

import java.io.InputStream;
import java.util.Scanner;

public class UtilityInputStream {

    public static boolean contains(InputStream inputStream, String content, long size) {
        Scanner scanner = new Scanner(inputStream);
        String isContain = scanner.findWithinHorizon(content, (int) size);
        scanner.close();
        if (isContain != null){
            return true;
        }else return false;
    }
}
