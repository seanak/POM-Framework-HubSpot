pipeline {
	  agent any
	  tools {
	    maven 'M3'
	  }
	  stages {
	    stage('build') {
	      steps {
	        sh 'mvn clean install'
	        echo 'everything fine until sh mvn clean install'
	      }
	    }
	  
	  
	stage('reports') {
	    steps {
	    script {
	            allure 
	            includeProperties: false, 
	            jdk: '', 
	            results: [[path: '/allure-results']]
	          
	           
	            
	          
	    }
	    }
	}
	

	}
	

	}
