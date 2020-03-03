job('NodeJS Docker example') {
    scm {
        git('git://github.com/mostran/dokcer-demo') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('master')
            node / gitConfigEmail('nofear9300@aol.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('master/job-dsl/nodejsdocker')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
