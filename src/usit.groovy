#!groovy
import de.se.jenkinsfile.groovy.Testit2
import de.se.jenkinsfile.groovy.Testit1

def call() {
}

def echoHelper(Testit1 test11, Testit2 test22) {
    echo "test ${test11.testconstant}"
    echo "testma ${test22.testconstant}"
}

return this;
