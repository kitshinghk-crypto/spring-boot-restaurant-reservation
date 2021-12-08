pipeline { 
    agent any  
     tools { 
        maven 'Maven3.8.2' 
        jdk 'jdk11' 
    }
    stages { 
        stage ('Initialize') {
            steps {
                sh '''
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
        stage('Build') { 
            steps { 
                echo 'Start building ...' 
                sh 'mvn clean install'
            }
        }
        stage('Deploy'){
            echo 'Start deploying ...' 
            steps{
                sh 'docker-compose up'
            }
        }
    }
}
