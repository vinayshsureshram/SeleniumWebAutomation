pipeline {
    agent any

    tools {
        maven 'Maven3'       // Must match the name configured in Jenkins → Global Tool Configuration
        allure 'Allure_2.29.0'  // Make sure you configure Allure in Jenkins tools
    }

    environment {
        ALLURE_RESULTS = "target/allure-results"
        ALLURE_REPORT = "target/site/allure-maven-plugin"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "📦 Checking out code from Git..."
                git branch: 'main', url: 'https://github.com/vinayshsureshram/SeleniumWebAutomation.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo "🧪 Running TestNG tests with Maven..."
                // Run with retry mechanism for transient issues (like Selenium/Network)
                retry(2) {
                    sh 'mvn clean test -DsuiteXmlFile=testng.xml'
                }
            }
            post {
                always {
                    echo "📁 Archiving Allure results..."
                    archiveArtifacts artifacts: "${ALLURE_RESULTS}/**", fingerprint: true
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo "📊 Generating Allure report..."
                sh 'mvn allure:report'
            }
        }

        stage('Publish Allure Report') {
            steps {
                echo "🚀 Publishing Allure report to Jenkins..."
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: "${ALLURE_RESULTS}"]],
                    reportBuildPolicy: 'ALWAYS'
                ])
            }
        }

        // Optional Parallel Execution (UI + API + Mobile)
        // Uncomment if your project supports multiple suites
        /*
        stage('Parallel Suites') {
            parallel {
                stage('UI Tests') {
                    steps { sh 'mvn test -DsuiteXmlFile=testng_ui.xml' }
                }
                stage('API Tests') {
                    steps { sh 'mvn test -DsuiteXmlFile=testng_api.xml' }
                }
                stage('Mobile Tests') {
                    steps { sh 'mvn test -DsuiteXmlFile=testng_mobile.xml' }
                }
            }
        }
        */

    }

    post {
        always {
            echo "🧹 Cleaning workspace..."
            cleanWs()
        }

        failure {
            echo "❌ Build failed! Check Allure report for screenshots and logs."
        }

        success {
            echo "✅ Build succeeded! Allure report generated at: ${ALLURE_REPORT}"
        }
    }
}