pipeline {
    agent any

    tools {
        maven 'Maven'   // Configure Maven under Jenkins Global Tool Config
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/modaniharsh-iiitb/mini_calculator'
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("mini_calculator:latest")
                }
            }
        }

        stage('Push to Local Registry (optional)') {
            steps {
                bat 'docker tag mini_calculator:latest localhost:5000/mini_calculator:latest'
                bat 'docker push localhost:5000/mini_calculator:latest'
            }
        }

        stage('Deploy with Ansible') {
            steps {
                bat 'ansible-playbook -i inventory deploy.yml'
            }
        }
    }
}
