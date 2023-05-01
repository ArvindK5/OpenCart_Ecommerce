pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build the Project'
            }
        }
        
         stage('Deploy on Dev') {
            steps {
                echo 'DEV Deploy'
            }
        }
        
        stage('Deploy on QA') {
            steps {
                echo 'QA Deploy'
            }
        }
        
        stage('Smoke Test') {
            steps {
                echo 'SMOKE TEST'
            }
        }
        
        stage('Stage Test') {
            steps {
                echo 'Stage Test'
            }
        }
        stage('Deploy on Production') {
            steps {
                echo 'Production deploy'
            }
        }
    }
}
