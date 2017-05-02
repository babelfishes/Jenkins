#!groovy
package de.se.jenkinsfile

import com.cloudbees.groovy.cps.NonCPS

// Stage Compile
class Testit1 implements Serializable{


    def _this
    def testconstant = "testme1"

    Testit1(__this) {
        _this=__this
    }

    def getTestconstant() {
        return "hallo ${testconstant}"
    }

    def useShell() {
        _this.sh("echo hallo")
    }

    @NonCPS
    def getMvn() {
        def mvnTool = _this.tool('Maven3')
        return "${mvnTool}/bin/mvn"
    }

}

def call() {
    //TODO createInstance
    return new Testit1(this)
}

return this;
