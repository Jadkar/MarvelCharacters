package com.openbank.data.testutils

import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.text.SimpleDateFormat
import java.util.*

class UtilsTest  {

    @Test
    fun testGetCurrentTimestampNotNull() {
        assertNotNull(com.openbank.data.utils.DateHelper.getCurrentTimestamp())
    }

    @Test
    fun testGetCurrentTimestamp(){
        val result=getFakeCurrentTimeStamp()
        Assertions.assertEquals(result, com.openbank.data.utils.DateHelper.getCurrentTimestamp())
    }
    @Test
    fun testCalculateHash() {

        val haskKey="testMarvelkeyAssigmnent"
        val dummyResult="177707ff0d3e69b918de8c40a3747a77"

        Assertions.assertEquals(dummyResult, com.openbank.data.utils.HasKeyGenerator.calculateHash(haskKey))
    }

    private fun getFakeCurrentTimeStamp():String{
        return SimpleDateFormat("yyyy-MM-dd HH:mm").format(
            Calendar
                .getInstance().time
        )
    }



}