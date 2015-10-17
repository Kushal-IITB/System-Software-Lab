#!/bin/bash
#question 6

for entry in *
do
  if [[ "$entry" == "ohwhattime.sh" ]]
  then
  	continue
  fi	
  temp=$entry
  temp=${temp%_*_*_*:*}
  modDate=$(date -r "$entry" +"%m_%d")
  modHour=$(date -r "$entry" +"%H")
  modHour=${modHour#0}
  modMin=$(date -r "$entry" +"%M")
  modMin=${modMin#0}
  modSec=$(date -r "$entry" +"%S")
  modSec=${modSec#0}
  if (("$modSec" >= "30")); then
	modMin=$(( $modMin + 1 ))

	 if (("$modMin" < "10")); then
		modMin="0$modMin"
 	 fi
	 if [ "$modMin" -eq "60" ]; then
		modMin=00
		modHour=$(( $modHour + 1 ))
		if (("$modHour" < "10")); then
		modHour="0$modHour"
 	 fi
	 fi
  fi
  var="${modDate}_${modHour}:${modMin}"
  if [[ "$entry" != "${temp}_${var}" ]]; then  
	mv "$entry" "${entry}_${var}"
  fi

done
