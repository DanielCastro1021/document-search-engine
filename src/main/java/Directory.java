
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author danielcastro
 */
public class Directory {


    private String directoryPath;
    private File[] listOfFiles;
    private String[] StringFiles;

    /**
     * Constructor method of the Directory Class.
     */

    public Directory() {

    }

    /**
     * This method allows to change the directory of the repository with documents.
     *
     * @param DirPath This is a Strin
     */

    public void setDirectoryPath(String DirPath) {
        this.directoryPath = DirPath;
    }

    public File[] getListOfFiles() {
        return listOfFiles;
    }

    public String[] getStringFiles() {
        return StringFiles;
    }

    /**
     * This method is responsible for loading the documents located in the repository.
     *
     * @return This returns a File array, that contains all documents that are in the repository.
     */

    public File[] loadDocuments() {
        File dir = new File(this.directoryPath);

        if (dir.exists() && dir.list().length != 0) {
            this.listOfFiles = dir.listFiles();

            if (listOfFiles != null && listOfFiles.length>0) {
                Arrays.sort(this.listOfFiles);
                return this.listOfFiles;
            }
        }
        return null;
    }


    /**
     * This method is responsible for reading the content of each document int the repository.
     *
     * @return This returns a String array, that contains all the content of each document of the repository.
     * @throws FileNotFoundException This throws an  FileNotFoundException, if isn´t possible to find a file.
     */

    public String[] loadDocumentsContent() throws FileNotFoundException {
        String[] StrFiles = new String[this.listOfFiles.length];

        for (int i = 0; i < this.listOfFiles.length; i++) {
            String entireFileText = new Scanner(this.listOfFiles[i]).useDelimiter("\\A").next();
            StrFiles[i] = entireFileText;
        }

        this.StringFiles=StrFiles;
        return StrFiles;
    }

}
