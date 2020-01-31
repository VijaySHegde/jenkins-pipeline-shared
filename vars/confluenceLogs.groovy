import groovy.json.JsonSlurper 
@NonCPS
def call(message)
{
 println(message)
 def request = libraryResource 'data.json' //using library resource for fetching json file
 def jsonSlurper = new JsonSlurper() //creating object for json slurper
 def resultJson = jsonSlurper.parseText(request)// here we are retriving json file
 def keyName = resultJson.key // your key/name used for rest api
  Date date = new Date() // to print timestamp
  sh " echo '${date}' confluence project with the keyName  '${keyName}' ${message} >log.txt"
}
