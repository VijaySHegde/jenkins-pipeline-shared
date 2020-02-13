
import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
 def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"))
 def value = resultJson.component.measures[0].value
pushToInflux(value);
}
def pushToInflux(value)
{
 sh """curl -w '%{http_code}' -s -i -X POST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data 'sonar vulnerabilities=${value}' > test.txt """

def response =new File('/var/lib/jenkins/workspace/' + JOB_NAME+ '/test.txt').text

 echo " ============ $response"

if(response == "204" || response == "200")
{
 echo " Data pushed into influxDB "
}
else
{
 error("Error while pushing")
}
}
def call()
{
 create()
}
