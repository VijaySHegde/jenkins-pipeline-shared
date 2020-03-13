@groovy.transform.Sortable
import groovy.json.*
import java.util.*; 

def call(jsondata){
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
	
def jsonSlurper1 = new JsonSlurper1()
def reader = jsonSlurper1.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/rigoutput.json"))
def Url = reader.url
def userId=reader.userName
def pass=reader.password
// Date date = new Date() 
 //withCredentials([usernamePassword(credentialsId: 'bitbucket_cred', passwordVariable: 'pass', usernameVariable: 'userId')]) {
	 sh "curl -X GET  -H -d  -u $userId:$pass '${Url}'/rest/api/1.0/projects/'${Key}'/repos/'${repoName}'/commits?limit=50 -o outputbitbucket.json"
 //} 

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


File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/commitsbitbucket.json")
file.write(jsonBuilder.toPrettyString())	
return jsonBuilder
}


