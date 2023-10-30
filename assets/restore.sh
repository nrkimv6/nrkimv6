#!/bin/bash

# Ensure a backup file is provided as an argument
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 /path/to/your/backup_file.sql"
    exit 1
fi

BACKUP_FILE="$1" # Get the backup file path from the command-line argument
DATABASE_NAME="codereviewgpt"
DB_USER="root"
DB_PASSWORD="password!"
DOCKER_CONTAINER_ID="a6753d2a9709b5374a075245fb9b57fc0075a25873a27be068b454ca75e6499a" # Replace with your container's ID

# Extract a substring of the container ID to match
SUBSTRING_ID=${DOCKER_CONTAINER_ID:0:12}

# Check if the Docker container is running using the substring of its ID
if ! docker ps --format '{{.ID}}' | grep -q "${SUBSTRING_ID}"; then
    echo "Container with ID ${DOCKER_CONTAINER_ID} is not running. Please ensure it's running and try again."
    exit 1
fi

# Create the database (it assumes the PostgreSQL version supports "IF NOT EXISTS", otherwise an alternative method might be needed)
docker exec -i ${DOCKER_CONTAINER_ID} psql -U ${DB_USER} -c "CREATE DATABASE IF NOT EXISTS ${DATABASE_NAME};"

# Restore the backup into the running PostgreSQL container
cat ${BACKUP_FILE} | docker exec -i ${DOCKER_CONTAINER_ID} psql -U ${DB_USER} ${DATABASE_NAME}

# Grant permissions
docker exec -i ${DOCKER_CONTAINER_ID} psql -U ${DB_USER} ${DATABASE_NAME} -c "
GRANT ALL ON SCHEMA public TO public;
GRANT ALL ON SCHEMA public TO ${DB_USER};
"

echo "Database restored and permissions granted successfully!"
