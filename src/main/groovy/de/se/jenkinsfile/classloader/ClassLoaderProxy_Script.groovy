#!groovy
package de.se.jenkinsfile.classloader

import de.se.jenkinsfile.processes.ProcessA
import de.se.jenkinsfile.processes.ProcessB
import de.se.jenkinsfile.processes.ProcessContext

/**
 * This class can create typed instances of Classes for the main workflow.
 * Therefore the main class could not be loaded here
 */
class ClassLoaderProxy implements Serializable {


    ClassLoader loader

    ClassLoaderProxy(ClassLoader _loader) {
        loader = _loader
    }

    ProcessA createProcessA() {
        return loader.processALoader.createInstance(loader.pipeline)
    }

    ProcessB createProcessB() {
        return loader.processBLoader.createInstance(loader.pipeline)
    }

    ProcessContext createProcessContext() {
        return loader.processContextLoader.createInstance()
    }

 }


ClassLoaderProxy createInstance(ClassLoader loader) {
    return new ClassLoaderProxy(loader)
}

def call() {
}


return this;
