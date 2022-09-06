package solusrex.core.baseenities

import solusrex.core.entity.Entity

class Account : Entity() {
    var login = ""
    var loginPhone = Phone()
    var password = ""
    fun Account(login0: String, loginPhone0: String?, password0: String) {
        login = login0
        loginPhone.parseAndSet(loginPhone0)
        password = password0
        }
    fun Account() {}
    fun getLoginPhone(): String? {
        return if (!loginPhone.isMobile()) "" else loginPhone.mobile()
        }
    fun loginPhoneValid(): Boolean {
        return loginPhone.isMobile()
        }
    fun setLoginPhone(ss: String?) {
        loginPhone.parseAndSet(ss)
        }
    override fun toString(): String {
        return "$login $loginPhone $password"
        }
    override fun title(): String {
        return "$login / $loginPhone"
        }
    fun load(proto: Account) {
        password = proto.password
        login = proto.login
        loginPhone = Phone(proto.getLoginPhone().toString())
        }
    fun Account(proto: Account) {
        password = proto.password
        login = proto.login
        loginPhone = Phone(proto.getLoginPhone().toString())
        }
    //---------------------------------------------------------------------------
    fun toStringValue(): String? {
        return "" + loginPhone.toStringValue().toString() + "|" + login + "|" + password
        }
    @Throws(Exception::class)
    fun parseValue(ss: String) {
        val idx1 = ss.indexOf("|")
        val idx2 = ss.lastIndexOf("|")
        loginPhone = Phone()
        if (idx1 != 0) loginPhone = Phone(ss.substring(0, idx1))
        login = ""
        login = ss.substring(idx1 + 1, idx2)
        password = ""
        if (idx2 != ss.length - 1) password = ss.substring(idx2 + 1)
        }
    }
