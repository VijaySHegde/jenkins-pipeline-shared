 def call(String projKey="BT",String msg="STAGE FAILED", String id="BT-0")
 {
 
 
 httpRequest authentication: 'jira_password',
 customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'],
                 [maskValue: false, name: 'Accept', value: 'application/json']], 
 httpMode: 'POST', requestBody: """{
     "fields": {
       "project":
       {
          "key": "${projKey}"
       },
       "summary": "${msg}",
       "description": "${id}",
       "issuetype": {
          "name": "bug"
       }
   }
}""", url: 'https://vijaysh.atlassian.net/rest/api/2/issue/'
}
 
