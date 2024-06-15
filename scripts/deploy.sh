#!/bin/bash

CURRENT_PID=$(pgrep -f sportsLink-0.0.1-SNAPSHOT.jar)

if [ -z "$CURRENT_PID" ]; then
  echo "> No application is currently running."
else
  echo "> Stopping the currently running application with PID: $CURRENT_PID"
  kill -9 $CURRENT_PID
  sleep 5
fi

echo "> Deploying new application"

JAR_PATH=/home/ubuntu/first-team-project-dev/build/libs/sportsLink-0.0.1-SNAPSHOT.jar

nohup java -jar $JAR_PATH > /dev/null 2>&1 &

echo "> Deployment completed successfully."
