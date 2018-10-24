#!/bin/bash
set p = $(echo $PWD | awk -v h="scripts" '$0 ~h')
if [[ $PWD = */scripts ]]; then
 cd ..
fi

nspace=greencompute
kubectl delete deployment,svc -l app=assetmanagerms -n $nspace
kubectl delete svc assetmanager-service -n $nspace
