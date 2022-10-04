package solusrex.core.API

import com.sun.org.apache.xerces.internal.xs.StringList
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import solusrex.core.baseenities.artifact.Artifact
import solusrex.core.baseenities.artifact.ArtifactList
import solusrex.core.baseenities.place.GPSPoint
import solusrex.core.baseenities.system.BugMessage
import solusrex.core.baseenities.system.ServerState
import solusrex.core.baseenities.user.Account
import solusrex.core.baseenities.user.User
import solusrex.core.common.DBRequest
import solusrex.core.constants.ConstValue
import solusrex.core.entity.EntityList
import solusrex.core.entity.EntityNamed
import solusrex.core.entity.base.HelpFile
import solusrex.core.entity.baseentityes.*
import solusrex.core.entity.notifications.NTMessage

interface RestAPIBase {
    //======================== API настроек сервера данных
    /** Получить объект настроек сервера данных  */
    @GET("/api/worksettings")
    fun workSettings(@Header("SessionToken") token: String?): Call<DBRequest?>?

    /** Получить int-поле настроек по имени  */
    @GET("/api/worksettings/get/int")
    fun getWorkSettingsInt(@Header("SessionToken") token: String?, @Query("field") field: String?): Call<JInt?>?

    /** Получить String-поле настроек по имени  */
    @GET("/api/worksettings/get/string")
    fun getWorkSettingsString(@Header("SessionToken") token: String?, @Query("field") field: String?): Call<JString?>?

    /** Получить boolean-поле настроек по имени  */
    @GET("/api/worksettings/get/boolean")
    fun getWorkSettingsBoolean(@Header("SessionToken") token: String?, @Query("field") field: String?): Call<JBoolean?>?

    /** Обновить объект настроек  */
    @POST("/api/worksettings/update")
    fun updateWorkSettings(@Header("SessionToken") token: String?, @Body workSettings: DBRequest?): Call<JEmpty?>?

    /** Обновить int-поле настроек по имени  */
    @POST("/api/worksettings/update/int")
    fun updateWorkSettings(
        @Header("SessionToken") token: String?,
        @Query("field") field: String?,
        @Query("value") value: Int
    ): Call<JEmpty?>?

    /** Обновить String-поле настроек по имени  */
    @POST("/api/worksettings/update/string")
    fun updateWorkSettings(
        @Header("SessionToken") token: String?,
        @Query("field") field: String?,
        @Query("value") value: String?
    ): Call<JEmpty?>?

    /** Обновить boolean-поле настроек по имени  */
    @POST("/api/worksettings/update/boolean")
    fun updateWorkSettings(
        @Header("SessionToken") token: String?,
        @Query("field") field: String?,
        @Query("value") value: Boolean
    ): Call<JEmpty?>?
    //========================== Универсальный интерфейс бизнес-объектов БД ===================
    /** добавить объект - имя класса-JSON  */
    @POST("/api/entity/add")
    fun addEntity(
        @Header("SessionToken") token: String?,
        @Body body: DBRequest?,
        @Query("level") level: Int
    ): Call<JLong?>?

    /** обновить объект - имя класса-JSON  */
    @POST("/api/entity/update")
    fun updateEntity(@Header("SessionToken") token: String?, @Body body: DBRequest?): Call<JEmpty?>?

    /** обновить поле объекта имя поля-имя класса-JSON  */
    @POST("/api/entity/update/field")
    fun updateEntityField(
        @Header("SessionToken") token: String?,
        @Query("name") fieldName: String?,
        @Body body: DBRequest?
    ): Call<JEmpty?>?

    /** получить объект - имя класса-oid  */
    @GET("/api/entity/get")
    fun getEntity(
        @Header("SessionToken") token: String?,
        @Query("classname") classname: String?,
        @Query("id") id: Long,
        @Query("level") level: Int
    ): Call<DBRequest?>?

