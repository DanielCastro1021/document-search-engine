import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author danielcastro
 */
public class TestQuery {


    @Test
    public void changeDirectory_TC1() {
        Query query = new Query();
        assertEquals(false, query.changeDirectory(null));
    }


    @Test
    public void changeDirectory_TC2() {
        String path = "Path tem de corresponder a um dirétorio que não existe.";
        Query query = new Query();
        assertEquals(false, query.changeDirectory(path));
    }


    @Test
    public void changeDirectory_TC3() {
        String path = "Path tem de corresponder a um dirétorio vazio.";
        Query query = new Query();
        assertEquals(false, query.changeDirectory(path));
    }


    @Disabled
    public void changeDirectory_TC4() {
        String path = "Path tem de corresponder a um dirétorio com ficheiros.";
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
    public void splitStringByWords_TC12() {
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
    public void TC20() {
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
    public void  getTotalWords_TC2() {
        Query query = new Query();
        String[][] words = {};
        assertEquals(0, query.getTotalWords(words));
    }
    @Test
    public void getTotalWords_TC3() {
        Query query = new Query();
        String[][] words = {{"Test","case"},{"Case","test"}};
        assertEquals(4, query.getTotalWords(words));
    }

    @Test
    public void getOccurrenceMatrix_TC1() {
        Query query = new Query();

        String [] uniqueWords=null;
        String[][] stringsWords = null;

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC2() {
        Query query = new Query();

        String [] uniqueWords={"Test","Case"};
        String[][] stringsWords =null;

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC3() {
        Query query = new Query();

        String [] uniqueWords=null;
        String[][] stringsWords = {{"Test"},{"Case"}};

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC4() {
        Query query = new Query();

        String [] uniqueWords={};
        String[][] stringsWords = {{"Test"},{"Case"}};

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC5() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {};

        assertArrayEquals(null, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC6() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {{}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC7() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {{"Case","test","case"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC8() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {{"Case","test","case"},{"Coding","Testing"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC9() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {{"Test","Test","Test"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=3;

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC10() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {{"Test","Test","Test"},{"Test","Test","Test"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=3;
        occurrenceMatrix[1][0]=3;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    ///
    @Test
    public void getOccurrenceMatrix_TC11() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {{"Test","Coding","Planning"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=1;

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC12() {
        Query query = new Query();

        String [] uniqueWords={"Test"};
        String[][] stringsWords = {{"Test","Coding","Planning"},{"Test","Executing","Planning"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=1;
        occurrenceMatrix[1][0]=1;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }

    @Test
    public void getOccurrenceMatrix_TC13() {
        Query query = new Query();

        String [] uniqueWords={"Testing","Coding"};
        String[][] stringsWords = {{"Test","Planning"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC14() {
        Query query = new Query();

        String [] uniqueWords={"Testing","Coding"};
        String[][] stringsWords = {{"Test","Planning"},{"Executing","Planning"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void TC38() {
        Query query = new Query();

        String [] uniqueWords={"Testing","Coding"};
        String[][] stringsWords = {{"Testing","Coding"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=1;
        occurrenceMatrix[0][1]=1;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC15() {
        Query query = new Query();

        String [] uniqueWords={"Testing","Coding"};
        String[][] stringsWords = {{"Testing","Coding"},{"Coding","Testing"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=1;
        occurrenceMatrix[0][1]=1;
        occurrenceMatrix[1][0]=1;
        occurrenceMatrix[1][1]=1;

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC16() {
        Query query = new Query();

        String [] uniqueWords={"Testing","Coding"};
        String[][] stringsWords = {{"Testing","Planning"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=1;
        occurrenceMatrix[0][1]=0;
        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }
    @Test
    public void getOccurrenceMatrix_TC17() {
        Query query = new Query();

        String [] uniqueWords={"Testing","Coding"};
        String[][] stringsWords = {{"Testing","Planning"},{"Design","Testing"}};

        int[][]occurrenceMatrix=new int[stringsWords.length][uniqueWords.length];
        occurrenceMatrix[0][0]=1;
        occurrenceMatrix[0][1]=0;
        occurrenceMatrix[1][0]=1;
        occurrenceMatrix[1][1]=0;

        assertArrayEquals(occurrenceMatrix, query.getOccurrenceMatrix(uniqueWords,stringsWords));
    }


}
