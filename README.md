#### refarch-asset-manager-microservice

# Java Microservice integration with Cassandra - Reference implementation based on Kubernetes

## Table of Contents

* [Introduction](#introduction)
* [Architecture](#architecture)
* [Application Overview](#application-overview)
* [Implementation](#implementation)
* [References](#references)

## Introduction

This project provides a reference implementation for running a Cloud Native Application using [***Cassandra***](http://cassandra.apache.org/) by leveraging the [**Java MicroProfile**](https://microprofile.io/) and [**Spring Boot**](https://projects.spring.io/spring-boot/) as technologies for its microservices. The target cloud environment for the application is a [**Kubernetes-based**](https://kubernetes.io/) platform which might be [**IBM Cloud**](https://www.ibm.com/cloud/) or [**IBM Cloud Private**](https://www.ibm.com/cloud-computing/products/ibm-cloud-private/).

## Architecture

<p align="center">
    <img src="images/assetmgr.png">
</p>

## Application Overview

The application is an asset management system which supports CRUD operations and manages the data for manufacturing asset. The persistence repository is [***Cassandra***](http://cassandra.apache.org/).

## Implementation

This application has been implemented using two of the most popular technologies used for Java microservices development.
- [**Spring Boot**](https://projects.spring.io/spring-boot/) 
- [**Java MicroProfile**](https://microprofile.io/)

To know more about these implementations, Click on the below images to access respective versions of our application.

<p align="center">
  <a href="https://github.com/ibm-cloud-architecture/refarch-asset-manager-microservice/tree/spring">
    <img src="images/spring_small.png">
  </a>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="https://github.com/ibm-cloud-architecture/refarch-asset-manager-microservice/tree/microprofile">
    <img src="images/microprofile_small.png">
  </a>
</p>

## References

- [Java MicroProfile](https://microprofile.io/)
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Kubernetes](https://kubernetes.io/)
- [IBM Cloud](https://www.ibm.com/cloud/)
- [IBM Cloud Private](https://www.ibm.com/cloud-computing/products/ibm-cloud-private/)

