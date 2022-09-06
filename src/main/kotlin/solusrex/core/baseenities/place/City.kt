package solusrex.core.baseenities.place

import solusrex.core.constants.CoreSingleton
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.Entity

class City : Entity {
    var name = ""
    var type: Int = CoreValues.CTown
    constructor() {}
    constructor(nm: String) {
        name = nm
        }
    constructor(nm: String, tp: Int) {
        name = nm
        type = tp
        }
    fun load(src: City) {
        name = src.name
        type = src.type
        }
    fun cityType(): Int {
        return type and 0x0F
        }
    fun typeName() : String{
        val ss = CoreSingleton.get().constMap.getConstValue("TownType", cityType())
        if (ss!=null)
            return ss.title!!
        else
            return ""
            }
    override fun toFullString(): String {
        return super.toFullString() + (if (cityType() == 0) "" else typeName()) + name
        }
    override fun toString(): String {
        return typeName()+" " + name
        }
    override fun objectName(): String {
        return name
        }
}