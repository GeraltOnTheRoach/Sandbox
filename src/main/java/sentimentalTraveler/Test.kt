package sentimentalTraveler

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SolutionTest {
    @Test fun test1(){
        assertEquals(5, solution(IntArray(3){2}))
    }
    @Test fun test2(){
        assertEquals(12, solution(arrayOf(2,0,2,2,1,0).toIntArray()))
    }
    @Test fun test3(){
        assertEquals(13, solution(arrayOf(5,5,0,0,2,8,8,0,5,5).toIntArray()))
    }
}