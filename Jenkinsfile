pipeline {
	  agent any
	  tools {
	    maven 'M3'
	  }
	  stages {
	    stage('Build') {
	      steps {
	        withMaven(maven : 'apache-maven-3.6.1') {
                bat'mvn clean compile'
                
               }
	      }
	    }
	  
	  
	stage('reports') {
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
	

	}
	

	}
