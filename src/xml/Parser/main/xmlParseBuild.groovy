package main


//https://stackoverflow.com/questions/15077592/iterate-through-each-xml-node-with-groovy-printing-each-node
/*
xml attributes to map
https://coderanch.com/t/620651/languages/convert-xml-tag-attribute-values
generate XML from MAP
https://coderanch.com/t/471377/languages/generate-XML-MAP
https://stackoverflow.com/questions/48709822/dynamically-generate-xml-with-attributes-and-child-nodes-from-map-in-groovy
 */


//working snippet start
doc = new XmlSlurper().parse("/Users/Muzakkir/IdeaProjects/XmlParsers-Builders/src/samp/files/test.xml")
def list = []
writer("<JVMSettings>")
doc.deployables.each { thing ->
    thing.children().each { tag ->
        //println "---------------------"
        //println "${tag.name()}"
        map1 = "${tag.name()}"
        println "<$map1>"
        writer("<$map1>")
        //def map1 = [:]
        //println "---------------------"
        tag.children().each { child ->
            //println "  ${child.name()}: ${child.text()}"
            //map1[ "${child.name()}" ] = "${child.text()}"
           // println "Param2: ${tag.name()}"
           // println "Param3: ${child.name()}"

            //getSpeckey("UCDxml", "${tag.name()}", "${child.name()}" )
           if (getSpeckey("UCDxml", "${tag.name()}", "${child.name()}" )){
                //println "key found in json : "
               newkeyFromJson = getSpeckey("UCDxml", "${tag.name()}", "${child.name()}" )
               xmlline = xmlCreator("$newkeyFromJson", "${child.text()}")
               writer("$xmlline")
           }
        }
        println "</$map1>"
        writer("</$map1>")
        //println(map1)
        //list << map1
    }
}
writer("</JVMSettings>")
//println list

/*
list.each(){
    println "loop start---"
    println "item : $it"
    println "loop end---"
}*/
//working snippet end



// json reader start
import groovy.json.JsonSlurper

def getSpeckey( param1, param2, param3 ){
    def inputFile = new File("/Users/Muzakkir/IdeaProjects/XmlParsers-Builders/src/samp/files/ref.json")
    def InputJSON = new JsonSlurper().parseText(inputFile.text)

    String getSelectedKey = InputJSON."$param1"."$param2"."$param3"
    if (getSelectedKey == null){
        println "Key doesnt not exists"
    }else{
        //println "Corresponding key form json: $getSelectedKey"
        return getSelectedKey
    }
}
// json reader end.
//getSpeckey("UCDxml","was.War","tag")

import groovy.xml.MarkupBuilder

def xmlCreator(element, value){
    def xmlWriter = new StringWriter()
    def xmlMarkup = new MarkupBuilder(xmlWriter)
     xmlMarkup."$element"("$value")
    println xmlWriter.toString()
    String xmlline = xmlWriter.toString()
    return xmlline
}

//xmlCreator('UCDxml', 'test1')
def writer( line ) {
File greetingsFile = new File("myfile.xml")
greetingsFile.withWriterAppend{ out ->
    out.println "$line"
}
}