
import java.io.File;

public class Directory {


        private String DirPath;
        private File[] listOfFiles;


        public Directory(String path) {
            this.DirPath = path;
        }

        public String getDirPath() {
            return DirPath;
        }

        public void setDirPath(String DirPath) {
            this.DirPath = DirPath;
        }

        public File[] getDocuments() {
            File dir = new File(this.DirPath);
            File[] listOfFiles=null;
            if (dir != null && dir.exists()) {
                this.listOfFiles = dir.listFiles();


            }
          return listOfFiles;
        }


}
