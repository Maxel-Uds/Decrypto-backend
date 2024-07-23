pipeline {
  agent any
//   options {
//     buildDiscarder(logRotator(numToKeepStr: '5'))
//   }
//   environment {
//     HEROKU_API_KEY = credentials('heroku-api-key')
//   }
  stages {
    stage('STAGE 00'){
         steps {
            echo "Pipeline Usando Jenkinsfile"
         }
    }
    stage('STAGE 00'){
        steps {
            echo "Pipeline Usando Jenkinsfile"
        }
    }
  }

//     stage('Build') {
//       steps {
//         sh 'docker build --no-cache -t maxel/decrypto-app:latest .'
//       }
//     }
//     stage('Deploy') {
//       steps {
//         sh '''
//           echo $HEROKU_API_KEY | docker login --username=_ --password-stdin registry.heroku.com
//           docker tag maxel/decrypto-app:latest registry.heroku.com/the-encryptor/web
//           docker push registry.heroku.com/the-encryptor/web
//           heroku container:release web --app=the-encryptor
//         '''
//       }
//     }
//   }
//   post {
//     always {
//       sh 'docker logout'
//     }
//   }
}
