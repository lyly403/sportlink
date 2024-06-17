#!/bin/bash

LOG_FILE=/home/ubuntu/build/stop.log

echo "Stopping application at $(date)" >> $LOG_FILE

CURRENT_PID=$(pgrep -f sportsLink-0.0.1-SNAPSHOT.jar)

if [ -z "$CURRENT_PID" ]; then
  echo "No application is currently running." >> $LOG_FILE
else
  echo "Stopping application with PID: $CURRENT_PID" >> $LOG_FILE
  kill -9 $CURRENT_PID
  sleep 5
fi

echo "Application stopped at $(date)" >> $LOG_FILE
