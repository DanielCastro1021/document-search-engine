import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author danielcastro
 */
public class Query {

    private Directory directory;


    /**
     *
     */
    public Query() {
        this.directory = new Directory();


    }

    /**
     *
     */
    public void SearchEngine() {
    }

    /**
     * This method allows you to insert a location from a repository with documents.
     *
     * @param path This is a String with the path to local folder of the repository with documents.
     * @return This returns true if the path was successfully entered, false if this does not happen.
     */

    public boolean changeDirectory(String path) {
        if (path == null) {
            return false;
        } else {
            directory.setDirPath(path);

            if (directory.getDocuments() == null) {
                return false;
            } else {
                return true;
            }
        }
    }


    public int[][] preperationFase() throws FileNotFoundException {
        String[] docsStrings = this.directory.readFiles();
        docsStrings = cleanRepository(docsStrings);
        String[][] docsWords = separateRepositoryByWords(docsStrings);
        String[] uniqueWords = allRepositoryWords(docsWords);
        return searchMatrix(occurrenceMatrix(uniqueWords, docsWords));
    }

    /**
     * This method replaces punctuation and digits of a String with spaces.
     *
     * @param string This is a String.
     * @return This returns a String with spaces instead of punctuation and digits.
     */


    public String cleanString(String string) {
        String cleanString = null;

        if (string != null) {
            //Remove enter
            string=string.replaceAll(System.lineSeparator(), " ");
            //Remove punctuation
            string=string.replaceAll("\\p{Punct}", " ");
            //Remove digits
            string=string.replaceAll("\\d"," ");

            cleanString=string;
        }
        return cleanString;
    }


    /**
     * This method replaces punctuation and digits with spaces, from the collection of documents in the repository.
     *
     * @param docsStrings This is an array of String, that contain the content of each document in the repository.
     * @return This returns an array with all the content of each document in the repository with spaces instead of punctuation and digits.
     */


    public String[] cleanRepository(String[] docsStrings) {
        String[] cleanDocStrings = null;

        if (docsStrings != null) {
            cleanDocStrings = new String[docsStrings.length];
            for (int i = 0; i < docsStrings.length; i++) {
                cleanDocStrings[i] = cleanString(docsStrings[i]);
            }
        }
        return cleanDocStrings;
    }


    /**
     * This method separates a String by words.
     *
     * @param string This is a String.
     * @return This returns an array of String, that contains all the words of String.
     */


    public String[] separateByWord(String string) {
        String[] words = null;

        if (string != null) {
            words = string.split("\\s+");
        }

        return words;
    }


    /**
     * This method separates the contents of documents in the repository, by words.
     *
     * @param docsStrings This is an array of String with Strings that contain the content of each document in the repository.
     * @return This returns an array of array of String, that contains all content of documents in the repository separated by words.
     */


    public String[][] separateRepositoryByWords(String[] docsStrings) {
        String[][] docsWords = null;

        if (docsStrings != null) {
            docsWords = new String[docsStrings.length][];
            for (int i = 0; i < docsStrings.length; i++) {
                docsWords[i] = separateByWord(docsStrings[i]);
            }
        }
        return docsWords;
    }


    /**
     * This method returns all unique words in the repository.
     *
     * @param stringsCollection This is an array of array of String that contain the content of each document in the repository, separated by words.
     * @return This returns an array of String, that contains all the unique words in the documents of the repository.
     */

    public String[] allRepositoryWords(String[][] stringsCollection) {
        String[] uniqueWords = new String[numberOfWordsInRepository(stringsCollection)];
        int k = 0;

        if (stringsCollection != null) {
            for (int i = 0; i < stringsCollection.length; i++) {
                for (int j = 0; j < stringsCollection[i].length; j++) {
                    uniqueWords[k] = stringsCollection[i][j];
                    k++;
                }
            }
            uniqueWords = Arrays.stream(uniqueWords).distinct().toArray(String[]::new);
        }
        return uniqueWords;
    }


    /**
     * This method returns the total words contained in the repository.
     *
     * @param collectionWords This is an array of array of String that contain the content of each document in the repository, separated by words.
     * @return This returns the total number of words in the repository.
     */

    public int numberOfWordsInRepository(String[][] collectionWords) {
        int totalWords = 0;

        if (collectionWords != null) {
            for (int i = 0; i < collectionWords.length; i++) {
                for (int j = 0; j < collectionWords[i].length; j++) {
                    totalWords++;
                }
            }
        }
        return totalWords;
    }


    /**
     * This method returns a matrix with the number of occurrences of each word in all the documents of the repository.
     *
     * @param uniqueWords This is an array of String, with all the unique words in the repository.
     * @param docsWords   This is an array of array of String, that contains all content of documents in the repository separated by words.
     * @return This returns a matrix with the occurrence of the unique words ,of the repository, in all the documents of the repository.
     */

    public int[][] occurrenceMatrix(String[] uniqueWords, String[][] docsWords) {

        int[][] occurrenceMatrix = new int[docsWords.length][uniqueWords.length];

        if(uniqueWords!=null && docsWords!=null) {
            for (int i = 0; i < docsWords.length; i++) {
                for (int j = 0; j < uniqueWords.length; j++) {
                    for (int k = 0; k < docsWords[i].length; k++) {
                        if (uniqueWords[j].equals(docsWords[i][k])) {
                            occurrenceMatrix[i][j]++;
                        }
                    }
                }
            }
        }
        return occurrenceMatrix;
    }


    /**
     * This method transforms an occurrence matrix into an search matrix.
     *
     * @param occurrenceMatrix This an occurrence matrix with the occurrence of the unique words, in each document of the repository.
     * @return This returns a search matrix.
     */

    public int[][] searchMatrix(int[][] occurrenceMatrix) {
        int[][] searchMatrix = occurrenceMatrix;
        //...
        return searchMatrix;
    }

}
