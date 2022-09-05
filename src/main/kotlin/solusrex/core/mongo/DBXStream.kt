package solusrex.core.mongo

import com.thoughtworks.xstream.XStream

class DBXStream : XStream() {
    init {
        alias("QList", DBQueryList::class.java)
        alias("QInt", DBQueryInt::class.java)
        alias("QLong", DBQueryLong::class.java)
        alias("QString", DBQueryString::class.java)
        alias("QBoolean", DBQueryBoolean::class.java)
        alias("QDouble", DBQueryDouble::class.java)
        useAttributeFor(DBQueryList::class.java, "mode")
        useAttributeFor(DBQueryInt::class.java, "value")
        useAttributeFor(DBQueryInt::class.java, "field")
        useAttributeFor(DBQueryInt::class.java, "cmpMode")
        useAttributeFor(DBQueryLong::class.java, "value")
        useAttributeFor(DBQueryLong::class.java, "field")
        useAttributeFor(DBQueryLong::class.java, "cmpMode")
        useAttributeFor(DBQueryString::class.java, "value")
        useAttributeFor(DBQueryString::class.java, "field")
        useAttributeFor(DBQueryString::class.java, "cmpMode")
        useAttributeFor(DBQueryDouble::class.java, "value")
        useAttributeFor(DBQueryDouble::class.java, "field")
        useAttributeFor(DBQueryDouble::class.java, "cmpMode")
        useAttributeFor(DBQueryBoolean::class.java, "value")
        useAttributeFor(DBQueryBoolean::class.java, "field")
        useAttributeFor(DBQueryBoolean::class.java, "cmpMode")
        addImplicitCollection(DBQueryList::class.java, "obj")
    }
}