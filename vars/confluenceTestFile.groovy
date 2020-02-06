Map modules = [:]
pipeline {
    agent any
    stages {
        stage('test') {
            steps {
                script{
                    modules.confluenceTest = load "confluenceTest.groovy"
                    //modules.confluenceTest.call()
                    //modules.confluenceTest.info()
                }
            }
        }
    }
}
