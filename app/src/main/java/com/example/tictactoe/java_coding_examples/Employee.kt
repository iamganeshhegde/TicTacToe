package com.example.tictactoe.java_coding_examples

class Employee:Comparable<Employee> {


    var id:Int = 0
    var name:String = ""
    var age = 0
    var salary:Long = 0


    override fun compareTo(other: Employee): Int {


        return  other.name.compareTo(this.name)
    }
}