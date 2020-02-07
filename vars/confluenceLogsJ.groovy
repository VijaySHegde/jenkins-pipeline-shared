def call(message)
{
 println(message)
 Date date = new Date() 
  sh " echo '${date}' CONFLUENCE ${message} >>log.txt"
}
