import query.Query;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuery {

    @Test
    void changeDirectory_TC1() {
        Query query = new Query();
        assertFalse(query.changeDirectory(null));
    }

    @Test
    void changeDirectory_TC2() {
        Query query = new Query();
        assertFalse(query.changeDirectory("testDirectories/testfile"));
    }

    @Test
    void changeDirectory_TC3() {
        Query query = new Query();
        assertFalse(query.changeDirectory("testDirectories/emptyDirectory"));
    }

    @Test
    void changeDirectory_TC4() {
        Query query = new Query();
        assertTrue(query.changeDirectory("testDirectories/files"));
    }

    @Test
    void cleanString_TC1() {
        Query query = new Query();
        assertNull(query.cleanString(null));
    }

    @Test
    void cleanString_TC2() {
        Query query = new Query();
        assertEquals("Test case   ", query.cleanString("Test,case!!!"));
    }

    @Test
    void cleanString_TC3() {
        Query query = new Query();
        assertEquals("Test case   ", query.cleanString("Test case119"));
    }

    @Test
    void cleanString_TC4() {
        Query query = new Query();
        assertEquals("Test case      ", query.cleanString("Test,case1!!?2?"));
    }

    @Test
    void cleanStringArray_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.cleanStrings(null));
    }

    @Test
    void cleanStringArray_TC2() {
        Query query = new Query();
        String[] strings = {"Test,case!!!", "Case,Test!?!"};
        String[] cleanStrings = {"Test case   ", "Case Test   "};
        assertArrayEquals(cleanStrings, query.cleanStrings(strings));
    }

    @Test
    void cleanStringArray_TC3() {
        Query query = new Query();
        String[] strings = {"Test case119", "109Test000"};
        String[] cleanStrings = {"Test case   ", "   Test   "};
        assertArrayEquals(cleanStrings, query.cleanStrings(strings));
    }

    @Test
    void cleanStringArray_TC4() {
        Query query = new Query();
        String[] strings = {"Test,case1!!?2?", "??11Case99?!._12"};
        String[] cleanStrings = {"Test case      ", "    Case        "};
        assertArrayEquals(cleanStrings, query.cleanStrings(strings));
    }

    @Test
    void splitStringByWords_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.splitStringByWords(null));
    }

    @Test
    void splitStringByWords_TC2() {
        Query query = new Query();
        String str = "Test case";
        String[] words = {"Test", "case"};
        assertArrayEquals(words, query.splitStringByWords(str));
    }

    @Test
    void splitStringArrayByWords_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.splitRepositoryByWords(null));
    }

    @Test
    void splitStringArrayByWords_TC2() {
        Query query = new Query();
        String[] strings = {"Test case", "Case test"};
        String[][] words = {{"Test", "case"}, {"Case", "test"}};
        assertArrayEquals(words, query.splitRepositoryByWords(strings));
    }

    @Test
    void getUniqueWords_TC1() {
        Query query = new Query();
        assertNull(query.getUniqueWords(null));
    }

    @Test
    void getUniqueWords_TC2() {
        Query query = new Query();
        String[] strings = {"Test"};
        String[][] words = {{"Test", "Test"}, {"Test", "Test"}};

        assertArrayEquals(strings, query.getUniqueWords(words));
    }

    @Test
    void getUniqueWords_TC3() {
        Query query = new Query();
        String[][] words = {{"Test", "case"}, {"Case", "test"}};
        String[] strings = {"Test", "case", "Case", "test"};
        assertArrayEquals(strings, query.getUniqueWords(words));
    }

    @Test
    void getUniqueWords_TC4() {
        Query query = new Query();
        String[][] words = {{"Test", "case"}, {"Case", "Test"}};
        String[] strings = {"Test", "case", "Case"};
        assertArrayEquals(strings, query.getUniqueWords(words));
    }

    @Test
    void getTotalWords_TC1() {
        Query query = new Query();
        assertEquals(0, query.getTotalWords(null));
    }

    @Test
    void getTotalWords_TC2() {
        Query query = new Query();
        String[][] words = {};
        assertEquals(0, query.getTotalWords(words));
    }

    @Test
    void getTotalWords_TC3() {
        Query query = new Query();
        String[][] words = {{"Test", "case"}, {"Case", "test"}};
        assertEquals(4, query.getTotalWords(words));
    }

    @Test
    void getOccurrenceMatrix_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.getOccurrenceMatrix(null, null));
    }

    @Test
    void getOccurrenceMatrix_TC2() {
        Query query = new Query();
        String[] uniqueWords = {"Test", "Case"};
        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, null));
    }

    @Test
    void getOccurrenceMatrix_TC3() {
        Query query = new Query();
        String[][] stringsWords = {{"Test"}, {"Case"}};
        assertArrayEquals(null, query.getOccurrenceMatrix(null, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC4() {
        Query query = new Query();
        String[] uniqueWords = {};
        String[][] stringsWords = {{"Test"}, {"Case"}};
        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC5() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {};
        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC6() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC7() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Case", "test", "case"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC8() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Case", "test", "case"}, {"Coding", "Testing"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC9() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Test", "Test"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 3;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC10() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Test", "Test"}, {"Test", "Test", "Test"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 3;
        occurrenceMatrix[1][0] = 3;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC11() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Coding", "Planning"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC12() {
        Query query = new Query();
        String[] uniqueWords = {"Test"};
        String[][] stringsWords = {{"Test", "Coding", "Planning"}, {"Test", "Executing", "Planning"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[1][0] = 1;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC13() {
        Query query = new Query();
        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Test", "Planning"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC14() {
        Query query = new Query();
        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Test", "Planning"}, {"Executing", "Planning"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC15() {
        Query query = new Query();
        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Testing", "Coding"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[0][1] = 1;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC16() {
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
    void getOccurrenceMatrix_TC17() {
        Query query = new Query();
        String[] uniqueWords = {"Testing", "Coding"};
        String[][] stringsWords = {{"Testing", "Planning"}};
        int[][] occurrenceMatrix = new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0] = 1;
        occurrenceMatrix[0][1] = 0;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords, stringsWords));
    }

    @Test
    void getOccurrenceMatrix_TC18() {
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
    void getOccurrenceArray_TC1() {
        Query query = new Query();
        String[] uniqueWords = {"tudo", "bem"};
        String[] queryWords = {"Ola"};
        int[] expected = {0, 0};
        assertArrayEquals(expected, query.getOccurrenceArray(uniqueWords, queryWords));
    }

    @Test
    void getOccurrenceArray_TC2() {
        Query query = new Query();
        String[] uniqueWords = {"Ola", "tudo", "bem"};
        String[] queryWords = {"Ola"};
        int[] expected = {1, 0, 0};
        assertArrayEquals(expected, query.getOccurrenceArray(uniqueWords, queryWords));
    }

    @Test
    void getOccurrenceArray_TC3() {
        Query query = new Query();
        String[] uniqueWords = {"Ola", "tudo", "bem"};
        String[] queryWords = {"Ola", "Ola"};
        int[] expected = {2, 0, 0};
        assertArrayEquals(expected, query.getOccurrenceArray(uniqueWords, queryWords));
    }

    @Test
    void getOccurrenceArray_TC4() {
        Query query = new Query();
        String[] queryWords = {"Ola", "tudo"};
        assertArrayEquals(null, query.getOccurrenceArray(null, queryWords));
    }

    @Test
    void getOccurrenceArray_TC5() {
        Query query = new Query();
        String[] uniqueWords = {"Ola", "tudo"};
        assertArrayEquals(null, query.getOccurrenceArray(uniqueWords, null));
    }

    @Test
    void getOccurrenceArray_TC6() {
        Query query = new Query();
        assertArrayEquals(null, query.getOccurrenceArray(null, null));
    }

    @Test
    void getSearchMatrix_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.getSearchMatrix(null));
    }

    @Test
    void getSearchMatrix_TC2() {
        Query query = new Query();
        int[][] occurrenceMatrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] expected = {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}};
        double[][] results = query.getSearchMatrix(occurrenceMatrix);
        assertArrayEquals(expected, results);
    }

    @Test
    void getSearchMatrix_TC3() {
        Query query = new Query();
        int[][] occurrenceMatrix = {{-2, -2, -2}, {-2, -2, -2}, {-2, -2, -2}};
        double[][] expected = {{-2.0, -2.0, -2.0}, {-2.0, -2.0, -2.0}, {-2.0, -2.0, -2.0}};
        double[][] results = query.getSearchMatrix(occurrenceMatrix);
        assertArrayEquals(expected, results);
    }

    @Test
    void getSearchMatrix_TC4() {
        Query query = new Query();
        int[][] occurrenceMatrix = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        double[][] expected = {{2.0, 2.0, 2.0}, {2.0, 2.0, 2.0}, {2.0, 2.0, 2.0}};
        double[][] results = query.getSearchMatrix(occurrenceMatrix);
        assertArrayEquals(expected, results);
    }

    @Test
    void getSearchMatrix_TC5() {
        Query query = new Query();
        int[][] occurrenceMatrix = {{0, -2, 0}, {-2, 0, 0}, {0, 0, -2}};
        double[][] expected = {{0.0, -2.9542425094393249, 0.0}, {-2.9542425094393249, 0.0, 0.0}, {0.0, 0.0, -2.9542425094393249}};
        double[][] results = query.getSearchMatrix(occurrenceMatrix);
        assertArrayEquals(expected, results);
    }

    @Test
    void getSearchMatrix_TC6() {
        Query query = new Query();
        int[][] occurrenceMatrix = {{2, 0, 2}, {2, 0, 0}, {0, 2, 0}};
        double[][] expected = {{2.3521825181113625, 0.0, 2.9542425094393249}, {2.3521825181113625, 0.0, 0.0}, {0.0, 2.9542425094393249, 0.0}};
        double[][] results = query.getSearchMatrix(occurrenceMatrix);
        assertArrayEquals(expected, results);
    }

    @Test
    void getSearchMatrix_TC7() {
        Query query = new Query();
        int[][] occurrenceMatrix = {{-2, 1, -1}, {2, 3, -1}, {3, -1, 2}};
        double[][] expected = {{-2.0, 1.0, -1.0}, {2.0, 3.0, -1.0}, {3.0, -1.0, 2.0}};
        double[][] results = query.getSearchMatrix(occurrenceMatrix);
        assertArrayEquals(expected, results);
    }

    @Test
    void getSearchMatrix_TC8() {
        Query query = new Query();
        int[][] occurrenceMatrix = {{0, 2, -2}, {-1, 0, 1}, {3, 1, 0}};
        double[][] expected = {{0.0, 2.3521825181113625, -2.3521825181113625}, {-1.17609125905568124, 0.0, 1.17609125905568124}, {3.528273777167044, 1.17609125905568124, 0.0}};
        double[][] results = query.getSearchMatrix(occurrenceMatrix);
        assertArrayEquals(expected, results);
    }

    @Test
    void getResults_TC1() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        assertArrayEquals(null, query.getResults(null));
    }

    @Test
    void getResults_TC2() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {0, 0, 0, 0};
        String[] expected = {"1.txt", "2.txt", "3.txt", "4.txt"};
        assertArrayEquals(expected, query.getResults(input));
    }

    @Test
    void getResults_TC3() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {-2, -4, -1, -3};
        String[] expected = {"3.txt", "1.txt", "4.txt", "2.txt"};
        assertArrayEquals(expected, query.getResults(input));
    }

    @Test
    void getResults_TC4() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {5, 7, 8, 2};
        String[] expected = {"3.txt", "2.txt", "1.txt", "4.txt"};
        assertArrayEquals(expected, query.getResults(input));
    }


    @Test
    void getResults_TC5() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {0, -7, 0, -2};
        String[] expected = {"1.txt", "3.txt", "4.txt", "2.txt"};
        assertArrayEquals(expected, query.getResults(input));
    }

    @Test
    void getResults_TC6() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {0, 7, 0, 2};
        String[] expected = {"2.txt", "4.txt", "3.txt", "1.txt"};
        assertArrayEquals(expected, query.getResults(input));
    }

    @Test
    void getResults_TC7() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {-5, 7, -8, 2};
        String[] expected = {"2.txt", "4.txt", "1.txt", "3.txt"};
        assertArrayEquals(expected, query.getResults(input));
    }

    @Test
    void getResults_TC8() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {-5, 7, 0, 2};
        String[] expected = {"2.txt", "4.txt", "3.txt", "1.txt"};
        assertArrayEquals(expected, query.getResults(input));
    }

    @Test
    void getResultsByNumberOfFiles_TC1() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        assertArrayEquals(null, query.getResultsByNumberOfFiles(null, -2));
    }

    @Test
    void getResultsByNumberOfFiles_TC2() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        assertArrayEquals(null, query.getResultsByNumberOfFiles(null, 0));
    }

    @Test
    void getResultsByNumberOfFiles_TC3() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        assertArrayEquals(null, query.getResultsByNumberOfFiles(null, 2));
    }

    @Test
    void getResultsByNumberOfFiles_TC4() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {5, 7, 8, 2};
        assertArrayEquals(null, query.getResultsByNumberOfFiles(input, -2));
    }

    @Test
    void getResultsByNumberOfFiles_TC5() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {5, 7, 8, 2};
        assertArrayEquals(null, query.getResultsByNumberOfFiles(input, 0));
    }

    @Test
    void getResultsByNumberOfFiles_TC6() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {5, 7, 8, 2};
        String[] expected = {"3.txt", "2.txt"};
        assertArrayEquals(expected, query.getResultsByNumberOfFiles(input, 2));
    }

    @Test
    void getResultsByDegreeOfSimilarity_TC1() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        assertArrayEquals(null, query.getResultsByDegreeOfSimilarity(null, -2));
    }

    @Test
    void getResultsByDegreeOfSimilarity_TC2() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        assertArrayEquals(null, query.getResultsByDegreeOfSimilarity(null, 0));
    }

    @Test
    void getResultsByDegreeOfSimilarity_TC3() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        assertArrayEquals(null, query.getResultsByDegreeOfSimilarity(null, 2));
    }

    @Test
    void getResultsByDegreeOfSimilarity_TC4() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {5, 7, 8, 2};
        String[] expected = {"3.txt", "2.txt", "1.txt", "4.txt"};
        assertArrayEquals(expected, query.getResultsByDegreeOfSimilarity(input, -2));
    }

    @Test
    void getResultsByDegreeOfSimilarity_TC5() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {5, 7, 8, 2};
        String[] expected = {"3.txt", "2.txt", "1.txt", "4.txt"};
        assertArrayEquals(expected, query.getResultsByDegreeOfSimilarity(input, 0));
    }

    @Test
    void getResultsByDegreeOfSimilarity_TC6() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        double[] input = {5, 7, 8, 2};
        String[] expected = {"3.txt", "2.txt"};
        assertArrayEquals(expected, query.getResultsByDegreeOfSimilarity(input, 6));
    }

    @Test
    void preparationPhaseQuery_TC1() {
        Query query = new Query();
        query.changeDirectory("testDirectories/file");
        query.getDirectory().loadDocumentsContent();
        assertArrayEquals(null, query.preparationPhaseQuery(null));
    }

    @Test
    void preparationPhaseQuery_TC2() {
        Query query = new Query();
        query.changeDirectory("testDirectories/file");
        query.getDirectory().loadDocumentsContent();
        assertArrayEquals(null, query.preparationPhaseQuery(" "));
    }

    @Test
    void preparationPhaseQuery_TC3() {
        Query query = new Query();
        query.changeDirectory("testDirectories/file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Test"));
    }

    @Test
    void preparationPhaseQuery_TC4() {
        Query query = new Query();
        query.changeDirectory("testDirectories/file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Test Case"));
    }

    @Test
    void preparationPhaseQuery_TC5() {
        Query query = new Query();
        query.changeDirectory("testDirectories/file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        arrayQuery[0] = 1;
        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Olá"));
    }

    @Test
    void preparationPhaseQuery_TC6() {
        Query query = new Query();
        query.changeDirectory("testDirectories/file");
        query.getDirectory().loadDocumentsContent();
        int[] arrayQuery = new int[2];
        arrayQuery[0] = 1;
        arrayQuery[1] = 1;
        assertArrayEquals(arrayQuery, query.preparationPhaseQuery("Olá Programador."));
    }

    @Test
    void preparationPhaseSearchMatrix_TC1() {
        Query query = new Query();
        query.changeDirectory("testF");
        query.getDirectory().loadDocumentsContent();
        assertArrayEquals(null, query.preparationPhaseSearchMatrix());

    }

    @Test
    void preparationPhaseSearchMatrix_TC2() {
        Query query = new Query();
        query.changeDirectory("testDirectories/emptyDirectory");
        query.getDirectory().loadDocumentsContent();
        assertArrayEquals(null, query.preparationPhaseSearchMatrix());
    }

    @Test
    void preparationPhaseSearchMatrix_TC3() {
        Query query = new Query();
        query.changeDirectory("testDirectories/emptyFile");
        query.getDirectory().loadDocumentsContent();
        assertArrayEquals(null, query.preparationPhaseSearchMatrix());
    }

    @Test
    void preparationPhaseSearchMatrix_TC4() {
        Query query = new Query();
        query.changeDirectory("testDirectories/file");
        query.getDirectory().loadDocumentsContent();
        double[][] searchMatrix = new double[1][2];
        searchMatrix[0][0] = 1.0;
        searchMatrix[0][1] = 1.0;
        assertArrayEquals(searchMatrix, query.preparationPhaseSearchMatrix());
    }

    @Test
    void preparationPhaseSearchMatrix_TC5() {
        Query query = new Query();
        query.changeDirectory("testDirectories/emptyFiles");
        query.getDirectory().loadDocumentsContent();
        assertArrayEquals(null, query.preparationPhaseSearchMatrix());
    }

    @Test
    void preparationPhaseSearchMatrix_TC6() {
        Query query = new Query();
        query.changeDirectory("testDirectories/files");
        query.getDirectory().loadDocumentsContent();
        double[][] searchMatrix = new double[query.getDirectory().loadDocuments().length][13];
        searchMatrix[0][0] = 1.6020599913279625;
        searchMatrix[0][1] = 1.6020599913279625;
        searchMatrix[1][2] = 1.6020599913279625;
        searchMatrix[1][3] = 1.6020599913279625;
        searchMatrix[2][4] = 1.6020599913279625;
        searchMatrix[2][5] = 1.6020599913279625;
        searchMatrix[2][6] = 1.6020599913279625;
        searchMatrix[2][7] = 1.6020599913279625;
        searchMatrix[2][8] = 1.6020599913279625;
        searchMatrix[3][9] = 1.6020599913279625;
        searchMatrix[3][10] = 1.6020599913279625;
        searchMatrix[3][11] = 1.6020599913279625;
        searchMatrix[3][12] = 1.6020599913279625;
        assertArrayEquals(searchMatrix, query.preparationPhaseSearchMatrix());
    }

    @Test
    void calculationPhase_TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.calculationPhase(null, null));
    }

    @Test
    void calculationPhase_TC2() {
        Query query = new Query();
        double[][] searchMatrix = {{1, 0}, {0, 1}};
        assertArrayEquals(null, query.calculationPhase(searchMatrix, null));
    }

    @Test
    void calculationPhase_TC3() {
        Query query = new Query();
        int[] keyArray = {1, 0};
        assertArrayEquals(null, query.calculationPhase(null, keyArray));
    }

    @Test
    void calculationPhase_TC4() {
        Query query = new Query();
        int[] keyArray = {1, 0};
        double[][] searchMatrix = {{1, 0}, {0, 1}};
        double[] expected = {1, 0.25};
        assertArrayEquals(expected, query.calculationPhase(searchMatrix, keyArray));
    }

    @Test
    void calculationPhase_TC5() {
        Query query = new Query();
        int[] keyArray = {1, 0};
        double[][] searchMatrix = {{0, 0}, {0, 0}};
        double[] expected = {0, 0};
        assertArrayEquals(expected, query.calculationPhase(searchMatrix, keyArray));
    }

    @Test
    void calculationPhase_TC6() {
        Query query = new Query();
        int[] keyArray = {0, 0};
        double[][] searchMatrix = {{1, 0}, {2, 1}};
        double[] expected = {0, 0};
        assertArrayEquals(expected, query.calculationPhase(searchMatrix, keyArray));
    }

    @Test
    void searchEngine_1TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine(null, ""));
    }

    @Test
    void searchEngine_1TC2() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testF", ""));
    }

    @Test
    void searchEngine_1TC3() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/emptyDirectory", ""));
    }

    @Test
    void searchEngine_1TC4() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/emptyFiles", ""));
    }

    @Test
    void searchEngine_1TC5() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/emptyFiles", null));
    }

    @Test
    void searchEngine_1TC6() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/emptyFiles", " "));
    }

    @Test
    void searchEngine_1TC7() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/emptyFiles", "Test"));
    }

    @Test
    void searchEngine_1TC8() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/emptyFiles", "Test Case"));
    }

    @Test
    void searchEngine_1TC9() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/files", " "));
    }

    @Test
    void searchEngine_1TC10() {
        Query query = new Query();
        String[] expected = {"1.txt", "2.txt", "3.txt", "4.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Test"));
    }

    @Test
    void searchEngine_1TC11() {
        Query query = new Query();
        String[] expected = {"1.txt", "2.txt", "3.txt", "4.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Test Case"));
    }

    @Test
    void searchEngine_1TC12() {
        Query query = new Query();
        String[] expected = {"1.txt", "2.txt", "3.txt", "4.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Hello"));
    }

    @Test
    void searchEngine_1TC13() {
        Query query = new Query();
        String[] expected = {"2.txt", "3.txt", "4.txt", "1.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Rabbit pork"));
    }

    @Test
    void searchEngine_2TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/files", null, 2));
    }

    @Test
    void searchEngine_2TC2() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine(null, "Hello", 2));
    }

    @Test
    void searchEngine_2TC3() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/files", "Hello", -2));
    }

    @Test
    void searchEngine_2TC4() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/files", "Hello", 0));
    }

    @Test
    void searchEngine_2TC5() {
        Query query = new Query();
        String[] expected = {"1.txt", "2.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Hello", 2));
    }

    @Test
    void searchEngine_3TC1() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine("testDirectories/files", null, 0.5));
    }

    @Test
    void searchEngine_3TC2() {
        Query query = new Query();
        assertArrayEquals(null, query.searchEngine(null, "Hello", 0.5));
    }

    @Test
    void searchEngine_3TC3() {
        Query query = new Query();
        String[] expected = {"1.txt", "2.txt", "3.txt", "4.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Hello", -0.5));
    }

    @Test
    void searchEngine_3TC4() {
        Query query = new Query();
        String[] expected = {"1.txt", "2.txt", "3.txt", "4.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Hello", 0.0));
    }

    @Test
    void searchEngine_3TC5() {
        Query query = new Query();
        String[] expected = {"1.txt"};
        assertArrayEquals(expected, query.searchEngine("testDirectories/files", "Hello", 0.5));
    }


}

