import groovy.json.* 


/*@NonCPS
//collectissues(String projectName){
//def jsonSlurper = new JsonSlurper() 
//def resultJson = jsonSlurper.parseText(data)
//def projectName = resultJson.key
//echo "$projectName"
 httpRequest authentication: 'jira_password',
  customHeaders: [[maskValue: false, name: 'Accept', value: 'application/json']], 
     httpMode: 'GET', url:"http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}&fields=key%2Csummary%2Cdescription&maxResults=1000&startAt=0"
 }*/



def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.confluence)
//String a=jsonObj.alm.projects.project.project_name
//String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
String a=jsonObj.confluence.spaces.space.name
String spaceName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
//env.name = projectName


/*sh '''curl -X GET \
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 03a3b589-c1d3-4234-8043-9335bf162947' \
  -d '{"jql":"project = EDN","startAt":0,"maxResults":2,"fields":["id","key"]} '| json_reformat
 '''
} */
sh """curl -X GET \
  'http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/search?cql=space%3D${spaceName}%20AND%20type%3Dpage' \
  -H 'authorization: Basic YXNobmltOmppcmFAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: a43ac1ca-0d0d-8c33-f433-34704ca8b4e9' \
  -H 'start: 1'
  
 
"""
 
 
//  HTTP_STATUS='$(echo "${process}" | tr -d '\n' | sed -e 's/.*HTTPSTATUS://')'
 //echo '$HTTP_STATUS
 //echo "$process"
}
/*def call(){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
//println(jsonObj.alm)
String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
collectissues(projectName)
}*/

