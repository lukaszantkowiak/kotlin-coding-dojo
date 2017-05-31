package com.infusion.kcd

class KotlinClass(val abcd: String) {
    var abc: String;

    init {
        abc = JavaClass("aaaa").abc;
    }

    fun abcd(): String {
        return abcd
    }
}

fun main(args: Array<String>) {
    val kotlinClass = KotlinClass("cdabbb")
    println(kotlinClass.abc)
    println(kotlinClass.abcd)
}