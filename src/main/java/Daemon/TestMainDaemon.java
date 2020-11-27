package Daemon;

import java.io.File;

public class TestMainDaemon {

    public static void main(String[] args) {
        DockerEnv con = new DockerEnv();
        con.connect();
        File mysql = new File("D:\\SlujebenJavaAndStuff\\IdeaProjects\\MasterProject\\src\\main\\java\\MySqlData\\Dockerfile");
        File postgres = new File("D:\\SlujebenJavaAndStuff\\IdeaProjects\\MasterProject\\src\\main\\java\\PostgreData\\Dockerfile");
//        con.createDBContainers(mysql, "mydb","8080:3306" , postgres, "mypostdb", "8081:5432");
//        con.standaloneChromeDebug("alonecrome","4445:4444","4446:5900");
        con.standaloneFireFoxDebug("alonefox","4444:4444","4443:5900");
//        con.imageFromDockerFile(mysql,"name");

    }
}
