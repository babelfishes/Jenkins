#!groovy
package de.se.jenkinsfile

// Stage Compile
class Testit2 implements Serializable{
    def testconstant = "testme2"

}

def call() {
    return new Testit2()
}

return this;
