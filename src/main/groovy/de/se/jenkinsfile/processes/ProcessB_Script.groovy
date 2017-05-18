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
        pipeline.stage name , { stageName ->
            pipeline.parallel("${stageName}-p1": {
                pipeline.echo "${stageName}-p1-line"
            }, "${stageName}-p2": {
                pipeline.echo "${stageName}-p2-line"
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
