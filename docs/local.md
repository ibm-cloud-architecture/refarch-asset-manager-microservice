# Run Asset Management Service locally

## Table of Contents

* [Setting up Cassandra](#setting-up-cassandra)
* [Building the app](#building-the-app)
* [Running the app and stopping it](#running-the-app-and-stopping-it)

## Setting up Cassandra

To set up Cassandra locally, we are running it as a docker container. You need [Docker](https://www.docker.com/) as a prerequisite.

To run Cassandra on docker locally, run the below commands.

1. Run the container.

`docker run --name cassandra -p 9042:9042 -e CASSANDRA_CLUSTER_NAME=Cassandra -e CASSANDRA_RACK=Rack1 -e CASSANDRA_ENDPOINT_SNITCH=GossipingPropertyFileSnitch -e CASSANDRA_DC=DC1 -d cassandra`

3. To access cqlsh, run the below command.

`docker exec -ti cassandra cqlsh`

## Building the app

To build the application, we used maven build. Maven is a project management tool that is based on the Project Object Model (POM). Typically, people use Maven for project builds, dependencies, and documentation. Maven simplifies the project build. In this task, you use Maven to build the project.

1. Clone this repository.

   `git clone https://github.com/ibm-cloud-architecture/refarch-asset-manager-microservice.git`
   
   `cd refarch-asset-manager-microservice/`

2. Checkout MicroProfile branch.

   `git checkout microprofile`

3. Run this command. This command builds the project and installs it. Before building the project, make sure the `cassandra` instance is running as the application is dependent on it.

   `mvn install`
   
   If this runs successfully, you will be able to see the below messages.

```
[INFO] --- maven-failsafe-plugin:2.18.1:verify (verify-results) @ assetmanager ---
[INFO] Failsafe report directory: /Users/Hemankita.Perabathini@ibm.com/AssetManager/refarch-asset-manager-microservice/target/test-reports/it
[INFO]
[INFO] --- maven-install-plugin:2.4:install (default-install) @ assetmanager ---
[INFO] Installing /Users/Hemankita.Perabathini@ibm.com/AssetManager/refarch-asset-manager-microservice/target/assetmanager-1.0-SNAPSHOT.war to /Users/Hemankita.Perabathini@ibm.com/.m2/repository/projects/assetmanager/1.0-SNAPSHOT/assetmanager-1.0-SNAPSHOT.war
[INFO] Installing /Users/Hemankita.Perabathini@ibm.com/AssetManager/refarch-asset-manager-microservice/pom.xml to /Users/Hemankita.Perabathini@ibm.com/.m2/repository/projects/assetmanager/1.0-SNAPSHOT/assetmanager-1.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 41.352 s
[INFO] Finished at: 2018-08-28T15:12:21-05:00
[INFO] Final Memory: 31M/368M
[INFO] ------------------------------------------------------------------------
```

## Running the app and stopping it

1. Start your server.

`mvn liberty:start-server`

You will see something similar to the below messages.

```
[INFO] Starting server defaultServer.
[INFO] Server defaultServer started with process ID 36850.
[INFO] Waiting up to 30 seconds for server confirmation:  CWWKF0011I to be found in /Users/Hemankita.Perabathini@ibm.com/AssetManager/refarch-asset-manager-microservice/target/liberty/wlp/usr/servers/defaultServer/logs/messages.log
[INFO] CWWKM2010I: Searching for CWWKF0011I in /Users/Hemankita.Perabathini@ibm.com/AssetManager/refarch-asset-manager-microservice/target/liberty/wlp/usr/servers/defaultServer/logs/messages.log. This search will timeout after 30 seconds.
[INFO] CWWKM2015I: Match number: 1 is [28/8/18 15:13:05:297 CDT] 0000001a com.ibm.ws.kernel.feature.internal.FeatureManager            A CWWKF0011I: The server defaultServer is ready to run a smarter planet..
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 16.183 s
[INFO] Finished at: 2018-08-28T15:13:05-05:00
[INFO] Final Memory: 13M/309M
[INFO] ------------------------------------------------------------------------
```

3. Validate the asset service. 

Post an asset to your database like below.

```
$ curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id":"10", "os":"windows", "type":"hehe", "ipAddress": "0.0.1.1", "version": "1.0.0", "antivirus":"mcafee"}' \
  http://localhost:9080/assetmanager/assets
```

You should get a list of all assets items when you do the below.

```
curl http://localhost:9080/assetmanager/assets
```

4. If you are done accessing the application, you can stop your server using the following command.

`mvn liberty:stop-server`

Once you do this, you see the below messages.

```
[INFO] CWWKM2001I: Invoke command is [/Users/Hemankita.Perabathini@ibm.com/AssetManager/refarch-asset-manager-microservice/target/liberty/wlp/bin/server, stop, defaultServer].
[INFO] objc[37132]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/bin/java (0x1079274c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x1079e54e0). One of the two will be used. Which one is undefined.
[INFO] Stopping server defaultServer.
[INFO] Server defaultServer stopped.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.692 s
[INFO] Finished at: 2018-08-28T15:27:33-05:00
[INFO] Final Memory: 12M/245M
[INFO] ------------------------------------------------------------------------
```
