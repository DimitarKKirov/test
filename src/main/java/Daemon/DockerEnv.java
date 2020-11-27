package Daemon;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.SwarmSpec;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;


import java.io.*;
import java.util.concurrent.TimeUnit;

public class DockerEnv {

    // This class is used for setting the needed docker environment with images and containers
    // This class uses Docker-Java library to achieve its goals
    // Docker-Java is used in order operations to be made in Docker
    // without the usage of Docker CLI (writing commands in Windows command prompt)
    // base class for Docker connection and command execution


    public static DockerEnv remoteCon = new DockerEnv();
    private DockerClient dockerClient;
    DockerClientConfig standard;


    public DockerEnv getRemoteCon() {
        return remoteCon;
    }

    // creating connection to docker daemon locally,by changing the host you can change the docker daemon client
    public void connect() {
        standard = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("tcp://localhost:2375").build();
        dockerClient = DockerClientBuilder.getInstance(standard).build();


    }

    public void closeConnection() throws IOException {
        dockerClient.close();
    }

    // using the connection we are sending request to the docker daemon to pull an image from the docker repository
    // name is the name of the image that is currently available in docker repository, tag is for the version of the image
    // as in docker Cli we are able to use tag Latest for the image version
    public void pull(String name, String tag) throws InterruptedException {
        dockerClient.pullImageCmd(name)
                .withTag(tag)
                .exec(new PullImageResultCallback())
                .awaitCompletion(60, TimeUnit.SECONDS);


    }

    // when we have pulled the image from the repository we can instantiate a container from it
    // env - POSTGRES_DB name
    // ports - localeMachine-container -> 8080:5432
    // name of container must be with small letters only
    public void createContainer(String imageName, String containerName, String envV, String ports) {
        dockerClient.createContainerCmd(imageName)
                .withEnv(envV)
                .withName(containerName)
                .withPortSpecs(ports).exec();
    }
    public void createContainer(String imageName, String containerName, String ports) {
        dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withPortSpecs(ports).exec();
    }
    public void createContainer(String imageName, String containerName) {
        dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .exec();
    }




    // if there is a container we can remove it by sending the command with the name of the container
    public void removeContainer(String name) {
        dockerClient.removeContainerCmd(name).exec();
    }

    // if there is a container we can stop it by sending the command with the name of the container
    public void stopContainer(String name) {
        dockerClient.stopContainerCmd(name).exec();
    }

    // if there is a container we can start it by sending the command with the name of the container
    public void startContainer(String name) {
        dockerClient.startContainerCmd(name).exec();
    }

    // if there is a container we can kill it by sending the command with the name of the container
    public void killContainer(String name) {
        dockerClient.killContainerCmd(name).exec();
    }

    // method for creating a network that containers can be linked to
    public void createNetwork(String name) {
        dockerClient.createNetworkCmd().withName(name).exec();
    }

    // listing the available docker networks
    public void networkList() {
        dockerClient.listNetworksCmd().exec();
    }

    // creating our image from a dockerfile and assigning it a name.
    // File dockerfile -> absolute path to the dockerfile, example:

    // File mysql = new File("localPath\\Dockerfile");
    // DockerEnv con = new DockerEnv();
    // con.imageFromDockerFile(mysql,"name");
    // this method can be made to return the id of the created image bu making a String return type
    public void imageFromDockerFile(File dockerfile, String name) {
        dockerClient.buildImageCmd()
                .withDockerfile(dockerfile)
                .withTag(name)
                .start()
                .awaitImageId();
    }

    // remove image that is not currently in use
    public void removeImage(String name) {
        dockerClient.removeImageCmd(name).exec();
    }

    // initialization of a swarm with default settings
    public void swarmInit() {
        SwarmSpec spec = new SwarmSpec();
        dockerClient.initializeSwarmCmd(spec).exec();
    }

    // leaving the swarm with option for force leave
    public void leaveSwarm(Boolean force) {
        dockerClient.leaveSwarmCmd().withForceEnabled(force).exec();
    }

    // sending commands to Command Prompt
    public void sendCommand(String commandSequence) throws IOException {
        Process process = Runtime.getRuntime().exec(commandSequence);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
    // method for downloading image and creating web browser chrome standalone container,
    // with VNC,ports and name of container are flexible,ports syntax - 8080:8080
    // the vncPort is for connecting to the container with a vnc viewer

    public void standaloneChromeDebug(String containerName,String ports, String vncPort) {
        try {
            dockerClient.pullImageCmd("selenium/standalone-chrome-debug")
                    .withTag("latest")
                    .exec(new PullImageResultCallback())
                    .awaitCompletion(120, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PortBinding one = PortBinding.parse(ports);
        PortBinding two = PortBinding.parse(vncPort);
        dockerClient.createContainerCmd("selenium/standalone-chrome-debug")
                .withPortBindings(one,two)
                .withName(containerName)
                .exec();
        dockerClient.startContainerCmd(containerName).exec();
    }

    // method for downloading image and creating web browser firefox standalone container,
    // ports and name of container are flexible
    public void standaloneFireFoxDebug(String containerName,String ports, String vncPort) {
        try {
            dockerClient.pullImageCmd("selenium/standalone-firefox-debug")
                    .withTag("latest")
                    .exec(new PullImageResultCallback())
                    .awaitCompletion(120, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PortBinding one = PortBinding.parse(ports);
        PortBinding two = PortBinding.parse(vncPort);
        Volume volume = Volume.parse("/dev/shm/dev/shm");
        dockerClient.createContainerCmd("selenium/standalone-firefox-debug")
                .withVolumes(volume)
                .withPortBindings(one,two)
                .withName(containerName)
                .exec();
        dockerClient.startContainerCmd(containerName).exec();
    }
    // pulling and container creating from docker file for databases, ports syntax - 8080:8080,
    // name of container must be with small letters only
    // EXAMPLE:
    // DockerEnv con = new DockerEnv();
    // File mysql = new File("localPath\\Dockerfile");
    // File postgres = new File("localPath\\Dockerfile");
    //con.createDBContainers(mysql, "mydb","8080:3306" , postgres, "mypostdb", "8081:5432");
    public void createDBContainers(File dockerFile, String name, String port1, File dockerFile2, String secondName, String port2) {
        dockerClient.buildImageCmd()
                .withDockerfile(dockerFile)
                .withTag(name)
                .start()
                .awaitImageId();
        dockerClient.createContainerCmd(name)
                .withName(name)
                .withPortBindings(PortBinding.parse(port1))
                .exec();
        dockerClient.startContainerCmd(name).exec();

        dockerClient.buildImageCmd()
                .withDockerfile(dockerFile2)
                .withTag(secondName)
                .start()
                .awaitImageId();
        dockerClient.createContainerCmd(secondName)
                .withName(secondName)
                .withPortBindings(PortBinding.parse(port2))
                .exec();
        dockerClient.startContainerCmd(secondName).exec();
        dockerClient.removeImageCmd("mysql/mysql-server").exec();
        dockerClient.removeImageCmd("postgres").exec();
    }

}
