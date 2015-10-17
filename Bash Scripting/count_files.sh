#!/bin/bash
files=0
folders=0

for f in * .*; do
	if [[ -d $f ]]; then
		let folders++
	else
		let files++
	fi
done

echo $files
echo $folders
