package solusrex.core.common

import retrofit2.Response
import solusrex.core.constants.values.CoreValues
import java.io.IOException
import java.lang.management.ManagementFactory
import javax.swing.JTextArea

object Utility {
    fun hasDigits(ss: String): Boolean {
        val cc = ss.toCharArray()
        for (zz in cc) if (!(zz >= '0' && zz <= '9')) return false
        return true
        }
    fun nextMonth(mnt: Int): Int {
        var mnt = mnt
        mnt++
        if (mnt == 13) mnt = 1
        return mnt
        }
    fun prevMonth(mnt: Int): Int {
        var mnt = mnt
        mnt--
        if (mnt == 0) mnt = 12
        return mnt
        }
    fun nextMonth(mnt: Int, cnt: Int): Int {
        var mnt = mnt
        mnt += cnt
        if (mnt > 12) mnt = mnt - 12
        return mnt
        }
    fun cutWithWords(ss: String, sz: Int): String? {
        var ss = ss
        if (ss.length > sz) ss = ss.substring(0, sz)
        val idx = ss.lastIndexOf(" ")
        if (idx != -1) ss = ss.substring(0, idx) + "..."
        return ss
        }
    fun toTextArea(ss1: String, sz: Int, mes: JTextArea) {
        var ss = ss1
        mes.text = ""
        while (true) {
            if (ss.length < sz) {
                mes.append(ss)
                return
            }
            val zz = ss.substring(0, sz)
            val idx = zz.lastIndexOf(" ")
            ss = if (idx != -1) {
                mes.append("${zz.substring(0, idx)}".trimIndent())
                ss.substring(idx + 1)
            } else {
                mes.append(ss)
                return
                }
            }
        }
    fun generateToken(): String? {
        return generateToken(CoreValues.SessionTokenLength)
        }
    fun generateToken(sz: Int): String? {
        val cc = CharArray(sz)
        for (i in cc.indices) {
            val k = (62 * Math.random()).toInt()
            if (k < 26) cc[i] = ('A'.code + k).toChar() else if (k < 52) cc[i] =
                ('a'.code + k - 26).toChar() else cc[i] = ('0'.code + k - 52).toChar()
            }
        return String(cc)
        }
    fun timeInMinToString(time: Int): String? {
        return if (time / (60 * 24) == 0) String.format("%2d:%2d", time / 60, time % 60) else String.format("%d:%2d", time / 60, time % 60)
        }
    fun timeInSecToString(time: Int): String? {
        return if (time / 3600 == 0) String.format("%2d:%2d", time / 60, time % 60) else String.format("%2d:%2d:%2d", time / 3600, time / 60 % 60, time % 60)
        }
    fun getPID(): Long {
        val processName = ManagementFactory.getRuntimeMXBean().name
        return if (processName != null && processName.length > 0) {
            try {
                processName.split("@".toRegex()).toTypedArray()[0].toLong()
                } catch (e: Exception) { 0 }
            }
        else 0
        }
    fun nDigits(vv: Int, ndig: Int): String? {
        var vv = vv
        var ndig = ndig
        var out = ""
        while (ndig-- != 0) {
            out = (vv % 10 + '0'.code).toChar().toString() + out
            vv /= 10
            }
        return out
        }
    fun httpError(res: Response<*>): String? {
        var ss: String? = res.message() + " (" + res.code() + ") "
        try {
            ss += res.errorBody()!!.string()
            } catch (e: IOException) {}
        return ss
        }
    fun createFatalMessage(ee: Throwable): String? {
        return createFatalMessage(ee, CoreValues.FatalExceptionStackSize)
        }
    fun createFatalMessage(ee: Throwable, stackSize: Int): String? {
        var ss = "$ee".trimIndent()
        val dd = ee.stackTrace
        var i = 0
        while (i < dd.size && i < stackSize) {
            ss += "${dd[i].className}.${dd[i].methodName}:${dd[i].lineNumber}".trimIndent()
            i++
            }
        return "Программная ошибка:\n$ss"
        }
    fun printFatalMessage(ee: Throwable) {
        println(createFatalMessage(ee))
        }
    fun whenDO(ss: String): String? {
        if (ss.length == 0) return ss
        val idx = ss.length
        val cc = ss[idx - 1]
        return when (cc) {
            'а' -> ss.substring(0, idx - 1) + 'у'
            'о' -> ss
            'й' -> ss.substring(0, idx - 1) + 'я'
            'я' -> ss.substring(0, idx - 1) + 'ю'
            else -> ss + 'a'
            }
        }
    @JvmStatic
    fun main(a: Array<String>) {
        println(whenDO("Иванов"))
        println(whenDO("Зуева"))
        println(whenDO("Евгений"))
        println(whenDO("Петр"))
        println(whenDO("Мария"))
        println(whenDO("Семенович"))
    }
}