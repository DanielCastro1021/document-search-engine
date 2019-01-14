import query.Directory;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestDirectory {

    @Test
    public void loadDocuments_TC1(){
        Directory directory = new Directory();
        directory.setDirectoryPath("");
        File[] results = directory.loadDocuments();
        assertEquals(null, results);
    }

    @Test
    public void loadDocuments_TC2(){
        Directory directory = new Directory();
        directory.setDirectoryPath("testDirectories/files");
        File[] results = directory.loadDocuments();
        File[] expected = {new File("testDirectories/files/1.txt"), new File("testDirectories/files/2.txt"), new File("testDirectories/files/3.txt"), new File("testDirectories/files/4.txt")};
        assertArrayEquals(expected, results);
    }

    @Test
    public void getNameFiles_TC1(){
        Directory directory = new Directory();
        directory.setDirectoryPath("");
        directory.loadDocuments();
        String[] results = directory.getNameFiles();
        assertArrayEquals(null, results);
    }

    @Test
    public void getNameFiles_TC2(){
        Directory directory = new Directory();
        directory.setDirectoryPath("testDirectories/files");
        directory.loadDocuments();
        String[] results = directory.getNameFiles();
        String[] expected = {"1.txt","2.txt","3.txt","4.txt"};
        assertArrayEquals(expected, results);
    }

    @Test
    public void loadDocumentsContent_TC1(){
        Directory directory = new Directory();
        directory.setDirectoryPath("");
        directory.loadDocumentsContent();
        String[] results = directory.getStringFiles();
        assertArrayEquals(null, results);
    }

    @Test
    public void loadDocumentsContent_TC2(){
        Directory directory = new Directory();
        directory.setDirectoryPath("testDirectories/directoryFile");
        directory.loadDocuments();
        directory.loadDocumentsContent();
        String[] results = directory.getStringFiles();
        String[] expected ={"Ola"};
        assertArrayEquals(expected, results);
    }

}
