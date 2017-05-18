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
        pipeline.stage name , {
            pipeline.parallel("${name}-p1": {
                pipeline.echo "P3"
            }, "${name}-p2": {
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
