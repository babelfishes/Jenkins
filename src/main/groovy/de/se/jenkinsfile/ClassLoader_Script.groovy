#!groovy
package de.se.jenkinsfile

class ClassLoader implements Serializable {

    //loaded classes must be untyped
    def processA
    def processB
    def processContext
    def main
    def pipeline
    def _this

    ClassLoader(def __this) {
        _this = __this

    }

    def loadClasses(ClassLoader classLoader) {
        //loading all classes typeless
        //in the right order, so that every import statement could be fulfilled
        def pipelineLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/PipelineWrapper.groovy")
        def processContextLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/ProcessContext_Script.groovy")
        def processALoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/ProcessA_Script.groovy")
        def processBLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/ProcessB_Script.groovy")
        def mainLoader = loadClass("/home/debian-jenkins/IdeaProjects/Jenkinsfile/src/main/groovy/de/se/jenkinsfile/Main_Script.groovy")

        pipeline = initClass(pipelineLoader)
        processContext = initClass(processContextLoader)
        processA = initClass(processALoader, pipeline)
        processB = initClass(processBLoader, pipeline)
        //main should be the last, because the constructor sets up all inner classes,
        //to be typed
        main = initClass(mainLoader, classLoader)
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
