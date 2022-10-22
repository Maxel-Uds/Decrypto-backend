pipeline {
  agent any
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  environment {
    HEROKU_API_KEY = credentials('heroku-api-key')
  }
  parameters { 
    string(name: 'APP_NAME', defaultValue: 'the-encryptor', description: 'heroku app name') 
  }
  stages {
    stage('Build') {
      steps {
        sh 'docker build -t maxel/decrypto-app:latest .'
      }
    }
    stage('Docker Login') {
      steps {
        sh 'echo $HEROKU_API_KEY | docker login --username=_ --password-stdin registry.heroku.com'
      }
    }
    stage('Push to Heroku registry') {
      steps {
        sh '''
          docker tag maxel/decrypto-app:latest registry.heroku.com/$APP_NAME/web
          docker push registry.heroku.com/$APP_NAME/web
        '''
      }
    }
    stage('Release the image') {
      steps {
        sh '''
          heroku container:release web --app=$APP_NAME
        '''
      }
    }
  }
  post {
    always {
      sh 'docker logout'
    }
  }
}
