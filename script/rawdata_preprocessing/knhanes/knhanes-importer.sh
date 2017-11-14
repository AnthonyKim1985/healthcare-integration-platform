#!/usr/bin/env bash
for file in $(ls data/);
do
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/knhanes/data/${file}' overwrite into table knhanes.${file}_text;"
        hive -e "INSERT INTO TABLE knhanes.${file} SELECT * FROM knhanes.${file}_text;"
done