# Run the Application on Docker Edge

## Table of Contents

* [Introduction](#introduction)
* [Pre-requisites](#pre-requisites)
* [Set up your environment](#set-up-your-environment)
* [Setting up Cassandra](#setting-up-cassandra)
* [Run the App](#run-the-app)
* [Validate the App](#validate-the-app)
* [Delete the App](#delete-the-app)
* [References](#references)

## Introduction

[Docker Edge](https://docs.docker.com/docker-for-mac/kubernetes/)

Using docker edge, you can run your application on a local kubernetes cluster. Alternatively, you can use [Minikube](https://kubernetes.io/docs/getting-started-guides/minikube/).

## Pre-requisites

To run the application locally on your laptop on a Kubernetes-based environment such as Docker Edge or Minikube (which are meant to be small development environments) we first need Kubernetes CLI.

- [Kubectl](https://kubernetes.io/docs/user-guide/kubectl-overview/) (Kubernetes CLI) - Follow the instructions [here](https://kubernetes.io/docs/tasks/tools/install-kubectl/) to install it on your platform.

Finally, we must create a Kubernetes Cluster. As already mentioned before, we are going to use Docker Edge:

- [Docker Edge](https://docs.docker.com/docker-for-mac/kubernetes/) - Follow the instructions [here](https://docs.docker.com/docker-for-mac/#kubernetes) to get docker edge installed on your work station.

If you want to use Minikube, follow the instructions [here](https://kubernetes.io/docs/getting-started-guides/minikube/).

## Set up your environment

We are running this application in a namespace called `greencompute`. Lets first create this namespace.

```
$ kubectl create namespace greencompute
```

## Setting up Cassandra

To set up Cassandra locally on docker edge, below are the steps.

1. Run the below commands.

```
$ cd refarch-asset-manager-microservice

$ kubectl create -f manifests/storageclass.yaml -n greencompute

$ kubectl create -f chart/cassandra/templates/cassandra-service.yaml -n greencompute

$ kubectl create -f chart/cassandra/templates/cassandra-volumes.yaml -n greencompute

$ kubectl create -f chart/cassandra/templates/cassandra-statefulset.yaml -n greencompute
```

3. To access cqlsh, run the below command.

```
$ kubectl exec -ti cassandra-0 cqlsh -n greencompute
```

## Run the App 

To deploy the application, run the below commands

```
$ ./scripts/deployLocal.sh local
```

## Validate the App 

Before accessing the application, make sure that all the pods are up and running. 

```
$ kubectl get pods -n greencompute
NAME                            READY     STATUS    RESTARTS   AGE
assetmanager-7d5bb76567-mmgfk   1/1       Running   0          23h
cassandra-0                     1/1       Running   0          23h
```
On `Docker edge` you can access the application using `localhost:<Port>`. 

To get the port, run the below command.

```
$ kubectl get svc -n greencompute
NAME                      TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)                         AGE
assetmanager-service      NodePort    10.110.147.141   <none>        9080:31959/TCP,9443:32462/TCP   23h
```

In this case, you can access the application at `http://localhost:31959`

APIs available are as follows

```
POST
http://localhost:<Port>/assetmanager/assets                         - Save an Asset
http://localhost:<Port>/assetmanager/events                         - Save an Event

GET
http://localhost:<Port>/assetmanager/assets                         - Get all the available assets
http://localhost:<Port>/assetmanager/assets/{id}                    - Get all the available assets by id
http://localhost:<Port>/assetmanager/assets/type/{type}             - Get all the available assets by type
http://localhost:<Port>/assetmanager/events                         - Get all the events
http://localhost:<Port>/assetmanager/events/{fromDate}/{toDate}     - Get the event history

PUT
http://localhost:<Port>/assetmanager/assets/{id}                    - Update an Asset

DELETE
http://localhost:<Port>/assetmanager/assets/{id}                    - Delete an Asset
```

You can either use CURL or Postman to access the APIs.

## Delete the App

To delete the application from your cluster, run the below command.

```
$ ./scripts/unDeployLocal.sh
```

## References

* [Docker Edge](https://docs.docker.com/docker-for-mac/kubernetes/)
* [Kubectl](https://kubernetes.io/docs/user-guide/kubectl-overview/)





