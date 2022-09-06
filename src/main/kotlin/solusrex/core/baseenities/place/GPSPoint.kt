package solusrex.core.baseenities.place

import solusrex.core.baseenities.OwnDateTime
import solusrex.core.entity.EntityBack

class GPSPoint : EntityBack {
    var state = GeoNone
    var geoy = 0.0 // Широта
    var geox = 0.0 // Долгота
    var gpsTime: OwnDateTime = OwnDateTime()
    constructor() {
        state = GeoNone
        }
constructor(y0: String, x0: String, exact: Boolean) {
        try {
            geoy = fromStr(y0)
            geox = fromStr(x0)
            state = if (exact) GeoGPS else GeoNet
            } catch (ee: Throwable) {
                state = GeoNone
                }
        }
    constructor(y0: Double, x0: Double, exact: Boolean) {
        geoy = y0
        geox = x0
        state = if (exact) GeoGPS else GeoNet
        }
    constructor(y0: Double, x0: Double, exact: Boolean, timeMs: Long) {
        geoy = y0
        geox = x0
        state = if (exact) GeoGPS else GeoNet
        gpsTime = OwnDateTime(timeMs)
        }
    fun elapsedTimeInSec(): Int {
        return gpsTime.elapsedTimeInSec()
        }
    constructor(crd: String, exact: Boolean) {
        setCoord(crd, exact)
        }
    fun gpsValid(): Boolean {
        return state != GeoNone
        }
    fun setCoord(y: Double, x: Double, exact: Boolean) {
        geox = x
        geoy = y
        state = if (exact) GeoGPS else GeoNet
        }
    fun copy(): GPSPoint {
        val out = GPSPoint(geoy, geox, true)
        out.state = state
        out.gpsTime = OwnDateTime(gpsTime.timeInMS())
        return out
        }
    fun toStrX(): String {
        return if (state == GeoNone) "" else toStr(geox)
        }
    fun toStrY(): String {
        return if (state == GeoNone) "" else toStr(geoy)
        }
    fun copy(src: GPSPoint) {
        geox = src.geox
        geoy = src.geoy
        state = src.state
        }
    fun diff(T: GPSPoint): Int {        // В метрах
        if (state == GeoNone || T.state == GeoNone) return -1
        val dx = (geox - T.geox) * gradus * 1000 * Math.cos(Math.PI * geoy / 180)
        val dy = (geoy - T.geoy) * gradus * 1000
        return Math.sqrt(dy * dy + dx * dx).toInt()
        }
    override fun toString(): String {
        return if (state == GeoNone) "" else toStr(geoy) + "," + toStr(geox) + "[" + gpsTime + "]"
        }
    override fun toShortString(): String {
        return if (state == GeoNone) "" else toStr(geoy) + "," + toStr(geox)
        }
    override fun toFullString(): String {
        return super.toFullString() + toShortString()
        }
    override fun  title(): String{
        return if (state == GeoNone) "" else toStr(geoy) + "," + toStr(geox)
        }
    fun moveY(dd: Double) {
        geoy += dd / 1000 / gradus
        }
    fun moveX(dd: Double) {
        geox += dd / 1000 / gradus / Math.cos(Math.PI * geoy / 180)
        }
    fun setCoord(crd: String, exact: Boolean) {
        state = GeoNone
        val k = crd.indexOf(",")
        if (k == -1) return
        var v1 = 0.0
        var v2 = 0.0
        try {
            v1 = crd.substring(0, k).trim { it <= ' ' }.toDouble()
            v2 = crd.substring(k + 1).trim { it <= ' ' }.toDouble()
            setCoord(v1, v2, exact)
            } catch (ee: Throwable) {
                state = GeoNone
                }
        }
    fun toStringValue(): String {
        return toShortString() + "|" + gpsTime.timeInMS()
        }
    @Throws(Exception::class)
    fun parseValue(ss: String) {
        val idx = ss.indexOf("|")
        if (idx == -1) return
        setCoord(ss.substring(0, idx), true)
        gpsTime = OwnDateTime(ss.substring(idx + 1).toLong())
        }
    companion object {
        const val GeoNone = 0   // Координаты недоступны
        const val GeoNet = 1    // Координаты от сети (вышек)
        const val GeoGPS = 2    // Координаты от GPS
        const val gradus = 111.12
        //----------- Конвертирование в формат ggmm.xxxxx (градусы-минуты-дробная часть)
        @Throws(Throwable::class)
        fun fromStr(ss: String): Double {
            var dd = 0.0
            dd = ss.toDouble()
            return dd
            }
        fun toStr(dd: Double): String {
            val ss = "" + dd
            var k = ss.indexOf(".") + 5
            if (k > ss.length) k = ss.length
            return ss.substring(0, k)
            }
        fun convert(dd: Double): String {
            var dd = dd
            var ss = "" + dd.toInt()
            dd -= dd.toInt().toDouble()
            dd *= 60.0
            val xx = dd.toInt()
            ss += if (xx < 10) "0$xx" else "" + xx
            val s2 = "" + dd
            val k = s2.indexOf(".")
            ss += s2.substring(k)
            return ss
            }
        }
}
