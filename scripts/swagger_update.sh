#!/bin/bash

set -Eeuo pipefail

# Configuration
API_PORT=8080
API_DOCS_PATH="/v3/api-docs.yaml"
OUTPUT_DIR="temp_api"
OUTPUT_FILE="api-docs.yaml"

# Check if curl is available
if ! command -v curl &> /dev/null; then
    echo "Error: curl is required but not installed."
    exit 1
fi

# Create output directory
mkdir -p "$OUTPUT_DIR" || {
    echo "Warning: Directory exists or cannot be created"
}

# Try to get API docs
echo "Attempting to fetch API docs from http://localhost:${API_PORT}${API_DOCS_PATH}"
if ! curl -o "${OUTPUT_DIR}/${OUTPUT_FILE}" "http://localhost:${API_PORT}${API_DOCS_PATH}"; then
    echo ""
    echo "ERROR: Failed to fetch API documentation."
    echo "Possible reasons:"
    echo "1. Your application is not running"
    echo "2. It's running on a different port (not ${API_PORT})"
    echo "3. The API docs path is different (not ${API_DOCS_PATH})"
    echo "4. There's a network/firewall issue"
    echo ""
    echo "Try these solutions:"
    echo "1. Start your application first: mvn spring-boot:run"
    echo "2. If using a different port, modify the API_PORT variable in this script"
    exit 1
fi

echo "Successfully downloaded API docs to ${OUTPUT_DIR}/${OUTPUT_FILE}"
cat "${OUTPUT_DIR}/${OUTPUT_FILE}"
