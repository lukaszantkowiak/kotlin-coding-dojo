package com.infusion.kcd

import com.infusion.kcd.controller.GameController
import com.infusion.kcd.model.Board
import com.infusion.kcd.model.State.ALIVE
import com.infusion.kcd.model.State.DEAD
import java.util.*

fun main(args: Array<String>) {
    val rows = 20;
    val columns = 22;
    val board = Board(rows, columns)
    val threshold = 0.4
//    board.setState(2, 3, State.ALIVE)
//    board.setState(2, 4, State.ALIVE)
//    board.setState(2, 5, State.ALIVE)

    val random = Random()
    for (i in 0..rows - 1) {
        for (j in 0..columns - 1) {
            val state = if (random.nextDouble() < threshold) ALIVE else DEAD
            board.setState(i, j, state);
        }
    }

    val gameController = GameController(board);
    gameController.start()
}