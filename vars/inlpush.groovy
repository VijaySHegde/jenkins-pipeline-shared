def call(IP)
{
	 def response = sh(script: """curl -X POST --header 'Content-Type: application/json' -L -w '%{http_code}\n' --data @/var/lib/jenkins/workspace/${JOB_NAME}/Indivdual1.json  '${IP}'/api/metrics/members/add""", returnStdout: true)
  //echo "Sending data to Gamification API"
	if(response.contains("200"))
		{
		println("Sending data to Gamification API")	
		
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
