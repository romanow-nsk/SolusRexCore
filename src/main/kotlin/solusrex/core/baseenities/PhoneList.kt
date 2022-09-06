package solusrex.core.baseenities

import java.util.*

class PhoneList : Contact() {
    @Transient var ready = false
    @Transient
    private val list = ArrayList<Phone>()
    open fun PhoneList() {}
    open fun innerParse(): Boolean {
        return innerParse(value!!)
        }
    open fun innerParse(zz: String): Boolean {
        var zz: String? = zz
        if (ready) return true
        if (zz == null) zz = "" // При десериализации отсуствующих полей
        zz = zz.trim { it <= ' ' }
        list.clear()
        val tt = StringTokenizer(zz, ",")
        var ss: String? = null
        while (tt.hasMoreElements()) {
            ss = tt.nextToken()
            val xx = Phone(ss)
            if (!xx.valid()) return false
            list.add(xx)
            }
        value = zz
        ready = true
        return true
        }
    open fun parse(ss: String): Boolean {
        ready = false
        return innerParse(ss)
        }
    open fun test() {
        if (ready) return
        innerParse()
        }
    open fun refresh() {
        ready = false
        innerParse(value!!)
        }
    open fun refreshBack() {
        value = ""
        if (list.size == 0) {
            return
            }
        for (i in list.indices) {
            if (i != 0) value += ","
            value += list[i].value
            }
        }
    open fun remove(pp: Phone): Boolean {
        test()
        for (i in list.indices) if (list[i].value.equals(pp.value)) {
            list.removeAt(i)
            refreshBack()
            return true
            }
        return false
        }
    open fun clear() {
        value = ""
        list.clear()
        }
    open fun getList(): ArrayList<Phone>? {
        innerParse()
        return list
        }
    open fun addPhone(phone: Phone): Boolean {
        if (!phone.valid()) return false
        test()
        for (pp in list) {
            if (pp.value.equals(phone.value)) return false
            }
        if (value!!.length !== 0) value += ","
        value += phone.value
        list.add(phone)
        return true
        }
    companion object{
        @JvmStatic
        open fun main(ss: Array<String>) {
            val pp = PhoneList()
            pp.addPhone(Phone("9139449081"))
            pp.addPhone(Phone("+79131111111"))
            pp.addPhone(Phone("89132222222"))
            println(pp.toString() + " " + pp.getList()!!.size)
            pp.addPhone(Phone("89132222222"))
            println(pp.toString() + " " + pp.getList()!!.size)
            pp.refresh()
            println(pp.toString() + " " + pp.getList()!!.size)
            }
        }
    }