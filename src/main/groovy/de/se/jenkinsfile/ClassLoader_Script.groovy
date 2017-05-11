#!groovy
package de.se.jenkinsfile

class ClassLoader implements Serializable {

    //loaded classes must be untyped
    def processALoader
    def processBLoader
    def processContextLoader
    def main
    def pipelineLoader
    def _this

    ClassLoader(def __this) {
        _this = __this

    }

    def loadClasses(ClassLoader classLoader) {
        //loading all classes typeless
        //in the right order, so that every import statement could be fulfilled
        pipelineLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/PipelineWrapper.groovy")
        processContextLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/ProcessContext_Script.groovy")
        processALoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/ProcessA_Script.groovy")
        processBLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/ProcessB_Script.groovy")
        def mainLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/Main_Script.groovy")

        main = initClass(mainLoader, classLoader, initClass(pipelineLoader))
    }

    private def loadClass(String filename) {
        def loader = _this.load(filename)
        loader()
        return loader
    }

    private def initClass(def loader, def initializer1, def initializer2) {
        return loader.createInstance(initializer1, initializer2)
    }

    private def initClass(def loader, def initializer) {
        return loader.createInstance(initializer)
    }

    private def initClass(def loader) {
        return loader.createInstance()
    }

 }


ClassLoader createInstance() {
    return new ClassLoader(this)
}

def call() {
}


return this;
