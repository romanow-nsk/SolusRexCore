package solusrex.core.constants

class ConstList(val group : String?="") : ArrayList<ConstValue>() {
    fun ConstList(){}
    fun getValuesList(fgroup: String?): ArrayList<ConstValue> {
        val out = ArrayList<ConstValue>()
        for (cc in this)
            if (cc.groupName.equals(fgroup))
                out.add(cc)
        return out
        }
    override fun toString():String{
        var ss = ""
        for(cc in this)
            ss += "$cc\n"
        return ss
        }
    companion object {
        @JvmStatic
        fun main(a: Array<String>) {
            val cc = ConstList()
            cc.add(ConstValue("group","name","title","class",125))
            cc.add(ConstValue("group","name2","title2","class2",126))
            println(cc)
            }
        }
}