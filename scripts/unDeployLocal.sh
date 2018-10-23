#!/bin/bash
set p = $(echo $PWD | awk -v h="scripts" '$0 ~h')
if [[ $PWD = */scripts ]]; then
 cd ..
fi

nspace=greencompute
delete deployment,svc -l app=assetmanagerms -n $nspace
