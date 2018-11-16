#!/bin/bash
set p = $(echo $PWD | awk -v h="scripts" '$0 ~h')
if [[ $PWD = */scripts ]]; then
 cd ..
fi
mode="dev"
if [ $# -eq 1 ]
then
  mode=$1
fi
nspace=greencompute
if [ "$mode"  = "dev" ]
then
  echo Deploy for development
  kubectl apply -f ./manifests/dev/asset-mgr-ms-deploy.yml  --namespace $nspace
else
  echo Deploy for production
  kubectl apply -f ./manifests/asset-mgr-ms-deploy.yml  --namespace $nspace
fi
