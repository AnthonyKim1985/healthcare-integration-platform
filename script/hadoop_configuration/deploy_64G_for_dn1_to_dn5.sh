#!/bin/bash
# USAGE: sh hadoop_64G_for_dn1_to_dn5.sh

rm -rf /home/hadoop/hadoop/etc/hadoop/*
cp hadoop_64G_for_dn1_to_dn5/* /home/hadoop/hadoop/etc/hadoop/

ssh was -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_64G_for_dn1_to_dn5/* hadoop@was:/home/hadoop/hadoop/etc/hadoop

ssh dn1 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_64G_for_dn1_to_dn5/* hadoop@dn1:/home/hadoop/hadoop/etc/hadoop

ssh dn2 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_64G_for_dn1_to_dn5/* hadoop@dn2:/home/hadoop/hadoop/etc/hadoop

ssh dn3 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_64G_for_dn1_to_dn5/* hadoop@dn3:/home/hadoop/hadoop/etc/hadoop

ssh dn4 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_64G_for_dn1_to_dn5/* hadoop@dn4:/home/hadoop/hadoop/etc/hadoop

ssh dn5 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_64G_for_dn1_to_dn5/* hadoop@dn5:/home/hadoop/hadoop/etc/hadoop


echo "All jobs are done."
