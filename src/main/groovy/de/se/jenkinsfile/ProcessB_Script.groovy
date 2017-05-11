#!groovy
package de.se.jenkinsfile

import de.se.jenkinsfile.Pipeline

class ProcessB implements Serializable {

    Pipeline pipeline

    ProcessB(Pipeline pipe) {
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


ProcessB createInstance(Pipeline pipeline) {
    return new ProcessB(pipeline)
}

def call() {
}


return this;
