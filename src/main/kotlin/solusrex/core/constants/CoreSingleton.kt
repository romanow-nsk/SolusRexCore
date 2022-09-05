package solusrex.core.constants

import solusrex.core.common.ErrorList
import solusrex.core.common.UniException
import solusrex.core.constants.values.CoreValues
import solusrex.core.constants.values.ScriptValues
import solusrex.core.dao.access.DAOAccessFactory
import solusrex.core.entity.EntityField
import solusrex.core.entity.EntityIndexedFactory

//------- Singleton констант ----------------------------------------
class CoreSingleton {
    private val constMap = ConstMap()               // Константы ядра
    val entityFactory = EntityIndexedFactory()      // Фабрика таблиц DAO
    val errorsMap = HashMap<String, Int>()          // Накопленные ошибки DAO
    val errors  : ErrorList = ErrorList()           // Ошибки инициализации
    val daoAccessFactory = DAOAccessFactory()
    constructor() {
        println("Инициализация Values")
        constMap.clear()
        constMap.createConstList(CoreValues::class.java)
        constMap.createConstList(ScriptValues::class.java)
        daoAccessFactory.init(errors)
        }
    //----------- Фиксация ошибок DAO ---------------------------------
    private fun error(prefix: String, ff: EntityField) {
        val key = this.javaClass.simpleName + "." + prefix + ff.name
        val map = CoreSingleton.get().errorsMap
        val vv: Int? = map.get(key)
        if (vv == null) {
            println("$key отсутствует")
            map.put(key, 1)
            }
        else{
            val vv2 = vv + 1
            map.put(key, vv2)
            if (vv2 % CoreValues.noFieldErrorCount == 0) println("$key отсутствует =$vv2")
            }
        }
    //--------------------------------------------------------------------------------
    public companion object {
        //-------------------- Синглетон ---------------------------------------------
        private var one: CoreSingleton? = null
        @JvmStatic
        fun get(): CoreSingleton {
            if (one == null)
                one = CoreSingleton()
            return one!!                                    // !!!!!!!!!!!!!!!!!!!!
            }
        //-------------------------------------------------------------------------------
        @JvmStatic
        fun main(a: Array<String>) {
            val core = CoreSingleton.get()
            println(core.constMap.constAll)
            println(core.errors.toString())
        }
    }
}