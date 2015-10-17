#!/bin/bash
cat power.c | while read entry
do
	echo "$entry" | (sed -n 's/^ \{0,\}# \{0,\}include \{0,\}[<,\"] \{0,\}\([^\.]*\.h\) \{0,\}[>,\"].*/\1/p' )
done