    /** получить все объекты - имя класса  */
    @GET("/api/entity/list")
    fun getEntityList(
        @Header("SessionToken") token: String?,
        @Query("classname") classname: String?,
        @Query("mode") mode: Int,
        @Query("level") level: Int
    ): Call<ArrayList<DBRequest?>?>?

    /** удалить объект - имя класса-oid  */
    @POST("/api/entity/remove")
    fun removeEntity(
        @Header("SessionToken") token: String?,
        @Query("classname") classname: String?,
        @Query("id") id: Long
    ): Call<JBoolean?>?

    /** получить количество объектов - имя класса  */
    @GET("/api/entity/number")
    fun getEntityNumber(@Header("SessionToken") token: String?, @Query("classname") classname: String?): Call<JInt?>?

    @GET("/api/entity/list/withpaths")
    fun  //660
            getEntityListWithPaths(
        @Header("SessionToken") token: String?,
        @Query("classname") classname: String?,
        @Query("mode") mode: Int,
        @Query("level") level: Int,
        @Query("paths") paths: String?
    ): Call<ArrayList<DBRequest?>?>?

    @GET("/api/entity/get/withpaths")
    fun  //660
            getEntityWithPaths(
        @Header("SessionToken") token: String?,
        @Query("classname") classname: String?,
        @Query("id") id: Long,
        @Query("level") level: Int,
        @Query("paths") paths: String?
    ): Call<DBRequest?>?

    /** получить последние объекты - имя класса-количество  */
    @GET("/api/entity/list/last")
    fun getEntityListLast(
        @Header("SessionToken") token: String?,
        @Query("classname") classname: String?,
        @Query("number") number: Int,
        @Query("level") level: Int
    ): Call<ArrayList<DBRequest?>?>?

    /** получить объекты - имя класса-XML-запрос  */
    @GET("/api/entity/list/query")
    fun getEntityListByQuery(
        @Header("SessionToken") token: String?,
        @Query("classname") classname: String?,
        @Query("xmlquery") xmlQuery: String?,
        @Query("level") level: Int
    ): Call<ArrayList<DBRequest?>?>?

    /** Установка GPS-координат для адреса  */
    @POST("/api/address/setgps")
    fun setAddressGPS(
        @Header("SessionToken") token: String?,
        @Query("id") id: Long,
        @Body gps: GPSPoint?
    ): Call<JLong?>?
    //---------------------------- Администрирование ---------------------------------------------------------------------------
    /** ping - запрос клиента  */
    @GET("/api/debug/ping")
    fun ping(): Call<JEmpty?>?

    /** получить отладочный токен по системному паролю  */
    @GET("/api/debug/token")
    fun debugToken(@Query("pass") pass: String?): Call<JString?>?

    /** Инициализировать БД сервера  */
    @GET("/api/admin/cleardb")
    fun clearDB(@Header("SessionToken") token: String?, @Query("pass") pass: String?): Call<JString?>?

    /** Инициализировать таблицу БД по имени класса бизнес-сущности  */
    @GET("/api/admin/cleartable")
    fun clearTable(
        @Header("SessionToken") token: String?,
        @Query("table") table: String?,
        @Query("pass") pass: String?
    ): Call<JString?>?

    /** Активность клиента - в ответе - количество неподтвержденных уведомлений  */
    @GET("/api/keepalive")
    fun keepalive(@Header("SessionToken") token: String?): Call<JInt?>?

    /** Получить count последних строк лога  */
    @GET("/api/debug/consolelog")
    fun getConsoleLog(@Header("SessionToken") token: String?, @Query("count") count: Int): Call<StringList?>?

    /** Добавить объект - описатель бага  */
    @POST("/api/bug/add")
    fun sendBug(@Header("SessionToken") token: String?, @Body bug: BugMessage?): Call<JLong?>?

    /** Получить список багов  */
    @GET("/api/bug/list")
    fun getBugList(@Header("SessionToken") token: String?, @Query("mode") mode: Int): Call<EntityList<BugMessage?>?>?

