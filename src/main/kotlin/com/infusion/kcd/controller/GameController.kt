package com.infusion.kcd.controller

import com.infusion.kcd.model.Board
import com.infusion.kcd.model.State
import com.infusion.kcd.view.CellsBoard

class GameController(initialBoard: Board) {
    private val WAIT_TIME = 150L

    private var board = initialBoard
    private lateinit var cellsBoard: CellsBoard

    fun start() {
        cellsBoard = CellsBoard.getInstance();
        refreshUI()
        waitForNextIteration()
        startSimulation()
    }

    private fun startSimulation() {
        while (isAliveCell()) {
            simulateNextStep()
            refreshUI()
            waitForNextIteration()
        }
    }

    internal fun simulateNextStep() {
        val newBoard = Board(board.rows, board.columns)

        for (i in 0..board.rows - 1) {
            for (j in 0..board.columns - 1) {
                val liveNeighbours = countLiveNeighbours(i, j)
                val currentState = board.getState(i, j)
                val cellState = getCellState(currentState, liveNeighbours)
                newBoard.setState(i, j, cellState)
            }
        }
        board = newBoard
    }

    internal fun getCellState(currentState: State, liveNeighbours: Int): State {
        return when (currentState) {
            State.ALIVE -> if (liveNeighbours in 2..3) return State.ALIVE else State.DEAD
            State.DEAD -> if (liveNeighbours == 3) return State.ALIVE else State.DEAD
        }
    }

    internal fun countLiveNeighbours(row: Int, column: Int): Int {
        var liveNeighbours = 0

        for (i in -1..1) {
            for (j in -1..1) {
                if (i != 0 || j != 0) {
                    if (board.getState(mod(row + i, board.rows), mod(column + j, board.columns)) == State.ALIVE) {
                        liveNeighbours++
                    }
                }
            }
        }
        return liveNeighbours
    }

    internal fun mod(m: Int, n: Int): Int {
        return if (m < 0) (m + n) % n else m % n
    }

    internal fun isAliveCell(): Boolean {
        for (i in 0..board.rows - 1) {
            for (j in 0..board.columns - 1) {
                if (board.getState(i, j) == State.ALIVE) {
                    return true;
                }
            }
        }
        return false;
    }

    private fun waitForNextIteration() {
        Thread.sleep(WAIT_TIME)
    }

    private fun refreshUI() {
        cellsBoard.updateState(board)
    }
}

