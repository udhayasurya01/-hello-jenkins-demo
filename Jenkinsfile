pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    tools {
        maven 'maven-3.9.12'
        jdk 'jdk-25'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/udhayasurya01/-hello-jenkins-demo.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t hello-jenkins-demo:latest .'
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker stop hello-jenkins-demo-container || ver>nul'
                bat 'docker rm hello-jenkins-demo-container || ver>nul'
                bat 'docker run -d --name hello-jenkins-demo-container hello-jenkins-demo:latest'
            }
        }
    }

    post {
        success {
            echo 'Deploy success!'
        }

        failure {
            echo 'Build/Deploy failed — Console Output.'
        }
    }
}