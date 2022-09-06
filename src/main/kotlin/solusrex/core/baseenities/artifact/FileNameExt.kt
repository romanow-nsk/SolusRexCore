package solusrex.core.baseenities.artifact

import solusrex.core.dao.DAO

class FileNameExt : DAO {
    var path: String = ""
    var name = ""
    var ext = ""
    constructor() {}
    constructor(pt: String, nm: String, ex: String) {
        path = pt
        name = nm
        ext = ex
        }
    constructor(pt: String, nm: String) {
        path = pt
        val idx = nm.lastIndexOf(".")
        if (idx == -1) {
            name = nm
            ext = ""
            }
        else {
            name = nm.substring(0, idx)
            ext = nm.substring(idx + 1)
            }
        }
    constructor(fspec: String) {
        val idx0 = fspec.lastIndexOf("/")
        val nm: String
        if (idx0 == -1) {
            nm = fspec
            path = ""
            }
        else{
            nm = fspec.substring(idx0 + 1)
            path = fspec.substring(0, idx0)
            }
        val idx = nm.lastIndexOf(".")
        if (idx == -1) {
            name = nm
            ext = ""
            }
        else{
            name = nm.substring(0, idx)
            ext = nm.substring(idx + 1)
            }
        }
    fun fileName(): String {
        return "$name.$ext"
        }
    fun fullName(): String? {
        return (if (path != null && path!!.length != 0) "$path/" else "") + fileName()
        }
    //-------------------------------------------------------------------------------------------------------
    open fun toStringValue(): String? {
        return "$path|$name|$ext"
        }
    @Throws(Exception::class)
    open fun parseValue(ss: String) {
        val idx1 = ss.indexOf("|")
        val idx2 = ss.lastIndexOf("|")
        path = ""
        if (idx1 != 0) path = ss.substring(0, idx1)
        name = ""
        name = ss.substring(idx1 + 1, idx2)
        ext = ""
        if (idx2 != ss.length - 1) ext = ss.substring(idx2 + 1)
        }
    }