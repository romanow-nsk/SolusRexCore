package solusrex.core.dao

import solusrex.core.constants.ConstMap
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.EntityField
import solusrex.core.entity.EntityFoo
import java.lang.reflect.Modifier

class TableItem (){
    var name: String = ""
    var clazz : Class<*>? = null
    var indexes: ArrayList<String> = ArrayList()
    val fields : ArrayList<EntityField> = ArrayList()
    var isExportXLS : Boolean = true
    var isTable: Boolean=true
    constructor(name0: String, clazz0: Class<*>?, indexes0: ArrayList<String>) : this() {
        name = name0
        clazz = clazz0
        indexes = indexes0
        createFields()
        }
    constructor(name: String, clazz: Class<*>?) : this() {
        this.name = name
        this.clazz = clazz
        createFields()
        }
    constructor(name0: String, clazz: Class<*>?, isTable0: Boolean) : this() {
        name = name0
        this.clazz = clazz
        indexes = ArrayList()
        isTable = isTable0
        createFields()
        }
    fun add(ss: String): TableItem {
        indexes.add(ss)
        return this
        }
    fun disableExportXLS(): TableItem {
        isExportXLS = false
        return this
        }
    //------------------------------------------------------------------------------------------------------------------
    private fun createFields() {
        val map = ConstMap()
        map.createConstList(CoreValues::class.java)
        val typeMap = map.getGroupMapByClass("DAOType")
        fields.clear()
        var cls = clazz
        while (cls != DAO::class.java) {
            // Цикл по текущему и базовым
            val flds = cls!!.declaredFields //
            for (i in flds.indices) {          // Перебор всех полей
                flds[i].isAccessible = true // Сделать доступными private-поля
                if (flds[i].modifiers and Modifier.TRANSIENT != 0) continue
                if (flds[i].modifiers and Modifier.STATIC != 0) continue
                val tname = flds[i].type.name
                val typeDef = typeMap.get(tname)
                val name = flds[i].name
                if (typeDef == null) {
                    if (isDAOClass(flds[i].type)) {
                        fields!!.add(EntityField(name, CoreValues.DAOLink, flds[i]))
                        }
                    continue
                    }
                fields!!.add(EntityField(name, typeDef.value, flds[i]))
                }
            cls = cls.superclass
            }
        }
    override fun toString():String{
        var out = ""
        for(ss in fields){
            out += ss.toString()+"\n"
            }
        return out
        }
    companion object {
        fun isDAOClass(cls: Class<*>?): Boolean {
            var cls = cls
            while (cls != null) {
                // Цикл по текущему и базовым
                if (cls == DAO::class.java) return true
                cls = cls.superclass
                }
            return false
            }
        //----------------------------------------------------------------------------------------------------------
        @JvmStatic
        fun main(ss: Array<String>) {
            println(TableItem(EntityFoo::class.java.simpleName, EntityFoo::class.java))
        }
    }
}