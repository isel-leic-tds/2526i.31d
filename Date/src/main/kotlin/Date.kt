
const val GREGORIAN_YEAR = 1582
const val MAX_YEAR = 2200

private val daysOfMonth = listOf(31,28,31,30,31,30,31,31,30,31,30,31)
val MONTHS_IN_YEAR = daysOfMonth.size

class Date(
    val year: Int = 2000,
    val month: Int = 1,
    val day: Int = 1,
) :Any() {
    init {
        /*if (year !in GREGORIAN_YEAR..MAX_YEAR)
            throw IllegalArgumentException("Invalid year $year")*/
        require(year in GREGORIAN_YEAR..MAX_YEAR) { "Invalid year $year" }
        require(month in 1..MONTHS_IN_YEAR) { "Invalid month $month" }
        require(day in 1..lastDayOfMonth) { "Invalid day $day" }
    }
    //constructor(y: Int, m: Int) : this(y,m,1)
    override fun equals(other: Any?): Boolean =
        other is Date && year==other.year && month==other.month && day==other.day
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
/*
tailrec fun fact(n: Int, res: Int=1) : Int {
    if (n <= 1) return res
    return fact(n-1, res*n)
}*/
