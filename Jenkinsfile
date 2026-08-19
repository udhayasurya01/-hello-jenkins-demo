pipeline {
    agent any

    triggers {
        githubPush()
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

        stage('Save Docker Image') {
            steps {
                bat 'docker save -o hello-jenkins-demo.tar hello-jenkins-demo:latest'
            }
        }

        stage('Transfer Image to Server') {
            steps {
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'office-server',
                        transfers: [
                            sshTransfer(
                                sourceFiles: 'hello-jenkins-demo.tar',
                                remoteDirectory: '.',
                                execCommand: 'sudo k3s ctr images import /home/mani/hello-jenkins-demo.tar'
                            )
                        ]
                    )
                ])
            }
        }

        stage('Deploy to K8s') {
            steps {
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'office-server',
                        transfers: [
                            sshTransfer(
                                execCommand: 'sudo k3s kubectl apply -f /home/mani/deployment.yaml'
                            )
                        ]
                    )
                ])
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