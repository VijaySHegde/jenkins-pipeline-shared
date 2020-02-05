def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.confluence)

String a=jsonObj.confluence.spaces.space.name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
String b=jsonObj.confluence.spaces.space.key
String keyName=b.replaceAll("\\[", "").replaceAll("\\]","");
println(projectName)
println(keyName)

	
env.name = projectName

env.key = keyName
  

httpRequest authentication: 'confluence_cred1', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'POST', requestBody: 
  """{
    	
      "key":"${keyName}",
      "name":"${projectName}"
        
   }""", url: "http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/space"
}
