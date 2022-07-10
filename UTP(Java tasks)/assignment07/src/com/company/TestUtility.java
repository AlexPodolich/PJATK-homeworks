package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class TestUtility {

    private File TEST_DIR = new File("testdir");
    private File TEST_TXT = new File("testdir/testfile.txt");
    private File ZIP_DIR = new File("testdir.zip");
    private File JAR_DIR = new File("testdir.jar");

    @Before
    public void before(){
        Assert.assertTrue(TEST_DIR.exists());
        Assert.assertTrue(TEST_TXT.exists());
        Assert.assertTrue(ZIP_DIR.exists());
        Assert.assertTrue(JAR_DIR.exists());
    }

    @Test
    public void searchByName() throws Exception {
        List<File> files = UtilityRegular.searchByName(TEST_DIR, "testfile");
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("testfile.txt", file.getName());
        Assert.assertEquals(TEST_TXT, file);
    }

    @Test
    public void wrongFileName() throws Exception {
        List<File> files = UtilityRegular.searchByName(TEST_DIR, "test123");
        Assert.assertEquals(0, files.size());
    }

    @Test
    public void searchByContent() throws Exception {
        List<File> files = UtilityRegular.searchByContent(TEST_DIR, "some correct text");
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("testfile.txt", file.getName());
        Assert.assertEquals(TEST_TXT, file);
    }

    @Test
    public void wrongContent() throws Exception {
        List<File> files = UtilityRegular.searchByContent(TEST_DIR, "some wrong text");
        Assert.assertEquals(0, files.size());
    }

    @Test
    public void searchZipByName() throws Exception {
        List<String> strings = UtilityZip.searchByName(ZIP_DIR, "testfile");
        Assert.assertEquals(1, strings.size());
        String file = strings.get(0);
        Assert.assertEquals("testdir/testfile.txt", file);
    }

    @Test
    public void wrongZipFileName() throws Exception {
        List<String> strings = UtilityZip.searchByName(ZIP_DIR, "test123");
        Assert.assertEquals(0, strings.size());
    }

    @Test
    public void searchZipByContent() throws Exception {
        List<File> files = UtilityRegular.searchByContent(ZIP_DIR, "some correct text");
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals(ZIP_DIR, file);
    }

    @Test
    public void wrongZipContent() throws Exception {
        List<File> files = UtilityRegular.searchByContent(ZIP_DIR, "some wrong text");
        Assert.assertEquals(0, files.size());
    }

    @Test
    public void searchJarByName() throws Exception {
        List<String> strings = UtilityJar.searchByName(JAR_DIR, "testfile");
        Assert.assertEquals(1, strings.size());
        String file = strings.get(0);
        Assert.assertEquals("testdir/testfile.txt", file);
    }

    @Test
    public void wrongJarFileName() throws Exception {
        List<String> strings = UtilityJar.searchByName(JAR_DIR, "test123");
        Assert.assertEquals(0, strings.size());
    }

    @Test
    public void searchJarByContent() throws Exception {
        List<File> files = UtilityRegular.searchByContent(JAR_DIR, "some correct text");
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals(JAR_DIR, file);
    }

    @Test
    public void wrongJarContent() throws Exception {
        List<File> files = UtilityRegular.searchByContent(JAR_DIR, "some wrong text");
        Assert.assertEquals(0, files.size());
    }

}
