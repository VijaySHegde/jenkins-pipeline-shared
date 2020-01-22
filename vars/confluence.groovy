def call(def pagename, def spaceid)
{
  
  curl -u admin:admin -X POST -H 'Content-Type: application/json' -d '{"type":"page","title":"${pagename}",
  "space":{"key":"${spaceid}"},"body":{"storage":{"value":"<p>This is <br/> a new page</p>","representation":
"storage"}}}' http://3.15.148.45:8090/confluence/rest/api/content/ | python -mjson.tool
}