    /** Получить баг по oid  */
    @GET("/api/bug/get")
    fun getBug(@Header("SessionToken") token: String?, @Query("id") id: Long): Call<BugMessage?>?

    /** Экспортировать БД в Excel-файл - артефакт сервера (xls) */
    @GET("/api/admin/exportdb")
    fun exportDBxls(@Header("SessionToken") token: String?): Call<Artifact?>?

    /** Экспортировать БД в Excel-файл - артефакт сервера (xls/xlsx)  */
    @GET("/api/admin/exportdb")
    fun exportDBxlsx(
        @Header("SessionToken") token: String?,
        @Query("xlsx") xlsx: Boolean,
        @Query("blocksize") blocksize: Int
    ): Call<Artifact?>?

    /** Экспортировать БД в архив MongoDB  */
    @GET("/api/admin/dump")
    fun dump(@Header("SessionToken") token: String?): Call<Artifact?>?

    /** Перезагрузить сервер  */
    @POST("/api/admin/reboot")
    fun rebootServer(@Header("SessionToken") token: String?, @Query("pass") pass: String?): Call<JEmpty?>?

    /** Импортировать БД из Excel-файла - артефакта сервера (oid), ответ - строка лога  */
    @POST("/api/admin/importdb")
    fun importDBxls(
        @Header("SessionToken") token: String?,
        @Query("pass") pass: String?,
        @Query("id") id: Long
    ): Call<JString?>?

    /** Импортировать БД из архива БД  */
    @POST("/api/admin/restore")
    fun restore(
        @Header("SessionToken") token: String?,
        @Query("pass") pass: String?,
        @Query("id") id: Long
    ): Call<JString?>?

    /** Обновить сервер загруженным артефактом и перезагрузить  */
    @POST("/api/admin/deploy")
    fun deploy(@Header("SessionToken") token: String?, @Query("pass") pass: String?): Call<JString?>?

    /** Обновить сервер загруженным артефактом, выделить память заданного размера и перезагрузить  */
    @POST("/api/admin/deploy")
    fun deployMB(
        @Header("SessionToken") token: String?,
        @Query("pass") pass: String?,
        @Query("mb") sizeInMb: Int
    ): Call<JString?>?

    /** Выполнить в режиме командной строки  */
    @POST("/api/admin/execute")
    fun execute(
        @Header("SessionToken") token: String?,
        @Query("pass") pass: String?,
        @Query("cmd") cmd: String?
    ): Call<JString?>?

    /** Завершение  работы сервера  */
    @POST("/api/admin/shutdown")
    fun shutdown(@Header("SessionToken") token: String?, @Query("pass") pass: String?): Call<JString?>?

    /** Выполнить продолжительную операцию на сервере с опросом в long polling  */
    @GET("/api/admin/preparedb")
    fun prepareDB(
        @Header("SessionToken") token: String?,
        @Query("operation") operation: Int,
        @Query("pass") pass: String?
    ): Call<JString?>?

    /** long polling продолжительной операции  */
    @GET("/api/admin/longpoll")
    fun longPolling(@Header("SessionToken") token: String?, @Query("pass") pass: String?): Call<JString?>?

    /** Заблокировать login-ы для монопольной операции  */
    @POST("/api/admin/lock")
    fun lock(
        @Header("SessionToken") token: String?,
        @Query("pass") pass: String?,
        @Query("on") on: Boolean
    ): Call<JEmpty?>?

    /** Тестовая операция при отладке  */
    @GET("/api/admin/testcall")
    fun testCall(
        @Header("SessionToken") token: String?,
        @Query("operation") operation: Int,
        @Query("value") value: String?
    ): Call<JString?>?

    /** Обновить лог - закрыть текущий и открыть новый  */
    @POST("/api/admin/logfile/reopen")
    fun reopenLogFile(@Header("SessionToken") token: String?, @Query("pass") pass: String?): Call<JEmpty?>?

