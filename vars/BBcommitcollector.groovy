@groovy.transform.Sortable
import groovy.json.*
import java.util.*; 

def call(jsondata,rig){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
String a=jsonObj.scm.projects.project.repositories.repository.repo_name
String repoName=a.replaceAll("\\[", "").replaceAll("\\]","");
String b=jsonObj.scm.projects.project.project_key 
String Key=b.replaceAll("\\[", "").replaceAll("\\]","");
int ecount = jsonObj.riglet_info.auth_users.size()
println("No of users "+ ecount)
println(Key)
println(repoName)
	
def jsona = readJSON text: rig
def userId=jsona.userName
def pass=jsona.password
def Url=jsona.url
println(userId)
println(Url)
// Date date = new Date() 
 //withCredentials([usernamePassword(credentialsId: 'bitbucket_cred', passwordVariable: 'pass', usernameVariable: 'userId')]) {
def sresponse = sh(script: """curl -X GET -L -w '%{http_code}\\n' -H -d  -u '${userId}':'${pass}' '${Url}'/rest/api/1.0/projects/'${Key}'/repos/'${repoName}'/comits?limit=50 -o outputbitbucket.json""", returnStdout: true)
 //} 
//HttpURLConnection http = (HttpURLConnection)url.openConnection();
//int statusCode = http.getResponseCode();
println(sresponse)
try{
def jsonSlurper = new JsonSlurper()
def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/outputbitbucket.json"))
def total = resultJson.size
echo "Total no.of commits in $repoName $total"
//File file = new File(output.json)
//file.write(total)
//def commiter=1
List<String> JSON = new ArrayList<String>();
List<String> JCOPY = new ArrayList<String>();
List<String> JSON1= new ArrayList<String>();
for(i=0;i<ecount;i++)
{	 
  for(j=0;j<total;j++)
  {

 if(jsonObj.riglet_info.auth_users[i]==resultJson.values.author[j].emailAddress)
	     {
	JSON.add(resultJson.values[j])
	//println(JSON) 
    }
}
	 count=JSON.size()
	 //  println(USER)
         	
 JSON1[i]=JSON.clone()
 JCOPY.add(["Email":jsonObj.riglet_info.auth_users[i],"Individual_commit":JSON1[i],"Commit_count":count])
	
JSON.clear()
}

def jsonBuilder = new groovy.json.JsonBuilder()

jsonBuilder.bitbucket(
  "Total_commits": resultJson.values,
 "Commit_count": resultJson.size,
 "Individual_commits":JCOPY
)


//File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/commitsbitbucket.json")
//file.write(jsonBuilder.toPrettyString())
	println(jsonBuilder.toPrettyString())
return jsonBuilder
}
	catch(Exception e)
	{
	if(sresponse==400)
	println("Bad Request")
        if(sresponse==401)
	println("Unauthorized")
        if(sresponse==403)
	println("Forbidden")
        if(sresponse==404)
	println("Not Found")
        if(sresponse==500)
	println("Internal Server Error")
	
	}
}

