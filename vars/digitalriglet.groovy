import groovy.json.*
import groovy.json.JsonOutput


def riglet1(jsondata,gitlab,bamboo,sonar)
{
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.riglet_info.auth_users.size()
	def team=jsonObj.riglet_info.name
	def scm=jsonObj.scm.tool.name
	def ci=jsonObj.scm.tool.name
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
	 

	  if(jsonStringa[i].contains("gitlab"))
    {
      name="gitlab"
def jsonObjc = readJSON text: jsonStringa[i]
def cnt =jsonObjc.gitlab.commit_cnt

 
	    LIST.add(["toolName":name,"metricName":"commits","value":cnt])

  }
   if(jsonStringa[i].contains("Bamboo"))
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
	    def jsonObjd= readJSON text: jsonStringa[i]
	    for(i=0;i<jsonObjd.sonar.metrics.component.measures.size();i++){
    def sonar_metric=jsonObjd.sonar.metrics.component.measures[i].metric
		    def d=jsonObjd.sonar.metrics.component.measures[i].value
    double data = Double.parseDouble(d); 
       LIST.add(["toolName":name,"metricName":sonar_metric,"value":data])
	    }
    }
  }

	
    for(k=0;k<ecount;k++)
   {
	 def email=jsonObj.riglet_info.auth_users[k]
	   int score=0
    int reward=0
    String name="  "
	 for(l=0;l<jsonStringa.size();l++)
  { 
   
	  
  if(jsonStringa[l].contains("Bamboo"))
    {
     name="bamboo"
   
	   def jsonObja = readJSON text: jsonStringa[l]
  def scnt =jsonObja.Bamboo.individualsuccess[k].Success_cnt
  def fcnt =jsonObja.Bamboo.individualfailure[k].Failure_cnt
def    total=jsonObja.Bamboo.individualtotal[k].totalBuilds
 def email1=jsonObja.Bamboo.individualsuccess[k].email
      
	  
 if(email==email1)
  {
   LIST1.add(["toolName":name,"metricName":"total_builds","value":total])
	    
 
	    LIST1.add(["toolName":name,"metricName":"success_builds","value":scnt])
	    

	    LIST1.add(["toolName":name,"metricName":"failure_builds","value":fcnt])
  }
   }
	   
	  if(jsonStringa[l].contains("gitlab"))
      {
        name="gitlab"
	      
        def jsonObjd= readJSON text: jsonStringa[l]
  
  def cnt =jsonObjd.gitlab.individual_commit_Details[k].Commit_cnt
	       def email1=jsonObjd.gitlab.individual_commit_Details[k].email
	       if(email==email1)
  {
    LIST1.add(["toolName":name,"metricName":"commits","value":cnt])
  }
      }
    }
	   JSON1[k]=LIST.clone()
	   
   JSON.add(["teamMemberName":email,"teamName":team,"metrics":JSON1[k]])
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
  
 // File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/game.json")
//file.write(jsonBuilder.toPrettyString())
    
  println(jsonBuilder1.toPrettyString())
  
}
