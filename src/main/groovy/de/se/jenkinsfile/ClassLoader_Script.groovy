#!groovy
package de.se.jenkinsfile

class ClassLoader implements Serializable {


    def processA
    def mainProgramm
    def pipeline
    def _this

    ClassLoader(def __this) {
        _this = __this

    }

    def loadClasses(ClassLoader classLoader) {
        def pipelinePath = "/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/PipelineWrapper.groovy"
        def pipelineLoader = _this.load(pipelinePath)
        pipelineLoader()
        pipeline = pipelineLoader.createInstance(classLoader)

        def processAPath = "/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/ProcessA_Script.groovy"
        def processALoader = _this.load(processAPath)
        processALoader()
        processA = processALoader.createInstance(pipeline)

        def mainPath = "/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/Main_Script.groovy"
        def mainLoader = _this.load(mainPath)
        mainLoader()
        mainProgramm = mainLoader.createInstance(pipeline)

    }

    private def loadClass(String filename, def initializer) {

    }

 }


ClassLoader createInstance() {
    return new ClassLoader(this)
}

def call() {
}


return this;
