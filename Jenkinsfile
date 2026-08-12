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
        stage('Run') {
            steps {
                bat 'java -jar target/hello-jenkins-demo.jar'
            }
        }
    }

    post {
        success {
            echo 'Build success!'
        }
        failure {
            echo 'Build failed — Console Output பாருங்க.'
        }
    }
}