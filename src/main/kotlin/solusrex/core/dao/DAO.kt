package solusrex.core.dao

import org.apache.poi.ss.usermodel.Row
import solusrex.core.common.Counter
import solusrex.core.common.UniException
import solusrex.core.constants.CoreSingleton
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityField
import solusrex.core.entity.EntityList
import solusrex.core.entity.EntityRefList
import solusrex.core.export.I_ExcelRW
import solusrex.core.mongo.DBQueryList
import solusrex.core.mongo.I_MongoDB
import solusrex.core.mongo.I_MongoRW

public open class DAO : I_MongoRW, I_ExcelRW {
    //--------------------------------------------------------------------------------------------------------
    @Transient
    private var fld: ArrayList<EntityField>? = null

    @Throws(UniException::class)
    open fun getFields(): ArrayList<EntityField>? {
        if (fld != null) return fld
        val item: TableItem? = CoreSingleton.get().entityFactory.getItemForSimpleName(javaClass.simpleName)
        fld = item!!.fields
        return fld
    }

    open fun afterLoad() {}

    //----------------- Операции с БД ----------------------------------------------------------------------------------
    @Throws(UniException::class)
    override fun putDBValues(prefix: String, document: org.bson.Document, level: Int, mongo: I_MongoDB) {
        }

    @Throws(UniException::class)
    override fun getDBValues(prefix: String, res: org.bson.Document, level: Int, mongo: I_MongoDB) {
        var field : EntityField? = null
        try {
            getFields();
            val errors = CoreSingleton.get().errors
            for (ff in fld!!) {
                field = ff
                val face = CoreSingleton.get().daoAccessFactory.classMap.get(ff.type)
                if (face==null)
                    errors.addError("Не найдет класс доступа DAO для "+ff.name)
                else{
                    try {
                        face.getDBValue(ff,this, prefix, res, level,mongo)
                        } catch (ee : UniException){
                            errors.addError("Ошибка класса доступа DAO для "+ff.name+": "+ee.toString())
                            }
                    }

                }
            for (ff in fld!!) {          // После ВСЕХ
                if (ff.type != CoreValues.DAOEntityRefList)
                    continue
                field = ff;
                val list2 = ff.field.get(this) as EntityRefList<*>
                val cc = list2.typeT
                    break;
                val bb = level != 0 && cc != null;
                if (!bb)
                    break;      // Имя поля = EntityLink совпадает с именем класса, на который ссылается
                val par1 = cc!!.newInstance() as Entity;
                val par2 = (this as Entity).oid;
                val query = DBQueryList().add("valid", true).add(this::class.java.getSimpleName(), par2);
                //System.out.println("LinkRefList: "+cc.getSimpleName()+"."+this.getClass().getSimpleName()+"["+par2+"]");
                val vv = mongo!!.getAllByQuery(par1, query, level - 1);
                list2.set(vv as Nothing);
                }
            afterLoad();
            } catch (ee: Exception) {
                //Utils.printFatalMessage(ee);
                throw UniException.bug(this::class.java.simpleName + "[" + res!!.get("oid") as Long + "]." + field!!.name + "\n" + ee.toString())!!;
                }
            }
    //----------------- Импорт/экспорт Excel ------------------------------------------------------------
    @Throws(UniException::class)
    override fun getXLSValues(row: Row?, prefix: String?, colMap: HashMap<String?, Int?>?) {
        }
    @Throws(UniException::class)
    override fun putXLSValues(row: Row?, cnt: Counter?) {
        }
    @Throws(UniException::class)
    override fun putXLSHeader(prefix: String?, list: ArrayList<String?>?) {
        }


}