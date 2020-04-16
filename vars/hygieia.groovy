import groovy.json.*
import groovy.json.JsonOutput

def call(JSON)
{
def jsonObj = readJSON text: JSON
def dashboardname=jsonObj.hygieia.applicationname
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
  		"options":{"branch":"master","url":"https://github.com/kavi1997/demo",
  	"personalAccessToken":""}
  	
  	}
  ],
  "staticCodeEntries":[
  	{"toolName":"Sonar","description":"","options":{"projectName":"DC_Universe","projectId":"AXDJOcHFW7M5TlKFU-W3",
  	"instanceUrl":"http://ec2-3-133-107-212.us-east-2.compute.amazonaws.com:9000"}
  	}],
  	 "buildEntries": [
    {
      "description": "Wolvorines",
     "options": {
                        "jobName": "Wolvorines",
                                    "jobUrl": "http://3.130.89.18:8080/job/Wolvorines/",
                                    "instanceUrl": "http://admin:119956a871303842579c80b9609fb2261e@3.130.89.18:8080/view/Digital%20Rig"
                  },
      "pushed": false,
      "toolName": "Hudson"
    }
  ],
    "featureEntries": [
  	{
    	"description": " ",
         "options": {
                                    "teamName": "KEY board",
                                    "featureTool": "Jira",
                                    "projectName": "Keerthi",
                                    "projectId": "10967",
                                    "teamId": "49",
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
    "template": "hygieia",
    "title": "Hygiei11",
    "type": "Team"
   
  }
}' """

}
