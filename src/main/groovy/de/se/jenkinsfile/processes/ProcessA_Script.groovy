#!groovy
package de.se.jenkinsfile.processes

// import can be used, because these classes are loaded befere
import de.se.jenkinsfile.pipeline.Pipeline

class ProcessA implements Serializable {

    Pipeline pipeline

    ProcessA(Pipeline pipe) {
        pipeline = pipe
    }

    def run(String name, ProcessContext context) {
        context.paramA += "start ProcessA;"
        pipeline.stage name , { stageName ->
            pipeline.parallel("${stageName }-p1": {
                pipeline.echo "${stageName}-p1-line"
            }, "${stageName }-p2": {
                pipeline.echo "${stageName}-p2-line"
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
