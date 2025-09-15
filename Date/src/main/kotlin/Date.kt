class Date(
    val year: Int = 2000,
    val month: Int = 1,
    val day: Int = 1,
) {
    //constructor(y: Int, m: Int) : this(y,m,1)
}

val Date.leapYear get() = year.isLeapYear

private val daysOfMonth = listOf(31,28,31,30,31,30,31,31,30,31,30,31)

val Date.lastDayOfMonth: Int
    get() = if (month == 2 && leapYear) 29
            else daysOfMonth[month - 1]

val Int.isLeapYear: Boolean
    get() = this%4 == 0 && this%100 !=0 || this%400==0