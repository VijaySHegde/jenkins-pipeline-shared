def call (body)

{

sh """ curl 'https://a.blazemeter.com/api/v4/tests//start' \
    -X POST \
    -H 'Content-Type: application/json' \
    --user 'api_key_id:api_key_secret'   """
}
