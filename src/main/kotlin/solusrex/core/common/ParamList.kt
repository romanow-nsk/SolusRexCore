package solusrex.core.common

class ParamList : HashMap<String?, String?>() {
    fun add(name: String?, value: String?): ParamList {
        put(name, value)
        return this
        }
    fun add(name: String?, value: Int): ParamList {
        put(name, "" + value)
        return this
        }
    fun add(name: String?, bb: Boolean): ParamList {
        put(name, "" + if (bb) 1 else 0)
        return this
        }
    @Throws(UniException::class)
    fun getParam(name: String): String {
        return get(name) ?: throw UniException.user("Нет параметра настроек $name")!!
        }
    @Throws(UniException::class)
    fun getParamInt(name: String): Int {
        val ss = get(name) ?: throw UniException.user("Нет параметра настроек $name")!!
        return try {
            ss.toInt()
            } catch (ee: Exception) {
                throw UniException.user("Нечисловое значение параметра $name")!!
                }
        }
    @Throws(UniException::class)
    fun getParamBoolean(name: String): Boolean {
        val ss = get(name) ?: throw UniException.user("Нет параметра настроек $name")!!
        return try {
            val vv = ss.toInt()
            vv != 0
            }catch (ee: Exception) {
                throw UniException.user("Недопустимое значение параметра $name")!!
                }
        }
    fun testParam(name: String?): Boolean {
        return get(name) != null
        }
    }