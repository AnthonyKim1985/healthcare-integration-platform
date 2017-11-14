#!/usr/bin/env bash
for file in $(ls /home/hadoop/hyuk0628/raw_data/ykiho/data/nps/);
do
        echo processing ${file} ...
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/ykiho/data/nps/${file}' overwrite into table nps.${file}_text;"
        hive -e "INSERT INTO TABLE nps.${file} SELECT * FROM nps.${file}_text;"
done

echo "All jobs are done."