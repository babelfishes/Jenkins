#!groovy

import groovy.transform.Field

@Field def test1
@Field def test2

def call() {
    def test1Load = load 'de/se/jenkinsfile/groovy/test1.groovy'
    test1=test1Load()
    def test2Load = load 'de/se/jenkinsfile/groovy/test2.groovy'
    test2=test2Load()
}


return this;
