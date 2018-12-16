package PACKAGE_NAME;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestQuery {

    //Path null
    @Test
    public void TC1_1(){
        String path = "null";
        boolean resultado = false;
        boolean valor;
        Query query = new Query();
        valor = query.changeDirectory(path);
        assertEquals(resultado,valor);
    }

    //Pasta vazia
    @Test
    public void TC1_2() {
        String path = "C:\\Users\\windows\\Desktop\\Nova Pasta";
        boolean resultado = false;
        boolean valor;
        Query query = new Query();
        valor = query.changeDirectory(path);
        assertEquals(resultado, valor);
    }

    //Pasta com ficheiro
    @Test
    public void TC1_3(){
        String path = "C:\\Users\\windows\\Desktop\\ola";
        boolean resultado = true;
        boolean valor;
        Query query = new Query();
        valor = query.changeDirectory(path);
        assertEquals(resultado,valor);
    }

}
