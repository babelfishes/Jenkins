#!groovy
package de.se.jenkinsfile.pipeline


/**
 * Typed wrapper for the pipeline dsl.
 * Here are some examples.
 * Extend with your own functions.
 */
class Pipeline implements Serializable{

    def _this

    Pipeline(def __this) {
        _this=__this
    }

    def echo(def line) {
        _this.echo(line)
    }

    def stage(def stageName, def closure) {
        _this.stage "${stageName}", closure(stageName)
    }

    def parallel(def closure) {
        _this.parallel closure
    }

    def shellWithStdOut(def command) {
        def retValue = _this.sh (script: "${command}", returnStdout: true)
        echo "shell command[${command}] returns [${retValue}]"
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