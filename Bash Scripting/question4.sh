#!/bin/bash

temp=$1
if [ -z "$1" ];
then
	echo "No Input"
	exit 1
fi
FILENAME=${temp%%.*}
EXT=${temp#*.}
CONFIG="$HOME/myopen.cfg"
if [ ! -f "$CONFIG" ];
then
	echo "Config File Not Found"
	exit 2
fi
cat $CONFIG | while read LINE
do
	ext=${LINE%:*}
	PROGRAM=${LINE#*:}
	if [  "$ext" == "$EXT"  ];
	then 
		"$PROGRAM" "$FILENAME.$EXT"
		if [ "$?" == "127" ];
		then
			echo "Program does not exist"
			exit 4
		fi
		exit 5
	fi
done
var="$?"
if [ "$var" == "4" ]; 
then 
	exit 4

fi
if [  "$var" == "5" ];
then
	exit 5
else 
	echo "No program for $FILENAME.$EXT "
	exit 3
fi

