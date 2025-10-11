pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        RECIPIENTS = 'modani.harsh@gmail.com'
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

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t mini_calculator:latest .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        docker login -u $DOCKER_USER -p $DOCKER_PASS
                        docker tag mini_calculator:latest $DOCKER_USER/mini_calculator:latest
                        docker push $DOCKER_USER/mini_calculator:latest
                    '''
                }
            }
        }

        stage('Run Interactive Demo') {
            steps {
                writeFile file: 'input.txt', text: '1\n9\n0\n'
                sh 'docker run -i mini_calculator:latest < input.txt'
            }
        }

        stage('Deploy via Ansible') {
            steps {
                sh 'ansible-playbook -i inventory deploy.yml'
            }
        }

    }

    post {
        always {
            script {
                // Detect build trigger cause
                def cause = currentBuild.rawBuild.getCauses().collect { it.shortDescription }.join(', ')
                def triggerSource = "Manual"
                if (cause.toLowerCase().contains("github")) {
                    triggerSource = "GitHub Webhook"
                } else if (cause.toLowerCase().contains("timer")) {
                    triggerSource = "Scheduled (Timer)"
                } else if (cause.toLowerCase().contains("branch indexing")) {
                    triggerSource = "SCM Change / Branch Indexing"
                }

                // Send email with build and trigger info
                mail to: 'modani.harsh@gmail.com',
                    subject: "Build ${env.JOB_NAME} #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
                    body: """Build Details:
Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Result: ${currentBuild.currentResult}
Triggered By: ${triggerSource}
Cause: ${cause}
URL: ${env.BUILD_URL}
"""
            }
        }
    }
}