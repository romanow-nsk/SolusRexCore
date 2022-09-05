package solusrex.core.common

class ErrorList {
    private var errCount = 0
    private var info = ""
    fun addError(ss: String) {
        errCount++
        info += ss.trimIndent()+"\n"
        }
    fun clear() {
        errCount = 0
        info = ""
        }
    fun valid(): Boolean {
        return errCount == 0
        }
    fun addError(two: ErrorList): ErrorList {
        errCount += two.errCount
        if (two.info.length != 0) info += two.info
        return this
        }
    fun addInfo(ss: String): ErrorList {
        info += ss.trimIndent()
        return this
        }
    override fun toString(): String {
        return (if (errCount == 0) "" else "Ошибок: $errCount\n") + info
        }
}