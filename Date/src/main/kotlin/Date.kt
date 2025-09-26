import kotlin.or
import kotlin.shl

const val GREGORIAN_YEAR = 1582
const val MAX_YEAR = 2200

private val daysOfMonth = listOf(31,28,31,30,31,30,31,31,30,31,30,31)
val MONTHS_IN_YEAR = daysOfMonth.size

const val DAY_BITS = 5
const val MONTH_BITS = 4
const val YEAR_BITS = 12

@JvmInline
value class Date private constructor(private val bits: Int) {

    constructor(y: Int = 2000, m: Int = 1, d: Int = 1)
       :this(d or (m shl DAY_BITS) or (y shl (DAY_BITS+MONTH_BITS)))
    {
        require(y in GREGORIAN_YEAR..MAX_YEAR) { "Invalid year $y" }
        require(m in 1..MONTHS_IN_YEAR) { "Invalid month $m" }
        require(d in 1..lastDayOfMonth) { "Invalid day $d" }
    }

    val year: Int get() = bits shr (DAY_BITS+MONTH_BITS)
    val month: Int get() = (bits shr DAY_BITS) and ((1 shl MONTH_BITS)-1)
    val day: Int get() = bits and ((1 shl DAY_BITS)-1)

    override fun toString(): String =
        //this::class.simpleName+"@"+hashCode().toString(16)
        "$year-"+"%02d-%02d".format(month,day)

    operator fun compareTo(dt: Date): Int = bits - dt.bits
}

val Date.leapYear get() = year.isLeapYear

val Date.lastDayOfMonth: Int
    get() = if (month == 2 && leapYear) 29
            else daysOfMonth[month - 1]

val Int.isLeapYear: Boolean
    get() = this%4 == 0 && this%100 !=0 || this%400==0

operator fun Date.plus(days: Int): Date = addDays(days)
operator fun Int.plus(date: Date): Date = date.addDays(this)

private tailrec fun Date.addDaysInternal(days: Int) : Date {
    val d = day + days
    return if (d <= lastDayOfMonth)
        Date(year, month, d)
    else {
        val date =
            if (month < MONTHS_IN_YEAR) Date(year, month + 1)
            else Date(year + 1)
        date.addDaysInternal(days - (lastDayOfMonth - day) - 1)
    }
}

fun Date.addDays(days: Int): Date {
    require(days > 0) { "days must be >0" }
    return addDaysInternal(days)
}