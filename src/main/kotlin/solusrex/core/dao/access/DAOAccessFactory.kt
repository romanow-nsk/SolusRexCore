package solusrex.core.dao.access

import solusrex.core.common.ErrorList
import solusrex.core.common.UniException
import solusrex.core.constants.ConstMap
import solusrex.core.constants.values.CoreValues

class DAOAccessFactory {
    val classMap : HashMap<Int?,I_DAOAccess> = HashMap()
    fun DAOAccessFactory(){}
    fun init(errorList : ErrorList){
        val map:ConstMap = ConstMap()
        map.createConstList(CoreValues::class.java)
        val list = map.getValuesList("DAOType")
        for(constValue in list){
            val className = CoreValues.DAOAccessPrefix+constValue.className
            try {
                classMap.put(constValue.value, Class.forName(className).newInstance() as I_DAOAccess)
                } catch (ee : Exception){
                    errorList.addError("Отсутствует класс доступа DAO для "+constValue.className)
                    }
            }
    }
}