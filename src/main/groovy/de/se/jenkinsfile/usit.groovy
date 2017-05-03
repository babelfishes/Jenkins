#!groovy
package de.se.jenkinsfile

class Usit implements Serializable {

    def Pipeline pipeline
    def Testit1 test11
    def Testit2 test22

    Usit() {

    }
    Usit(Pipeline pipe, Testit1 test1, Testit2 test2) {
        pipeline = pipe
        test11 = test1
        test22 = test2
    }

    def runInStage(name) {
        pipeline.staging(name) {
            pipeline.echo "test ${test11.testconstant}"
            pipeline.echo "testma ${test22.testconstant}"
        }
    }

    String getTph_SONAR_CACHE() {
        return "SONAR_CACHE"
    }

    }


def call(Pipeline pipeline, Testit1 test11, Testit2 test22) {
    return new Usit(pipeline, test11, test22)
}


return this;
