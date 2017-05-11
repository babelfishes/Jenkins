#!groovy
package de.se.jenkinsfile

// import can be used, because these classes are loaded befere
import de.se.jenkinsfile.Pipeline
import de.se.jenkinsfile.ProcessContext


class ProcessB implements Serializable {

    Pipeline pipeline

    ProcessB(Pipeline pipe) {
        pipeline = pipe
    }

    def run(String name, ProcessContext processContext) {
        processContext.paramA += "start ProcessB;"
        pipeline.stage name , {
            pipeline.parallel(p1: {
                pipeline.echo "P3"
            }, p2: {
                pipeline.echo "P4"
            })
        }
        processContext.paramA += "end ProcessB;"
    }

 }


ProcessB createInstance(Pipeline pipeline) {
    return new ProcessB(pipeline)
}

def call() {
}


return this;
