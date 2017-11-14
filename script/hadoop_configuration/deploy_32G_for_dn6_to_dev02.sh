#!/bin/bash
# USAGE: sh deploy_32G_for_dn6_to_dev02.sh

ssh dn6 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dn6:/home/hadoop/hadoop/etc/hadoop

ssh dn7 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dn7:/home/hadoop/hadoop/etc/hadoop

ssh dn8 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dn8:/home/hadoop/hadoop/etc/hadoop

ssh dn9 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dn9:/home/hadoop/hadoop/etc/hadoop

ssh dn10 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dn10:/home/hadoop/hadoop/etc/hadoop

ssh dn11 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dn11:/home/hadoop/hadoop/etc/hadoop

ssh dev01 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dev01:/home/hadoop/hadoop/etc/hadoop

ssh dev02 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dn6_to_dev02/* hadoop@dev02:/home/hadoop/hadoop/etc/hadoop

echo "All jobs are done."
