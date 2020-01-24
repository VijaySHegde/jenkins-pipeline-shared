def call()
{ sh '''
  curl --request POST \
  --url https://vijaysh.atlassian.net/wiki/rest/api/space \
  --header 'authorization: Basic aGVnZGV2aWpheTExOEBnbWFpbC5jb206U3B4MEdVWGRLREhpVmVCRXdJMFhDRDU5' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: 44321770-69d0-0b6b-9732-731230318d5b' \
  --data '{\r\n    "key": "NEW3",\r\n    "name": "finaldemo",\r\n    "description": {\r\n        "plain": {\r\n            "value": "This is an example space",\r\n            "representation": "plain"\r\n        }\r\n    },\r\n    "metadata": {}\r\n}\r\n'
           
'''

}
