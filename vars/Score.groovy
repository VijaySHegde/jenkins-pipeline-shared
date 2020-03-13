import groovy.json.*

def call(jsondata,gitlab,bamboo,sonar){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.riglet_info.auth_users.size()
	def team=jsonObj.riglet_info.name
	def scm=jsonObj.scm.tool.name
	def ci=jsonObj.scm.tool.name
List<String> jsonStringa= new ArrayList<String>();
  //jsonStringa.add(bitbucket)
   jsonStringa.add(gitlab)
	jsonStringa.add(bamboo)
	jsonStringa.add(sonar)
	println(sonar)
   //jsonStringa.add(gitlab)
  //println(jsonStringa)
/*def jsonStringa = bitbucket
def jsonObja = readJSON text: jsonStringa
  
def jsonStringb = bamboo
def jsonObjb = readJSON text: jsonStringb*/
 int[] score1= new int[100]
 List<String> JSON = new ArrayList<String>();
  List<String> LIST = new ArrayList<String>();
  List<String> JSON1 = new ArrayList<String>();
	
for(i=0;i<jsonStringa.size();i++)
  { 
    int score=0
    String name="  "
	  String metric=" "

	  if(jsonStringa[i].contains("gitlab"))
    {
      name="gitlab"
	  //  metric="commits"
//def jsonStringa = bitbucket
def jsonObjc = readJSON text: jsonStringa[i]
def cnt =jsonObjc.gitlab.commit_cnt
 // println(jsonObja)
  //println(total)
 
	    LIST.add(["toolName":name,"metricName":"commits","value":cnt])
 /* if(total>5)
  {
    score=score+10
  }*/
  }
   if(jsonStringa[i].contains("Bamboo"))
    {
      name="bamboo"
	   // metric="successfulbuilds"
    //  def jsonStringb = bamboo
def jsonObjb = readJSON text: jsonStringa[i]
  //println(jsonObj)
def total=jsonObjb.Bamboo.totalBuilds
  def scnt =jsonObjb.Bamboo.teamsuccessbuild_cnt
	    def fcnt=jsonObjb.Bamboo.teamfailurebuild_cnt
      
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 //println(cnt)
	  
	    LIST.add(["toolName":name,"metricName":"total_builds","value":total])
	    
 
	    LIST.add(["toolName":name,"metricName":"success_builds","value":scnt])
	    

	    LIST.add(["toolName":name,"metricName":"failure_builds","value":fcnt])
	    
    }
	  
    
      
	  if(jsonStringa[i].contains("sonar"))
    {
	    name="sonar"
	    def jsonObjd= readJSON text: jsonStringa[i]
	    //print jsonObjc
	    for(i=0;i<jsonObjd.sonar.metrics.component.measures.size();i++){
		    //print jsonObjc.Sonar.Metrics.component.measures
    def sonar_metric=jsonObjd.sonar.metrics.component.measures[i].metric
		    def d=jsonObjd.sonar.metrics.component.measures[i].value
    double data = Double.parseDouble(d); 
       LIST.add(["toolName":name,"metricName":sonar_metric,"value":data])
	    }
    }
  }
def jsonBuilder = new groovy.json.JsonBuilder()

jsonBuilder(
 "teamName":team,
  "metrics" : LIST
  
) 
  
 // File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/Teamscore.json")
//file.write(jsonBuilder.toPrettyString())	
  return jsonBuilder
 
  //println(JSON)
}

