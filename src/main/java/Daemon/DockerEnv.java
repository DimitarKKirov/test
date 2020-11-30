package Daemon;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.SwarmSpec;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;


import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class is used for setting the needed docker environment with images and containers
 *  This class uses Docker-Java library to achieve its goals
 *  Docker-Java is used in order operations to be made in Docker
 *  without the usage of Docker CLI (writing commands in Windows command prompt)
 *  base class for Docker connection and command execution
 */
public class DockerEnv {




    private DockerClient dockerClient;
    DockerClientConfig standard;

    /**
     * creating connection to docker daemon locally,by changing the host you can change the docker daemon client
     * In order this to happen the port of the DAEMON must be exposed, for Windows Docker there is option in
     * Settings->General Tab->Expose daemon on tcp://localhost:2375 without TLS : this is ok for local use
     * not recommended for remote, if possible Expose the port with TLS
     */
    public void connect() {
        standard = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("tcp://localhost:2375").build();
        dockerClient = DockerClientBuilder.getInstance(standard).build();


    }


    public void closeConnection() throws IOException {
        dockerClient.close();
    }


    /**
     * using the connection we are sending request to the docker daemon to pull an image from the docker repository
     * name is the name of the image that is currently available in docker repository, tag is for the version of the image
     * as in docker Cli we are able to use tag Latest for the image version
     *
     * @param name target name of the image of Docker repository
     * @param tag target image version, default is "latest"
     * @throws InterruptedException
     */
    public void pull(String name, String tag) throws InterruptedException {
        dockerClient.pullImageCmd(name)
                .withTag(tag)
                .exec(new PullImageResultCallback())
                .awaitCompletion(60, TimeUnit.SECONDS);


    }

    /**
     * when we have pulled the image from the repository we can instantiate a container from it
     *
     * @param imageName the name of the image that we want to create container from
     * @param containerName the name of the container that we wil create, the name must be with small latter's only
     * @param envV environment variables, if you are creating postgres DB for example :
     *            POSTGRES_DB name ,POSTGRES_PASSWORD etc.
     * @param ports setting the exposed ports separately, syntax is local port - container port: 8080:5432
     */
    public void createContainer(String imageName, String containerName, String envV, String ports) {
        dockerClient.createContainerCmd(imageName)
                .withEnv(envV)
                .withName(containerName)
                .withPortSpecs(ports).exec();
    }

    /**
     * when we have pulled the image from the repository we can instantiate a container from it
     *
     * @param imageName the name of the image that we want to create container from
     * @param containerName the name of the container that we wil create
     * @param ports setting the exposed ports separately, syntax is local port - container port: 8080:5432
     */
    public void createContainer(String imageName, String containerName, String ports) {
        dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withPortSpecs(ports).exec();
    }

    /**
     * when we have pulled the image from the repository we can instantiate a container from it
     *
     * @param imageName the name of the image that we want to create container from
     * @param containerName the name of the container that we wil create
     */
    public void createContainer(String imageName, String containerName) {
        dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .exec();
    }




    /**
     * if there is a container we can remove it by sending the command with the name of the container
     *
     * @param name by stating the name of the container the method will remove it if found any
     *             with that name
     */
    public void removeContainer(String name) {
        dockerClient.removeContainerCmd(name).exec();
    }



    /**
     * if there is a container we can stop it by sending the command with the name of the container
     *
     * @param name by stating the name of the container the method will stop it if found any
     *             with that name
     */
    public void stopContainer(String name) {
        dockerClient.stopContainerCmd(name).exec();
    }



    /**
     * if there is a container we can start it by sending the command with the name of the container
     *
     * @param name by stating the name of the container the method will start it if found any
     *             with that name
     */
    public void startContainer(String name) {
        dockerClient.startContainerCmd(name).exec();
    }


    /**
     * if there is a container we can kill it by sending the command with the name of the container
     *
     * @param name by stating the name of the container the method will stop it if found any
     *             with that name
     *             the difference between stop and kill is that kill will stop the container
     *             immediately, there are no waits for the container to stop
     *             all processes (ungraceful stopping)
     */
    public void killContainer(String name) {
        dockerClient.killContainerCmd(name).exec();
    }


