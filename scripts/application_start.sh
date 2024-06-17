#!/bin/bash

# Application start script
echo "Starting application at $(date)"

JAR_PATH=/home/ubuntu/first-team-project-dev/sportsLink-0.0.1-SNAPSHOT.jar

if [ ! -f "$JAR_PATH" ]; then
  echo "JAR file not found: $JAR_PATH"
  exit 1
fi

nohup java -jar $JAR_PATH > /home/ubuntu/first-team-project-dev/app.log 2>&1 &
echo "Application started at $(date)"
