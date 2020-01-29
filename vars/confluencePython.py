@Library('shlib6')_
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
