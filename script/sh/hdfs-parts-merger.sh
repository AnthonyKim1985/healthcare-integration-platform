#!/usr/bin/env bash
#USAGE: sh hdfs-parts-merger.sh [hdfs_location] [header] [home_path] [data_file_name] [data_set_name]

hdfsLocation=$1
header=$2
homePath=$3
dataFileName=$4
dataSetName=$5

# dbTableName=`echo ${hdfsLocation} | cut -d'/' -f4`
# dataFileName=`echo ${hdfsLocation} | cut -d'/' -f4 | cut -d'.' -f2`
dataSetDirName=${homePath}/extracted_dataset/${dataSetName}

if ! test -d ${dataSetDirName}  ; then
    mkdir -p ${dataSetDirName}
fi

# Extract Data Set with merging parts files
hdfs dfs -getmerge ${hdfsLocation}/* ${dataSetDirName}/${dataFileName}.csv

# Extract the Data Set Header
# ssh -lhadoop nn1 "hive -e \"set hive.cli.print.header=false; show columns in ${dbTableName};\"" > ${dataSetDirName}/header

# Insert header to the Data Set
# sed -i ':a;N;$!ba;s/\n/,/g' ${dataSetDirName}/header # replace newline character to comman(,)
# header=`cat ${dataSetDirName}/header | sed -r 's/\s+//g'`
echo ${header}
sed -i -e '1i'${header}'\' ${dataSetDirName}/${dataFileName}.csv

# Remove header temp file
# rm -rf ${dataSetDirName}/header