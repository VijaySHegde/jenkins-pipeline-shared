def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.confluence)

String a=jsonObj.confluence.spaces.space.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
String keyName=confluence.spaces.space.keyName
env.name = projectName
  

httpRequest authentication: 'confluence_cred', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'POST', requestBody: 
  """{
    	
      "key":"${keyName}",
      "name":"${projectName}"
        
   }""", url: "https://vijaysh1.atlassian.net/wiki/rest/api/space"
}
