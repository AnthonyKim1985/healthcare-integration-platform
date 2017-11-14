#!/usr/bin/env bash
for file in $(ls /home/hadoop/hyuk0628/raw_data/ykiho/data/pps/);
do
        echo processing ${file} ...
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/ykiho/data/pps/${file}' overwrite into table pps.${file}_text;"
        hive -e "INSERT INTO TABLE pps.${file} SELECT * FROM pps.${file}_text;"
done

echo "All jobs are done."