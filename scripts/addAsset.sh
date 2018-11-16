#!/bin/bash
if [ $# -eq 0 ]
then
  filen="pumpDAL01.json"
else
  filen="$1"
fi
echo "$filen"
curl -H "Content-Type: application-json" --request POST --data @$filen http://assetmgr.greencompute.ibmcase.com/assetmanager/assets
