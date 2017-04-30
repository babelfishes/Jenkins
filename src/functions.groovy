#!groovy

import groovy.transform.Field

@Field def test1
@Field def test2
@Field def wrapper

def call() {
    def test1Load = load "src/de/se/jenkinsfile/groovy/test1.groovy"
    test1=test1Load()
    def test2Load = load "src/de/se/jenkinsfile/groovy/test2.groovy"
    test2=test2Load()
    def wrapperLoad = load "src/de/se/jenkinsfile/groovy/PipelineWrapper.groovy"
    wrapper=wrapperLoad()
}


return this;
