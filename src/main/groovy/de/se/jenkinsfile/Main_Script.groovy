#!groovy
package de.se.jenkinsfile

import de.se.jenkinsfile.Pipeline
import de.se.jenkinsfile.ProcessA

class Main implements Serializable {

    Pipeline pipeline

    Main(Pipeline pipe) {
        pipeline = pipe
    }

    def run(name) {

        pipeline.stage name , {
            pipeline.parallel(p1: {
                pipeline.echo "P1"
            }, p2: {
                pipeline.echo "P2"
            })
        }

        ProcessA processA = pipeline.classLoader.processA
        processA.run("processA")
    }

 }


Main createInstance(Pipeline pipeline) {
    return new Main(pipeline)
}

def call() {
}


return this;
