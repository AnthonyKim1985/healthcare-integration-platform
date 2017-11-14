#!/usr/bin/env bash
for file in $(ls data/);
do
        echo processing ${file} ...
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/nhic/data/${file}' overwrite into table nhic.${file}_text;"
        hive -e "INSERT INTO TABLE nhic.${file} SELECT * FROM nhic.${file}_text;"
done

echo "All jobs are done."