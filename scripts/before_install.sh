#!/bin/bash

LOG_FILE=/home/ubuntu/build/before_install.log

# Ensure the log file exists
if [ ! -f "$LOG_FILE" ]; then
  mkdir -p /home/ubuntu/build
  touch $LOG_FILE
fi

# Before install script
echo "Before install script executed at $(date)" >> $LOG_FILE

# 필요한 디렉토리를 생성
mkdir -p /home/ubuntu/build
mkdir -p /home/ubuntu/build/scripts

echo "Directories created successfully" >> $LOG_FILE
