package solusrex.core.baseenities.place

import solusrex.core.constants.CoreSingleton
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityLink

class Street : Entity {
    var name = ""
    var type: Int = CoreValues.SStreet
    val city: EntityLink<City?> = EntityLink(City::class.java)
    val location = GPSPoint()
    constructor() {}
    constructor(nm: String) {
        name = nm
        }
    constructor(nm: String, tp: Int) {
        name = nm
        type = tp
        }
    constructor(nm: String, tp: Int, cityId: Long) {
        name = nm
        type = tp
        city.oid = cityId
        }
    fun load(src: Street) {
        name = src.name
        type = src.type
        }
    fun setCity(city0: City?): Street? {
        city.setOidRef(city0)
        return this
        }
    fun streetType(): Int {
        return type and 0x0F0 shr 4
        }
    fun cityType(): Int {
        return city.ref!!.type
        }
    fun getTitle(): String? {
        return toString()
        }
    fun typeName() : String{
        val ss = CoreSingleton.get().constMap.getConstValue("StreetType", streetType())
        if (ss!=null)
            return ss.title!!
        else
            return ""
        }
    override fun toString(): String {
        return city.title() + ":" + if (streetType() == 0) "" else typeName() + name
        }
    override fun toFullString(): String {
        return super.toFullString() + city.toFullString().toString() + ":" + if (streetType() == 0) "" else typeName() + name
        }
    override fun toShortString(): String {
        return if (streetType() == 0) "" else typeName() + name
        }
}