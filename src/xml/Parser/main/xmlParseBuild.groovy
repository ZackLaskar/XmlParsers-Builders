package main

//https://stackoverflow.com/questions/15077592/iterate-through-each-xml-node-with-groovy-printing-each-node
/*

xml attributes to map
https://coderanch.com/t/620651/languages/convert-xml-tag-attribute-values

generate XML from MAP
https://coderanch.com/t/471377/languages/generate-XML-MAP
https://stackoverflow.com/questions/48709822/dynamically-generate-xml-with-attributes-and-child-nodes-from-map-in-groovy
 */
doc = new XmlSlurper().parse("/Users/Muzakkir/IdeaProjects/XmlParsers-Builders/src/samp/files/test.xml")
def list = []
doc.deployables.each { thing ->
    thing.children().each { tag ->
        println "---------------------"
        //println "${tag.name()}"
        map1 = "${tag.name()}"
        println map1
        def map1 = [:]
        println "---------------------"
        tag.children().each { child ->
            println "  ${child.name()}: ${child.text()}"
            map1[ "${child.name()}" ] = [ "${child.text()}" ]
        }
        println(map1)
        list << map1
    }
}

println list

/*
Groovy > Nested Map to Xml
https://stackoverflow.com/questions/30997222/groovy-nested-map-to-xml

*/