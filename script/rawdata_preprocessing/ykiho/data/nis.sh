#!/usr/bin/env bash
for file in $(ls /home/hadoop/hyuk0628/raw_data/ykiho/data/nis/);
do
        echo processing ${file} ...
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/ykiho/data/nis/${file}' overwrite into table nis.${file}_text;"
        hive -e "INSERT INTO TABLE nis.${file} SELECT * FROM nis.${file}_text;"
done

echo "All jobs are done."