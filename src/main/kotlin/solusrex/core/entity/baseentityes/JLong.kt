package solusrex.core.entity.baseentityes

import com.google.gson.Gson
import solusrex.core.dao.DAO

class JLong : DAO {
    var value: Long = 0
    constructor() {}
    constructor(`val`: Long) {
        value = `val`
        }
    override fun toString(): String {
        return "" + value
        }
    companion object {
        @JvmStatic
        fun main(ss: Array<String>) {
            println(Gson().toJson(JLong(123)))
            println(Gson().toJson(JEmpty()))
        }
    }
}