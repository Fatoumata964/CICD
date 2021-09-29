pipeline{
    agent any
    options {
      timeout(300)
    }
    
    stages{ 
        
        stage('Mvn  test'){
            steps{
                
                    sh 'mvn clean install test'
                }
            }
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
        stage('Mvn  Build'){
            steps{
                
                    sh 'mvn clean package'
                }
            }
        
        stage('nexus '){
            steps{
                
                  nexusArtifactUploader artifacts: [
                      [artifactId: 'TestCI', classifier: '', file: '/var/lib/jenkins/workspace/PP/target/TestCI-1.0-SNAPSHOT.jar', type: 'jar']
                  ], 
                      credentialsId: 'ef8c3ee5-e691-47b5-a9f9-44f6f3db2a19', 
                      groupId: 'org.example',
                      nexusUrl: '35.125.1.217:8081', 
                      nexusVersion: 'nexus2',
                      protocol: 'http',
                      repository: 'http://135.125.1.217:8081/repository/test/',
                      version: '1.0-SNPAPSHOT'  
                }
            }
     }
    //https://appfleet.com/blog/publishing-artifacts-to-nexus-using-jenkins-pipelines/
    //https://www.youtube.com/watch?v=p_Wo3aqUJto
}
 
