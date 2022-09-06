package solusrex.core.common

import solusrex.core.baseenities.Contact

class Mail : Contact {
    override fun typeName(): String? {
        return "E-mail"
        }
    override fun valid(): Boolean {
        return valid(value!!)
        }
    fun Mail() {}
    constructor(ss: String?){
        parseAndSet(ss)
        }
    fun valid(ss: String): Boolean {
        val c = ss.toCharArray()
        if (c.size == 0) return false
        var cnt = 0
        for (i in c.indices) {
            val cc = c[i]
            if (cc == '@') {
                if (i == 0) return false
                cnt++
            } else return if (cc == '.' || cc >= 'A' && cc <= 'Z' || cc >= 'a' && cc <= 'z' || cc >= '0' && cc <= '9' || cc == '_')
                continue else false
            }
        return cnt == 1
        }
    override fun parseAndSet(ss: String?): Boolean {
        val rez = valid(ss!!)
        if (rez) value = ss
        return rez
        }
    override fun toString(): String {
        return if (valid()) value!! else ""
        }
    companion object{
        @JvmStatic
        fun main(ss: Array<String>) {
            println(Mail("aaa@ddd.ccc").valid())
            println(Mail("aaa@d_dd.ccc").valid())
            println(Mail("@ddd.ccc").valid())
            println(Mail("aaa@dd@d.ccc").valid())
            }
        }

    }