package solusrex.core.baseenities.user

import solusrex.core.baseenities.contact.Phone
import solusrex.core.baseenities.contact.Mail
import solusrex.core.common.Utility
import solusrex.core.entity.Entity

open class Person : Entity {
    var lastName = ""       //Фамилия
    var firstName = ""      // Имя
    var middleName = ""     // Отчество
    var phone: Phone = Phone()
    var mail: Mail = Mail()
    var post = "" // Должность
    //-----------------------------------------------------------------------------------------------------
    constructor() {}
    constructor(nm1: String, nm2: String, nm3: String, post0: String, phone0: String, mail0: String) : this(nm1, nm2, nm3) {
        post = post0
        phone = Phone(phone0)
        mail = Mail(mail0)
        }
    constructor(nm1: String, nm2: String, nm3: String) {
        lastName = nm1
        firstName = nm2
        middleName = nm3
        }
    fun valid(): Boolean {
        return lastName.length != 0
        }
    fun fullUserName(): String {
        var out = lastName
        if (firstName.length == 0) return out
        out += " $firstName"
        if (middleName.length == 0) return out
        out += " $middleName"
        return out
        }
    fun fullUserNameWhen(): String {
        var out: String =Utility.whenDO(lastName)!!
        if (firstName.length == 0) return out
        out += " " + Utility.whenDO(firstName)
        if (middleName.length == 0) return out
        out += " " + Utility.whenDO(middleName)
        return out
        }
    override fun title(): String {
        return shortUserName() + "," + post + "," + phone.toString() + "," + mail.toString()
        }
    fun personTitle(): String{
        return shortUserName() + "," + post + "," + phone.toString() + "," + mail.toString()
        }
    override fun toString(): String {
        return "$lastName $firstName $middleName [$post,$phone,$mail] "
        }
    override fun toFullString(): String {
        return super.toFullString() + lastName + " " + firstName + " " + middleName + " [" + post + "," + phone + "," + mail + "] "
        }
    fun shortUserName(): String {
        var out = lastName
        if (firstName.length == 0) return out
        out += " " + firstName.substring(0, 1) + "."
        if (middleName.length == 0) return out
        out += " " + middleName.substring(0, 1) + "."
        return out
        }
    fun load(proto: Person) {
        firstName = proto.firstName
        lastName = proto.lastName
        middleName = proto.middleName
        phone = Phone(proto.phone.toString())
        mail = Mail(proto.mail.toString())
        post = proto.post
        }
    override fun objectName(): String {
        return shortUserName()
        }
    }