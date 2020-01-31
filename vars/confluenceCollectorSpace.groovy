import groovy.json.JsonSlurper 

@NonCPS
showRepo(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
//def repoName = resultJson.name
def spaceKey = resultJson.key
//def projUrl = resultJson.url

httpRequest authentication: 'confluence_cred', contentType: "APPLICATION_JSON", 
    
    httpMode: 'GET', url: "https://vijaysh.atlassian.net/wiki/rest/api/space/${spaceKey}"
}
	def call(){
def request = libraryResource 'confluence1.json'
showRepo(request)
}
