#!groovy
package de.se.jenkinsfile.groovy


// Stage Compile
class Testit1 implements Serializable{
    def testconstant = "testme1"
    def getTestconstant() {
        return "hallo ${testconstant}"
    }



}

def call() {
    //TODO createInstance
    return new Testit1()
}

return this;
