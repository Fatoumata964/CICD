pipeline{
    agent any
    options {
      timeout(300)
    }
    
    stages{ 
            stage('Sonar Analysis'){
                steps{
                    withSonarQubeEnv('sonar6') {
                        sh 'mvn sonar:sonar'
                    }
                    sleep(20)
                    timeout(time: 1, unit: 'HOURS') {
                        script{
                          def qg = waitForQualityGate()
                           if (qg.status != 'OK') {
                              error "Pipeline aborted due to quality gate failure: ${qg.status}" 
                           } 
                        }
                    }
                }
           }
            
        stage('Mvn  Test and Build'){
            steps{
                
                    sh 'mvn clean package'
                }
            }
     }
    //https://appfleet.com/blog/publishing-artifacts-to-nexus-using-jenkins-pipelines/
}
 
