#!groovy
package de.se.jenkinsfile

import com.cloudbees.groovy.cps.NonCPS
import java.util.ArrayList


// Stage Compile
class Testit1 implements Serializable{


    def _this
    def testconstant = "testme1"
    ArrayList list

    Testit1(__this, ArrayList _list) {
        _this=__this
        list = _list
    }

    def getTestconstant() {
        return "Testconstant: ${list} hallo ${testconstant}"
    }

    def useShell() {
        _this.sh("echo hallo")
    }

    def getMvn() {
        return "${_getMvn()}/bin/mvn"
    }

    def _getMvn() {
        //the tool call has to be capsolated in a Method, while the call returns immedeately
        return _this.tool('Maven3')
    }

    def getBuildNumber() {
        return _this.env.BUILD_NUMBER
    }

    def senMail() {
        _this.mail(to: "rs@ppi.de", subject: "headline", body: "TEXT")
    }


}

def call() {
}

Testit1 createInstance(def liste) {
    return new Testit1(this, liste)
}

return this;
