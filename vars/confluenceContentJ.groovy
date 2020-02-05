def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.confluence)

String a=jsonObj.confluence.spaces.space.key
String keyName=a.replaceAll("\\[", "").replaceAll("\\]","");
String b=jsonObj.confluence.spaces.space.pages.page.title
String titleName=b.replaceAll("\\[", "").replaceAll("\\]","");
String c=jsonObj.confluence.spaces.space.pages.page.type
String typeName=c.replaceAll("\\[", "").replaceAll("\\]","");

	

println(keyName)
println(titleName)
println(typeName)

	
//env.name = projectName

//env.key = keyName
  

httpRequest authentication: 'confluence_cred1', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'POST', requestBody: 
  """{
    	
    
    "key":"${keyName}",
	"title":"${titleName}",
	"type":"${typeName}"
	
	
   }""", url: "http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/content"
	//,validResponseCodes: '200:600' for resolving not in range error.
}
