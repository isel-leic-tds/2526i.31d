package model

@JvmInline
value class Name(val name: String) {
    init { require(isValid(name)) { "Invalid name $name"} }
    override fun toString() = name
    companion object {
        fun isValid(s: String) =
            s.isNotBlank() && s.all{ it.isLetterOrDigit() }
    }
}