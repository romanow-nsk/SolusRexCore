package solusrex.core.constants

import solusrex.core.baseenities.Account
import solusrex.core.baseenities.Contact
import solusrex.core.baseenities.Phone
import solusrex.core.baseenities.PhoneList
import solusrex.core.common.ErrorList
import solusrex.core.common.Mail
import solusrex.core.constants.values.CoreValues
import solusrex.core.constants.values.ScriptValues
import solusrex.core.dao.TableItem
import solusrex.core.dao.access.DAOAccessFactory
import solusrex.core.entity.EntityField
import solusrex.core.entity.EntityIndexedFactory

//------- Singleton констант ----------------------------------------
class CoreSingleton {
    private val constMap = ConstMap()                   // Константы ядра
    val entityFactory = EntityIndexedFactory()          // Фабрика таблиц DAO
    val errorsMap = HashMap<String, Int>()              // Накопленные ошибки DAO
    val errors  : ErrorList = ErrorList()               // Ошибки инициализации
    val daoAccessFactory = DAOAccessFactory()           // Фабрика объектов доступа DAO
    val prefixMap: HashMap<String, String> = HashMap()  // Фабрика префиксов имен поле для внедренных объектов

    constructor() {
        println("Инициализация Values")
        constMap.clear()
        constMap.createConstList(CoreValues::class.java)
        constMap.createConstList(ScriptValues::class.java)
        daoAccessFactory.init(errors)
        //--------------------------------------------------------------------------------------------------------------
        /*
        entityFactory.put(TableItem("Настройки_0", WorkSettingsBase::class.java))
        entityFactory.put(TableItem("Метка GPS", GPSPoint::class.java))
        entityFactory.put(TableItem("Адрес", Address::class.java))
        entityFactory.put(TableItem("Артефакт", Artifact::class.java))
        entityFactory.put(TableItem("Пользователь", User::class.java))
        entityFactory.put(
            TableItem("Уведомление", NTMessage::class.java)
                .add("type").add("state").add("r_timeInMS").add("s_timeInMS")
                .add("userSenderType").add("userReceiverType").add("param"))
        entityFactory.put(TableItem("Улица", Street::class.java))
        entityFactory.put(TableItem("Нас.пункт", City::class.java))
        entityFactory.put(TableItem("Ошибка", BugMessage::class.java))
        entityFactory.put(TableItem("Персоналия", Person::class.java))
        entityFactory.put(TableItem("Отчет", ReportFile::class.java))
        entityFactory.put(TableItem("Состояние", ServerState::class.java))
        entityFactory.put(TableItem("Подсказка", HelpFile::class.java))
        entityFactory.put(TableItem("Контакт", Contact::class.java, false))
        entityFactory.put(TableItem("Спец.файла", FileNameExt::class.java, false))
        entityFactory.put(TableItem("Дата/время", OwnDateTime::class.java, false))
         */
        entityFactory.put(TableItem("E-mail", Mail::class.java, false))
        entityFactory.put(TableItem("Телефон", Phone::class.java, false))
        entityFactory.put(TableItem("Телефоны", PhoneList::class.java, false))
        entityFactory.put(TableItem("Аккаунт", Account::class.java))
        prefixMap.put("BugMessage.date", "d")
        prefixMap.put("Artifact.date", "d")
        prefixMap.put("Artifact.original", "f")
        prefixMap.put("Person.phone", "p")
        prefixMap.put("Person.mail", "m")
        prefixMap.put("User.phone", "p")
        prefixMap.put("User.mail", "m")
        prefixMap.put("GPSPoint.gpsTime", "a")
        prefixMap.put("Street.location", "s")
        prefixMap.put("Account.loginPhone", "p")
        prefixMap.put("AccountData.loginPhone", "p")
        prefixMap.put("NTMessage.sndTime", "s")
        prefixMap.put("NTMessage.recTime", "r")
        prefixMap.put("Street.location", "s")
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