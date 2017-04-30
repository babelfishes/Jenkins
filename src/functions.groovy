#!groovy

import groovy.transform.Field

@Field def test1
@Field def test2

def call(rootDir) {
    def test1Load = load "${rootDir}/de/se/jenkinsfile/groovy/test1.groovy"
    test1=test1Load()
    def test2Load = load "${rootDir}/de/se/jenkinsfile/groovy/test2.groovy"
    test2=test2Load()
}


return this;
