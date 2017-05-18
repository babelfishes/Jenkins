#!groovy
package de.se.jenkinsfile.processes

// import can be used, because these classes are loaded befere
import de.se.jenkinsfile.pipeline.Pipeline

class ProcessB implements Serializable {

    Pipeline pipeline

    ProcessB(Pipeline pipe) {
        pipeline = pipe
    }

    def run(String name, ProcessContext processContext) {
        processContext.paramA += "start ProcessB;"
        pipeline.stage name , {
            pipeline.parallel("${name}-p1": {
                pipeline.echo "P3"
            }, "${name}-p1": {
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
