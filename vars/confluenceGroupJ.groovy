def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.confluence)

//String a=jsonObj.confluence.spaces.space.key
//String keyName=a.replaceAll("\\[", "").replaceAll("\\]","");
String b=jsonObj.confluence.spaces.space.pages.page.name
String groupName=b.replaceAll("\\[", "").replaceAll("\\]","");
//String c=jsonObj.confluence.spaces.space.pages.page.type
//String typeName=c.replaceAll("\\[", "").replaceAll("\\]","");

	

println(groupName)
//println(privateName)


	
//env.name = projectName

//env.key = keyName
  
sh """ 
curl -X GET \
  http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/group/${groupName} \
  -H 'authorization: Basic YXNobmltOmppcmFAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 3a14aa21-0edf-bf0a-2c60-4ba2d495e2e3' \
  -d '{ "space":{
	

		"key":"ramya"
},	
	
	"title": "ramya",
	 "type": "page"
}' """
/*httpRequest authentication: 'confluence_cred1', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'GET', url: "http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/group/${groupName}"
	//,validResponseCodes: '200:600' for resolving not in range error. */
}
