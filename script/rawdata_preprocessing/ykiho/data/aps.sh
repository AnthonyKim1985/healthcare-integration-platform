#!/usr/bin/env bash

for file in $(ls /home/hadoop/hyuk0628/raw_data/ykiho/data/aps/);
do
        echo processing ${file} ...
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/ykiho/data/aps/${file}' overwrite into table aps.${file}_text;"
        hive -e "INSERT INTO TABLE aps.${file} SELECT * FROM aps.${file}_text;"
done

echo "All jobs are done."