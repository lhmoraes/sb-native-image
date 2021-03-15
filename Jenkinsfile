#!/usr/bin/env groovy

/**
 * Parla Internal Repository
 *
 * Import the Parlacom Chain certificate to the JVM
 * keytool -import -trustcacerts -keystore /Users/luiz.henrique/.sdkman/candidates/java/13.0.2-open/lib/security/cacerts -storepass changeit -noprompt -alias mycert -file /Users/luiz.henrique/Downloads/parlacom-net-chain.pem
 *
 *
 *
 * Jenkins Environment
 *
 * BRANCH_NAME:master
 * BUILD_DISPLAY_NAME:#36
 * BUILD_ID:36
 * BUILD_NUMBER:36
 * BUILD_TAG:jenkins-Chokito Microservice (Everynet RESTful)-master-36
 * BUILD_URL:http://jenkins.parlacom.net:8081/job/Chokito%20Microservice%20(Everynet%20RESTful)/job/master/36/
 * CLASSPATH:
 * HUDSON_HOME:/var/jenkins_home
 * HUDSON_SERVER_COOKIE:44719063ed24cbeb
 * HUDSON_URL:http://jenkins.parlacom.net:8081/
 * JENKINS_HOME:/var/jenkins_home
 * JENKINS_SERVER_COOKIE:44719063ed24cbeb
 * JENKINS_URL:http://jenkins.parlacom.net:8081/
 * JOB_BASE_NAME:master
 * JOB_DISPLAY_URL:http://jenkins.parlacom.net:8081/job/Chokito%20Microservice%20(Everynet%20RESTful)/job/master/display/redirect
 * JOB_NAME:Chokito Microservice (Everynet RESTful)/master
 * JOB_URL:http://jenkins.parlacom.net:8081/job/Chokito%20Microservice%20(Everynet%20RESTful)/job/master/
 * RUN_CHANGES_DISPLAY_URL:http://jenkins.parlacom.net:8081/job/Chokito%20Microservice%20(Everynet%20RESTful)/job/master/36/display/redirect?page=changes
 * RUN_DISPLAY_URL:http://jenkins.parlacom.net:8081/job/Chokito%20Microservice%20(Everynet%20RESTful)/job/master/36/display/redirect
 *
 * WORKSPACE:/var/jenkins_home/workspace/oservice_Everynet_RESTful_master *
 */

def environment = env.getEnvironment()

def GRADLE = 'gradle-6.3'
//def JDK    = 'openjdk-11.0'
//
//def BUILD_NUMBER = environment.BUILD_NUMBER
//
def PROJ_GIT = 'ParlaCloud-Kinder.git'

def APP_MODULE = "mod-rest-app"
def APP_NAME   = "sigfox-callback-prod"

def GIT_REPO_URL       = 'ssh://git@github.com/parlacom/'+PROJ_GIT
def GIT_REPO_BRANCH    = 'master'
def GITHUB_CREDENTIALS = 'github-parlacom-key'

def KUBERNETES_CREDENTIALS = 'kubernetes-config-credentials'
def KUBERNETES_SERVER_URL  = 'https://api.parlacom.net:6443'
def KUBERNETES_DEPLOY      = 'k8s-deploy.yml'


//properties([
//    parameters([
//        gitParameter(branch: '',
//                     branchFilter: 'origin/(.*)',
//                     defaultValue: 'master',
//                     description: '',
//                     name: 'BRANCH',
//                     quickFilterEnabled: false,
//                     selectedValue: 'NONE',
//                     sortMode: 'NONE',
//                     tagFilter: '*',
//                     type: 'PT_BRANCH')
//                ])
//    ])



if (environment.BRANCH_NAME != GIT_REPO_BRANCH) {
    print 'This is not a master branch'
    return
}

