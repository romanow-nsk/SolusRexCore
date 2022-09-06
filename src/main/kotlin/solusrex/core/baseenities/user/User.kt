package solusrex.core.baseenities.user

import solusrex.core.baseenities.artifact.Artifact
import solusrex.core.baseenities.contact.Phone
import solusrex.core.common.UniException
import solusrex.core.constants.CoreSingleton
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.EntityLink

class User : Person {
    var typeId: Int = CoreValues.UserGuestType
    var accountData: EntityLink<Account> = EntityLink()                 // Загружается по API
    var photo: EntityLink<Artifact> = EntityLink(Artifact::class.java)  // Фотография
    var secondTableId: Long = 0                                         // ID для производного класса
    var sessionToken = ""
    var simCardICC = ""
    fun typeName(): String {
        val ss = CoreSingleton.get().constMap.getConstValue("User", typeId)
        if (ss!=null)
            return ss.title!!
        else
            return ""
        }
    constructor() :
         this(CoreValues.UserGuestType, "", "", "", "", "", ""){}
    constructor(log: String, loginPh: String, pass: String) :
        this(CoreValues.UserGuestType, "", "", "", log, pass, loginPh) {}
    constructor(typeId0: Int, nm1: String, nm2: String, nm3: String, log: String, pass: String, loginPh: String){
        firstName = nm1
        lastName = nm2
        middleName = nm3
        typeId = typeId0
        phone = Phone(loginPh)
        accountData.ref = Account(log, loginPh, pass)
        }
    fun getAccount(): Account {
        return if (accountData.ref != null)
            return accountData.ref!!
        else
            return Account()
        }
    fun load(proto: User) {
        oid = proto.oid
        //account = new Account(proto.getAccount());
        accountData.ref = proto.getAccount()
        lastName = proto.lastName
        firstName = proto.firstName
        middleName = proto.middleName
        }

    fun getTitle(): String? {
        return shortUserName()
        }

    fun getHeader(): String? {
        return shortUserName() + " [" + typeName() + "]"
        }
    override fun toString(): String {
        return typeName() + " " + super.toString() + photo + " " + getAccount()
        }
    override fun toFullString(): String {
        return super.toFullString() + typeName() + " " + super.toString() + getAccount() + " " + photo
        }
    fun getLogin(): String? {
        return getAccount().login
        }
    fun getLoginPhone(): String? {
        return getAccount().getLoginPhone()
        }
    fun loginPhoneValid(): Boolean {
        return getAccount().loginPhoneValid()
        }
    fun setAccount(account0: Account) {
        accountData.ref = Account(account0)
        }
    fun setPhoto(photo0: Artifact?) {
        photo.ref=photo0
        }
    fun getPassword(): String? {
        return getAccount().password
        }
    fun setPassword(ss: String) {
        getAccount().password=ss
        }
    fun setLoginPhone(ss: String) {
        getAccount().setLoginPhone(ss)
        }
    fun setLogin(ss: String) {
        getAccount().login=ss
        }
    companion object{
        @JvmStatic
        @Throws(UniException::class)
        fun main(ss: Array<String>) {
            //val db = MongoDB()
            //db.openDB(ValuesBase.dataServerPort)
            val uu = User(
             CoreValues.UserAdminType,
            "Романов",
            "Евгений",
            "Леонидович",
            "root",
            "1234",
            "89139449081"
            )
            //db.add(uu, 0)
            System.out.println(uu.oid)
            val xx: User = User()
            //db.getById(xx, uu.getOid(), null)
            println(xx)
            }
        }
    }