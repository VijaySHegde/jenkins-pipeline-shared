import groovy.json.*
import groovy.json.JsonOutput
def call()
{
  def request = libraryResource 'rig1.json'
  
 
  sh "curl -X POST  -H  Accept: application/json -H  'Content-Type: application/json' -d @{request}.json  'http://3.134.156.211:3013/api/riglets/connectorServerDetails' -o rigoutput.json"
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/rigoutput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
return resultJson
}
