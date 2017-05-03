package de.se.jenkinsfile

import spock.lang.Specification


/**
 * Created by debian-jenkins on 01.05.17.
 */
class UsitTest extends Specification {

    //TODO Mock fuer jenkinsfile

    def "checkValue"() {
        when: def usit = new Usit()
        then: usit.tph_SONAR_CACHE == "SONAR_CACHE"
    }


}