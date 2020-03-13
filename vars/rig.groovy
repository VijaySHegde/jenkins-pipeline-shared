import groovy.json.JsonSlurper 

@NonCPS
json(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def rigletName = resultJson.rigletName
def toolName= resultJson.toolName
 httpRequest  contentType: "APPLICATION_JSON", 
    
    httpMode: 'POST', requestBody: 
  """{
  
    "rigletName":"${rigletName}",
    "toolName":"${toolName}"


  }""" ,url:"http://3.134.156.211:3013/api/riglets/connectorServerDetails"
     def connection = url.toURL().openConnection()
 
 try {
      connection.connect()
     def line = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine()
     return jsonSlurper.parseText(line)
  } finally {
        connection.disconnect();
    } 
}
def call(){
 def request = libraryResource 'rig1.json'
 json(request)
}
