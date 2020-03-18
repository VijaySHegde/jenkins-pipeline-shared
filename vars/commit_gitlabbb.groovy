import groovy.json.*
import groovy.json.JsonSlurper 
//int ids1;

def call(jsondata,rig){
      def jsonString = jsondata
      def jsonObj = readJSON text: jsonString
      String a=jsonObj.scm.repositories.repository.repo_name
String Name=a.replaceAll("\\[", "").replaceAll("\\]","");
	def jsona = readJSON text: rig
	def ip=jsona.url
	def username=jsona.userName
	def password=jsona.password
     //withCredentials([usernamePassword(credentialsId: 'gitlab_creds', passwordVariable: 'password', usernameVariable:'username')]) {
	sh "curl -X GET    -u ${username}:${password} ${ip}api/v4/users/5418155/projects -o outputgitlab.json"
    // }
   def jsonSlurper = new JsonSlurper()
 def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/outputgitlab.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def usertotal = resultJson.size()
      println(usertotal)
      println(Name)
      for(i=0;i<usertotal;i++)
         {
            if(Name==resultJson[i].name)
            {
               def id1 = resultJson[i].id 
               println(id1)
             return id1
            }
         }
         }
def commit(ids1,jsondata,rig){
	def jsonString = jsondata
def jsonObj = readJSON text: jsonString
      println(ids1)
	int ecount = jsonObj.riglet_info.auth_users.size()
         println("No of users "+ ecount)
	def jsona = readJSON text: rig
	def ip=jsona.url
	def username=jsona.userName
	def password=jsona.password
      //withCredentials([usernamePassword(credentialsId: 'gitlab_creds', passwordVariable: 'password', usernameVariable:'username')]) {
	     // sh "curl -X GET   -u${username}:${password}  ${ip}api/v4/projects/${ids1}/repository/commits?per_page=100 -o outputgitlab.json"
      //}
 String response = sh(script: """curl  -X GET  -u ${username}:${password} '${ip}api/v4/projects/${ids1}/repository/commits?per_page=100'  """, returnStdout: true)

  /* def jsonSlurper = new JsonSlurper()
   def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/outputgitlab.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson.size()
   println(total)
	println(ecount)*/
		def resultJson= readJSON text: response
	def total = resultJson.size()
   println(total)

      //println(JsonOutput.toJson(resultJson))
      List<String> JSON = new ArrayList<String>();
   	 List<String> LIST = new ArrayList<String>();
	 List<String> LIST1 = new ArrayList<String>();

	 def jsonBuilder = new groovy.json.JsonBuilder()
	
for(i=0;i<ecount;i++)
 {
	def email=jsonObj.riglet_info.auth_users[i] 
  for(j=0;j<total;j++)
  {
	 // println(jsonObj.config.emails.email[i])
	 // println(resultJson[j].author_email)
   if(email==resultJson[j].author_email)
   {
	   JSON.add(resultJson[j])
	 // JSON.add(resultJson[j])
     }
     }
	// println(jsonObj.config.emails.email[i])
	 cnt=JSON.size()
	 LIST1[i]=JSON.clone()
	 LIST.add(["email":email,"Commit": LIST1[i],"Commit_cnt":cnt])
	//LIST.add(["email":email,"Commit":JSON,"Commit_cnt":cnt])
	 //JCOPY[i]=(JsonOutput.toJson(JSON))
	// println(JCOPY[i])
	 JSON.clear()
	 
	   
     }
 /* for(i=0;i<JCOPY.size();i++)
	{
		println(JCOPY[i])
	}
    */
 jsonBuilder.gitlab(
  "total_commit" : resultJson,
  "commit_cnt" : resultJson.size(),
	 "individual_commit_Details":LIST
  
  )
File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/gitlabcommit.json")
	file.write(jsonBuilder.toPrettyString())

return jsonBuilder
	
}
catch(Exception e)
{
	//println(response)
	e.printStackTrace()
	
}
	 finally{
		//println(response)
		
		if(response.contains("200"))
		{
		println("data collected scuccesslfully")	
		
		}
	if(response.contains("404"))
	println("Not found")
	if(response.contains("400"))
	println("Bad Request")
        if(response.contains("401"))
	println("Unauthorized")
	if(response.contains("403"))
		println("Forbidden")
	if(response.contains("500"))
		println("Internal Server Error")
		 }
