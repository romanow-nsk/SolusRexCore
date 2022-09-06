package solusrex.core.constants

import java.lang.reflect.Modifier
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ConstMap : HashMap<String?, ConstGroup?>() {
    fun title(group: String, constId: Int): String? {
        val gmap = get(group) ?: return "???: $group"
        val ss = gmap[constId]
        return if (ss != null) ss.title else "???: $group:$constId"
        }
    fun put(group: String?, constId: Int, title: String?, className: String?, constName: String?) {
        var gmap = get(group)
        if (gmap == null) {
            gmap = ConstGroup(group)
            put(group, gmap)
            }
        gmap.put(ConstValue(group, constName, title, className, constId))
        }
    fun createConstList(cl:Class<*>) {
        //val oo = try {
        //    cl.newInstance()
        //    } catch (e:Exception) {
        //        Object()
        //        }
        val fields = cl!!.declaredFields
        for (fd in fields) {
            if (fd.modifiers and Modifier.STATIC == 0) continue
            fd.isAccessible = true // Сделать доступными private-поля
            val mname = fd.name
            if (fd.isAnnotationPresent(CONST::class.java)) {
                val about = fd.getAnnotation(CONST::class.java) as CONST
                var vv = 0
                vv = try {
                    fd.getInt(null)
                    } catch (e: Exception) {
                        0
                        }
                //System.out.println(about.group()+":"+mname + " ="+vv+" "+about.title());
                put(about.group, vv, about.title, about.className, mname)
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    fun getValuesList(gName: String?): ConstList {
        val gmap = get(gName) ?: return ConstList()
        val list = gmap.createList()
        list.sortWith(java.util.Comparator { o1, o2 -> o1.value - o2.value })
        return list
        }

    fun getGroupMapByValue(gName: String?): HashMap<Int, ConstValue> {
        val list = getValuesList(gName)
        val map = HashMap<Int, ConstValue>()
        for (cc in list) map[cc.value] = cc
        return map
        }

    fun getGroupList(gName: String?): ArrayList<ConstValue> {
        val map = getGroupMapByValue(gName)
        val oo: Array<Any> = map.values.toTypedArray()
        val out = ArrayList<ConstValue>()
        for (vv in oo) out.add(vv as ConstValue)
        out.sortWith(java.util.Comparator { o1, o2 -> o1.value - o2.value })
        return out
        }

    fun getGroupMapByName(gName: String?): HashMap<String, ConstValue> {
        val list = getValuesList(gName)
        val map = HashMap<String, ConstValue>()
        for (cc in list) map.put(cc.name!!,cc)
        return map
        }

    fun getGroupMapByClass(gName: String?): HashMap<String, ConstValue> {
        val list = getValuesList(gName)
        val map = HashMap<String, ConstValue>()
        for (cc in list) map.put(cc.className!!,cc)
        return map
        }
    fun getGroupMapByTitle(gName: String?): HashMap<String, ConstValue> {
        val list = getValuesList(gName)
        val map = HashMap<String, ConstValue>()
        for (cc in list) map.put(cc.title!!,cc)
        return map
        }

    override fun toString(): String {
        var out = ""
        for (gg in keys) out += get(gg).toString()
        return out
        }
    fun getConstValue(gName : String?, key :Int) : ConstValue?{
        val gmap = get(gName)
        if (gmap==null)
            return null;
        return gmap.get(key)
        }
    fun constAll(): ConstList{
            val out = ConstList()
            for (gg in keys) {
                val group = get(gg)
                val list = ConstList(group!!.group)
                for (vv in group.createList()) out.add(vv)
                }
            return out
            }
    fun constByGroups(): ArrayList<ConstList>{
            val out = ArrayList<ConstList>()
            for (gg in keys) {
                val group = get(gg)
                val list = ConstList(group!!.group)
                for (vv in group.createList()) list.add(vv)
                out.add(list)
                }
            return out
        }
    }