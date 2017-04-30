#!groovy
package de.se.jenkinsfile.groovy

/**
 * Created by debian-jenkins on 30.04.17.
 */
class Pipeline {

    def _this

    Pipeline(def __this) {
        _this=__this
    }

    def echo(def line) {
        _this.echo(line)
    }

    def stage(def name, def closure) {
        _this.stage(closure(name))
    }
}

def call() {
    return new Pipeline(this)
}

return this;