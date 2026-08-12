pipeline {
    agent any

    tools {
        maven 'maven-3.9.12'
        jdk 'jdk-25'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/udhayasurya01/-hello-jenkins-demo.git'
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
        stage('Docker Run') {
            steps {
                bat 'docker run --rm hello-jenkins-demo:latest'
            }
        }
    }

    post {
        success {
            echo 'Build & Docker image success!'
        }
        failure {
            echo 'Build failed — Console Output.'
        }
    }
}