package solusrex.core.constants.values

import solusrex.core.constants.CONST
import solusrex.core.constants.ConstMap

public class CoreValues {
    public companion object {
        const val GetAllModeActual = 0      // Только актуальные
        const val GetAllModeTotal = 1       // Все
        const val GetAllModeDeleted = 2     // Удаленные
        const val noFieldErrorCount = 100   // Количество накопленных ошибок ДО ИСКЛЮЧЕНИЯ
        const val DAOAccessPrefix = "solusrex.core.dao.access.DAO"  // Префикс имени класса доступа DAO
        //------------- Аутентификация и сессия ---------------------------------------------------
        const val SessionTokenLength = 32           // Размер сессионного ключа
        const val SessionSilenceTime = 30 * 60      // Время молчания до разрыва сессии (сек)
        const val SessionCycleTime = 30             // Цикл проверки сессией (сек)
        const val ClockCycleTime = 30               // Цикл проверки событий (процессы) (сек)
        const val SessionHeaderName = "SessionToken"// Параметр заголовка - id сессии
        const val JWTSessionSecretKey = "FireFighterTopSecret"
        const val JWTSessionMode = true
        //-----------------------------------------------------------------------------------------
        const val ConsoleLogSize: Int = 1000    // Количество строк лога
        const val CKeepALiveTime = 10           // Интервал времени проверки соединения
        const val DebugTokenPass = "pi31415926" // Пароль отладочного токена
        const val PopupListMaxSize = 25         // Максимальный размер выпадающего списка
        const val ServerRebootDelay = 10        // Задержка сервера при перезагрузке
        const val ConsolePrintPause = 10        // Тайм-аут паузы вывода при завуске сторонней команды
        const val HTTPTimeOut = 60              // Тайм-аут клиента
        const val BackgroundOperationMaxDelay = 300 //
        const val FatalExceptionStackSize = 20  // Стек вызовов при исключении
        const val PopupMessageDelay = 6         // Тайм-аут всплывающего окна
        const val PopupLongDelay = 20           // Тайм-аут всплывающего окна
        const val SparkThreadPoolSize = 10      // Размер буферного пула потоков Spark
        //-------------------- Типы DAO ----------------------------------------------
        @CONST(group = "DAOType", title = "int", className = "int")
        val DAOInt: Int = 0
        @CONST(group = "DAOType", title = "String", className = "String")
        val DAOString: Int = 1
        @CONST(group = "DAOType", title = "double", className = "double")
        val DAODouble: Int = 2
        @CONST(group = "DAOType", title = "boolean", className = "boolean")
        val DAOBoolean: Int = 3
        @CONST(group = "DAOType", title = "short", className = "short")
        val DAOShort: Int = 4
        @CONST(group = "DAOType", title = "long", className = "long")
        val DAOLong: Int = 5
        @CONST(group = "DAOType", title = "String", className = "java.lang.String")
        val DAOString2: Int = 6
        @CONST(group = "DAOType", title = "EntityLink", className = "solusrex.core.entity.EntityLink")
        val DAOEntityLink: Int = 7
        @CONST(group = "DAOType", title = "EntityLinkList", className = "solusrex.core.entity.EntityLinkList")
        val DAOEntityLinkList: Int = 8
        @CONST(group = "DAOType", title = "EntityRefList", className = "solusrex.core.entity.EntityRefList")
        val DAOEntityRefList: Int = 9
        @CONST(group = "DAOType", title = "DAOLink", className = "")
        val DAOLink: Int = 10
        @CONST(group = "DAOType", title = "void", className = "void")
        val DAOVoid: Int = 11
        //------------------------ Типы улиц и нас пунктов -------------------------------------------------
        @CONST(group = "TownType", title = "Город")
        val CTown: Int = 1
        @CONST(group = "TownType", title = "Поселок")
        val CCountry = 2
        @CONST(group = "StreetType", title = "Улица")
        val SStreet = 0x10
        @CONST(group = "StreetType", title = "Проспект")
        val SProspect = 0x20
        @CONST(group = "StreetType", title = "Шоссе")
        val SWay = 0x30
        @CONST(group = "StreetType", title = "Площадь")
        val SPlace = 0x40
        @CONST(group = "HomeType", title = "Дом")
        val HHome = 0x100
        @CONST(group = "HomeType", title = "Корпус")
        val HCorpus = 0x200
        @CONST(group = "OfficeType", title = "Офис")
        val OOffice = 0x1000
        @CONST(group = "OfficeType", title = "Кабинет")
        val OCabinet = 0x2000
        @CONST(group = "OfficeType", title = "Квартира")
        val OQuart = 0x3000
        //----------------------- Операции над EntityLink при Update (Put)---------------------------
        @CONST(group = "Operation", title = "Нет операции")
        val OperationNone = 0
        @CONST(group = "Operation", title = "Добавить")
        val OperationAdd = 1 // Добавить ref и записать oid
        @CONST(group = "Operation", title = "Изменить")
        val OperationUpdate = 2 // Обновить по ref
        //------------- Типы пользователей -----------------------------------------------------
        @CONST(group = "User", title = "Гость")
        val UserGuestType = 0
        @CONST(group = "User", title = "Суперадмин")
        val UserSuperAdminType = 1
        @CONST(group = "User", title = "Администратор")
        val UserAdminType = 2
        //------------------- Вид уведомления  -------------------------------------------------------------------------
        @CONST(group = "NotificationType", title = "Не определено")
        val NTUndefined = 0
        @CONST(group = "NotificationType", title = "Управление клиентом") // Внешнее управление клиентом - param
        val NTUserAction = 1
        @CONST(group = "NotificationType", title = "Действие") // Действие над объектом (class,id)
        val NTObjectAction = 2
        @CONST(group = "NotificationType", title = "Изменение состояния") // Изменение сорстояния клиента - источника
        val NTUserStateChanged = 3
        @CONST(group = "NotificationType", title = "Сообщение") // Сообщение от клиента - источника
        val NTUserMessage = 4
        @CONST(group = "NotificationType", title = "Событие") // Событие на объекте (class,id)
        val NTObjectEvent = 5
        @CONST(group = "NotificationType", title = "Система") //
        val NTSystemEvent = 6
        //------------------- Состояние уведомлнния  -------------------------------------------------------------------------
        @CONST(group = "NotificationState", title = "Не определено")
        val NSUndefined = 0
        @CONST(group = "NotificationState", title = "Передано")
        val NSSend = 1
        @CONST(group = "NotificationState", title = "Просмотрено")
        val NSReceived = 2
        @CONST(group = "NotificationState", title = "В работе")
        val NSInProcess = 3
        @CONST(group = "NotificationState", title = "Закрыто")
        val NSClosed = 4
        //--------------------------------------------------------------------------------------------------
        @JvmStatic
        fun main(a: Array<String>) {
            val map = ConstMap()
            map.createConstList(CoreValues::class.java)
            println(map.constAll)
        }
     }
}