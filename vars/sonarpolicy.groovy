@NonCPS
//getting the id of user who started the build.
def getuserid(){
def build = currentBuild.rawBuild
def cause = build.getCause(hudson.model.Cause.UserIdCause.class)
def userid = cause.getUserName()
}

def call(){
sh "curl -X PUT http://52.66.210.196:8181/v1/data/myapi/acl --data-binary @open-policy-agent/sonar/jenkins-acl.json"
sh "curl -X PUT http://52.66.210.196:8181/v1/policies/myapi --data-binary @open-policy-agent/sonar/jenkins-policy.rego"

getuserid()

println(userid)


String response = sh(script:"""curl --location --request POST 'http://52.66.210.196:8181/v1/data/myapi/policy/allow' --header 'Content-Type: application/json' --data-raw '{ "input": { "user": "'${userid}'", "access": "build" } }'""", returnStdout: true)
println(response)
if ( response == "{\"result\":true}" ){
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}
}
