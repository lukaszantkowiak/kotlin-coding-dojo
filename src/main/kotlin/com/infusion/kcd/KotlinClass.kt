package com.infusion.kcd

import com.infusion.kcd.model.Board
import com.infusion.kcd.model.State
import com.infusion.kcd.view.CellsBoard

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
    val instance = CellsBoard.getInstance()
    val board1 = Board(1, 5);
    board1.setState(0, 4, State.ALIVE);
    instance.updateState(board1);

    Thread.sleep(5000)


    val board2 = Board(5, 1);
    board2.setState(4, 0, State.ALIVE);
    instance.updateState(board2);
}