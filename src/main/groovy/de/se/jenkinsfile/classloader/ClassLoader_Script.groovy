#!groovy
package de.se.jenkinsfile.classloader

class ClassLoader implements Serializable {

    //loaded classes must be untyped at this time
    def processALoader
    def processBLoader
    def processContextLoader
    def main
    def pipelineLoader
    def _this
    def pipeline

    ClassLoader(def __this) {
        _this = __this

    }


    def loadClasses(ClassLoader classLoader,def startPath) {
        //loading all classes typeless
        //in the right order, so that every import statement could be fulfilled
        pipelineLoader = loadClass("${startPath}/src/main/groovy/de/se/jenkinsfile/pipeline/Pipeline_Script.groovy")
        processContextLoader = loadClass("${startPath}/src/main/groovy/de/se/jenkinsfile/processes/ProcessContext_Script.groovy")
        processALoader = loadClass("${startPath}/src/main/groovy/de/se/jenkinsfile/processes/ProcessA_Script.groovy")
        processBLoader = loadClass("${startPath}/src/main/groovy/de/se/jenkinsfile/processes/ProcessB_Script.groovy")
        def classLoaderProxyLoader = loadClass("${startPath}/src/main/groovy/de/se/jenkinsfile/classloader/Factory_Script.groovy")
        def mainLoader = loadClass("${startPath}/src/main/groovy/de/se/jenkinsfile/Main_Script.groovy")

        pipeline = initClass(pipelineLoader)
        def classLoaderProxy = initClass(classLoaderProxyLoader, classLoader)
        main = initClass(mainLoader, classLoaderProxy, pipeline)
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
