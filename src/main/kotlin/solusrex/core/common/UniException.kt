package solusrex.core.common

import solusrex.core.constants.CONST
import solusrex.core.constants.ConstMap
import solusrex.core.constants.values.CoreValues
import java.io.IOException
import java.sql.SQLException

public class UniException(var exType : Int=0, var sysMessage : String? = "", var userMessage : String? = "") : Exception() {
    override fun toString(): String {
        return "${exceptLevel[exType]}:$userMessage\n$sysMessage"
        }
    constructor(type: Int, message: String?, ee: Throwable?, stackTrace: Boolean) : this() {
        exType = type
        userMessage = message
        sysMessage = ""
        if (ee == null) return
        sysMessage = """${ee.message} $ee""".trimIndent()
        if (!stackTrace) return
        val dd = ee.stackTrace
        var i = 0
        while (dd != null && i < dd.size) {
            sysMessage += "\n${dd[i].className}.${dd[i].methodName}:${dd[i].lineNumber}"
            i++
            }
        }
    constructor(type: Int, message: String?) : this() {
        UniException(type, message, "") }
    constructor(type: Int) : this() {
        UniException(type, "", "")
        }
    companion object {
        //---------------- Уровни исключений --------------------------------------------------------------
        @CONST(group = "UniEx", title = "Сценарий")
        val warning: Int = 0
        @CONST(group = "UniEx", title = "Исполнение кода")
        const val runTime = 1
        @CONST(group = "UniEx", title = "Програмный баг")
        const val bug = 2
        @CONST(group = "UniEx", title = "Фатальная ошибка")
        const val fatal = 3
        private val exceptLevel = arrayOf(
            "Сценарий",
            "Исполнение кода",
            "Программный баг",
            "Фатальная ошибка"
            )
        //----------------- Типовые исключения -------------------------------------------------------------
        const val sysCode = "Ошибка кода исполнительной системы"
        const val sql = "Ошибка базы данных"
        const val net = "Ошибка сети"
        const val api = "Ошибка запроса сервера данных"
        const val format = "Ошибка формата даннных"
        const val noFunc = "Функция не реализована"
        const val other = "Прочие ошибки"
        const val indirect = "Ошибка удаленной компоненты"
        const val settings = "Ошибка настроек"
        const val io = "Ошибка в/в"
        const val userData = "Ошибка пользователя"
        const val userCode = "Ошибка кода пользователя"
        const val userVars = "Ошибка данных пользователя"
        //--------------------------------------------------------------------------------------------------
        fun fatal(mes: String?, ee: Throwable?) : UniException {
            return UniException(bug, mes, ee, true)
            }
        fun fatal(ee: Throwable?): UniException? {
            return UniException(bug, sysCode, ee, true)
            }
        fun fatal(mes: String): UniException? {
            return UniException(bug, mes, null, false)
            }
        fun warning(mes: String?): UniException? {
            return UniException(warning, mes)
            }
        fun sql(ee: Throwable?): UniException? {
            return UniException(bug, sql, ee, false)
            }
        fun sql(mes: String?): UniException? {
            return UniException(bug, sql, mes)
            }
        fun net(ee: Throwable?): UniException? {
            return return UniException(runTime, net, ee, false)
            }
        fun net(mes: String?): UniException? {
            return UniException(runTime, net, mes)
            }
        fun api(ee: Throwable?): UniException? {
            return UniException(runTime, api, ee, false)
            }
        fun api(mes: String?): UniException? {
            return UniException(runTime, api, mes)
            }
        fun format(mes: String?): UniException? {
            return UniException(bug, format, mes)
            }
        fun userFormat(mes: String?): UniException? {
            return UniException(bug, format, mes)
            }
        fun user(ee: Throwable?): UniException? {
            return UniException(warning, userData, ee, true)
            }
        fun noFunc(): UniException? {
            return UniException(bug, noFunc)
            }
        fun noFunc(mes: String?): UniException? {
            return UniException(bug, noFunc, mes)
            }
        fun bug(mes: String?): UniException? {
            return UniException(bug, sysCode, mes)
            }
        fun bug(ee: Throwable?): UniException? {
            return UniException(bug, sysCode, ee, false)
            }
        fun user(mes: String?): UniException? {
            return UniException(warning, userData, mes)
            }
        fun code(mes: String?): UniException? {
            return UniException(runTime, userCode, mes)
            }
        fun vars(mes: String?): UniException? {
            return UniException(runTime, userVars, mes)
            }
        fun other(): UniException? {
            return UniException(bug, other)
            }
        fun other(ee: Throwable?): UniException? {
            return UniException(bug, other, ee, true)
            }
        fun other(mes: String?): UniException? {
            return UniException(bug, mes)
            }
        fun indirect(mes: String?): UniException? {
            return UniException(runTime, indirect, mes)
            }
        fun config(mes: String?): UniException? {
            return UniException(warning, settings, mes)
            }
        fun io(ee: Throwable?): UniException? {
            return UniException(runTime, io, ee, true)
            }
        fun io(mes: String?): UniException? {
            return UniException(runTime, io, mes)
            }
        fun total(ee: Throwable): UniException? {
            if (ee is UniException) {
                val vv: UniException = ee as UniException
                vv.sysMessage = vv.userMessage + ":" + vv.sysMessage
                vv.userMessage = indirect
                return vv
                }
            return (ee as? SQLException)?.let { sql(it) }
                ?: ((ee as? IOException)?.let { io(it) } ?: ((ee as? Error)?.let { fatal(it) } ?: other(ee)))
            }
        //---------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------
        @JvmStatic
        fun main(a: Array<String>) {
            val ss:String? = null
            try {
                ss!!.length
                } catch (ee : Exception){
                    println(UniException.fatal(ee).toString());
                    }
            }
    }
}