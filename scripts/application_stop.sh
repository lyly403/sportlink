#!/bin/bash

LOG_DIR=/home/ubuntu/build
LOG_FILE=$LOG_DIR/stop.log

# Ensure the log directory exists
mkdir -p $LOG_DIR

echo "Stopping application at $(date '+%Y-%m-%d %H:%M:%S')" >> $LOG_FILE

CURRENT_PID=$(pgrep -f 'sportsLink-.*-SNAPSHOT.jar')

if [ -z "$CURRENT_PID" ]; then
  echo "No application is currently running." >> $LOG_FILE
else
  echo "Stopping application with PID: $CURRENT_PID" >> $LOG_FILE
  sudo kill -15 $CURRENT_PID
  sleep 5

  if kill -0 $CURRENT_PID > /dev/null 2>&1; then
    echo "Application did not terminate gracefully, forcing shutdown." >> $LOG_FILE
    sudo kill -9 $CURRENT_PID
  fi
fi

echo "Application stopped at $(date '+%Y-%m-%d %H:%M:%S')" >> $LOG_FILE