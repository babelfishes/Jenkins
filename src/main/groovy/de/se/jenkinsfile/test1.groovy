#!groovy
package de.se.jenkinsfile


// Stage Compile
class Testit1 implements Serializable{
    def testconstant = "testme1"
    def getTestconstant() {
        return "hallo ${testconstant}"
    }

    def useShell(def shell) {
        shell "echo hallo"
    }


}

def call() {
    //TODO createInstance
    return new Testit1()
}

return this;
