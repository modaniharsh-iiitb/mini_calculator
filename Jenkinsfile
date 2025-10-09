pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/modaniharsh-iiitb/mini_calculator'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("mini_calculator:latest")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker tag mini_calculator:latest %DOCKER_USER%/mini_calculator:latest
                        docker push %DOCKER_USER%/mini_calculator:latest
                    """
                }
            }
        }


        stage('Push to Local Registry (optional)') {
            steps {
                sh 'docker tag mini_calculator:latest localhost:5000/mini_calculator:latest'
                sh 'docker push localhost:5000/mini_calculator:latest'
            }
        }

        stage('Run Calculator Test') {
            steps {
                writeFile file: 'input.txt', text: '1\n9\n0\n'
                sh 'docker run -i mini-calculator < input.txt'
            }
        }

        stage('Deploy via Ansible') {
            steps {
                sh 'ansible-playbook -i inventory deploy.yml'
            }
        }
    }
}
