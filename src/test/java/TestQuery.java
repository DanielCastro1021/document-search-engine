import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestQuery {


    @Test
    public void changeDirectory_TC1() {
        Query query = new Query();
        assertEquals(false, query.changeDirectory(null));
    }


    @Test
    public void changeDirectory_TC2() {
        String path = "file";
        Query query = new Query();
        assertEquals(false, query.changeDirectory(path));
    }


    @Test
    public void changeDirectory_TC3() {
        String path = "filestest";
        Query query = new Query();
        assertEquals(false, query.changeDirectory(path));
    }


    @Disabled
    public void changeDirectory_TC4() {
        String path = "files";
        Query query = new Query();
        assertEquals(true, query.changeDirectory(path));
    }


    @Test
    public void cleanString_TC1() {
        Query query = new Query();
        assertEquals(null, query.cleanString(null));
    }

    @Test
    public void cleanString_TC2() {
        Query query = new Query();
        assertEquals("Test case   ", query.cleanString("Test,case!!!"));
    }


    @Test
    public void cleanString_TC3() {
        Query query = new Query();
        assertEquals("Test case   ", query.cleanString("Test case119"));
    }


    @Test
    public void cleanString_TC4() {
        Query query = new Query();
        assertEquals("Test case      ", query.cleanString("Test,case1!!?2?"));
    }


    @Test
    public void cleanStringArray_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.cleanStrings(null));
    }


    @Test
    public void cleanStringArray_TC2() {
        Query query = new Query();

        String[] strings = {"Test,case!!!", "Case,Test!?!"};
        String[] cleanStrings = {"Test case   ", "Case Test   "};

        assertArrayEquals(cleanStrings, query.cleanStrings(strings));
    }


    @Test
    public void cleanStringArray_TC3() {
        Query query = new Query();

        String[] strings = {"Test case119", "109Test000"};
        String[] cleanStrings = {"Test case   ", "   Test   "};

        assertArrayEquals(cleanStrings, query.cleanStrings(strings));
    }


    @Test
    public void cleanStringArray_TC4() {
        Query query = new Query();

        String[] strings = {"Test,case1!!?2?", "??11Case99?!._12"};
        String[] cleanStrings = {"Test case      ", "    Case        "};

        assertArrayEquals(cleanStrings, query.cleanStrings(strings));
    }


    @Test
    public void splitStringByWords_TC1() {
        Query query = new Query();

        assertArrayEquals(null, query.splitStringByWords(null));
    }


    @Test
    public void splitStringByWords_TC2() {
        Query query = new Query();
        String str = "Test case";
        String[] words = {"Test", "case"};
        assertArrayEquals(words, query.splitStringByWords(str));
    }


    @Test
    public void splitStringArrayByWords_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.splitRepositoryByWords(null));
    }


    @Test
    public void splitStringArrayByWords_TC2() {
        Query query = new Query();
        String[] strings = {"Test case", "Case test"};
        String[][] words = {{"Test", "case"}, {"Case", "test"}};

        assertArrayEquals(words, query.splitRepositoryByWords(strings));
    }

    @Test
    public void getUniqueWords_TC1() {
        Query query = new Query();
        assertEquals(null, query.getUniqueWords(null));
    }

    @Test
    public void getUniqueWords_TC2() {
        Query query = new Query();
        String[] strings = {"Test"};
        String[][] words = {{"Test", "Test"}, {"Test", "Test"}};

        assertArrayEquals(strings, query.getUniqueWords(words));
    }

    @Test
    public void getUniqueWords_TC3() {
        Query query = new Query();
        String[][] words = {{"Test", "case"}, {"Case", "test"}};
        String[] strings = {"Test", "case", "Case", "test"};

        assertArrayEquals(strings, query.getUniqueWords(words));
    }

    @Test
    public void getUniqueWords_TC4() {
        Query query = new Query();
        String[][] words = {{"Test", "case"}, {"Case", "Test"}};
        String[] strings = {"Test", "case", "Case"};

        assertArrayEquals(strings, query.getUniqueWords(words));
    }

    @Test
    public void getTotalWords_TC1() {
        Query query = new Query();
        assertEquals(0, query.getTotalWords(null));
    }

    @Test
    public void getTotalWords_TC2() {
        Query query = new Query();
        String[][] words = {};
        assertEquals(0, query.getTotalWords(words));
    }

    @Test
    public void getTotalWords_TC3() {
        Query query = new Query();
        String[][] words = {{"Test", "case"}, {"Case", "test"}};
        assertEquals(4, query.getTotalWords(words));
    }

    @Test
    public void getOccurrenceMatrix_TC1() {
        Query query = new Query();

        String[] uniqueWords = null;
        String[][] stringsWords = null;

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC2() {
        Query query = new Query();

        String[] uniqueWords = {"Test", "Case"};
        String[][] stringsWords = null;

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC3() {
        Query query = new Query();

        String[] uniqueWords = null;
        String[][] stringsWords = {{"Test"}, {"Case"}};

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC4() {
        Query query = new Query();

        String[] uniqueWords = {};
        String[][] stringsWords = {{"Test"}, {"Case"}};

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC5() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {};

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC6() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC7() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Case", "test", "case"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC8() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Case", "test", "case"}, {"Coding", "Testing"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC9() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Test", "Test"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 3;

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC10() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Test", "Test"}, {"Test", "Test", "Test"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 3;
        occurrenceMatrix[1][0] = 3;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    ///
    @Test
    public void getOccurrenceMatrix_TC11() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Coding", "Planning"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC12() {
        Query query = new Query();

        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Coding", "Planning"}, {"Test", "Executing", "Planning"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[1][0] = 1;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC13() {
        Query query = new Query();

        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Test", "Planning"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC14() {
        Query query = new Query();

        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Test", "Planning"}, {"Executing", "Planning"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC15() {
        Query query = new Query();

        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Testing", "Coding"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[0][1] = 1;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC16() {
        Query query = new Query();

        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Testing", "Coding"}, {"Coding", "Testing"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[0][1] = 1;
        occurrenceMatrix[1][0] = 1;
        occurrenceMatrix[1][1] = 1;

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC17() {
        Query query = new Query();

        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Testing", "Planning"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[0][1] = 0;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC18() {
        Query query = new Query();

        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Testing", "Planning"}, {"Design", "Testing"}};

        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[0][1] = 0;
        occurrenceMatrix[1][0] = 1;
        occurrenceMatrix[1][1] = 0;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    public void getSearchMatrix_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.getSearchMatrix(null));
    }

    @Test
    public void getSearchMatrix_TC2() {
        Query query = new Query();

        int[][] occurrenceMatrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] esperado = {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}};
        double[][] resultado = query.getSearchMatrix(occurrenceMatrix);

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getSearchMatrix_TC3() {
        Query query = new Query();

        int[][] occurrenceMatrix = {{-2, -2, -2}, {-2, -2, -2}, {-2, -2, -2}};
        double[][] esperado = {{-2.0, -2.0, -2.0}, {-2.0, -2.0, -2.0}, {-2.0, -2.0, -2.0}};
        double[][] resultado = query.getSearchMatrix(occurrenceMatrix);

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getSearchMatrix_TC4() {
        Query query = new Query();

        int[][] occurrenceMatrix = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        double[][] esperado = {{2.0, 2.0, 2.0}, {2.0, 2.0, 2.0}, {2.0, 2.0, 2.0}};
        double[][] resultado = query.getSearchMatrix(occurrenceMatrix);

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getSearchMatrix_TC5() {
        Query query = new Query();

        int[][] occurrenceMatrix = {{0, -2, 0}, {-2, 0, 0}, {0, 0, -2}};
        double[][] esperado = {{0.0, -2.9542425094393249, 0.0}, {-2.9542425094393249, 0.0, 0.0}, {0.0, 0.0, -2.9542425094393249}};
        double[][] resultado = query.getSearchMatrix(occurrenceMatrix);

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getSearchMatrix_TC6() {
        Query query = new Query();

        int[][] occurrenceMatrix = {{2, 0, 2}, {2, 0, 0}, {0, 2, 0}};
        double[][] esperado = {{2.3521825181113625, 0.0, 2.9542425094393249}, {2.3521825181113625, 0.0, 0.0}, {0.0, 2.9542425094393249, 0.0}};
        double[][] resultado = query.getSearchMatrix(occurrenceMatrix);

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getSearchMatrix_TC7() {
        Query query = new Query();

        int[][] occurrenceMatrix = {{-2, 1, -1}, {2, 3, -1}, {3, -1, 2}};
        double[][] esperado = {{-2.0, 1.0, -1.0}, {2.0, 3.0, -1.0}, {3.0, -1.0, 2.0}};
        double[][] resultado = query.getSearchMatrix(occurrenceMatrix);

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getSearchMatrix_TC8() {
        Query query = new Query();

        int[][] occurrenceMatrix = {{0, 2, -2}, {-1, 0, 1}, {3, 1, 0}};
        double[][] esperado = {{0.0, 2.3521825181113625, -2.3521825181113625}, {-1.17609125905568124, 0.0, 1.17609125905568124}, {3.528273777167044, 1.17609125905568124, 0.0}};
        double[][] resultado = query.getSearchMatrix(occurrenceMatrix);

        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void getResults_TC1() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();
        assertArrayEquals(null, query.getResults(null));
    }

    @Test
    public void getResults_TC2() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {0, 0, 0, 0};
        String[] esperado = {"1.txt", "2.txt", "3.txt", "4.txt"};

        assertArrayEquals(esperado, query.getResults(input));
    }

    @Test
    public void getResults_TC3() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {-2, -4, -1, -3};
        String[] esperado = {"3.txt", "1.txt", "4.txt", "2.txt"};

        assertArrayEquals(esperado, query.getResults(input));
    }

    @Test
    public void getResults_TC4() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {5, 7, 8, 2};
        String[] esperado = {"3.txt", "2.txt", "1.txt", "4.txt"};

        assertArrayEquals(esperado, query.getResults(input));
    }


    @Test
    public void getResults_TC5() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {0, -7, 0, -2};
        String[] esperado = {"1.txt", "3.txt", "4.txt", "2.txt"};

        assertArrayEquals(esperado, query.getResults(input));
    }

    @Test
    public void getResults_TC6() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {0, 7, 0, 2};
        String[] esperado = {"2.txt", "4.txt", "3.txt", "1.txt"};

        assertArrayEquals(esperado, query.getResults(input));
    }

    @Test
    public void getResults_TC7() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {-5, 7, -8, 2};
        String[] esperado = {"2.txt", "4.txt", "1.txt", "3.txt"};

        assertArrayEquals(esperado, query.getResults(input));
    }

    @Test
    public void getResults_TC8() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {-5, 7, 0, 2};
        String[] esperado = {"2.txt", "4.txt", "3.txt", "1.txt"};

        assertArrayEquals(esperado, query.getResults(input));
    }

    @Test
    public void getResultsByNumberOfFiles_TC1() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        assertArrayEquals(null, query.getResultsByNumberOfFiles(null, -2));
    }

    @Test
    public void getResultsByNumberOfFiles_TC2() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        assertArrayEquals(null, query.getResultsByNumberOfFiles(null, 0));
    }

    @Test
    public void getResultsByNumberOfFiles_TC3() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        assertArrayEquals(null, query.getResultsByNumberOfFiles(null, 2));
    }

    @Test
    public void getResultsByNumberOfFiles_TC4() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {5, 7, 8, 2};

        assertArrayEquals(null, query.getResultsByNumberOfFiles(input, -2));
    }

    @Test
    public void getResultsByNumberOfFiles_TC5() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {5, 7, 8, 2};

        assertArrayEquals(null, query.getResultsByNumberOfFiles(input, 0));
    }

    @Test
    public void getResultsByNumberOfFiles_TC6() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {5, 7, 8, 2};
        String[] esperado = {"3.txt", "2.txt"};

        assertArrayEquals(esperado, query.getResultsByNumberOfFiles(input, 2));
    }

    @Test
    public void getResultsByDegreeOfSimilarity_TC1() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        assertArrayEquals(null, query.getResultsByDegreeOfSimilarity(null, -2));
    }

    @Test
    public void getResultsByDegreeOfSimilarity_TC2() throws FileNotFoundException {
        Directory directory = new Directory();
        Query query = new Query();
        directory.setDirectoryPath("files");

        assertArrayEquals(null, query.getResultsByDegreeOfSimilarity(null, 0));
    }

    @Test
    public void getResultsByDegreeOfSimilarity_TC3() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        assertArrayEquals(null, query.getResultsByDegreeOfSimilarity(null, 2));
    }

    @Test
    public void getResultsByDegreeOfSimilarity_TC4() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {5, 7, 8, 2};
        String[] esperado = {"3.txt", "2.txt", "1.txt", "4.txt"};

        assertArrayEquals(esperado, query.getResultsByDegreeOfSimilarity(input, -2));
    }

    @Test
    public void getResultsByDegreeOfSimilarity_TC5() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {5, 7, 8, 2};
        String[] esperado = {"3.txt", "2.txt", "1.txt", "4.txt"};

        assertArrayEquals(esperado, query.getResultsByDegreeOfSimilarity(input, 0));
    }

    @Test
    public void getResultsByDegreeOfSimilarity_TC6() throws FileNotFoundException {
        Query query = new Query();
        query.getDirectory().setDirectoryPath("files");
        query.getDirectory().loadDocumentsContent();

        double[] input = {5, 7, 8, 2};
        String[] esperado = {"3.txt", "2.txt"};

        assertArrayEquals(esperado, query.getResultsByDegreeOfSimilarity(input, 6));
    }

    @Test
    public void preparationPhaseQuery_TC1() throws FileNotFoundException {
        Query query = new Query();
        query.changeDirectory("file");
        query.getDirectory().loadDocumentsContent();


        assertArrayEquals(null, query.preparationPhaseQuery(null));
    }

    @Test
    public void preparationPhaseQuery_TC2() throws FileNotFoundException {
        Query query = new Query();
        query.changeDirectory("file");
        query.getDirectory().loadDocumentsContent();

        assertArrayEquals(null, query.preparationPhaseQuery(" "));
    }

    @Test
    public void preparationPhaseQuery_TC3() throws FileNotFoundException {
        Query query = new Query();
        query.changeDirectory("file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        arrayQuery[0] = 0;
        arrayQuery[1] = 0;

        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Test"));
    }

    @Test
    public void preparationPhaseQuery_TC4() throws FileNotFoundException {
        Query query = new Query();
        query.changeDirectory("file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        arrayQuery[0] = 0;
        arrayQuery[1] = 0;
        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Test Case"));
    }

    @Test
    public void preparationPhaseQuery_TC5() throws FileNotFoundException {
        Query query = new Query();
        query.changeDirectory("file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        arrayQuery[0] = 1;
        arrayQuery[1] = 0;
        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Olá"));
    }

    @Test
    public void preparationPhaseQuery_TC6() throws FileNotFoundException {
        Query query = new Query();
        query.changeDirectory("file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        arrayQuery[0] = 1;
        arrayQuery[1] = 1;
        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Olá Programador."));
    }


    @Test
    public void preparationPhaseMatrix_TC1() throws FileNotFoundException {
        Query query = new Query();
        query.changeDirectory("file");
        query.getDirectory().loadDocumentsContent();

    }


}
