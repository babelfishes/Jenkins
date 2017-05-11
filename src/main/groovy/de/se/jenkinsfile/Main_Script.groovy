#!groovy
package de.se.jenkinsfile

// import can be used, because these classes are loaded before
import de.se.jenkinsfile.ClassLoaderProxy
import de.se.jenkinsfile.Pipeline
import de.se.jenkinsfile.ProcessA
import de.se.jenkinsfile.ProcessB
import de.se.jenkinsfile.ProcessContext


/**
 * Anythons in this class is typed, because all Classes ar loaded before
 */
class Main implements Serializable {

    ClassLoaderProxy classloader
    Pipeline pipeline

    Main(ClassLoaderProxy _classloader, Pipeline _pipeline) {
        classloader = _classloader
        pipeline = _pipeline
    }

    def run(name) {

        pipeline.stage name , {
            pipeline.parallel(p1: {
                pipeline.echo "Parallel P1"
            }, p2: {
                pipeline.echo "Parallel P2"
            })
        }

        pipeline.stage "processes" , {
            ProcessA processA = classloader.createProcessA()

            ProcessContext processContext1 = classloader.createProcessContext()
            processA.run("seqentiell processA", processContext1)

            ProcessB processB = classloader.createProcessB()
            processB.run("seqentiell processB", processContext1)
            //showing that the the context instance is modified by both
            pipeline.echo("[${processContext1.paramA}][${processContext1.paramB}]")
        }

        pipeline.stage "parallel processes" , {
            ProcessContext processContext1 = classloader.createProcessContext()
            ProcessContext processContext2 = classloader.createProcessContext()
            pipeline.parallel(p11: {
                ProcessA processA = classloader.createProcessA()
                processA.run("Parallel processA", processContext1)
            }, p22: {
                ProcessB processB = classloader.createProcessB()
                processB.run("Parallel processB", processContext2)
            })
            //showing that the contextes are differnt
            pipeline.echo("[${processContext1.paramA}][${processContext1.paramB}]")
            pipeline.echo("[${processContext2.paramA}][${processContext2.paramB}]")
        }

    }

 }


Main createInstance(ClassLoaderProxy classloader, Pipeline pipeline) {
    return new Main(classloader, pipeline)
}

def call() {
}


return this;
