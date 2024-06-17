#!/bin/bash

LOG_FILE=/home/ubuntu/build/before_install.log

# Ensure the log file exists
if [ ! -f "$LOG_FILE" ]; then
  mkdir -p /home/ubuntu/build
  touch $LOG_FILE
fi

echo "Before install script executed at $(date)" >> $LOG_FILE

# 필요한 디렉토리를 생성
mkdir -p /home/ubuntu/build
mkdir -p /home/ubuntu/build/scripts

echo "Directories created successfully" >> $LOG_FILE

echo "Deployment started at $(date)" >> $LOG_FILE

# 애플리케이션을 중지하는 부분은 application_stop.sh에서 처리

JAR_PATH=/home/ubuntu/build/sportsLink-0.1.1-SNAPSHOT.jar

if [ ! -f "$JAR_PATH" ]; then
  echo "> JAR file not found: $JAR_PATH" >> $LOG_FILE
  exit 1
fi

nohup sudo java -jar $JAR_PATH >> $LOG_FILE 2>&1 &

# Check if the new application started successfully
NEW_PID=$(pgrep -f 'sportsLink-0.1.1-SNAPSHOT.jar')
if [ ! -z "$NEW_PID" ]; then
  echo "> Deployment completed successfully. New application is running with PID: $NEW_PID" >> $LOG_FILE
else
  echo "> Deployment failed. New application did not start." >> $LOG_FILE
fi