    /**
     * method for creating a network that containers can be linked to
     *
     * @param name giving a name of the network for easy linking
     */
    public void createNetwork(String name) {
        dockerClient.createNetworkCmd().withName(name).exec();
    }


    /**
     * listing the available docker networks
     * @return list of network objects
     */
    public List<Network> networkList() {
       return dockerClient.listNetworksCmd().exec();
    }


    /**
     * creating image from a dockerfile and assigning it a name.
     * @param dockerfile absolute path to the dockerfile
     *                   example:
     *                   File mysql = new File("localPath\\Dockerfile");
     *                   DockerEnv con = new DockerEnv();
     *                   con.imageFromDockerFile(mysql,"name");
     *
     *                   this method can be made to return the id of the created image bu making a String return type
     * @param name setting a name for the image that is created
     */
    public void imageFromDockerFile(File dockerfile, String name) {
        dockerClient.buildImageCmd()
                .withDockerfile(dockerfile)
                .withTag(name)
                .start()
                .awaitImageId();
    }


    /**
     * remove image that is not currently in use
     * @param name by stating the name of the container the method will remove it if found any
     *             with that name
     */
    public void removeImage(String name) {
        dockerClient.removeImageCmd(name).exec();
    }



    /**
     * initialization of a swarm with default settings
     */
    public void swarmInit() {
        SwarmSpec spec = new SwarmSpec();
        dockerClient.initializeSwarmCmd(spec).exec();
    }

    /**
     * leaving the swarm with option for force leave
     */
    public void leaveSwarm() {
        dockerClient.leaveSwarmCmd().withForceEnabled(true).exec();
    }

    /**
     * sending commands to Command Prompt
     *
     * @param commandSequence A String that represents the command that needs to be send to the console
     * @throws IOException
     */
    public void sendCommand(String commandSequence) throws IOException {
        Process process = Runtime.getRuntime().exec(commandSequence);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


    /**
     * the method is pulling an image and creating a container with set name
     * method for downloading image and creating web browser chrome standalone container,
     * the method image usage are hardcoded to - "selenium/standalone-chrome-debug" with tag "latest"
     *
     * @param containerName setting the name of the created container
     *
     * @param ports exposing ports for connection to the container via web driver for example,the
     *              syntax is local machine:container - 8080:5432
     *
     * @param vncPort exposing ports for VNC connection (default VNC password: secret) for
     *                visually following the test execution if needed
     *                The VNC is supported in "selenium/standalone-chrome-debug" docker image
     */
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

    /**
     * the method is pulling an image and creating a container with set name
     * method for downloading image and creating web browser firefox standalone container,
     * the method image usage are hardcoded to - "selenium/standalone-firefox-debug" with tag "latest"
     *
     * @param containerName setting the name of the created container
     *
     * @param ports exposing ports for connection to the container via web driver for example,the
     *              syntax is local machine:container - 8080:5432
     *
     * @param vncPort exposing ports for VNC connection (default VNC password: secret) for
     *                visually following the test execution if needed
     *                The VNC is supported in "selenium/standalone-chrome-debug" docker image
     */
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


    /**
     * the method is pulling and creating a containers from two docker files for databases
     * @param dockerFile absolute path to the first dockerfile, example of method usage:
     *                   DockerEnv con = new DockerEnv();
     *                   File mysql = new File("localPath\\Dockerfile");
     *                   File postgres = new File("localPath\\Dockerfile");
     *                   con.createDBContainers(mysql, "mydb","8080:3306" , postgres, "mypostdb", "8081:5432");
     *
     * @param name setting a name for the first created container
     *
     * @param port1 exposed port for the first created container, syntax - local machine:container
     *
     * @param dockerFile2 absolute path to the second dockerfile, example of method usage: see the example
     *                    of the first dockerfile
     *
     * @param secondName setting a name for the second created container
     *
     * @param port2 exposed port for the first created container, syntax - local machine:container
     */
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
