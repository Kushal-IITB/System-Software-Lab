#!/bin/bash
rm -f temp.txt
touch temp.txt
rm -f good.txt
rm -f bad.txt
touch good.txt
touch bad.txt

for i in $(ls)
do
	
    if [[ $i = *.c ]]
    	then
        
         gcc $i 2> temp.txt 
         if [[ -s temp.txt ]];
         	then
         	echo "$i" >> "bad.txt"
         	
         else 
         	echo "$i" >> "good.txt"
         	
         fi

        
    fi
done
rm -f temp.txt
