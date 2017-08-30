#!/usr/bin/env bash
ps -ef | grep healthcare-integration-platform-0.0.1-SNAPSHOT.war | grep -v grep | awk '{print $2}' | xargs kill
ps -ef | grep healthcare-nhic-data-extractor-0.0.1-SNAPSHOT.war | grep -v grep | awk '{print $2}' | xargs kill
ps -ef | grep healthcare-hira-data-extractor-0.0.1-SNAPSHOT.war | grep -v grep | awk '{print $2}' | xargs kill
ps -ef | grep healthcare-kihasa-data-extractor-0.0.1-SNAPSHOT.war | grep -v grep | awk '{print $2}' | xargs kill
ps -ef | grep healthcare-cdc-data-extractor-0.0.1-SNAPSHOT.war | grep -v grep | awk '{print $2}' | xargs kill