node
{
  stage('Clone'){
                git 'https://github.com/idiattara/CICD'
  }
  stage('Test unitaires'){
                  sh 'mvn test'
  }
  
  stage('Package'){
                  sh 'mvn package'
  }
}
