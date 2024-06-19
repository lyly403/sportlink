#!/bin/bash

# After install script
if [ ! -d "/home/ubuntu/build" ]; then
  mkdir -p /home/ubuntu/build
fi

echo "After install script executed at $(date)"
