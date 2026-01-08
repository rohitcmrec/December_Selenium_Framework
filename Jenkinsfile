pipeline
{
    agent any

    tools{
        maven 'maven'
        }

    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/rohitcmrec/December_Selenium_Framework.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }


        stage("Deploy to Dev"){
            steps{
                echo("deploy to Dev")
            }
        }

        stage('Run Sanity API Automation Test on DEV') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/rohitcmrec/December_Selenium_Framework.git'
                    bat "mvn clean test -Denv=dev"

                }
            }
        }



        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }


        stage('Run Regression API Automation Tests on QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/rohitcmrec/December_Selenium_Framework.git'
                    bat "mvn clean test -Denv=qa"

                }
            }
        }


        /* stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }


        stage('Publish ChainTest HTML Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'target/chaintest',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML API Regression ChainTest Report on QA',
                                  reportTitles: 'API Test HTML Report'])
            }
        } */

    }
}