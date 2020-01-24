def call(Map pipelineParams) {
pipeline {
 agent any
   tools{
        maven "Maven"
    }

    stages {
      
stage('confluence page')
        {
		steps {
		confluence_connector(pipelineParams keyname, pipelineParams spacename)
		}
		}
    }
		
		}
