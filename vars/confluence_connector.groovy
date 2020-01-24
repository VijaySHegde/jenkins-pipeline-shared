def call(def keyname)
{ sh '''
  curl -X POST \
  https://vijaysh.atlassian.net/wiki/rest/api/space \
  -H 'authorization: Basic aGVnZGV2aWpheTExOEBnbWFpbC5jb206U3B4MEdVWGRLREhpVmVCRXdJMFhDRDU5' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: bf2e7c28-3692-ba9b-7c91-41875647d4f2' \
  -d '{
    "key": "NEW",
    "name": "New1",
    "description": {
        "plain": {
            "value": "This is an example space",
            "representation": "plain"
        }
    },
    "metadata": {}
}
'''
}
