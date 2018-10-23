#!/bin/bash
if [ $# -eq 1 ]
then
  filen=$1
else
  filen="pumpDAL01.json"
fi
curl -H "Content-Type: application-json" --request POST --data @$filen http://assetmgr.greencompute.ibmcase.com:32544/assetmanager/assets
