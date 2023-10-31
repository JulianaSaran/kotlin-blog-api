package config.spawner

data class Employee(
    val name: String,
    val age: Int,
    val salary: Int,
    val active: Boolean,
    val team: String,
    val bonus: Int,
)

fun main() {
    val employees = mutableListOf(
        Employee(name = "Jose", age = 35, salary = 4000, active = true, team = "FE", bonus = 0),
        Employee(name = "Carolina", age = 20, salary = 1500, active = true, team = "RH", bonus = 0),
        Employee(name = "Pedro", age = 19, salary = 7000, active = true, team = "BE", bonus = 0),
        Employee(name = "Julia", age = 26, salary = 4000, active = false, team = "MKT", bonus = 0),
    )

    //  FIND
    val newer = employees.find { it.age == 20 }
    println(newer).toString()

    // FILTER
    val bigSalary = employees.filter { it.salary >= 4000 && it.active }
    println(bigSalary)

    // FIND INDEX
    val index = employees.indexOfFirst { it.team == "MKT" }
    println(index)

    // MAP
    val map = employees.map { it.salary * 2 }
    println("Os novos sálarios são: $map")

    // ADD
    employees.add(Employee(name = "Juliana", age = 29, salary = 3000, active = true, team = "BE", bonus = 0))
    println(employees)

    // REMOVE
    employees.removeAt(4)
    println(employees)

    // REVERSE
    employees.reverse()
    println(employees)

    // SORT
    employees.sortBy { it.salary }
    println(employees)
}
