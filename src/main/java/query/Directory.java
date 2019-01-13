package query;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author danielcastro
 */

public class Directory {
    private String directoryPath;
    private File[] listOfFiles;
    private String[] stringFiles;

    /**
     * This method allows to set the directory path of the repository.
     *
     * @param directoryPath This is a String that contains a path of a directory.
     */

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }


    /**
     * This method returns the files that exist in an Directory.
     *
     * @return This returns an array of File.
     */
    public String[] getStringFiles() {
        return stringFiles;
    }


    /**
     * This method returns the names of the files that exist in an Directory.
     *
     * @return This returns an array of String.
     */
    public String[] getNameFiles() {
        String[] names = null;
        if (this.listOfFiles != null && this.listOfFiles.length > 0) {
            names = new String[this.listOfFiles.length];
            for (int i = 0; i < this.listOfFiles.length; i++) {
                names[i] = this.listOfFiles[i].getName();
            }
        }
        return names;
    }

    /**
     * This method is responsible for loading the documents located in the repository.
     *
     * @return This returns an array of File, that contains all documents that are in the repository.
     */

    public File[] loadDocuments() {
        File dir = new File(this.directoryPath);
        File[] files = null;
        if (dir.exists() && Objects.requireNonNull(dir.list()).length != 0) {
            files = dir.listFiles();
            Arrays.sort(files);
            this.listOfFiles = files;
            return files;
        }
        return files;
    }

    /**
     * This method is responsible for reading the content of each document int the repository.
     */
    public void loadDocumentsContent() {
        try {
            String[] strings = new String[this.listOfFiles.length];

            for (int i = 0; i < this.listOfFiles.length; i++) {
                try (Scanner scanner = new Scanner(this.listOfFiles[i]).useDelimiter("\\A")) {
                    String entireFileText = scanner.next();
                    strings[i] = entireFileText;
                }
            }
            this.stringFiles = strings;
        } catch (NullPointerException | FileNotFoundException | NoSuchElementException e) {
            this.stringFiles = null;
        }
    }
}

