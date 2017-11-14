#!/usr/bin/env bash

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2008_text' overwrite into table chs.chs_2008_text;"
hive -e "INSERT INTO TABLE chs.chs_2008 SELECT * FROM chs.chs_2008_text;"

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2009_text' overwrite into table chs.chs_2009_text;"
hive -e "INSERT INTO TABLE chs.chs_2009 SELECT * FROM chs.chs_2009_text;"

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2010_text' overwrite into table chs.chs_2010_text;"
hive -e "INSERT INTO TABLE chs.chs_2010 SELECT * FROM chs.chs_2010_text;"

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2011_text' overwrite into table chs.chs_2011_text;"
hive -e "INSERT INTO TABLE chs.chs_2011 SELECT * FROM chs.chs_2011_text;"

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2012_text' overwrite into table chs.chs_2012_text;"
hive -e "INSERT INTO TABLE chs.chs_2012 SELECT * FROM chs.chs_2012_text;"

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2013_text' overwrite into table chs.chs_2013_text;"
hive -e "INSERT INTO TABLE chs.chs_2013 SELECT * FROM chs.chs_2013_text;"

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2014_text' overwrite into table chs.chs_2014_text;"
hive -e "INSERT INTO TABLE chs.chs_2014 SELECT * FROM chs.chs_2014_text;"

hive -e "load data local inpath '/home/hadoop/hyuk0628/raw_data/chs/chs_2015_text' overwrite into table chs.chs_2015_text;"
hive -e "INSERT INTO TABLE chs.chs_2015 SELECT * FROM chs.chs_2015_text;"