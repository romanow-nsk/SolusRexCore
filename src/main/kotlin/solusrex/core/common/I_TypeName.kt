package solusrex.core.common

interface I_TypeName {
    fun typeName(): String?
    fun typeId(): Int
    @Throws(UniException::class)
    fun typeClass(): Class<*>?
    }