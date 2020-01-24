def call(def keyname)
{ sh '''
  curl -X POST \
  https://vijaysh.atlassian.net/wiki/rest/api/space \
  -H 'authorization: Basic aGVnZGV2aWpheTExOEBnbWFpbC5jb206U3B4MEdVWGRLREhpVmVCRXdJMFhDRDU5' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ac7bc049-de7d-face-11e0-5458c2b74438' \
  -d "@repo.json" \
    "metadata": {}
'''

}
