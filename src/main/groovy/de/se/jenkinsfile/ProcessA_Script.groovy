#!groovy
package de.se.jenkinsfile

// import can be used, because these classes are loaded befere
import de.se.jenkinsfile.Pipeline
import de.se.jenkinsfile.ProcessContext

class ProcessA implements Serializable {

    Pipeline pipeline

    ProcessA(Pipeline pipe) {
        pipeline = pipe
    }

    def run(String name, ProcessContext context) {
        context.paramA += "start ProcessA;"
        pipeline.stage name , {
            pipeline.parallel(p1: {
                pipeline.echo "P3"
            }, p2: {
                pipeline.echo "P4"
            })
        }
        context.paramA += "end ProcessA;"
    }

 }


ProcessA createInstance(Pipeline pipeline) {
    return new ProcessA(pipeline)
}

def call() {
}


return this;
