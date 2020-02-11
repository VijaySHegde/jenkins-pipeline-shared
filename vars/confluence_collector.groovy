def call()
{
sh ''' 
A_USER="ashnim"
A_PASSWD="jira@123"
INSTANCE_HOST="http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090"
 
# Total number of pages
curl -sGLu ${A_USER}:${A_PASSWD} "https://${INSTANCE_HOST}/rest/api/search" --data-urlencode 'cql=type IN (blogpost, page)' --data-urlencode 'limit=0'
  }
