#!/bin/bash

LOG_FILE=/home/ubuntu/first-team-project-dev/deploy.log

echo "Deployment started at $(date)" >> $LOG_FILE

CURRENT_PID=$(pgrep -f sportsLink-0.0.1-SNAPSHOT.jar)

if [ -z "$CURRENT_PID" ]; then
  echo "> No application is currently running." >> $LOG_FILE
else
  echo "> Stopping the currently running application with PID: $CURRENT_PID" >> $LOG_FILE
  kill -9 $CURRENT_PID
  sleep 5
fi

echo "> Deploying new application" >> $LOG_FILE

JAR_PATH=/home/build/sportsLink-0.0.1-SNAPSHOT.jar

if [ ! -f "$JAR_PATH" ]; then
  echo "> JAR file not found: $JAR_PATH" >> $LOG_FILE
  exit 1
fi

nohup java -jar $JAR_PATH >> $LOG_FILE 2>&1 &

if [ $? -eq 0 ]; then
  echo "> Deployment completed successfully." >> $LOG_FILE
else
  echo "> Deployment failed." >> $LOG_FILE
fi
