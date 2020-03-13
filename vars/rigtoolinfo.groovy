import groovy.json.*
import groovy.json.JsonOutput

def call(rig,tool)
{
 
  
 //sh "curl -X POST  -H  Accept:application/json -H  Content-Type:application/json -d @resources/'${rig}'.json  http://3.134.156.211:3013/api/riglets/connectorServerDetails -o rigoutput.json"
   sh """
   curl -X POST \
  http://3.134.156.211:3013/api/riglets/connectorServerDetails \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
    "rigletName":"${rig}",
    "toolName":"${tool}"

}' -o rigoutput.json
"""
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/rigoutput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
 
return JsonOutput.toJson(resultJson)
}

