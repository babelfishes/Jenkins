#!groovy
package de.se.jenkinsfile.processes

class ProcessContext implements Serializable {

    public String paramA = "Default"
    public String paramB = "Default"

 }


ProcessContext createInstance() {
    return new ProcessContext()
}

def call() {
}


return this;
