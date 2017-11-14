#!/usr/bin/env bash

for file in $(ls data/);
do
        filename=`echo ${file} | cut -f1 -d"."`
        echo ${filename}
        mv data/${file} data/${filename}
done