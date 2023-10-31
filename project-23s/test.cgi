#!/usr/bin/env bash

echo "Content-type: text/plain"
echo ""
echo "Welcome to our application! Here are your search results:"


output=$(java -cp .:mysql.jar MovieBackend "$QUERY_STRING")

# Build JSON response and output it
echo "{\"movieData\": \"$output\"}"

