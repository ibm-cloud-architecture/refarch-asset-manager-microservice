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
kubectl apply -f ./manifests/asset-mgr-ms-deploy.yml  --namespace $nspace
