package pt.isel.tds

import kotlin.test.*

class SlotStateTests {
// TODO: II.5
/*  Crie testes automáticos para verificação da correção das funções random e isWinner da classe SlotState.
    Para os testes, assuma que pode chamar o construtor primário de SlotState */
    @Test fun randomTest() {
        val sut = SlotState.random()
        sut.slots.forEach { assertTrue( it in RANGE_VALUES) }
    }
    @Test fun isWinnerTest() {
        val sut = SlotState( List(SLOTS) { 5 } )
        assertTrue( sut.isWinner() )
    }
    @Test fun notIsWinnerTest() {
        val sut = SlotState( List(SLOTS) { it.toByte() })
        assertFalse( sut.isWinner() )
    }
}