import groovy.json.JsonSlurper 

@NonCPS
createRepo(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def spaceName = resultJson.name
def keyName = resultJson.key
//def projUrl = resultJson.url

httpRequest authentication: 'confluence_cred', contentType: "APPLICATION_JSON", 
    
    httpMode: 'POST', requestBody: 
  """{
    	"name"=${spaceName}
      "key"=${keyName}
        
   }""", url: "https://vijaysh.atlassian.net/wiki/rest/api/spaces"
}
	def call(){
def request = libraryResource 'data.json'
createRepo(request)
}
