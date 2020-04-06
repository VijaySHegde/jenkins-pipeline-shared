import groovy.json.*
import groovy.json.JsonOutput

def call()
{
sh   "curl -X  POST   --header apiUser:admindevops  --header Authorization:apiToken UGFzc3dvcmRJc0F1dGhUb2tlbjp7ImFwaUtleSI6IjxkZUMyd3JhS084ZjRQdjdCMy9IVk9DbitTUjhMVWxFQz4ifQo=  -H Accept:application/json  -H  Content-Type:application/json  -d '{\"codeRepoEntries\":[{\"toolName\":\"GitHub\",\"description\":\"Brief description\",\"options\":{\"branch\":\"master\",\"url\":\"https://github.com/kavi1997/Demo.git\"}}],"metaData": { "applicationName": "Hygieianew", "componentName": "Hygieianew", "owner": { "authType": "STANDARD", "username": "admin" }, "template": "Hygieia", "title": "Hygieiamew", "type": "team" }}' http://ec2-3-21-21-136.us-east-2.compute.amazonaws.com:3000/api/dashboard/remoteCreate"
//sh   "curl -X  POST   --header apiUser:admindevops  --header Authorization:apiToken UGFzc3dvcmRJc0F1dGhUb2tlbjp7ImFwaUtleSI6IjxkZUMyd3JhS084ZjRQdjdCMy9IVk9DbitTUjhMVWxFQz4ifQo=  -H Accept:application/json  -H  Content-Type:application/json  -d '{\"codeRepoEntries\":[{\"toolName\":\"GitHub\",\"description\":\"Brief description\",\"options\":{\"branch\":\"master\",\"url\":\"https://github.com/kavi1997/Demo.git\"}}]}' http://ec2-3-21-21-136.us-east-2.compute.amazonaws.com:3000/api/dashboard/remoteCreate"
  -H 'authorization: apiToken YWRtaW5kZXZvcHM6VUdGemMzZHZjbVJKYzBGMWRHaFViMnRsYmpwN0ltRndhVXRsZVNJNklqeGtaVU15ZDNKaFMwODRaalJRZGpkQ015OUlWazlEYml0VFVqaE1WV3hGUXo0aWZRbz0=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"codeRepoEntries":[{"toolName":"GitHub","description":"Brief description","options":{"branch":"master","url":"https://github.com/kavi1997/Demo.git","personalAccessToken":""}}]}' """
}*/
}
