#!/bin/bash

LOG_DIR=/home/ubuntu/build
LOG_FILE=$LOG_DIR/stop.log

# Ensure the log directory exists
if [ ! -d "$LOG_DIR" ]; then
  mkdir -p $LOG_DIR
  mkdir -p $LOG_DIR/scripts
fi

echo "Stopping application at $(date)" >> $LOG_FILE

CURRENT_PID=$(pgrep -f 'sportsLink-.*-SNAPSHOT.jar')

if [ -z "$CURRENT_PID" ]; then
  echo "No application is currently running." >> $LOG_FILE
else
  echo "Stopping application with PID: $CURRENT_PID" >> $LOG_FILE
  sudo kill -9 $CURRENT_PID
  sleep 5
fi

echo "Application stopped at $(date)" >> $LOG_FILE
