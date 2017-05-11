#!groovy
package de.se.jenkinsfile

// import can be used, because these classes are loaded before
import de.se.jenkinsfile.ClassLoaderProxy
import de.se.jenkinsfile.Pipeline
import de.se.jenkinsfile.ProcessA
import de.se.jenkinsfile.ProcessB
import de.se.jenkinsfile.ProcessContext


class Main implements Serializable {

    ClassLoaderProxy classloader
    Pipeline pipeline

    Main(ClassLoaderProxy _classloader, Pipeline _pipeline) {
        classloader = _classloader
        pipeline = _pipeline
    }

    def run(name) {
        ProcessA processA = classloader.createProcessA()
        ProcessB processB = classloader.createProcessB()

        pipeline.stage name , {
            pipeline.parallel(p1: {
                pipeline.echo "P1"
            }, p2: {
                pipeline.echo "P2"
            })
        }

        pipeline.stage "processes" , {
            ProcessContext processContext1 = classloader.createProcessContext()
            processA.run("processA", processContext1)
            processB.run("processA", processContext1)
            //showing that the the context instance is modified by both
            pipeline.echo("[${processContext1.paramA}][${processContext1.paramB}]")
        }

        pipeline.stage "processes 2. Run" , {
            ProcessContext processContext1 = classloader.createProcessContext()
            ProcessContext processContext2 = classloader.createProcessContext()
            processA.run("processA", processContext1)
            processB.run("processA", processContext2)
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
