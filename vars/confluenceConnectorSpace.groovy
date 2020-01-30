import groovy.json.JsonSlurper 

@NonCPS
createRepo(String data1){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data1)
def keyName = resultJson.key
def spaceName = resultJson.name
//def projUrl = resultJson.url
echo "$keyName"
httpRequest authentication: 'confluence_cred', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'POST', requestBody: 
  """{
    	
      "key":"${keyName}",
      "name":"${spaceName}"
        
   }""", url: "https://vijaysh1.atlassian.net/wiki/rest/api/space"
}
	def call(){
def request = libraryResource 'data.json'
createRepo(request)
}



/* responseHandle: 'NONE',url:*/
