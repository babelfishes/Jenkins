#!groovy
package de.se.jenkinsfile

import de.se.jenkinsfile.classloader.Factory
import de.se.jenkinsfile.pipeline.Pipeline

// import can be used, because these classes are loaded before
import de.se.jenkinsfile.processes.ProcessA
import de.se.jenkinsfile.processes.ProcessB
import de.se.jenkinsfile.processes.ProcessContext


/**
 * Anythons in this class is typed, because all Classes ar loaded before
 */
class Main implements Serializable {

    Factory factory
    Pipeline pipeline

    Main(Factory __factory, Pipeline _pipeline) {
        factory = __factory
        pipeline = _pipeline
    }

    def run(name) {

        pipeline.stage "${name}-Stage1", {
            pipeline.parallel("${name}-Stage1-p1": {
                pipeline.echo "${name}-p1-line"
            }, "${name}-p2": {
                pipeline.echo "${name}-p2-line"
            })
        }

        pipeline.stage "${name}-Stage2" , {
            ProcessA processA = factory.createProcessA()

            ProcessContext processContext1 = factory.createProcessContext()
            processA.run("${name}-processA", processContext1)

            ProcessB processB = factory.createProcessB()
            processB.run("${name}-processB", processContext1)
            //showing that the the context instance is modified by both
            pipeline.echo("[${processContext1.paramA}][${processContext1.paramB}]")
        }

        pipeline.stage "${name}-Stage3" , {
            ProcessContext processContext1 = factory.createProcessContext()
            ProcessContext processContext2 = factory.createProcessContext()
            pipeline.parallel("${name}-p1": {
                ProcessA processA = factory.createProcessA()
                processA.run("${name}-processA", processContext1)
            }, "${name}-p2": {
                ProcessB processB = factory.createProcessB()
                processB.run("${name}-processB", processContext2)
            })
            //showing that the contextes are differnt
            pipeline.echo("[${processContext1.paramA}][${processContext1.paramB}]")
            pipeline.echo("[${processContext2.paramA}][${processContext2.paramB}]")
        }

    }

 }


Main createInstance(Factory classloader, Pipeline pipeline) {
    return new Main(classloader, pipeline)
}

def call() {
}


return this;
