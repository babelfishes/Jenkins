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

        pipeline.stage "${name}-Stage1", { stageName ->
            pipeline.parallel("${stageName}-Stage1-p1": {
                pipeline.echo "${stageName}-p1-line"
            }, "${stageName}-p2": {
                pipeline.echo "${stageName}-p2-line"
            })
        }

        pipeline.stage "${name}-Stage2" , { stageName ->
            ProcessA processA = factory.createProcessA()

            ProcessContext processContext1 = factory.createProcessContext()
            processA.run("${stageName}-processA", processContext1)

            ProcessB processB = factory.createProcessB()
            processB.run("${stageName}-processB", processContext1)
            //showing that the the context instance is modified by both
            pipeline.echo("[${processContext1.paramA}][${processContext1.paramB}]")
        }

        pipeline.stage "${name}-Stage3" , { stageName ->
            ProcessContext processContext1 = factory.createProcessContext()
            ProcessContext processContext2 = factory.createProcessContext()
            pipeline.parallel("${stageName}-p1": {
                ProcessA processA = factory.createProcessA()
                processA.run("${stageName}-processA", processContext1)
            }, "${stageName}-p2": {
                ProcessB processB = factory.createProcessB()
                processB.run("${stageName}-processB", processContext2)
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
