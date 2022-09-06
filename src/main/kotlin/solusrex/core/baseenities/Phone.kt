package solusrex.core.baseenities

import solusrex.core.common.Utility

class Phone : Contact {
    constructor(vv:String?) {
        parseAndSet(vv)
        }
    constructor() {}
    override fun name(): String? {
        return if (!valid()) "" else value
        }
    override fun typeName(): String? {
        return "Телефон"
        }
    open fun testMobile(ss: String): String? {
        if (ss.startsWith("8") && ss.length == 11 && Utility.hasDigits(ss)) return ss.substring(1)
        if (ss.startsWith("+7") && ss.length == 12 && Utility.hasDigits(ss.substring(1))) return ss.substring(2)
        return if (ss.length == 10 && Utility.hasDigits(ss)) ss else null
        }
    open fun testLocal(ss: String): Boolean {
        return ss.length == 7 && Utility.hasDigits(ss)
        }

    open fun valid(ss: String?): Boolean {
        if (ss!!.length == 0) return false
        if (testMobile(ss!!) != null) return true
        return if (testLocal(ss)) true else false
        }
    override fun valid(): Boolean {
        return valid(value)
        }
    override fun parseAndSet(ss: String?): Boolean {
        val zz = testMobile(ss!!)
        if (zz != null) {
            value = zz
            return true
            }
        if (testLocal(ss)) {
            value = ss
            return true
            }
        return false
        }
    fun isMobile(): Boolean {
        return testMobile(value!!) != null
        }
    fun mobile(): String? {
        if (!isMobile()) return ""
        if (value!!.startsWith("8")) return value!!.substring(1)
        return if (value!!.startsWith("+7")) value!!.substring(2) else value
        }
    override fun toString(): String {
        return if (valid()) value!! else ""
        }
}