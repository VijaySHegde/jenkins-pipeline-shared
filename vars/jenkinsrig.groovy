import groovy.json.*
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

	

def call(JSON)
{
def jsonString = JSON
def jsonObj = readJSON text: jsonString
def mailcount = jsonObj.riglet_info.auth_users.size()
	String pro = jsonObj.ci.jobs.job.job_name
	String ProjectName=pro.replaceAll("\\[", "").replaceAll("\\]","");
	print(mailcount)
//withCredentials([usernamePassword(credentialsId: 'jenkins_cred', passwordVariable: 'pass', usernameVariable: 'user')]) {
//sh "curl -X GET -g http://52.14.229.175:8080/job/${JOB_NAME}/api/json?tree=builds[id,result,changeSets[items[authorEmail]]] -u suneel:11035ac86f58bc32d03d8e873b7cc063a3 -o username.json"
	sh "curl -X GET -g http://3.130.89.18:8080/job/${ProjectName}/api/json?tree=builds[id,result,changeSets[items[authorEmail]]] -u vj:11e992889bcab5d24752ab728c45cb8c48 -o username.json"
	//}
	def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/username.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
	def build=resultJson.builds[0].id
	print(build)
	int value = Integer.parseInt(build);
	print(value)


 


  List<String> USERS = new ArrayList<String>()
	List<String> USERF = new ArrayList<String>()
 List<String>  LISTSUCCESS=new ArrayList<String>()
	 List<String>  LISS=new ArrayList<String>()
	 List<String>  LISF=new ArrayList<String>()
	List<String> LISTFAILURE=new ArrayList<String>()
	List<String> SUCCESS = new ArrayList<String>()
    List<String> FAILURE = new ArrayList<String>()
	List<String> USERI = new ArrayList<String>()
 List<String>  LISI=new ArrayList<String>()
	 List<String>  LISTIN=new ArrayList<String>()


 


	def jsonBuilder = new groovy.json.JsonBuilder()

   for(j=0;j<mailcount;j++)
   {
	   def cns=0
	   def cnf=0
	     def cni=0
    def email=jsonObj.riglet_info.auth_users[j]
	   //print(email)
  for(i=1;i<value-1;i++)
  {
 
   
   def state=resultJson.builds[i].result
	  //print (state)
   def s=resultJson.builds[i].changeSets.size()
	 // def e=resultJson.builds[0].changeSets[s-1].items[0].authorEmail
	  //println(e)
	  //print (s)
	  if (s>0){
		  if(resultJson.builds[i].changeSets[s-1].items[0].authorEmail.equals(email) && state.equals("SUCCESS"))
		  //print("insidejjjjjjjjjjjjjjjjjjjjjjjjjjjjj")
   {
   
    USERS.add(resultJson.builds[i])
	   //print("insidejjjjjjjjjjjjjjjjjjjjjjjjjjjjj8888888888888888888888888888")
	  
   }
   else if(resultJson.builds[i].changeSets[s-1].items[0].authorEmail.equals(email) && state.equals("FAILURE"))
   {
	   
	   USERF.add(resultJson.builds[i])
   }
		  if(resultJson.builds[i].changeSets[s-1].items[0].authorEmail.equals(email)){
		 
   
    USERI.add(resultJson.builds[i])
		  
	 
	  
   }	  
		  
	  }
	  //int len = s-1;
   }
   cns=USERS.size()

	
	   LISS[j]=USERS.clone()
	   LISF[j]=USERF.clone()
	 LISI[j]=USERI.clone()
	   
   LISTSUCCESS.add(["email":email,"success":LISS[j],"Success_cnt":cns])
   USERS.clear()
	 
   cnf=USERF.size()
   LISTFAILURE.add(["email":email,"failure":LISF[j],"Failure_cnt":cnf])
   USERF.clear()
	 cni=USERI.size()
	LISTIN.add(["email":email,"total":LISI[j],"total_cnt":cni])
   USERI.clear()
   }
	for(i=1;i<value;i++)
  {
   //def date=resultJson.results.result[i].buildCompletedDate
   def state=resultJson.builds[i].result
	  print(state)

   
  if(state.equals("SUCCESS"))
  {
   
 
   SUCCESS.add(resultJson.builds[i])
     
  }
   else if(state.equals("FAILURE"))
   {
    
       FAILURE.add(resultJson.builds[i])
     
   }
  }
	
		    jsonBuilder.JENKINS(
  
  "teambuild_cnt" : value,
  "teambuilds" : resultJson,		    
  "teamsuccess" : SUCCESS,
  "teamsuccessbuild_cnt" : SUCCESS.size(),
  "teamfailure" : FAILURE, 
  "teamfailurebuild_cnt" :FAILURE.size(),
  "individualsuccess": LISTSUCCESS,
  "individualfailure": LISTFAILURE,
  "individualtotal":LISTIN
  )
	
File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/jenkins.json")
file.write(jsonBuilder.toPrettyString())
	return jsonBuilder
	//def reader1 = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/jenkins.json"),"UTF-8"))
//def resu = jsonSlurper.parse(reader1)

	//println(resu.individualsuccess[2].Success_cnt)
				   //println(jsonBuilder)
	

}
