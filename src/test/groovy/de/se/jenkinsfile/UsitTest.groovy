package de.se.jenkinsfile

import spock.lang.Specification


/**
 * Created by debian-jenkins on 01.05.17.
 */
class UsitTest extends Specification {

    //TODO Mock fuer jenkinsfile

    def "checkStage"() {
        when: def usit = new Usit(new Pipeline("hallo"), new Testit1(), new Testit2())
        then: usit.runInStage("hallo ") == "test"
    }


}