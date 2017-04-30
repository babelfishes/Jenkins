#!groovy
import de.se.jenkinsfile.groovy.Testit2
import de.se.jenkinsfile.groovy.Testit1
import de.se.jenkinsfile.groovy.Pipeline

def call(Pipeline pipeline, Testit1 test11, Testit2 test22) {
    pipeline.stage('hallo') {
        pipeline.echo "test ${test11.testconstant}"
        pipeline.echo "testma ${test22.testconstant}"
    }
}


return this;
