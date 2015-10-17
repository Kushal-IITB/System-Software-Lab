#!/bin/bash
rm -f "population_inc.csv"
numLines=0
while IFS=,  read year number
do
    if [[($numLines -gt 0)]]
    	then
    	((difference=number-prev))
    	echo "$year,$difference" >> "population_inc.csv"
    	((prev = $number))
    else 
    	((prev = $number))
	fi
	((numLines+=1))
done < population.csv
