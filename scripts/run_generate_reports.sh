#!/bin/bash

set -Eeuxo pipefail # Exit on error, undefined vars, trace commands, fail on pipe errors

# Check for Maven
if ! command -v mvn &> /dev/null; then
  echo "Error: Maven (mvn) not found. Please install Maven and ensure it's in your PATH."
  echo "On WSL/Linux: sudo apt install maven"
  echo "On Windows: Download from https://maven.apache.org/download.cgi and add to PATH"
  exit 1
fi

# Resolve script and project paths
export INITIAL_WORKING_DIRECTORY_PATH=$(pwd -P)
export INITIAL_WORKING_DIRECTORY_NAME=${INITIAL_WORKING_DIRECTORY_PATH##*/}

export SCRIPT_PATH="$(
  cd "$(dirname "$0")" >/dev/null 2>&1
  pwd -P
)"

cd "$SCRIPT_PATH" || exit 1
cd ..

# Initialize
############

echo "RUNNING TESTS IN PARALLEL FOR SPEED, MIND THAT SOME TESTS MAY NOT RUN PROPERLY"

# Run Maven build and tests with JaCoCo
mvn clean verify -Pjacoco

# Open JaCoCo report in browser
JACOCO_REPORT="boot/target/site/jacoco-aggregate/index.html"

if [[ -f "$JACOCO_REPORT" ]]; then
  # Detect operating system
  case "$(uname -s)" in
    Linux*)
      # Use xdg-open for Linux
      xdg-open "$JACOCO_REPORT" &
      ;;
    Darwin*)
      # Use open for macOS
      open -a "Google Chrome" "$JACOCO_REPORT" &
      ;;
    CYGWIN*|MINGW*|MSYS*|Windows*)
      # Check if running in WSL
      if [[ -f /proc/version ]] && grep -qi microsoft /proc/version; then
        # WSL: Convert Linux path to Windows path
        win_path=$(wslpath -w "$(pwd)/$JACOCO_REPORT")
        "/mnt/c/Program Files/Google/Chrome/Application/chrome.exe" "file://$win_path" &
      else
        # Git Bash or native Windows: Use Windows start command
        start "" "chrome" "file://$(cygpath -w "$(pwd)/$JACOCO_REPORT")" &
      fi
      ;;
    *)
      echo "Unsupported OS: $(uname -s)"
      exit 1
      ;;
  esac
else
  echo "JaCoCo report not found at $JACOCO_REPORT"
  exit 1
fi
