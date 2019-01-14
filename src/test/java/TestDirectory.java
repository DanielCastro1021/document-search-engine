import query.Directory;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestDirectory {

    @Test
    public void loadDocuments_TC1(){
        Directory directory = new Directory();
        directory.setDirectoryPath("");
        File[] resultado = directory.loadDocuments();

        assertEquals(null, resultado);
    }

    @Test
    public void loadDocuments_TC2(){
        Directory directory = new Directory();
        directory.setDirectoryPath("testDirectories/files");
        File[] resultado = directory.loadDocuments();
        File[] esperado = {new File("testDirectories\\files\\1.txt"), new File("testDirectories\\files\\2.txt"), new File("testDirectories\\files\\3.txt"), new File("testDirectories\\files\\4.txt")};

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getNameFiles_TC1(){
        Directory directory = new Directory();
        directory.setDirectoryPath("");
        directory.loadDocuments();
        String[] resultado = directory.getNameFiles();
        String[] esperado = null;

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getNameFiles_TC2(){
        Directory directory = new Directory();
        directory.setDirectoryPath("testDirectories/files");
        directory.loadDocuments();
        String[] resultado = directory.getNameFiles();
        String[] esperado = {"1.txt","2.txt","3.txt","4.txt"};

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void loadDocumentsContent_TC1(){
        Directory directory = new Directory();
        directory.setDirectoryPath("");
        directory.loadDocumentsContent();
        String[] resultado = directory.getStringFiles();

        assertArrayEquals(null, resultado);
    }

    @Test
    public void loadDocumentsContent_TC2(){
        Directory directory = new Directory();
        directory.setDirectoryPath("testDirectories/directoryFile");
        directory.loadDocuments();
        directory.loadDocumentsContent();
        String[] resultado = directory.getStringFiles();
        String[] esperado ={"Ola"};

        assertArrayEquals(esperado, resultado);
    }

}