    /** Получить список файлов-артефактов в каталоге  */
    @GET("/api/admin/files/list")
    fun getFolder(
        @Header("SessionToken") token: String?,
        @Query("pass") pass: String?,
        @Query("folder") folder: String?
    ): Call<StringList?>?

    /** Получить текущую версию системы  */
    @GET("/api/version")
    fun currentVersion(@Header("SessionToken") token: String?): Call<JString?>?

    /** Получить состояние сервера  */
    @GET("/api/serverstate")
    fun serverState(@Header("SessionToken") token: String?): Call<ServerState?>?

    /** Удаление по имени сущности и id  */
    @POST("/api/entity/delete")
    fun deleteById(
        @Header("SessionToken") token: String?,
        @Query("entity") entity: String?,
        @Query("id") id: Long
    ): Call<JBoolean?>?

    /** Восстановление по имени сущности и id  */
    @POST("/api/entity/undelete")
    fun undeleteById(
        @Header("SessionToken") token: String?,
        @Query("entity") entity: String?,
        @Query("id") id: Long
    ): Call<JBoolean?>?

    /** Получить список констант  */
    @GET("/api/const/all")
    fun getConstAll(@Header("SessionToken") token: String?): Call<ArrayList<ConstValue?>?>?

    /** Получить список констант (группа)  */
    @GET("/api/const/bygroups")
    fun getConstByGroups(@Header("SessionToken") token: String?): Call<ArrayList<ArrayList<ConstValue?>?>?>?

    /** Режим кэширования сервера  */
    @POST("/api/admin/cashmode")
    fun setCashMode(
        @Header("SessionToken") token: String?,
        @Query("mode") mode: Boolean,
        @Query("pass") pass: String?
    ): Call<JEmpty?>?
    //------------------------------------------------------------------------- не используются
    /** Список имен из таблицы по шаблону  */
    @GET("/api/names/get")
    fun getNamesByPattern(
        @Header("SessionToken") token: String?,
        @Query("entity") entity: String?,
        @Query("pattern") pattern: String?
    ): Call<EntityList<EntityNamed?>?>?

    //@POST("/api/gps/address")
    //fun getGPSByAddress(@Header("SessionToken") token: String?, @Body addr: Address?): Call<GPSPoint?>?
    //=========================API авторизации  =========================================================
    /** Завершить сеанс  */
    @GET("/api/user/logoff")
    fun logoff(@Header("SessionToken") token: String?): Call<JEmpty?>?

    /** Авторизация по Account в теле запроса (тест)  */
    @POST("/api/user/login")
    fun login(@Body body: Account?): Call<User?>?

    /** Авторизация по номеру телефона (логину) и паролю  */
    @GET("/api/user/login/phone")
    fun login(@Query("phone") phone: String?, @Query("pass") pass: String?): Call<User?>?

    /** Получить список пользователей  */
    @GET("/api/user/list")
    fun getUserList(
        @Header("SessionToken") token: String?,
        @Query("mode") mode: Int,
        @Query("level") level: Int
    ): Call<EntityList<User?>?>?

    /** Добавить пользователя  */
    @POST("/api/user/add")
    fun addUser(@Header("SessionToken") token: String?, @Body user: User?): Call<JLong?>?

    /** Обновить пользователя  */
    @POST("/api/user/update")
    fun updateUser(@Header("SessionToken") token: String?, @Body user: User?): Call<JEmpty?>?

    /** Получить пользователя по oid  */
    @GET("/api/user/get")
    fun getUserById(
        @Header("SessionToken") token: String?,
        @Query("id") id: Long,
        @Query("level") level: Int
    ): Call<User?>?

    @GET("/api/user/server/environment")
    fun getSetverEnvironment(@Header("SessionToken") token: String?): Call<ArrayList<String?>?>?
    //=============================API артефактов и файлов  ================================
    /** Получить описатель артефакта по oid   */
    @GET("/api/artifact/get")
    fun getArtifactById(
        @Header("SessionToken") token: String?,
        @Query("id") id: Long,
        @Query("level") level: Int
    ): Call<Artifact?>?

