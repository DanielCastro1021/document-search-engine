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
     * Constructor method of the Query Class .
     */
    public void Query(String directoryPath, String query) throws FileNotFoundException {
        this.directory = new Directory();
        changeDirectory(directoryPath);
        this.directory.loadDocumentsContent();
        getResults(calculationPhase(preparationPhaseMatrix(), preparationPhaseQuery(query)));
    }

    /**
     * Constructor method of the Query Class.
     */
    public void Query(String directoryPath, String query, int numberOfFiles) throws FileNotFoundException {
        this.directory = new Directory();
        changeDirectory(directoryPath);
        this.directory.loadDocumentsContent();
        getResultsByNumberOfFiles(calculationPhase(preparationPhaseMatrix(), preparationPhaseQuery(query)), numberOfFiles);
    }

    /**
     * Constructor method of the Query Class.
     */
    public void Query(String directoryPath, String query, double similarityDegree) throws FileNotFoundException {
        this.directory = new Directory();
        changeDirectory(directoryPath);
        this.directory.loadDocumentsContent();
        getResultsByDegreeOfSimilarity(calculationPhase(preparationPhaseMatrix(), preparationPhaseQuery(query)), similarityDegree);
    }


    public Directory getDirectory() {
        return directory;
    }

    /**
     * This method is responsible for loading of the documents in a repository, removing their punctuation and digits, and creating a search matrix.
     *
     * @return This returns an search matrix.
     */
    public double[][] preparationPhaseMatrix() {
        //Loading
        String[] docsStrings = this.directory.getStringFiles();
        //Removing punctuation and digits
        docsStrings = cleanStrings(docsStrings);
        //Spilt Words in Repository
        String[][] docsWords = splitRepositoryByWords(docsStrings);
        //Get Unique Words in Repository
        String[] uniqueWords = getUniqueWords(docsWords);
        //Create Search Matrix
        return getSearchMatrix(getOccurrenceMatrix(uniqueWords, docsWords));
    }

    /**
     * This method is responsible for removing punctuation and digits of query, and create an occurrence array.
     *
     * @return This returns an occurrence array.
     */
    public int[] preparationPhaseQuery(String query) {
        //Loading
        String[] docsStrings = this.directory.getStringFiles();
        //Removing punctuation and digits
        docsStrings = cleanStrings(docsStrings);
        //Spilt Words in Repository
        String[][] docsWords = splitRepositoryByWords(docsStrings);
        //Get Unique Words in Repository
        String[] uniqueWords = getUniqueWords(docsWords);

        //Removing punctuation and digits
        String cleanQuery = cleanString(query);
        //Spilt Words Query
        String[] queryWords = splitStringByWords(cleanQuery);

        return getOccurrenceArray(uniqueWords, queryWords);
    }


    /**
     * This method is responsible for calculating the similarity degree of each document in repository.
     *
     * @param searchMatrix This is a search matrix of the documents in the repository.
     * @param keyArray     This is an occurrence array of the query.
     * @return This returns an occurrence array.
     */

    public double[] calculationPhase(double[][] searchMatrix, int[] keyArray) {
        double[] results = null;
        double sumMQ = 0;
        double sumM = 0;
        double sumQ = 0;

        if (searchMatrix != null && keyArray != null && searchMatrix.length > 0 && keyArray.length > 0) {
            results = new double[searchMatrix.length];


            for (int line = 0; line < searchMatrix.length; line++) {
                if (searchMatrix[0].length == keyArray.length) {
                    for (int column = 0; column < searchMatrix[line].length; column++) {
                        sumMQ += (searchMatrix[line][column] * keyArray[column]);
                        sumM += searchMatrix[line][column];
                        sumQ += keyArray[column];
                    }

                    if (sumQ == 0 || sumM == 0) {
                        results[line] = 0.0;
                    } else {
                        results[line] = (sumMQ / ((Math.sqrt(Math.pow(sumM, 2)) * Math.sqrt(Math.pow(sumQ, 2)))));
                    }
                }
            }
        }

        return results;
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
            directory.setDirectoryPath(path);

            if (directory.loadDocuments() == null) {
                return false;
            } else {
                return true;
            }
        }
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
     * This method returns an array with the number of occurrences of each word of the Query in all the unique words of the repository.
     *
     * @param uniqueWords This is an array of String, with the unique words of a repository.
     * @param queryWords  This is an array of array of String, that contains the words of a Query.
     * @return This returns an array with the occurrence the words,of the Query, in all the documents of the repository.
     */
    public int[] getOccurrenceArray(String[] uniqueWords, String[] queryWords) {
        int[] occurrenceArray = null;

        if (uniqueWords != null && queryWords != null && uniqueWords.length > 0 && queryWords.length > 0) {
            occurrenceArray = new int[uniqueWords.length];
            for (int j = 0; j < uniqueWords.length; j++) {
                for (int k = 0; k < queryWords.length; k++) {
                    if (uniqueWords[j].equals(queryWords[k])) {
                        occurrenceArray[j]++;
                    }
                }
            }
        }
        return occurrenceArray;
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

        if (uniqueWords != null && stringsWords != null && uniqueWords.length > 0 && stringsWords.length > 0) {
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

    /**
     * This method transforms an occurrence matrix into an search matrix.
     *
     * @param occurrenceMatrix This an occurrence matrix with the occurrence of the unique words, in each document of the repository.
     * @return This returns a search matrix.
     */
    public double[][] getSearchMatrix(int[][] occurrenceMatrix) {
        int d;
        double[][] searchMatrix = null;

        if (occurrenceMatrix != null) {
            searchMatrix = new double[occurrenceMatrix.length][occurrenceMatrix[0].length];
            for (int i = 0; i < occurrenceMatrix.length; i++) {
                for (int j = 0; j < occurrenceMatrix[0].length; j++) {
                    d = 0;
                    for (int k = 0; k < occurrenceMatrix.length; k++) {
                        if (occurrenceMatrix[k][j] != 0) {
                            d++;
                        }
                    }
                    if (d != 0) {
                        searchMatrix[i][j] = occurrenceMatrix[i][j] * (1 + Math.log10((double) occurrenceMatrix.length / d));
                    }
                }
            }
        }
        return searchMatrix;
    }

    /**
     * This method returns the files ordered by their rate of simulation (Highest to Lowest)
     *
     * @param results This is an array of doubles with the the rate of simulation of each file
     * @return This method returns by order, the name of the documents according
     * to the search made by the user
     */
    public String[] getResults(double[] results) {

        if (results != null) {
            double[] arrayD = results;
            String[] arrayS = new String[results.length];

            for (int i = 0; i < arrayS.length; i++) {
                arrayS[i] = this.directory.loadDocuments()[i].getName();
            }

            for (int i = 0; i < results.length; i++) {
                for (int j = i; j < results.length; j++) {
                    if (arrayD[i] < arrayD[j]) {
                        double var = arrayD[i];
                        String str = arrayS[i];
                        arrayD[i] = arrayD[j];
                        arrayS[i] = arrayS[j];
                        arrayS[j] = str;
                        arrayD[j] = var;
                    }
                }
            }
            return arrayS;
        } else {
            return null;
        }
    }

    /**
     * This method returns the files ordered by their rate of simulation (Highest to Lowest)
     *
     * @param input This is an array of doubles with the the rate of simulation of each file
     * @param numberOfFiles This is the number of files that the user wants to be shown in the return of this method
     * @return This method returns by order, the name of the documents according
     * to the search made by the user
     */
    public String[] getResultsByNumberOfFiles(double[] input, int numberOfFiles) {

        if (input != null && numberOfFiles > 0) {
            double[] arrayD = input;
            String[] arrayS = new String[input.length];
            int contador = 0;

            for (int i = 0; i < arrayS.length; i++) {
                arrayS[i] = this.directory.loadDocuments()[i].getName();
            }

            for (int i = 0; i < input.length; i++) {
                for (int j = i; j < input.length; j++) {
                    if (arrayD[i] < arrayD[j]) {
                        double var = arrayD[i];
                        String str = arrayS[i];
                        arrayD[i] = arrayD[j];
                        arrayS[i] = arrayS[j];
                        arrayS[j] = str;
                        arrayD[j] = var;
                    }
                }
                contador++;
                if (contador == numberOfFiles) {
                    break;
                }
            }

            String[] arrayS2 = new String[contador];

            for (int p = 0; p < contador; p++) {
                arrayS2[p] = arrayS[p];
            }
            return arrayS2;
        } else {
            return null;
        }
    }

    /**
     * This method returns the files ordered by their rate of simulation (Highest to Lowest)
     *
     * @param results This is an array of doubles with the the rate of simulation of each file
     * @param degreeOfSimilarity
     * @return This method returns by order, the name of the documents according
     * to the search made by the user
     */
    public String[] getResultsByDegreeOfSimilarity(double[] results, double degreeOfSimilarity) {

        if (results != null) {
            double[] arrayD = results;
            String[] arrayS = new String[results.length];
            int contador = 0;

            for (int i = 0; i < arrayS.length; i++) {
                arrayS[i] = this.directory.loadDocuments()[i].getName();
            }

            for (int i = 0; i < results.length; i++) {
                for (int j = i; j < results.length; j++) {
                    if (arrayD[i] < arrayD[j]) {
                        double var = arrayD[i];
                        String str = arrayS[i];
                        arrayD[i] = arrayD[j];
                        arrayS[i] = arrayS[j];
                        arrayS[j] = str;
                        arrayD[j] = var;
                    }
                }
                if (arrayD[i] < degreeOfSimilarity) {
                    break;
                }
                contador++;
            }

            String[] arrayS2 = new String[contador];

            for (int i = 0; i < contador; i++) {
                arrayS2[i] = arrayS[i];
            }
            return arrayS2;
        } else {
            return null;
        }
    }


}
