#!/bin/bash

# Before install script
echo "Before install script executed at $(date)" >> /home/ubuntu/first-team-project-dev/before_install.log

# 필요한 디렉토리를 생성
mkdir -p /home/ubuntu/first-team-project-dev
mkdir -p /home/ubuntu/first-team-project-dev/scripts

echo "Directories created successfully" >> /home/ubuntu/first-team-project-dev/before_install.log
