#!/bin/bash

echo "running deploy_64G_for_dn1_to_dn5.sh"
sh deploy_64G_for_dn1_to_dn5.sh

echo "running deploy_32G_for_dev03_to_dev05.sh"
sh deploy_32G_for_dev03_to_dev05.sh

echo "running deploy_32G_for_dn6_to_dev02.sh"
sh deploy_32G_for_dn6_to_dev02.sh

echo "All jobs are done."
