package com.booking.bookingapi

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class BookingApiClientTest {
    @Test
    fun `default GET cities call is working`() {
        val respData = BookingApiClient.staticAPI.getCities()
            .execute().body()
        assertNotNull(respData)
        respData!!.let {
            assertEquals(10, it.result.size)
        }
    }
}