def call()
{
sh '''	curl -X GET \
  'http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/search?cql=space%3Dvijay%20AND%20type%3Dpage' \
  -H 'authorization: Basic YXNobmltOmppcmFAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: a13d0cc1-e2ff-c62c-8e77-483f2f8bd0d4' \
  -H 'start: 1'
  '''
}
