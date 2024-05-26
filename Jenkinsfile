pipeline {
    agent {
        docker{
            images 'node:10'
            args '-p 20000:8080'
        }
    } 
    stages {
        stage('Build') { 
            steps {
                sh 'node -v'
                sh 'echo "hello world !!"'
            }
        }
        // stage('Test') { 
        //     steps {
        //         // 
        //     }
        // }
        // stage('Deploy') { 
        //     steps {
        //         // 
        //     }
        // }
    }
}
