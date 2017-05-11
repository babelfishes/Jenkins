#!groovy
package de.se.jenkinsfile

import de.se.jenkinsfile.Pipeline

class ProcessA implements Serializable {

    Pipeline pipeline

    ProcessA(Pipeline pipe) {
        pipeline = pipe
    }

    def run(name) {

        pipeline.stage name , {
            pipeline.parallel(p1: {
                pipeline.echo "P3"
            }, p2: {
                pipeline.echo "P4"
            })
        }
    }

 }


ProcessA createInstance(Pipeline pipeline) {
    return new ProcessA(pipeline)
}

def call() {
}


return this;
