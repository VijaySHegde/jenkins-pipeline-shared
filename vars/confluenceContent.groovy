import groovy.json.JsonSlurper 

@NonCPS
createSpace(String data1){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data1)
def keyName = resultJson.key
//def spaceName = resultJson.name
def titleName = resultJson.title
def typeName = resultJson.type
def bodyName = resultJson.body
//def projUrl = resultJson.url
echo "$keyName"
httpRequest authentication: 'confluence_cred', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'POST', requestBody: 
  """{
    	
      "key":"${keyName}",
    
      "title":"${titleName}",
      "type":"${typeName}",
      "body":"${bodyName}"
      
        
   }""", url: "https://vijaysh1.atlassian.net/wiki/rest/api/content"
}
	def call(){
def request = libraryResource 'confluence2.json'
createSpace(request)
}


