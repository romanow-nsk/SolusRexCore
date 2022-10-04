package solusrex.core.entity.notifications

import org.jfree.data.Values
import solusrex.core.baseenities.OwnDateTime
import solusrex.core.baseenities.artifact.Artifact
import solusrex.core.baseenities.user.User
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityLink

class NTMessage : Entity {
    private var user: EntityLink<User> = EntityLink(User::class.java) // Для техника, id по таблице USERов
    private var artifact: EntityLink<Artifact> = EntityLink(Artifact::class.java) // Связанный артефакт
    var message = "" // Текст сообщения
    var header = "" // Заголовок сообщения
    var userSenderType: Int = CoreValues.NSUndefined // Тип (роль) отправителя
    var userReceiverType: Int = CoreValues.NSUndefined // Тип (роль) приемника
    var param: Long = 0 // id сущности или индекс формы для МК
        private set
    var entityName = "" // 656 - имя класса прикрепленного объекта
    private var sndTime: OwnDateTime = OwnDateTime() // Дата/время отправки
    var recTime: OwnDateTime = OwnDateTime() // Дата/время получения
    var executeMode: Int = CoreValues.NMUserAck // Важное (не сбрасывается при просмотре, выполняется сразу)
        private set
    var state: Int = CoreValues.NSSend // Состояние приема

    //==========================================================================================
    var type: Int = CoreValues.NSUndefined // Тип уведомления

    constructor(two: NTMessage) {
        user = two.user
        artifact = two.artifact
        message = two.message
        header = two.header
        userSenderType = two.userSenderType
        userReceiverType = two.userReceiverType
        param = two.param
        executeMode = two.executeMode
        type = two.type
        state = two.state
        user.oid = two.getUser().oid
        artifact.oid = two.getArtifact().oid
        }

    // 656 String ss = user.getTitle()+" "+ CoreValues.NTypes[type]+": "+sndTime.timeToString()+" "+header;
    val title: String
        get() =// 656 String ss = user.getTitle()+" "+ CoreValues.NTypes[type]+": "+sndTime.timeToString()+" "+header;
            (((CoreValues.title(
                "User",
                userSenderType
            ) + " " + user.title()).toString() + " " + CoreValues.title(
                "NotificationType",
                type
            )).toString() + ": " + sndTime.timeToString()).toString() + " " + header

    override fun toShortString(): String {
        return ("" + oid + " " + CoreValues.title("NotificationState", state)).toString() + " " + CoreValues.title("NotificationType", type)
    }

    override fun toString(): String {
        // 656 String ss = toShortString();
        var ss = toShortString() + " " + CoreValues.title("User", userSenderType) + "-->" + CoreValues.title(
            "User",
            userReceiverType
        )
        if (user.oid != 0L) ss += " " + user.title()
        ss += """
${sndTime.timeToString()} $header: $message"""
        return ss
    }

    constructor() {}
    constructor(type0: Int, sndType0: Int, recType0: Int, userId: Long, head: String, mes: String) {
        type = type0
        userSenderType = sndType0
        userReceiverType = recType0
        user.oid = userId
        header = head
        message = mes
        }
    constructor(type0: Int, sndType0: Int, recType0: Int, uu: User, head: String, mes: String) {
        type = type0
        userSenderType = sndType0
        userReceiverType = recType0
        user.oid = uu.oid
        header = head
        message = mes
        }
    fun setEntity(name: String, id: Long) {
        entityName = name
        param = id
        }
    fun getUser(): EntityLink<User> {
        return user
        }
    fun getArtifact(): EntityLink<Artifact> {
        return artifact
        }
    fun setParam(dataOid: Long): NTMessage {
        param = dataOid
        return this
        }
    fun getSndTime(): OwnDateTime {
        return sndTime
        }
    fun setSndTime(sndTime: OwnDateTime) {
        this.sndTime = sndTime
        }
    fun setExecuteMode(mode: Int): NTMessage {
        executeMode = mode
        return this
        }
}