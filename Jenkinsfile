pipeline {
	  agent any
	  tools {
	    maven 'M3'
	  }
	  stages {
	    stage('Build') {
	      steps {
	        bat 'mvn clean test'
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
