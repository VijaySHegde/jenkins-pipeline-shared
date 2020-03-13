import groovy.json.JsonSlurper 
import groovy.json.*
import groovy.json.JsonOutput

@NonCPS
json(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)

def json=JsonOutput.toJson(resultJson)
  sh "curl -X POST -H  Accept: application/json -H  Content-Type: application/json -d @json  http://3.134.156.211:3013/api/riglets/connectorServerDetails -o rigoutput.json"
    /* def connection = url.toURL().openConnection()
 
 try {
      connection.connect()
     def line = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine()
     return jsonSlurper.parseText(line)
  } finally {
        connection.disconnect();
    } */
}
def call(){
 def request = libraryResource 'rig1.json'
 json(request)
}
