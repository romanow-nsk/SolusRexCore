package solusrex.core.constants

class ConstValue(var groupName:String?="", var name:String?="", var title:String?="...", var className:String?="", var value:Int=0) {
    // private var groupName = ""   // Группа констант
    // private var name = ""        // Имя в VALUES
    // private var title = "..."    // Название по-русски
    // private var className = ""   // Имя класса сущности
    // private var value = 0        // Значение
    fun toFullString(): String? {
        return "$groupName.$name[$title]=$value"
        }
    override fun toString(): String {
        return "$groupName::$name[$title]=$value"
        }
    companion object {
        @JvmStatic
        fun main(a: Array<String>) {
            var cc = ConstValue("group","name","title","class",125)
            println(cc)
            }
        }
}
