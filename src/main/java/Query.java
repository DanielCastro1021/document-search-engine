import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author danielcastro
 */
public class Query {

    private Directory directory;

    /**
     * Constructor method of the Query Class.
     */
    public Query() {
        this.directory = new Directory();
    }

    /**
     *
     */
    public void Query() throws FileNotFoundException {
        preparationPhase();
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


    /**
     * This method is responsible for loading of the documents in a repository, removing their punctuation and digits, and creating a search matrix.
     *
     * @return This returns
     */
    public double[][] preparationPhase() throws FileNotFoundException {
        //Loading
        String[] docsStrings = this.directory.readFiles();
        //Removing punctuation and digits
        docsStrings = cleanStrings(docsStrings);
        //Creating a search matrix
        return getSearchMatrix(docsStrings);
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
            //Remove enter characters
            string = string.replaceAll(System.lineSeparator(), " ");
            //Remove punctuation
            string = string.replaceAll("\\p{Punct}", " ");
            //Remove digits
            string = string.replaceAll("\\d", " ");

            cleanString = string;
        }
        return cleanString;
    }


    /**
     * This method replaces punctuation and digits with spaces in a collection of documents.
     *
     * @param strings This is an array of String, that contain the content of each document in the repository.
     * @return This returns an array with all the content of each document in the repository with spaces instead of punctuation and digits.
     */


    public String[] cleanStrings(String[] strings) {
        String[] cleanDocStrings = null;

        if (strings != null) {
            cleanDocStrings = new String[strings.length];
            for (int i = 0; i < strings.length; i++) {
                cleanDocStrings[i] = cleanString(strings[i]);
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


    public String[] splitStringByWords(String string) {
        String[] words = null;

        if (string != null) {
            words = string.split("\\s+");
        }
        return words;
    }


    /**
     * This method separates the contents of documents in the repository, by words.
     *
     * @param strings This is an array of String with Strings that contain the content of each document in the repository.
     * @return This returns an array of array of String, that contains all content of documents in the repository separated by words.
     */


    public String[][] splitRepositoryByWords(String[] strings) {
        String[][] stringsWords = null;

        if (strings != null) {
            stringsWords = new String[strings.length][];
            for (int i = 0; i < strings.length; i++) {
                stringsWords[i] = splitStringByWords(strings[i]);
            }
        }
        return stringsWords;
    }


    /**
     * This method returns all unique words in the repository.
     *
     * @param stringsWords This is an array of array of String that contain the content of each document in the repository, separated by words.
     * @return This returns an array of String, that contains all the unique words in the documents of the repository.
     */

    public String[] getUniqueWords(String[][] stringsWords) {
        String[] uniqueWords = null;
        int k = 0;

        if (stringsWords != null) {
            uniqueWords = new String[getTotalWords(stringsWords)];
            for (int i = 0; i < stringsWords.length; i++) {
                for (int j = 0; j < stringsWords[i].length; j++) {
                    uniqueWords[k] = stringsWords[i][j];
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
     * @param stringsWords This is an array of array of String, that contains all the words of the repository.
     * @return This returns the total number of words in an array of String arrays.
     */

    public int getTotalWords(String[][] stringsWords) {
        int totalWords = 0;

        if (stringsWords != null) {
            for (int i = 0; i < stringsWords.length; i++) {
                for (int j = 0; j < stringsWords[i].length; j++) {
                    totalWords++;
                }
            }
        }
        return totalWords;
    }


    /**
     * This method returns a matrix with the number of occurrences of each word by the document of the repository.
     *
     * @param uniqueWords  This is an array of String, with the unique words of a repository.
     * @param stringsWords This is an array of array of String, that contains the words of each document.
     * @return This returns a matrix with the occurrence the words ,of the repository, in all the documents of the repository.
     */

    public int[][] getOccurrenceMatrix(String[] uniqueWords, String[][] stringsWords) {

        int[][] occurrenceMatrix = null;

        if (uniqueWords != null && stringsWords != null && uniqueWords.length>0 && stringsWords.length>0) {
            occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
            for (int i = 0; i < stringsWords.length; i++) {
                for (int j = 0; j < uniqueWords.length; j++) {
                    for (int k = 0; k < stringsWords[i].length; k++) {
                        if (uniqueWords[j].equals(stringsWords[i][k])) {
                            occurrenceMatrix[i][j]++;
                        }
                    }
                }
            }
        }
        return occurrenceMatrix;
    }



    public double[][] getSearchMatrix(String[] strings) {
        //Split strings by words
        String[][] docsWords = splitRepositoryByWords(strings);
        //Get unique words
        String[] uniqueWords = getUniqueWords(docsWords);
        //Creating occurrence matrix
        int[][] occurrenceMatrix = getOccurrenceMatrix(uniqueWords, docsWords);

        double[][] searchMatrix = new double[occurrenceMatrix.length][occurrenceMatrix[0].length];
        //...
        return searchMatrix;
    }

}
