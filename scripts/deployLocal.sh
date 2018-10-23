#!/bin/bash
set p = $(echo $PWD | awk -v h="scripts" '$0 ~h')
if [[ $PWD = */scripts ]]; then
 cd ..
fi
if [ $# -eq 1 ]
then
  mode=$1
else
  mode="dev"
fi
nspace=greencompute
if [ "$mode"  = "dev" ]
then
  kubectl apply -f ./manifests/dev/asset-mgr-ms-deploy.yml  --namespace $nspace
else
  kubectl apply -f ./manifests/asset-mgr-ms-deploy.yml  --namespace $nspace
fi
