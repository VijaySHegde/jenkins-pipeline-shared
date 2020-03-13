import groovy.json.*
import groovy.json.JsonOutput


def riglet1(jsondata,gitlab,bamboo,sonar)
{
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.riglet_info.auth_users.size()
	def team=jsonObj.riglet_info.name
	def scm=jsonObj.scm.tool.name
	def ci=jsonObj.ci.tool.name
List<String> jsonStringa= new ArrayList<String>();
 
   jsonStringa.add(gitlab)
	jsonStringa.add(bamboo)
	jsonStringa.add(sonar)
	List<String> JSON = new ArrayList<String>();
  List<String> LIST = new ArrayList<String>();
  List<String> LIST1 = new ArrayList<String>();
  List<String> JSON1 = new ArrayList<String>();
	int i,j,k,l
	
for(i=0;i<jsonStringa.size();i++)
  { 
   
    String name="  "
	 

	  if(jsonStringa[i].contains("gitlab") && scm=="gitlab")
    {
      name="gitlab"
def jsonObjc = readJSON text: jsonStringa[i]
def cnt =jsonObjc.gitlab.commit_cnt

 
	    LIST.add(["toolName":name,"metricName":"commits","value":cnt])

  }
	  if(jsonStringa[i].contains("bitbucket")&& scm=="bitbucket")
    {
      name="bitbucket"
	  //  metric="commits"
//def jsonStringa = bitbucket
def jsonObja = readJSON text: jsonStringa[i]
int total=jsonObja.bitbucket.Commit_count
 // println(jsonObja)
  //println(total)
 
	    LIST.add(["toolName":name,"metricName":"commits","value":total])
    }
	   if(jsonStringa[i].contains("JENKINS") && ci=="jenkins")
    {
      name="jenkins"
      def jsonObjb = readJSON text: jsonStringa[i]
	   // print jsonObjb
      def total=jsonObjb.JENKINS.teambuild_cnt
  def scnt =jsonObjb.JENKINS.teamsuccessbuild_cnt
	    def fcnt=jsonObjb.JENKINS.teamfailurebuild_cnt
	    LIST.add(["toolName":name,"metricName":"total_builds","value":total])
	    LIST.add(["toolName":name,"metricName":"successful_builds","value":scnt])
	    LIST.add(["toolName":name,"metricName":"failure_builds","value":fcnt])
      }
   if(jsonStringa[i].contains("Bamboo") && ci=="bamboo")
    {
      name="bamboo"
def jsonObjb = readJSON text: jsonStringa[i]
def total=jsonObjb.Bamboo.totalBuilds
  def scnt =jsonObjb.Bamboo.teamsuccessbuild_cnt
	    def fcnt=jsonObjb.Bamboo.teamfailurebuild_cnt
	    LIST.add(["toolName":name,"metricName":"total_builds","value":total])
	    LIST.add(["toolName":name,"metricName":"success_builds","value":scnt])
	    LIST.add(["toolName":name,"metricName":"failure_builds","value":fcnt]) 
    }   
	  if(jsonStringa[i].contains("sonar"))
    {
	    name="sonar"
	    def jsonObje= readJSON text: jsonStringa[i]
	    for(i=0;i<jsonObje.sonar.metrics.component.measures.size();i++){
    def sonar_metric=jsonObje.sonar.metrics.component.measures[i].metric
		    def d=jsonObje.sonar.metrics.component.measures[i].value
    double data = Double.parseDouble(d); 
       LIST.add(["toolName":name,"metricName":sonar_metric,"value":data])
	    }
    }
  }

	
    for(j=0;j<ecount;j++)
   {
	 def email=jsonObj.riglet_info.auth_users[j]
	   int score=0
   // int reward=0
    String name="  "
	 for(i=0;i<jsonStringa.size();i++)
  { 
   
	  
  if(jsonStringa[i].contains("Bamboo") && ci=="bamboo")
    {
     name="bamboo"
   
	   def jsonObja = readJSON text: jsonStringa[i]
  def scnt =jsonObja.Bamboo.individualsuccess[j].Success_cnt
  def fcnt =jsonObja.Bamboo.individualfailure[j].Failure_cnt
def    total=jsonObja.Bamboo.individualtotal[j].totalBuilds
 def email1=jsonObja.Bamboo.individualsuccess[j].email
      
	  
 if(email==email1)
  {
   LIST1.add(["toolName":name,"metricName":"total_builds","value":total])
	    
 
	    LIST1.add(["toolName":name,"metricName":"success_builds","value":scnt])
	    

	    LIST1.add(["toolName":name,"metricName":"failure_builds","value":fcnt])
  }
   }
	  if(jsonStringa[i].contains("JENKINS") && ci=="jenkins")
    {
 
	   
	    
     name="jenkins"
    
	   def jsonObja = readJSON text: jsonStringa[i]

 
  def scnt =jsonObja.JENKINS.individualsuccess[j].Success_cnt
  def fcnt =jsonObja.JENKINS.individualfailure[j].Failure_cnt
	    def    total=jsonObja.JENKINS.individualtotal[j].total_cnt
 def email1=jsonObja.JENKINS.individualsuccess[j].email
      

 println(scnt)
 
	  
 if(email==email1)
  {
   LIST1.add(["toolName":name,"metricName":"total_builds","value":total])
	    
 
	   LIST1.add(["toolName":name,"metricName":"successful_builds","value":scnt])
	    LIST1.add(["toolName":name,"metricName":"failure_builds","value":fcnt])
  }
   }
	   
	  if(jsonStringa[i].contains("gitlab") && scm=="gitlab")
      {
        name="gitlab"
	      
        def jsonObjd= readJSON text: jsonStringa[i]
  
  def cnt =jsonObjd.gitlab.individual_commit_Details[j].Commit_cnt
	       def email1=jsonObjd.gitlab.individual_commit_Details[j].email
	       if(email==email1)
  {
    LIST1.add(["toolName":name,"metricName":"commits","value":cnt])
  }
      }
	   if(jsonStringa[i].contains("bitbucket") && scm=="bitbucket")
      {
        name="bitbucket"
	      //metric="commits"
        def jsonObjc= readJSON text: jsonStringa[i]
  //println(jsonObj)
  def cnt=jsonObjc.bitbucket.Individual_commits[j].Commit_count
	    def email1=jsonObjc.bitbucket.Individual_commits[j].Email
	       if(email==email1)
  {
    LIST1.add(["toolName":name,"metricName":"commits","value":cnt])
  }
      }
    }
	   JSON1[j]=LIST1.clone()
	   
   JSON.add(["teamMemberName":email,"teamName":team,"metrics":JSON1[j]])
    LIST1.clear()

	   
    }
	println("hi")
	def jsonBuilder = new groovy.json.JsonBuilder()

jsonBuilder(
 "teamName":team,
  "metrics" : LIST
  
) 

   println(jsonBuilder.toPrettyString())
     def jsonBuilder1 = new groovy.json.JsonBuilder()

jsonBuilder1(
   JSON
  
) 
  
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/Team.json")
file.write(jsonBuilder.toPrettyString())
	File file1 = new File("/var/lib/jenkins/workspace/${JOB_NAME}/Indivdual.json")
file1.write(jsonBuilder1.toPrettyString())
    
 println(jsonBuilder1.toPrettyString())
  
}



