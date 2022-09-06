package solusrex.core.baseenities.system

import solusrex.core.common.Utility
import solusrex.core.entity.Entity
import javax.swing.text.Utilities

class ServerState : Entity() {
    var isServerRun = false
    var lastMailNumber = 0          // Номер последнего письма
    var isAsteriskMailOn = false    // Вклчюение asterisk-почты
    var isAsrteiskDialOn = false    // Включение asterisk-дозвона
    @get:Synchronized
    @set:Synchronized
    var isLocked = false            // Блокирован для выполнения админ. операций
    @get:Synchronized
    var requestNum = 0              // Кол-во обрабатываемых запросов
        private set
    @get:Synchronized
    @set:Synchronized
    var sessionCount = 0            // Кол-во активных сессий
    private var timeSum: Long = 0
    var timeMin: Long = 0
        private set
    var timeMax: Long = 0
        private set
    var timeCount: Long = 0
        private set
    var pID: Long = 0
        private set
    var releaseNumber = 0
        private set
    var totalGetCount = 0           // Количество операций чтения
    var cashGetCount = 0            // Количество операций чтения из кэша
    var isСashEnabled = false       // Режим кэширования
    @Synchronized
    fun incRequestNum() {
        requestNum++
        }
    @Synchronized
    fun decRequestNum() {
        requestNum--
        if (requestNum < 0) requestNum = 0
        }
    fun addTimeStamp(tt: Long) {
        if (timeCount == 0L || tt > timeMax) timeMax = tt
        if (timeCount == 0L || tt < timeMin) timeMin = tt
        timeSum += tt
        timeCount++
        }
    fun setPid() {
        pID = Utility.getPID()
        releaseNumber = 0  // ValuesBase.env().releaseNumber()  TODO окружение
        }
    val timeMiddle: Long
        get() = if (timeCount == 0L) 0 else timeSum / timeCount
    fun init() {
        timeCount = 0
        timeSum = 0
        timeMax = 0
        timeMin = 0
        }
    override fun toString(): String {
        return "Сервер=" + isServerRun + " Почта=" + isAsteriskMailOn + " Дозвон=" + isAsrteiskDialOn + " Писем=" + lastMailNumber
        }
    }
