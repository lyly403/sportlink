#!/bin/bash

# Validate service script
echo "Validating service at $(date)"
curl -f http://localhost:8080 || exit 1
echo "Service is up and running at $(date)"
