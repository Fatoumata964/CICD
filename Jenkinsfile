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
                      credentialsId: 'cc3fad3d-c9c0-4b63-9c4e-803411b39494', 
                      groupId: 'org.example',
                      nexusUrl: 'mosef.westeurope.cloudapp.azure.com:8082', 
                      nexusVersion: 'nexus3',
                      protocol: 'http',
                      repository: 'test',
                      version: '2.0-SNPAPSHOT'  
                }
            }
     }
    //https://appfleet.com/blog/publishing-artifacts-to-nexus-using-jenkins-pipelines/
    //https://www.youtube.com/watch?v=p_Wo3aqUJto
}
 