node {

    stage("Stage 1 - Checkout Source") {
        git branch: GIT_REPO_BRANCH,
            url: GIT_REPO_URL,
            credentialsId: GITHUB_CREDENTIALS,
            poll: true
    }

    stage("Stage 2 - Build App and Execute Unit & Integration Tests") {
        sh """
            ./gradlew clean
            ./gradlew :${APP_MODULE}:bootBuildImage -x test --refresh-dependencies
           """
    }

    stage("Stage 3 - Kubernetes Delete Deploy and Service") {
        withKubeConfig([credentialsId: KUBERNETES_CREDENTIALS,
                        serverUrl: KUBERNETES_SERVER_URL]) {

            try{
                sh "/usr/bin/kubectl delete svc ${APP_NAME}"
                sh "/usr/bin/kubectl delete deploy ${APP_NAME}"
            } catch (error) {
                print 'Warning: K8s Service doesnt exists'
                print error
            }
        }
    }

    stage("Stage 4 - Kubernetes Deploy App to the Cluster") {
       withKubeConfig([credentialsId: KUBERNETES_CREDENTIALS,
                       serverUrl: KUBERNETES_SERVER_URL]) {

            sh "/usr/bin/kubectl apply -f ${KUBERNETES_DEPLOY}"
        }
    }
}


//pipeline {
//
//    agent any
//
//    tools {
//        gradle GRADLE
//        jdk JDK
//    }
//
//    parameters {
//        string(name: 'IMAGE_NAME',          defaultValue: 'registry.parlacom.net:5000/leadingquest/leadingquest-kinder:prod-v1.0', description: '')
//        string(name: 'REGISTRY_URL',        defaultValue: 'http://registry.parlacom.net:5000', description: '')
//        string(name: 'REGISTRY_USERNAME',   defaultValue: 'parla', description: '')
//        string(name: 'REGISTRY_PASSWORD',   defaultValue: 'Leodouve10@', description: '')
//        string(name: 'REGISTRY_CONTACT',    defaultValue: 'luiz@leadingguest.com', description: '')
//        string(name: 'REPOSITORY_NAME',     defaultValue: 'parla', description: '')
//        string(name: 'REPOSITORY_URL',      defaultValue: 'https://repository.parlacom.net:8081/repository/parla/', description: '')
//        string(name: 'REPOSITORY_USERNAME', defaultValue: 'parla', description: '')
//        string(name: 'REPOSITORY_PASSWORD', defaultValue: 'apisuccess2018', description: '')
//    }
//
//    stages {
//
//        stage('Stage 0 - Java Version') {
//            steps {
//                sh """
//                    java -version
//                   """
//            }
//        }
//        stage('Stage 1 - Checkout Source') {
//            steps {
//                git branch: GIT_REPO_BRANCH,
//                        url: GIT_REPO_URL,
//                        credentialsId: 'github-parlacom-key'
//            }
//        }
//
////        stage('Stage 2 - Build App and Execute Unit & Integration Tests') {
////            steps {
////                sh """
////                    gradle clean
////                   """
////                sh """
////                    gradle :${APP_MODULE}:bootBuildImage -x test
////                   """
////            }
////        }
//
//
//
//
////        stage('Stage 3 - Build App and Execute Unit & Integration Tests') {
////            steps {
////                sh """
////                    gradle clean
////                   """
////
////                sh """
////                      gradle -Plula1=LulaLa1 -Dlula2=LulaLa2 :${APP_MODULE}:bootBuildImage -x test
////                   """
////            }
////        }
//
//
//
//        stage('Stage 3 - Deploy App') {
//            steps {
//                script {
//                    kubernetesDeploy(configs: KUBERNETES_DEPLOY, kubeconfigId: KUBERNETES_CREDENTIALS, enableConfigSubstitution: true)
//                }
//            }
//        }
//
//
//
////        stage('Stage 3 - Deploy App to Kubernetes Cluster') {
////            steps {
////                sshagent(credentials : ['deploy-ssh-kubernetes']) {
////                    sh 'scp -o StrictHostKeyChecking=no /home/luizh/lula.yml deploy@api.parlacom.net:/home/deploy'
////                }
////            }
////        }
//    }
//}
