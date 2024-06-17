#!/bin/bash

Before install script
echo "Before install script executed at $(date)" >> /home/ubuntu/build/before_install.log

mkdir -p /home/ubuntu/build
mkdir -p /home/ubuntu/build/scripts

echo "Directories created successfully" >> /home/ubuntu/build/before_install.log