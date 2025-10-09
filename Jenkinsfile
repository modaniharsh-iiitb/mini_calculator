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
        success {
            script {
                def triggerType = "Unknown"
                if (currentBuild.rawBuild.getCause(hudson.triggers.SCMTrigger$SCMTriggerCause)) {
                    triggerType = "GitHub Webhook"
                } else if (currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause)) {
                    triggerType = "Manual"
                }

                def commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()

                emailext(
                    subject: "Jenkins Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """<h3>Build Successful</h3>
                             <p><b>Project:</b> ${env.JOB_NAME}</p>
                             <p><b>Build #:</b> ${env.BUILD_NUMBER}</p>
                             <p><b>Trigger:</b> ${triggerType}</p>
                             <p><b>Commit Message:</b> ${commitMessage}</p>
                             <p><a href="${env.BUILD_URL}">View Build in Jenkins</a></p>""",
                    to: "${RECIPIENTS}",
                    mimeType: 'text/html'
                )
            }
        }

        failure {
            script {
                def triggerType = "Unknown"
                if (currentBuild.rawBuild.getCause(hudson.triggers.SCMTrigger$SCMTriggerCause)) {
                    triggerType = "GitHub Webhook"
                } else if (currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause)) {
                    triggerType = "Manual"
                }

                def commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()

                emailext(
                    subject: "Jenkins Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """<h3>Build Failed</h3>
                             <p><b>Project:</b> ${env.JOB_NAME}</p>
                             <p><b>Build #:</b> ${env.BUILD_NUMBER}</p>
                             <p><b>Trigger:</b> ${triggerType}</p>
                             <p><b>Commit Message:</b> ${commitMessage}</p>
                             <p><a href="${env.BUILD_URL}">View Build Logs</a></p>""",
                    to: "${RECIPIENTS}",
                    mimeType: 'text/html'
                )
            }
        }
    }
}