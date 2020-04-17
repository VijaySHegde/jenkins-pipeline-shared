import groovy.json.*
import groovy.json.JsonOutput

def call(JSON)
{
def jsonObj = readJSON text: JSON
def dashboardname=jsonObj.hygieia.applicationname
def dashboardtitle=jsonObj.hygieia.title
def dashboardtemplate=jsonObj.hygieia.template
def giturl=jsonObj.scm.projects.project[0].url
def gitbranch=jsonObj.scm.projects.project[0].branch
def buildjname=jsonObj.ci.jobs.job[0].jobName
def buildjurl=jsonObj.ci.jobs.job[0].jobUrl
def buildinsurl=jsonObj.ci.jobs.job[0].instanceUrl
def sonarprojname=jsonObj.code_quality.projects.project[0].projectName
def sonarproid=jsonObj.code_quality.projects.project[0].projectId
def sonarurl=jsonObj.code_quality.projects.project[0].instanceUrl
def featureprojname=jsonObj.alm.projects.project[0].projectName
def featureteamname=jsonObj.alm.projects.project[0].teamName
def featureprojid=jsonObj.alm.projects.project[0].projectId
def featureteamid=jsonObj.alm.projects.project[0].teamId
	
println(gitbranch)
	println(dashboardname)
//sh   "curl -X  POST   --header apiUser:admindevops  --header Authorization:apiTokenUGFzc3dvcmRJc0F1dGhUb2tlbjp7ImFwaUtleSI6ImRlQzJ3cmFLTzhmNFB2N0IzL0hWT0NuK1NSOExVbEVDIn0=  -H Accept:application/json  -H  Content-Type:application/json  -d '{\"codeRepoEntries\":[{\"toolName\":\"GitHub\",\"description\":\"Brief description\",\"options\":{\"branch\":\"master\",\"url\":\"https://github.com/kavi1997/Demo.git\"}}],\"staticCodeEntries\":[{\"toolName\":\"Sonar\",\"description\":"",\"options\":{\"projectName\":\"DC_Universe\",\"projectId\":\"AXDJOcHFW7M5TlKFU-W3\",\"instanceUrl\":\"http://ec2-3-133-107-212.us-east-2.compute.amazonaws.com:9000\"}}], \"buildEntries\": [{\"description\": \"Wolvorines\",\"options\": {\"jobName\": \"Wolvorines\",\"jobUrl\": \"http://3.130.89.18:8080/job/Wolvorines/\",\"instanceUrl\": \"http://admin:119956a871303842579c80b9609fb2261e@3.130.89.18:8080/view/Digital%20Rig\"},\"pushed\": false,\"toolName\": \"Hudson\"}],\"featureEntries\": [{\"description\": " ",\"options\": {\"teamName\": \"KEY board\",\"featureTool\": \"Jira\",\"projectName\": \"Keerthi\",\"projectId\": \"10967\",\"teamId\": \"49\",\"showStatus\" : {\"kanban\" : true, \"scrum\" : false},\"estimateMetricType\" : \"storypoints\", \"sprintType\" : \"scrumkanban\", \"listType\" : \"epics\"},\"pushed\": false,  \"toolName\": \"Jira\"}],\"metaData\": { \"applicationName\": \"Hygieianew\", \"componentName\": \"Hygieianew\", \"owner\": { \"authType\": \"STANDARD\", \"username\": \"admin\" }, \"template\": \"hygieia\", \"title\": \"Hygieiamew\", \"type\": \"team\" }}' http://ec2-3-21-21-136.us-east-2.compute.amazonaws.com:3000/api/dashboard/remoteCreate"
sh """ curl -X POST \
  http://ec2-3-21-21-136.us-east-2.compute.amazonaws.com:3000/api/dashboard/remoteCreate \
  -H 'apiuser: admindevops' \
  -H 'authorization: apiTokenUGFzc3dvcmRJc0F1dGhUb2tlbjp7ImFwaUtleSI6ImRlQzJ3cmFLTzhmNFB2N0IzL0hWT0NuK1NSOExVbEVDIn0=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
  "codeRepoEntries": [
  	{
  		 
  		"toolName":"GitHub",
  		"description":"Brief description",
  		"options":{"branch":"${gitbranch}","url":"${giturl}",
  	"personalAccessToken":""}
  	
  	}
  ],
  "staticCodeEntries":[
  	{"toolName":"Sonar","description":"","options":{"projectName":"${sonarprojname}","projectId":"${sonarproid}",
  	"instanceUrl":"${sonarurl}"}
  	}],
  	 "buildEntries": [
    {
      "description": "Wolvorines",
     "options": {
                        "jobName": "${buildjname}",
                                    "jobUrl": "${buildjurl}",
                                    "instanceUrl": "${buildinsurl}"
                  },
      "pushed": false,
      "toolName": "Hudson"
    }
  ],
    "featureEntries": [
  	{
    	"description": " ",
         "options": {
                                    "teamName": "${featureteamname}",
                                    "featureTool": "Jira",
                                    "projectName": "${featureprojname}",
                                    "projectId": "${featureprojid}",
                                    "teamId": "${featureteamid}",
                                     "showStatus" : {
                 	"kanban" : true, 
                "scrum" : false
                  },
            "estimateMetricType" : "storypoints", 
            "sprintType" : "scrumkanban", 
            "listType" : "epics"
                                },
        "pushed": false,
        "toolName": "Jira"
	}],

         
   "metaData": {
    "applicationName": "${dashboardname}",
    "componentName": "${dashboardname}",
    "owner": {
      "authType": "STANDARD",
      "username": "admin"
    },
    "template": "${dashboardtemplate}",
    "title": "${dashboardtitle}",
    "type": "Team"
   
  }
}' """

}
