/*import groovy.json.*
import groovy.json.JsonOutput

def call()
{
 
  
 sh "curl -X POST  -H  Accept: application/json -H  Content-Type: application/json -d @request  http://3.134.156.211:3013/api/riglets/connectorServerDetails -o rigoutput.json"
   
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/rigoutput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
return resultJson
}*/
import groovy.json.*

@NonCPS
def create(){
  
   sh "curl -X POST  -H  Accept: application/json -H  Content-Type: application/json -d @request  http://3.134.156.211:3013/api/riglets/connectorServerDetails -o rigoutput.json"
   
   def jsonSlurper = new JsonSlurper()
   def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/rigoutput.json"),"UTF-8"))
  
  def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"))
  
 // def value = resultJson.component.measures[0].value
  //echo "=============================Total $value"
  //pushToInflux(value);
  

 }


def pushToInflux(value) {
  
  sh """
    curl -w '%{http_code}' -s -i  -X POST \
      'http://3.134.156.211:3013/api/riglets/connectorServerDetails/write?db=Collector' \
       > test2.txt
     """
     
 def response =new File('/var/lib/jenkins/workspace/' + JOB_NAME + '/test2.txt').text
 
  echo "====================== $response" 
  if (response.contains("204")) {
    //l = response.replaceAll("[^0-9][^0-9]*"," ")
 if (response.contains(200))
 {
      echo "DATA PUSHED TO Mongo DB"
     } else {
     
       error("ERROR PUSHING DATA TO INFLUX")
     }
}


def call()
{
 create()
}
