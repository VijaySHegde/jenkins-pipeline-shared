import groovy.json.*
import groovy.json.JsonOutput

def call()
{
sh "curl -X POST  -H  --header apiUser: admindevops  --header Authorization: apiToken UGFzc3dvcmRJc0F1dGhUb2tlbjp7ImFwaUtleSI6IjxkZUMyd3JhS084ZjRQdjdCMy9IVk9DbitTUjhMVWxFQz4ifQo=  Accept:application/json -H  Content-Type:application/json  -d '{\"codeRepoEntries\":[{\"toolName\":\"GitHub\",\"description\":\"Brief description\",\"options\":{\"branch\":\"master\",\"url\":\"https://github.com/kavi1997/Demo.git\",\"personalAccessToken\":" "}}]}' http://ec2-3-21-21-136.us-east-2.compute.amazonaws.com:3000/api/dashboard/remoteCreate "
}