# Run the Application on IBM Cloud Private

## Table of Contents

* [Introduction](#introduction)
* [Pre-requisites](#pre-requisites)
* [Set up your environment](#set-up-your-environment)
* [Run the App](#run-the-app)
* [Validate the App](#validate-the-app)
* [Delete the App](#delete-the-app)
* [References](#references)

## Introduction

[IBM Cloud Private](https://www.ibm.com/cloud/private)

IBM Private Cloud has all the advantages of public cloud but is dedicated to single organization. You can have your own security requirements and customize the environment as well. It has tight security and gives you more control along with scalability and easy to deploy options, whether you require it on public cloud infrastructure or in an on-premise environment behind your firewall.

You can find the detailed installation instructions for IBM Cloud Private [here](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0.2/installing/install_containers_CE.html).

## Pre-requisites

[IBM Cloud Private Cluster](https://www.ibm.com/cloud/private)

Create a Kubernetes cluster in an on-premise datacenter. The community edition (IBM Cloud Private-ce) is free of charge.
Follow the instructions [here](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0.2/installing/install_containers_CE.html) to install IBM Cloud Private-ce.

[Helm](https://github.com/kubernetes/helm) (Kubernetes package manager)

Follow the instructions [here](https://github.com/kubernetes/helm/blob/master/docs/install.md) to install it on your platform.
If using IBM Cloud Private version 2.1.0.2 or newer, we recommend you to follow these [instructions](https://www.ibm.com/support/knowledgecenter/SSBS6K_2.1.0.2/app_center/create_helm_cli.html) to install helm.

## Set up your environment

1. Your [IBM Cloud Private Cluster](https://www.ibm.com/cloud/private) should be up and running.

2. Log in to the IBM Cloud Private.

<p align="center">
    <img src="https://github.com/ibm-cloud-architecture/refarch-cloudnative-kubernetes/blob/microprofile/static/imgs/icp_dashboard.png">
</p>

3. Go to `admin > Configure Client`.

<p align="center">
    <img width="300" height="300" src="https://github.com/ibm-cloud-architecture/refarch-cloudnative-kubernetes/blob/microprofile/static/imgs/client_config.png">
</p>

4. Grab the kubectl configuration commands.

<p align="center">
    <img src="https://github.com/ibm-cloud-architecture/refarch-cloudnative-kubernetes/blob/microprofile/static/imgs/kube_cmds.png">
</p>

5. Run those commands in your terminal.

6. If successful, you should see something like below.

```
Switched to context "xxx-cluster.icp-context".
```
7. Run the below command.

`helm init --client-only`

You will see something similar to the below message.

```
$HELM_HOME has been configured at /Users/user@ibm.com/.helm.
Not installing Tiller due to 'client-only' flag having been set
Happy Helming!
```

8. Verify the helm version

`helm version --tls`

You will see something like below.

```
Client: &version.Version{SemVer:"v2.7.2+icp", GitCommit:"d41a5c2da480efc555ddca57d3972bcad3351801", GitTreeState:"dirty"}
Server: &version.Version{SemVer:"v2.7.2+icp", GitCommit:"d41a5c2da480efc555ddca57d3972bcad3351801", GitTreeState:"dirty"}
```

## Run the App 

1. Create `greencompute` namespace.

`kubectl create namespace greencompute`

2. Add the `helm` package repository containing the reference application.

`helm repo add asset https://raw.githubusercontent.com/ibm-cloud-architecture/refarch-asset-manager-microservice/microprofile/assetmanager`

3. Install the reference application.

`helm install --name assetmanager asset/assetmanager --namespace greencompute --tls`

Note: If using IBM Cloud Private version older than 2.1.0.2, use `helm install --name assetmanager asset/assetmanager --namespace greencompute`

After a minute or so, the containers will be deployed to the cluster.  The output of the installation contains instructions on how to access the application once it has finished deploying.

## Validate the App 

Before accessing the application, make sure that all the pods are up and running. 

```
$ kubectl get pods -n greencompute
assetmanager-deployment-84dbdf8f56-fgzmm                          1/1       Running   0          8m
cassandra-0                                                       1/1       Running   0          8m
```
On `ICP` you can access the application using `assetmgr.greencompute.ibmcase.com`

APIs available are as follows

```
POST
assetmgr.greencompute.ibmcase.com/assetmanager/assets                         - Save an Asset
assetmgr.greencompute.ibmcase.com/assetmanager/events                         - Save an Event

GET
assetmgr.greencompute.ibmcase.com/assetmanager/assets                         - Get all the available assets
assetmgr.greencompute.ibmcase.com/assetmanager/assets/{id}                    - Get all the available assets by id
assetmgr.greencompute.ibmcase.com/assetmanager/assets/type/{type}             - Get all the available assets by type
assetmgr.greencompute.ibmcase.com/assetmanager/events                         - Get all the events
assetmgr.greencompute.ibmcase.com/assetmanager/events/{fromDate}/{toDate}     - Get the event history

PUT
assetmgr.greencompute.ibmcase.com/assetmanager/assets/{id}                    - Update an Asset

DELETE
assetmgr.greencompute.ibmcase.com/assetmanager/assets/{id}                    - Delete an Asset
```

You can either use CURL or Postman to access the APIs.

## Delete the App

To delete the application from your cluster, run the below command.

```
$ helm del --purge assetmanager --tls
```
Note: If using IBM Cloud Private version older than 2.1.0.2, use `helm del --purge assetmanager`

## References

* [Developer Tools CLI](https://console.bluemix.net/docs/cloudnative/dev_cli.html#developercli)
* [IBM Cloud Private](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0/kc_welcome_containers.html)
* [IBM Cloud Private Installation](https://github.com/ibm-cloud-architecture/refarch-privatecloud)
* [IBM Cloud Private version 2.1.0.2 Helm instructions](https://www.ibm.com/support/knowledgecenter/SSBS6K_2.1.0.2/app_center/create_helm_cli.html)
* [Kubectl](https://kubernetes.io/docs/user-guide/kubectl-overview/)
* [Helm](https://github.com/kubernetes/helm)




