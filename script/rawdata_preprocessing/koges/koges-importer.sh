#!/usr/bin/env bash
for file in $(ls data/);
do
        echo processing ${file} ...
        hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/koges/data/${file}' overwrite into table koges.${file}_text;"
        hive -e "INSERT INTO TABLE koges.${file} SELECT * FROM koges.${file}_text;"
done

echo "All jobs are done."
