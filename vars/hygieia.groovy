import groovy.json.*
import groovy.json.JsonOutput

def call()
{
sh "curl -X POST  -H  Accept:application/json -H  Content-Type:application/json  -d '{ {"codeRepoEntries":[{"toolName":"GitHub","description":"Brief description","options":{"branch":"master","url":"https://github.com/kavi1997/Demo.git","personalAccessToken":""}}] \"rigletName\":\"${rig}\", \"toolName\":\"${tool}\"}' http://3.134.156.211:3013/api/riglets/connectorServerDetails
