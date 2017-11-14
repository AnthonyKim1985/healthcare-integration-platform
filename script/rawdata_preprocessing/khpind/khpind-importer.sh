#!/usr/bin/env bash

for file in $(ls data/);
do
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/khpind/data/$file' overwrite into table khpind.${file}_text;"
        hive -e "INSERT INTO TABLE khpind.$file SELECT * FROM khpind.${file}_text;"
done