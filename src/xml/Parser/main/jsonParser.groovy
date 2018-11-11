import groovy.json.JsonSlurper

def getSpeckey( param1, param2, param3 ){
    def inputFile = new File("/Users/Muzakkir/IdeaProjects/XmlParsers-Builders/src/samp/files/ref.json")
    def InputJSON = new JsonSlurper().parseText(inputFile.text)
    String getSelectedKey = InputJSON."$param1"."$param2"."$param3"
    if (getSelectedKey == null){
        println "Key doesnt not exists"
    }else{
        println "Key: $getSelectedKey"
        return getSelectedKey
    }
  }


getSpeckey("UCDxml","was.jvm","minHeap")
if (getSpeckey("UCDxml","was.War","classloader")){
    println ("yes exists")
}