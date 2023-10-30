
# Get the current date as a string
DATE=$(date +"%Y%m%d%H%M")


docker exec -i a6753d2a9709b5374a075245fb9b57fc0075a25873a27be068b454ca75e6499a pg_dump -U root -d codereviewgpt --clean > prod_db_dump_$DATE.bak

echo "backup file : prod_db_dump_${DATE}.bak"
