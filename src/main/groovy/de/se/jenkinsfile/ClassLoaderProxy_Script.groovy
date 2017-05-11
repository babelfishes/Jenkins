#!groovy
package de.se.jenkinsfile

import de.se.jenkinsfile.ProcessA
import de.se.jenkinsfile.ProcessB
import de.se.jenkinsfile.ProcessContext
import de.se.jenkinsfile.ClassLoader


/**
 * This class creates typed instances of given Classes for the main workflow.
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
