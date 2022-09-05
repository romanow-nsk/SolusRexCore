package solusrex.core.entity

import java.lang.reflect.Field

class EntityField (val name: String?="",  val type: Int=-1, val field: Field, var value : String =""){
    constructor(nm: String, tp: Int, fld0: Field) :this(nm,tp,fld0,"") {}
    constructor(src: EntityField) : this(src.name,src.type,src.field,src.value) {}
    override fun toString():String{
        return "$name[$type]=$value"
        }
    }