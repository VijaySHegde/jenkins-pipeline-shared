import groovy.json.JsonSlurper 

@NonCPS
createSpace(String data1){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data1)
def keyName = resultJson.key
//def spaceName = resultJson.name
//def projUrl = resultJson.url
echo "$keyName"
httpRequest authentication: 'confluence_cred', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'DELETE', requestBody: 
  """{
    	
      "key":"${keyName}"
      
        
   }""", url: "https://vijaysh1.atlassian.net/wiki/rest/api/space/{keyName}"
}
	def call(){
def request = libraryResource 'confluence3.json'
createSpace(request)
}
