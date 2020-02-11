def call()
{
sh ''' 
curl -sGLu ashnim:jira@123 "http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/search" --data-urlencode 'cql=type IN (page)' --data-urlencode 'limit=0'
'''
}