    /** Записать файл как артефакт через multipart-запрос http  */
    @Streaming
    @Multipart
    @POST("/api/file/update")
    fun update(
        @Header("SessionToken") token: String?,
        @Query("artId") artId: Long,
        @Part file: MultipartBody.Part?
    ): Call<Artifact?>?

    /** Получить список артефактов   */
    @GET("/api/artifact/list")
    fun getArtifactList(
        @Header("SessionToken") token: String?,
        @Query("mode") mode: Int,
        @Query("level") level: Int
    ): Call<ArtifactList?>?

    /** Записать файл как артефакт через multipart-запрос http  */
    @Streaming
    @Multipart
    @POST("/api/file/upload")
    fun upload(
        @Header("SessionToken") token: String?,
        @Query("description") description: String?,
        @Query("origname") origName: String?,
        @Part file: MultipartBody.Part?
    ): Call<Artifact?>?

    /** Записать файл по имени в корневой каталог сервера или в каталог экземпляра (с именем = порт)  */
    @Streaming
    @Multipart
    @POST("/api/file/uploadByName")
    fun uploadByName(
        @Header("SessionToken") token: String?,
        @Query("fname") description: String?,
        @Part file: MultipartBody.Part?,
        @Query("root") root: Boolean
    ): Call<JEmpty?>?

    /** Читать файл по id артефакта через multipart-запрос http    */
    @Streaming
    @GET("/api/file/load")
    fun downLoad(@Header("SessionToken") token: String?, @Query("id") id: Long): Call<ResponseBody?>?

    /** Читать файл по имени из корневого каталога сервера или из каталога экземпляра (с именем = порт)   */
    @Streaming
    @GET("/api/file/loadByName")
    fun downLoadByName(
        @Header("SessionToken") token: String?,
        @Query("fname") fname: String?,
        @Query("root") root: Boolean
    ): Call<ResponseBody?>?

    /** Установить имя файла-ориганиала в артефакте  */
    @POST("/api/artifact/setname")
    fun setArtifactName(
        @Header("SessionToken") token: String?,
        @Query("id") id: Long,
        @Query("name") name: String?
    ): Call<Artifact?>?

    /** Создать объект-артефакт  */
    @POST("/api/artifact/create")
    fun createArtifact(
        @Header("SessionToken") token: String?,
        @Query("description") description: String?,
        @Query("origname") origName: String?,
        @Query("filesize") filesize: Long
    ): Call<Artifact?>?

    /** Конвертация формата  артефакта через внешнее приложение  */
    @POST("/api/artifact/convert")
    fun convertArtifact(@Header("SessionToken") token: String?, @Query("id") id: Long): Call<JEmpty?>?

    /** Получить список объектов-артефактов по условию  */
    @GET("/api/artifact/condition/list")
    fun getArtifactConditionList(
        @Header("SessionToken") token: String?,
        @Query("type") type: Int,
        @Query("owner") owner: String?,
        @Query("namemask") nameMask: String?,
        @Query("filenamemask") fileNameMask: String?,
        @Query("size1") size1: Long,
        @Query("size2") size2: Long,
        @Query("dateInMS1") dateMS1: Long,
        @Query("dateInMS2") dateMS2: Long
    ): Call<ArtifactList?>?

    /** Добавить ссылку на артефакт из поля-списка артефактов (EntityLinkList) класса  */
    @POST("/api/entity/artifactlist/add")
    fun addArtifactToList(
        @Header("SessionToken") token: String?,
        @Query("classname") className: String?,
        @Query("fieldname") fieldName: String?,
        @Query("id") id: Long,
        @Query("artifactid") artifactid: Long
    ): Call<JEmpty?>?

