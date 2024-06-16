#!/bin/bash

# Validate service script
echo "Validating service at $(date)"
curl -f http://sportlink.store || exit 1
echo "Service is up and running at $(date)"
