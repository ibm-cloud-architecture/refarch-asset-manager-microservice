# Microprofile based Microservice Apps Integration with Cassandra

## Table of Contents

* [Introduction](#introduction)
* [How it works](#how-it-works)
* [API Endpoints](#api-endpoints)
* [Implementation](#implementation)
    * [Microprofile](#microprofile)
* [Features and App details](#features)
* [Deploying the App](#deploying-the-app)
    + [IBM Cloud Private](#ibm-cloud-private)
    + [Minikube](#minikube)
    + [Run Inventory Service locally](#run-orders-service-locally)
* [References](#references)

## Introduction

This project is built to demonstrate how to build Asset Management Microservices applications using Microprofile. 
This application provides CRUD operations from a NoSQL database [***Cassandra***](http://cassandra.apache.org/).

- Based on [MicroProfile](https://microprofile.io/).
- Persist asset data to Cassandra.
- Deployment options for local environment and ICP.

## How it works

The Asset Management Microservice serves 'IBM Cloud Native Reference Architecture' suite, available at
https://github.com/ibm-cloud-architecture/refarch-asset-analytics. Though it is a part of a bigger application, 
the Asset Management service is itself an application that persists the data of assets to Cassandra database.

<p align="center">
    <img src="images/assetmgr.png">
</p>

## API Endpoints

```
POST   /assetmanager/assets
```

- Create an asset. 

Asset object must be passed as JSON object in the request body with the following format:

```
{
 "id":"1", 
 "os":"testdata",
 "type":"testdata",
 "ipAddress": "0.0.0.0",
 "version": "1.0.0",
 "antivirus":"testdata"
}
```

On success, `Asset with ID 1 got created` is returned.

```
GET     /assetmanager/assets
```

- Returns all assets. 

```
GET     /assetmanager/assets/{id}
```

- Returns all assets based on `Id`. 

```
GET     /assetmanager/assets/type/{type}
```

- Returns all assets based on `type`.

```
PUT     /assetmanager/assets/{id}
```

- Update an asset.

Asset object must be passed as JSON object in the request body with the following format:

```
{
 "id":"1", 
 "os":"updtestdata",
 "type":"updtestdata",
 "ipAddress": "0.0.0.0",
 "version": "1.0.0",
 "antivirus":"updtestdata"
}
```

On success, `Asset with ID 1 got updated` is returned.

```
DELETE     /assetmanager/assets/{id}
```

- Delete an asset.

On success, `Asset with ID 1 got deleted` is returned.

### Implementation

#### [MicroProfile](https://microprofile.io/)

MicroProfile is an open platform that optimizes the Enterprise Java for microservices architecture. In this application, 
we use [**MicroProfile 1.3**](https://github.com/eclipse/microprofile-bom).

You can make use of this feature by including this dependency in Maven.

```
<dependency>
    <groupId>org.eclipse.microprofile</groupId>
    <artifactId>microprofile</artifactId>
    <version>1.3</version>
    <type>pom</type>
    <scope>provided</scope>
</dependency>
```

You should also include a feature in [server.xml](https://github.com/ibm-cloud-architecture/refarch-cloudnative-micro-orders/blob/microprofile/src/main/liberty/config/server.xml).

```
<server description="Sample Liberty server">

  <featureManager>
      <feature>microprofile-1.3</feature>
  </featureManager>

  <httpEndpoint httpPort="${default.http.port}" httpsPort="${default.https.port}"
      id="defaultHttpEndpoint" host="*" />

</server>
```

### Features

1. Java SE 8 - Used Java Programming language

2. [CDI 1.2](https://jcp.org/en/jsr/detail?id=346) - Used CDI for typesafe dependency injection

3. [JAX-RS 2.0.1](https://jcp.org/en/jsr/detail?id=339) - 
JAX-RS is used for providing both standard client and server APIs for RESTful communication by the MicroProfile applications.

4. [Eclipse MicroProfile Config](https://github.com/eclipse/microprofile-config) - 
Configuration data comes from different sources like system properties, 
system environment variables, *.properties etc. These values may change dynamically. 
This feature enables us to pick up configured values immediately after they got changed.

    The config values are sorted according to their ordinal. We can override the less important values from outside. 
    The config sources three locations by default, and the list below shows their rank in priority from most to least:

    - System.getProperties()
    - System.getenv()
    - all META-INF/microprofile-config.properties files on the ClassPath.

    In our sample application, we obtained the configuration programmatically.

## Deploying the App

You can deploy the application locally on your system or on IBM Cloud Private based on your convenience.

### IBM Cloud Private

To deploy it on IBM Cloud Private, please follow the instructions provided 
[here](https://github.com/Hemankita/refarch-asset-manager-microservice/blob/microprofile/docs/icp.md).

### Run Asset Service locally

To deploy the app locally and test the individual service, please follow the instructions provided 
[here](https://github.com/Hemankita/refarch-asset-manager-microservice/blob/microprofile/docs/local.md).

## References

1. [MicroProfile](https://microprofile.io/)
2. [MicroProfile Config on Liberty](https://www.ibm.com/support/knowledgecenter/en/SSAW57_liberty/com.ibm.websphere.wlp.nd.multiplatform.doc/ae/twlp_microprofile_appconfig.html)
