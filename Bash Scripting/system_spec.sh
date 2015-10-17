#!/bin/bash

echo $(cat /proc/cpuinfo | grep -m 1 "cpu MHz" | grep -o "[0-9.][0-9.]*")
cat /proc/cpuinfo | grep -m 1 "address sizes" | grep -o "[0-9.][0-9.]*"