    /** Удалить ссылку на артефакт из поля-списка артефактов (EntityLinkList) класса   */
    @POST("/api/entity/artifactlist/remove")
    fun removeArtifactFromList(
        @Header("SessionToken") token: String?,
        @Query("classname") className: String?,
        @Query("fieldname") fieldName: String?,
        @Query("id") id: Long,
        @Query("artifactid") artifactid: Long
    ): Call<JEmpty?>?

    /** Заменить ссылку на артефакт в поле fieldName класса   */
    @POST("/api/entity/artifact/replace")
    fun replaceArtifact(
        @Header("SessionToken") token: String?,
        @Query("classname") className: String?,
        @Query("fieldname") fieldName: String?,
        @Query("id") id: Long,
        @Query("artifactid") artifactid: Long
    ): Call<JEmpty?>?

    /** Удалить артефакт вместе с файлом  */
    @POST("/api/artifact/remove")
    fun removeArtifact(@Header("SessionToken") token: String?, @Query("id") id: Long): Call<JEmpty?>?

    /** Получить список артефактов помощи - без авторизации  */
    @GET("/api/helpfile/list")
    fun getHelpFileList(@Query("question") question: String?): Call<EntityList<HelpFile?>?>?

    /** Записать строку в текстовый файл  */
    @POST("/api/artifact/fromstring")
    fun createArtifactFromString(
        @Header("SessionToken") token: String?,
        @Query("fileName") fileName: String?,
        @Query("text") text: String?
    ): Call<Artifact?>?

    //----------------- Для тестирования ----------------------------------------------------------------------
    @Streaming
    @GET("/api/file/load2")
    fun downLoad2(@Header("SessionToken") token: String?, @Body body: JString?): Call<ResponseBody?>?
    //================== Уведомления ==================================================
    /** Установить состояние уведомления  */
    @POST("/api/notification/setstate")
    fun setNotificationState(
        @Header("SessionToken") token: String?,
        @Query("id") id: Long,
        @Query("state") state: Int
    ): Call<JEmpty?>?

    /**  Селекция по типу пользователя (=0-нет), по id пользователя, по состоянию уведомления */
    @GET("/api/notification/user/list")
    fun getNotificationUserList(
        @Header("SessionToken") token: String?,
        @Query("userid") id: Long,
        @Query("usertype") type: Int,
        @Query("state") state: Int
    ): Call<EntityList<NTMessage?>?>?

    /** Получить количество уведомлений  */
    @GET("/api/notification/count")
    fun getNotificationCount(@Header("SessionToken") token: String?): Call<JInt?>?

    /** Добавить уведомление  */
    @POST("/api/notification/add")
    fun addNotification(@Header("SessionToken") token: String?, @Body body: NTMessage?): Call<JLong?>?

    /** Добавить широковещательное уведомление  */
    @POST("/api/notification/add/broadcast")
    fun addNotificationBroadcast(@Header("SessionToken") token: String?, @Body body: NTMessage?): Call<JInt?>?

    /** Обновить уведомление  */
    @POST("/api/notification/update")
    fun updateNotification(@Header("SessionToken") token: String?, @Body body: NTMessage?): Call<JEmpty?>?

    /** Добавить уведомление по oid  */
    @GET("/api/notification/get")
    fun getNotification(@Header("SessionToken") token: String?, @Query("id") id: Long): Call<NTMessage?>?

    /** Получить все уведомления  */
    @GET("/api/notification/list")
    fun getNotificationList(@Header("SessionToken") token: String?): Call<EntityList<NTMessage?>?>?

    /** Удалить уведомление  */
    @POST("/api/notification/remove")
    fun removeNotification(@Header("SessionToken") token: String?, @Query("id") id: Long): Call<JBoolean?>?

    /** Получить данные аккаунта авторизованного пользователя  */
    @GET("/api/user/account/get")
    fun getOwnAccount(@Header("SessionToken") token: String?): Call<Account?>? //==================================  API ПРЕДМЕТНОЙ ОБЛАСТИ =======================================================
    // В отдельном интерфейсе
}
