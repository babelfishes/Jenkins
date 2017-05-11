#!groovy
package de.se.jenkinsfile

// import can be used, because these classes are loaded before
import de.se.jenkinsfile.ClassLoader
import de.se.jenkinsfile.Pipeline
import de.se.jenkinsfile.ProcessA
import de.se.jenkinsfile.ProcessB

class Main implements Serializable {

    //typing
    Pipeline pipeline
    ProcessA processA
    ProcessB processB
    ProcessContext processContext

    Main(ClassLoader classloader) {
        // Casting the loaded classes to the right type,
        // which couldn't be done by the classeloader,
        // because the imports wouldn't be fulfilled then.
        // At this time all imports are known and therefore
        // all classes could be casted to the right type for a better use.
        pipeline = classloader.pipeline
        processA = classloader.processA
        processB = classloader.processB
        processContext = classloader.processContext
    }

    //everything here is completly typed
    def run(name) {
        pipeline.stage name , {
            pipeline.parallel(p1: {
                pipeline.echo "P1"
            }, p2: {
                pipeline.echo "P2"
            })
        }

        pipeline.stage "processes" , {
            processA.run("processA", processContext)
            processB.run("processA", processContext)
        }
        pipeline.echo("[${processContext.paramA}][${processContext.paramB}]")
    }

 }


Main createInstance(ClassLoader classloader) {
    return new Main(classloader)
}

def call() {
}


return this;
