
import java.util.Scanner;

public class Query {

    private Directory directory;

    public Query(){

        this.directory=new Directory();
    }



    /*Método que permite inserir a localização do repositório com documentos.
     *@return true se foi introduzida a path com sucesso, false se isto não acontecer.
     */
    private boolean changeDirectory(String path){
        if(path==null){
            return false;
        }else {
            directory.setDirPath(path);

            if(directory.getDocuments()==null){
                return false;
            }else{
                return true;
            }
        }
    }


    public void SearchEngine(){

        boolean insterdPath=false;


        while(!insterdPath) {
            System.out.println("\nInserir path:");

            Scanner scan=new Scanner(System.in);
            String pathDir =scan.nextLine();

            insterdPath=changeDirectory(pathDir);

        }
        System.out.println("Inseriu path!!");

    }



}
