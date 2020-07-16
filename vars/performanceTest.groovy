def call (body)

{

sh """ curl 'https://a.blazemeter.com/api/v4/tests/8285708/start' \
    -X POST \
    -H 'Content-Type: application/json' \
    --user '76e55f8643e1efa3908b72bc:1e5ddeaa983cbdc10a382b7b24edabf9fc71eab5d2f9fe834cad267583a817bb0eb647d3'   """
}
