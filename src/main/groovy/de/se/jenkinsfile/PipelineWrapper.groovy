#!groovy
package de.se.jenkinsfile

import de.se.jenkinsfile.ClassLoader

/**
 * Created by debian-jenkins on 30.04.17.
 */
class Pipeline implements Serializable{

    def _this
    ClassLoader classLoader

    Pipeline(def __this, ClassLoader _classLoader) {
        _this=__this
        classLoader = _classLoader
    }

    def echo(def line) {
        _this.echo(line)
    }

    def stage(def name, def closure) {
        _this.stage "ADDON ${name}", closure
    }

    def parallel(def closure) {
        _this.parallel closure
    }

    def shellWithOut(def command) {
        _this.echo "run shell command[${command}]"
        def retValue = _this.sh (script: "${command}", returnStdout: true)
        _this.echo "run shell command[${command}] returns [${retValue}]"
        return retValue
    }

    def loadGoovyFile(def filename) {
        def fileLoader = _this.load filename
        fileLoader()
        return fileLoader
    }
}

Pipeline createInstance(ClassLoader _classLoader) {
    return new Pipeline(this,_classLoader)
}

Pipeline call() {
}


return this;