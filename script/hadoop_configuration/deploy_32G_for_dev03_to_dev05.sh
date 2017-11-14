#!/bin/bash
# USAGE: sh deploy_32G_for_dev03_to_dev05.sh

ssh dev03 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dev03_to_dev05/* hadoop@dev03:/home/hadoop/hadoop/etc/hadoop

ssh dev04 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dev03_to_dev05/* hadoop@dev04:/home/hadoop/hadoop/etc/hadoop

ssh dev05 -lhadoop "rm -rf /home/hadoop/hadoop/etc/hadoop/*";
scp hadoop_32G_for_dev03_to_dev05/* hadoop@dev05:/home/hadoop/hadoop/etc/hadoop

echo "All jobs are done."
