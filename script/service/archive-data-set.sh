#!/usr/bin/env bash
#USAGE: sh archive-data-set.sh [archive_file_name] [ftp_location] [home_path] [data_set_name]

archiveFileName=$1
ftpLocation=$2
homePath=$3
dataSetName=$4

dataSetDirName=${homePath}/extracted_dataset/${dataSetName}
archiveDirName=${homePath}/archive

if ! test -d ${archiveDirName} ; then
    mkdir -p ${archiveDirName}
fi

find ${dataSetDirName} -size 0 -exec rm -rf {} \;
cd ${dataSetDirName}

if ! test -d ${archiveDirName}/${ftpLocation} ; then
    mkdir -p ${archiveDirName}/${ftpLocation}
fi

count=$(ls ${dataSetDirName} | wc -l)

if [[ ${count} =~ 0 ]]; then
    echo "file not found!"
    echo "No data was found that satisfied the criteria you entered. Please try again." > README.txt
fi

# zip ${dirName}/${ftpLocation}/${archiveFileName} ./*
tar zcvf ${archiveDirName}/${ftpLocation}/${archiveFileName} ./*

# Delete uncompressed raw data set
rm -rf ${dataSetDirName}/*

# TODO: send the archived data to ftp server
# echo Need to ftp server information.
# echo ${ftpLocation}