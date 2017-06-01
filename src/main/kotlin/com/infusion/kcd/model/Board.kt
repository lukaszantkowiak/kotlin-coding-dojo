package com.infusion.kcd.model

class Board(val rows: Int, val columns: Int) {
    private var cellBoard = Array(rows, { Array(columns, { State.DEAD }) });

    fun setState(row: Int, column: Int, state: State) {
        cellBoard[row][column] = state
    }

    fun getState(row: Int, column: Int): State {
        return cellBoard[row][column]
    }
}