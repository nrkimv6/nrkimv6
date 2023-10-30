
# Get the current date as a string
DATE=$(date +"%Y%m%d%H%M")


pg_dump -U root -d codereviewgpt --clean > prod_db_dump_local_$DATE.bak

echo "backup file : prod_db_dump_local_${DATE}.bak"