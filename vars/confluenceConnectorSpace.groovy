import groovy.json.JsonSlurper 

/*@NonCPS*/
createRepo(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def spaceName = resultJson.name
def keyName = resultJson.key
//def projUrl = resultJson.url

httpRequest authentication: 'confluence_cred', 
	customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']],
    
    httpMode: 'POST', requestBody: 
  """{
    	"name"=${spaceName}
      "key"=${keyName}
        
   }""",
	   responseHandle: 'NONE', url: "https://vijaysh.atlassian.net/wiki/rest/api/space"
}
	def call(){
def request = libraryResource 'data.json'
createRepo(request)
}
