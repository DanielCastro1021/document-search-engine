package query;

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
     * This method returns the names of all the files in a repository, ordered by their degree of similarity with the query.
     *
     * @param directoryPath This is a String, that contains the path of a directory.
     * @param query         This is a String, that contains a query.
     * @return This returns an array of String, with the names of the files.
     */
    public String[] searchEngine(String directoryPath, String query) {
        changeDirectory(directoryPath);
        this.directory.loadDocumentsContent();
        return getResults(calculationPhase(preparationPhaseSearchMatrix(), preparationPhaseQuery(query)));
    }

    /**
     * This method returns the names of a certain number of files in a repository, ordered by their degree of similarity with the query.
     *
     * @param directoryPath This is a String, that contains the path of a directory.
     * @param query         This is a String, that contains a query.
     * @param numberOfFiles This is an integer, that contains the number of files pretended.
     * @return This returns an array of String, with the names of the files.
     */
    public String[] searchEngine(String directoryPath, String query, int numberOfFiles) {
        changeDirectory(directoryPath);
        this.directory.loadDocumentsContent();
        return getResultsByNumberOfFiles(calculationPhase(preparationPhaseSearchMatrix(), preparationPhaseQuery(query)), numberOfFiles);
    }

    /**
     * This method returns the names of files in a repository, that have a superior degree of similarity that is defined, ordered by their degree of similarity with the query.
     *
     * @param directoryPath    This is a String, that contains the path of a directory.
     * @param query            This is a String, that contains a query.
     * @param similarityDegree This is an double, that contains the minin
     * @return This returns an array of String, with the names of the files.
     */
    public String[] searchEngine(String directoryPath, String query, double similarityDegree) {
        changeDirectory(directoryPath);
        this.directory.loadDocumentsContent();
        return getResultsByDegreeOfSimilarity(calculationPhase(preparationPhaseSearchMatrix(), preparationPhaseQuery(query)), similarityDegree);
    }

    /**
     * This method return an Directory Class of a Query Class.
     *
     * @return This returns a Directory Class.
     */
    public Directory getDirectory() {
        return directory;
    }

    /**
     * This method is responsible for loading of the documents in a repository, removing their punctuation and digits, and creating a search matrix.
     *
     * @return This returns an search matrix.
     */
    public double[][] preparationPhaseSearchMatrix() {
        String[] docsStrings = this.directory.getStringFiles();
        docsStrings = cleanStrings(docsStrings);
        String[][] docsWords = splitRepositoryByWords(docsStrings);
        String[] uniqueWords = getUniqueWords(docsWords);
        return getSearchMatrix(getOccurrenceMatrix(uniqueWords, docsWords));
    }

    /**
     * This method is responsible for removing punctuation and digits of query, and create an occurrence array.
     *
     * @return This returns an occurrence array.
     */
    public int[] preparationPhaseQuery(String query) {
        String[] docsStrings = this.directory.getStringFiles();
        docsStrings = cleanStrings(docsStrings);
        String[][] docsWords = splitRepositoryByWords(docsStrings);
        String[] uniqueWords = getUniqueWords(docsWords);
        String cleanQuery = cleanString(query);
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
                for (int column = 0; column < searchMatrix[line].length; column++) {
                    sumMQ += (searchMatrix[line][column] * keyArray[column]);
                    sumM += searchMatrix[line][column];
                    sumQ += keyArray[column];
                }
                if (sumQ == 0 || sumM == 0) {
                    results[line] = 0.0;
                } else {
                    results[line] = (sumMQ / (Math.sqrt(Math.pow(sumM, 2)) * Math.sqrt(Math.pow(sumQ, 2))));
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
            return directory.loadDocuments() != null;
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
            for (String[] stringsWord : stringsWords) {
                for (String s : stringsWord) {
                    uniqueWords[k] = s;
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
            for (String[] stringsWord : stringsWords) {
                for (int j = 0; j < stringsWord.length; j++) {
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
                for (String queryWord : queryWords) {
                    if (uniqueWords[j].equals(queryWord)) {
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

        if (uniqueWords == null || stringsWords == null || uniqueWords.length <= 0 || stringsWords.length <= 0) {
            return occurrenceMatrix;
        }
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

        if (occurrenceMatrix == null) {
            return searchMatrix;
        }
        searchMatrix = new double[occurrenceMatrix.length][occurrenceMatrix[0].length];
        for (int i = 0; i < occurrenceMatrix.length; i++) {
            for (int j = 0; j < occurrenceMatrix[0].length; j++) {
                d = 0;
                for (int[] occurrenceMatrix1 : occurrenceMatrix) {
                    if (occurrenceMatrix1[j] != 0) {
                        d++;
                    }
                }
                if (d != 0) {
                    searchMatrix[i][j] = occurrenceMatrix[i][j] * (1 + Math.log10((double) occurrenceMatrix.length / d));
                }
            }
        }
        return searchMatrix;
    }

    /**
     * This method returns the files ordered by their degree of similarity (Highest to Lowest).
     *
     * @param results This is an array of doubles with the the degree of similarity of each file in the repository.
     * @return This an array of String, with the name os the files in repository.
     */
    public String[] getResults(double[] results) {
        String[] arrayS = null;
        if (results != null) {
            arrayS = this.directory.getNameFiles();
            for (int i = 0; i < results.length; i++) {
                for (int j = i; j < results.length; j++) {
                    if (results[i] < results[j]) {
                        double var = results[i];
                        String str = arrayS[i];
                        results[i] = results[j];
                        arrayS[i] = arrayS[j];
                        arrayS[j] = str;
                        results[j] = var;
                    }
                }
            }
            return arrayS;
        }
        return arrayS;
    }

    /**
     * This method returns a certain number of the files ordered by their rate of simulation (Highest to Lowest)
     *
     * @param results       This is an array of doubles with the the degree of similarity of each file in the repository.
     * @param numberOfFiles This is the number of files that the user wants to be shown in the return of this method.
     * @return This an array of String, with the name os the files in repository.
     */
    public String[] getResultsByNumberOfFiles(double[] results, int numberOfFiles) {
        String[] arrayS2 = null;

        if (results != null && numberOfFiles > 0) {
            String[] arrayS = this.directory.getNameFiles();
            int counter = 0;

            for (int i = 0; i < results.length; i++) {
                for (int j = i; j < results.length; j++) {
                    if (results[i] < results[j]) {
                        double var = results[i];
                        String str = arrayS[i];
                        results[i] = results[j];
                        arrayS[i] = arrayS[j];
                        arrayS[j] = str;
                        results[j] = var;
                    }
                }
                counter++;
                if (counter == numberOfFiles) {
                    break;
                }
            }
            arrayS2 = new String[counter];
            System.arraycopy(arrayS, 0, arrayS2, 0, counter);
            return arrayS2;
        }
        return arrayS2;
    }

    /**
     * This method returns the files ordered by their rate of simulation (Highest to Lowest)
     *
     * @param results            This is an array of doubles with the the degree of similarity of each file in the repository.
     * @param degreeOfSimilarity This is the minimum degreeOfSimilarity defined by the User.
     * @return This an array of String, with the name os the files in repository.
     */
    public String[] getResultsByDegreeOfSimilarity(double[] results, double degreeOfSimilarity) {
        int counter = 0;
        String[] arrayS2 = null;
        if (results != null) {
            String[] arrayS = this.directory.getNameFiles();

            for (int i = 0; i < results.length; i++) {
                for (int j = i; j < results.length; j++) {
                    if (results[i] < results[j]) {
                        double var = results[i];
                        String str = arrayS[i];
                        results[i] = results[j];
                        arrayS[i] = arrayS[j];
                        arrayS[j] = str;
                        results[j] = var;
                    }
                }
                if (results[i] < degreeOfSimilarity) {
                    break;
                }
                counter++;
            }
            arrayS2 = new String[counter];
            System.arraycopy(arrayS, 0, arrayS2, 0, counter);
            return arrayS2;
        }
        return arrayS2;
    }
}