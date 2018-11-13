
def response = new XmlSlurper().parse("/Users/Muzakkir/IdeaProjects/XmlParsers-Builders/src/samp/files/deployit.xml")
def command = response.deployables.'cmd.Command'.each{ node ->
    if (node.@name == "create-log-folder-if-non-existent") {
        println node.commandLine.text()
        cmd = node.commandLine.text()
        excecuteShell( "$cmd", "Directory creation"  )
        //println System.getProperty("user.dir")
    }
}

def excecuteShell( command, String echoInfo ) {
    println "inside exceute shell"
    Process process = "$command".execute()
    //println "Process exit code: ${process.exitValue()}"
    if ( process.exitValue() == 0 ) {println "Command execution exit code: ${process.exitValue()} : $echoInfo SUCCESS "}
    def out = new StringBuffer()
    def err = new StringBuffer()
    process.consumeProcessOutput(out, err)
    process.waitFor()

    if (out.size() > 0) println out
    if (err.size() > 0) println err
}