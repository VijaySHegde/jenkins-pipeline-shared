@Library('shlib')_
pipeline{
  agent any
  stages{
stage("confluence"){
            steps{
       confluence.py
        }
        }
        
  }
}
