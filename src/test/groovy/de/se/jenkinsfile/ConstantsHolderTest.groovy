package de.se.jenkinsfile

import spock.lang.Specification


class ConstantsHolderTest extends Specification {

    def "checkConstant"() {
        when: def test1 = new Testit1()
        then: test1.testconstant ==  "hallo testme1"
    }

}