package solusrex.core.baseenities

import com.google.gson.Gson
import com.thoughtworks.xstream.XStream
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import solusrex.core.common.I_XStream
import solusrex.core.dao.DAO
import java.text.ParseException
import java.text.SimpleDateFormat

class OwnDateTime : DAO, I_XStream {
    var timeInMS: Long = 0
    @Transient var dd: DateTime = DateTime()
    @Transient var refresh = false
    //---------------------------------------------------------------------------
    open fun toStringValue(): String? {
        return "" + timeInMS
        }
    @Throws(Exception::class)
    open fun parseValue(ss: String) {
        timeInMS = ss.toLong()
        refresh = false
        refresh()
        }
    //---------------------------------------------------------------------------
    private fun refresh() {
        if (refresh || !dateTimeValid()) return
        dd = DateTime(timeInMS)
        refresh = true
        }
    open fun equals(two: OwnDateTime): Boolean {
        return if (!dateTimeValid() || !two.dateTimeValid()) false else timeInMS == two.timeInMS
        }
    constructor(date: String?) {
        val format = SimpleDateFormat("dd.MM.yyyy")
        try {
            timeInMS = format.parse(date).time
            dd = DateTime(timeInMS)
            refresh = true
            } catch (e: ParseException) {}
        }
    constructor(hh: Int, mm: Int) {
        refresh = true
        dd = DateTime()
        dd = DateTime(dd.getYear(), dd.getMonthOfYear(), dd.getDayOfMonth(), hh, mm)
        timeInMS = dd.getMillis()
        }
    constructor(ff: Boolean) {
        dd = DateTime(0)
        timeInMS = 0
        }
    constructor(day: Int, mm: Int, yy: Int, hh: Int, min: Int) {
        refresh = true
        dd = DateTime(yy, mm, day, hh, min)
        timeInMS = dd.getMillis()
        }
    constructor(day: Int, mm: Int, yy: Int, hh: Int, min: Int, sec: Int) {
        refresh = true
        dd = DateTime(yy, mm, day, hh, min, sec)
        timeInMS = dd.getMillis()
        }
    constructor(day: Int, mm: Int, yy: Int) {
        refresh = true
        dd = DateTime(yy, mm, day, 0, 0)
        timeInMS = dd.getMillis()
        }
    constructor(ms: Long) {
        timeInMS = ms
        dd = DateTime(timeInMS)
        refresh = true
        }
    constructor() {
        dd = DateTime()
        timeInMS = dd.getMillis()
    }
    open fun clone(): OwnDateTime? {
        return OwnDateTime(timeInMS)
        }
    open fun onlyDate() {
        dd = DateTime(dd.getYear(), dd.getMonthOfYear(), dd.getDayOfMonth(), 0, 0)
        timeInMS = dd.getMillis()
        }
    open fun dateTimeValid(): Boolean {
        return timeInMS != 0L
        }
    open fun setDate(day: Int, mm: Int, yy: Int) {
        dd = DateTime(yy, mm, day, 0, 0)
        timeInMS = dd.getMillis()
        refresh = true
        }
    open fun changeDateSaveTime(two: OwnDateTime) {
        dd = DateTime(two.year(), two.month(), two.day(), dd.getHourOfDay(), dd.getMinuteOfHour())
        timeInMS = dd.getMillis()
        refresh = true
        }
    open fun compareDate(two: OwnDateTime): Int {
        refresh()
        two.refresh()
        if (year() != two.year()) return year() - two.year()
        return if (month() != two.month()) month() - two.month() else day() - two.day()
        }
    open fun elapsedTimeInSec(): Int {
        refresh()
        val tt1: Long = OwnDateTime().timeInMS()
        val tt2: Int = ((tt1 - timeInMS()) / 1000).toInt()
        return if (tt2 < 0) 0 else tt2
        }
    open fun date(): DateTime? {
        refresh()
        return dd
        }
    open fun timeInMS(): Long {
        return timeInMS
        }
    open fun monthDifference(two: OwnDateTime): Int {
        return if (!dateTimeValid() || !two.dateTimeValid()) 0 else year() * 12 + month() - two.year() * 12 - two.month()
        }
    open fun monthToString(): String? {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("MM-yyyy"))
        }
    open fun timeToString(): String? {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("HH:mm"))
        }
    open fun timeFullToString(): String? {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("HH:mm:ss"))
        }
    open fun dateToString(): String? {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("dd-MM-yyyy"))
        }
    override fun toString(): String {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("yyyy-MM-dd_HH.mm"))
        }
    open fun toString2(): String {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("yyyy-MM-dd_HH.mm.ss"))
        }
    open fun dateTimeToString(): String {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"))
        }
    open fun fullToString(): String {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss"))
        }
    open fun dateTimeToString2(): String {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("dd-MM HH:mm"))
        }
    open fun toStringSec(spDate: String, spTime: String): String? {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("yyyy" + spDate + "MM" + spDate + "dd_HH" + spTime + "mm" + spTime + "ss"))
        }
    open fun toStringMark(): String? {
        refresh()
        return if (!dateTimeValid()) "---" else dd.toString(DateTimeFormat.forPattern("yyyyMMddHHmmss"))
        }
    open fun toStringSec(): String? {
        return toStringSec("-", ".")
        }
    open fun year(): Int {
        refresh()
        return if (!dateTimeValid()) 0 else dd.getYear()
        }
    open fun month(): Int {
        refresh()
        return if (!dateTimeValid()) 0 else dd.getMonthOfYear()
        }
    open fun day(): Int {
        refresh()
        return if (!dateTimeValid()) 0 else dd.getDayOfMonth()
        }
    open fun second(): Int {
        refresh()
        return if (!dateTimeValid()) 0 else dd.getSecondOfMinute()
        }
    open fun dayOfWeek(): Int {
        refresh()
        return dd.getDayOfWeek()
        }
    open fun incMonth() {
        refresh()
        dd = dd.plusMonths(1)
        timeInMS = dd.getMillis()
        }
    open fun decMonth() {
        refresh()
        dd = dd.minusMonths(1)
        timeInMS = dd.getMillis()
        }
    open fun day(vv: Int): OwnDateTime? {
        refresh()
        dd = dd.withDayOfMonth(vv)
        timeInMS = dd.getMillis()
        return this
        }
    open fun hour(vv: Int): OwnDateTime? {
        refresh()
        dd = dd.withHourOfDay(vv)
        timeInMS = dd.getMillis()
        return this
        }
    open fun minute(vv: Int): OwnDateTime? {
        refresh()
        dd = dd.withMinuteOfHour(vv)
        timeInMS = dd.getMillis()
        return this
        }
    open fun month(vv: Int): OwnDateTime? {
        refresh()
        dd = dd.withMonthOfYear(vv)
        timeInMS = dd.getMillis()
        return this
        }
    open fun year(vv: Int): OwnDateTime? {
        refresh()
        dd = dd.withYear(vv)
        timeInMS = dd.getMillis()
        return this
        }
    open fun hour(): Int {
        refresh()
        return if (!dateTimeValid()) 0 else dd.getHourOfDay()
        }
    open fun minute(): Int {
        refresh()
        return if (!dateTimeValid()) 0 else dd.getMinuteOfHour()
        }
    open fun plusDays(vv: Int) {
        refresh()
        dd = dd.plusDays(vv)
        timeInMS = dd.getMillis()
        }
    open fun minusDays(vv: Int) {
        refresh()
        dd = dd.minusDays(vv)
        timeInMS = dd.getMillis()
        }
    open fun setOnlyDate() {
        refresh()
        val d1 = dd.getDayOfMonth()
        val m1 = dd.getMonthOfYear()
        val yy = dd.getYear()
        dd = DateTime(yy, m1, d1, 0, 0)
        timeInMS = dd.getMillis()
        }
    override open fun setAliases(xs: XStream) {
        xs.alias("DateTime", OwnDateTime::class.java)
        xs.useAttributeFor("timeInMS", OwnDateTime::class.java)
        }
    open fun getOnlyTime(): Int {
        return hour() * 60 + minute()
        }
    open fun setOnlyTime(time: Int) {
        minute(time % 60)
        hour(time / 60)
        }
    open fun isToday(): Boolean {
        val tt = OwnDateTime()
        tt.onlyDate()
        val tt2 = OwnDateTime(timeInMS)
        tt2.onlyDate()
        return tt.timeInMS == tt2.timeInMS
        }
    open fun timeOfDayInMin(): Long {
        return (hour() * 60 + minute()).toLong()
        }
    //------------------------------------------------------------------------------------------------------------------
    override open fun afterLoad() {
        dd = DateTime(timeInMS)
        refresh = true
        }
    open fun time(duration: Int): String? {
        return String.format("%2d:%2d", duration / 60, duration % 60)
        }
    companion object{
    @JvmStatic
        open fun main(a: Array<String>) {
            val xx = OwnDateTime()
            println(xx.date()!!.toString(DateTimeFormat.forPattern("yyyyMMdd_HHmmss")))
            println(Gson().toJson(xx))
            val tt = OwnDateTime()
            println(tt.isToday())
            tt.hour(12)
            println(tt.isToday())
            tt.incMonth()
            println(tt.isToday())
            println(OwnDateTime().timeOfDayInMin())
            tt.setDate(1, 12, 2019)
            tt.incMonth()
            println(tt)
            }
        }
    }