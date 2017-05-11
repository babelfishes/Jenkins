#!groovy
package de.se.jenkinsfile


/**
 * Created by debian-jenkins on 30.04.17.
 */
class Pipeline implements Serializable{

    def _this

    Pipeline(def __this) {
        _this=__this
    }

    def echo(def line) {
        _this.echo(line)
    }

    def stage(def name, def closure) {
        _this.stage "STAGE:${name}", closure
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

Pipeline createInstance() {
    return new Pipeline(this)
}

def call() {
}


return this;