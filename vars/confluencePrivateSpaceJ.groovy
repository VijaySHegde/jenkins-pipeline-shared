def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.confluence)

String a=jsonObj.confluence.spaces.space.pages.page.key
String keyName=a.replaceAll("\\[", "").replaceAll("\\]","");
String b=jsonObj.confluence.spaces.space.pages.page.name
String privateName=b.replaceAll("\\[", "").replaceAll("\\]","");
//String c=jsonObj.confluence.spaces.space.pages.page.type
//String typeName=c.replaceAll("\\[", "").replaceAll("\\]","");

	

println(keyName)
println(privateName)


	
//env.name = projectName

//env.key = keyName
  

httpRequest authentication: 'confluence_cred1', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'POST', requestBody: 
  """{
    	
   
   	
		"key":"${keyName}",
	
	
	"name": "${privateName}"
	
	
	
   }""", url: 'http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/space/_private'
	//,validResponseCodes: '200:600' for resolving not in range error.
}
