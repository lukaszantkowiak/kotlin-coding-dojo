package com.infusion.kcd.controller

import com.infusion.kcd.model.Board
import junit.framework.Assert.assertEquals
import org.junit.Test

class GameControllerTest {
    @Test fun mod(): Unit {
        val gameController = GameController(Board(1, 1))

        val result = gameController.mod(3, 7)

        assertEquals(3, result)
    }
}