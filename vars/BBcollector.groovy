def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.scm)


String b=jsonObj.scm.projects.project.project_key 
String projectKey=b.replaceAll("\\[", "").replaceAll("\\]","");



httpRequest authentication: 'bitbucket_cred', contentType: 'APPLICATION_JSON', customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json']], httpMode: 'GET', requestBody: """
{
    
}""", responseHandle: 'NONE', url:"http://18.224.68.30:7990/rest/api/1.0/projects/${projectKey}/repos"
}
