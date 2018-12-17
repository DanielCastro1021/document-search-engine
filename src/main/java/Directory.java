import java.util.Arrays;
import java.io.File;

public class Directory {


    private String DirPath;
    private File[] listOfFiles;

    /*Método construtor que instância uma classe Directory.
     *
     */
    public Directory() {

    }


    /*Método que devolve o diretório do repositório com documentos.
     *@return DirPath path de um repositório com documentos.
     */
    public String getDirPath() {
        return DirPath;
    }


    /*Método que permite inserir/alterar  o diretório do repositório com documentos.
     *@param DirPath path de um repositório com documentos.
     */
     public void setDirPath(String DirPath) {
        this.DirPath = DirPath;
    }

    /*Método responsável por carregar ficheiros localizados na DirPath.
     *
     *@return listOfFiles retorna um array do tipo File, que contêm os documentos presentes no DirPath.
     */
    public File[] getDocuments() {
        File dir = new File(this.DirPath);

        if (dir.exists()&& dir.list().length != 0) {
            this.listOfFiles = dir.listFiles();

            if(listOfFiles!=null) {
                Arrays.sort(this.listOfFiles);
                return this.listOfFiles;
            }
        }
        return null;
    }


}
