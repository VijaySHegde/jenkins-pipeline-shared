
import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def value = resultJson.component.measures[0].value
 
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'sonar vulnerabilities=${value}' >output.txt"""

 def response =new File('/var/lib/jenkins/workspace/sonarnew/output.txt').text
echo "========================= $response"

if(response == "204" || response == "200")
{
 echo " Data pushed into influxDB "
}
else
{
 error("Error while pushing")
}
}


