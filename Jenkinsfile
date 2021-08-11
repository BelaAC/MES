pipeline {
    agent { label 'windows' }
    options {
        disableConcurrentBuilds()
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '40'))
        timeout(time: 5, unit: 'HOURS')
    }

    stages {
        stage("Checkout"){
         steps {
        		dir('prioridade-alta') {
					steps {
	    				git branch: 'main', credentialsId: 'e6f3affd-0aa9-480b-9ab8-7feb9f95085e', url: 'https://github.com/BelaAC/MES'
					}
        		}
        	
        		dir('prioridade-media') {
					steps {
	    				git branch: 'main', credentialsId: 'e6f3affd-0aa9-480b-9ab8-7feb9f95085e', url: 'https://github.com/BelaAC/MES'
					}
        		}
        		dir('prioridade-baixa') {
					steps {
	    				git branch: 'main', credentialsId: 'e6f3affd-0aa9-480b-9ab8-7feb9f95085e', url: 'https://github.com/BelaAC/MES'
					}
        		}
        	}
        }

		stage("UI-Tests"){
	        parallel {
                stage('prioridade-alta') {
                    steps {
			        	dir('prioridade-alta') {
							steps {
				    			bat "mvn clean test -Dtest=MESPrioridadeAltaTest"
							}
			        	}
                    }
                }
                stage('prioridade-media') {
                    steps {
			        	dir('prioridade-media') {
							steps {
				    			bat "mvn clean test -Dtest=MESPrioridadeMediaTest"
							}
			        	}
                    }
                }
                stage('prioridade-baixa') {
                    steps {
			        	dir('prioridade-baixa') {
							steps {
				    			bat "mvn clean test -Dtest=MESPrioridadeBaixaTest"
							}
			        	}
                    }
                }
	        }
        }

    }

    post {
        always {
	        archiveArtifacts '**/target/report/html/*.html,**/target/report/html/img/*'
	        junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
	        cucumber fileIncludePattern: '**/cucumber.json', sortingMethod: 'ALPHABETICAL'
	        //bat "powershell Compress-Archive target\\report html.zip -Force"
	        //emailext attachmentsPattern: '*.zip', body: '$DEFAULT_CONTENT', subject: '$DEFAULT_SUBJECT', to: 'isaalborghetti@gmail.com';
        }
    }

}