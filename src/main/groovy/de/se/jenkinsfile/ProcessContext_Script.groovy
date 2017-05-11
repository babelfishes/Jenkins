#!groovy
package de.se.jenkinsfile

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
