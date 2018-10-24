#!/bin/bash
set p = $(echo $PWD | awk -v h="scripts" '$0 ~h')
if [[ $PWD = */scripts ]]; then
 cd ..
fi
if [ $# -eq 1 ]
then
  filen=$1
else
  filen="scripts/pumpDAL01.json"
fi
curl -H "Content-Type: application-json" --request POST --data @$filen http://assetmgr.greencompute.ibmcase.com:32544/assetmanager/assets
