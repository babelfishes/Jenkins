#!groovy
package de.se.jenkinsfile


// Stage Compile
class Testit1 implements Serializable{


    def _this
    def testconstant = "testme1"
    def mvnTool

    Testit1(__this) {
        _this=__this
        mvnTool = _this.tool('Maven3')
    }

    def getTestconstant() {
        return "hallo ${testconstant}"
    }

    def useShell() {
        _this.sh("echo hallo")
    }

    def getMvn() {
        return "${mvnTool}/bin/mvn"
    }

}

def call() {
    //TODO createInstance
    return new Testit1(this)
}

return this;
