def call()
{
sh ''' curl --request GET \
  --url https://vijaysh.atlassian.net/wiki/rest/api/space \
  --header 'authorization: Basic aGVnZGV2aWpheTExOEBnbWFpbC5jb206U3B4MEdVWGRLREhpVmVCRXdJMFhDRDU5' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: 7a3d7cbd-3984-036a-7915-dbbab83a8245' \
  --data '{\r\n    "key": "TEST1",\r\n    "name": "Example space",\r\n    "description": {\r\n        "plain": {\r\n            "value": "This is an example space",\r\n            "representation": "plain"\r\n        }\r\n    },\r\n    "metadata": {}\r\n}\r\n'
  '''
  }
