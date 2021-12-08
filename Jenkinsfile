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
                sh 'export JAVA_HOME=/opt/java/openjdk/bin'
                sh 'mvn clean install'
            }
        }
    }
}
