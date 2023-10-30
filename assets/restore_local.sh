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

export PGPASSWORD=${DB_PASSWORD}

# Drop the database if it exists
psql -U ${DB_USER} -h localhost -d postgres -c "DROP DATABASE IF EXISTS ${DATABASE_NAME};"

# Create a new database
psql -U ${DB_USER} -h localhost -d postgres -c "CREATE DATABASE ${DATABASE_NAME};"


# Restore the backup into the local PostgreSQL
psql -U ${DB_USER} -h localhost -d ${DATABASE_NAME} < ${BACKUP_FILE}

# Grant permissions
psql -U ${DB_USER} -h localhost -d ${DATABASE_NAME} -c "
GRANT ALL ON SCHEMA public TO public;
GRANT ALL ON SCHEMA public TO ${DB_USER};
"

echo "Database restored and permissions granted successfully!"
