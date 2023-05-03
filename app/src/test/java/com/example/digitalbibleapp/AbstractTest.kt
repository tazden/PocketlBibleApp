package com.example.digitalbibleapp

import com.example.digitalbibleapp.core.Abstract
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.io.IOException

class AbstractTest {

    @Test
    fun test_success() {
        val dataObject=TestDataObject.Success("a","b")
        val domainObject=dataObject.map(DataMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
    }

    @Test
    fun test_fail(){
        val dataObject=TestDataObject.Fail(IOException())
        val domainObject=dataObject.map(DataMapper.Base())
        assertTrue(domainObject is DomainObject.Fail)
    }

    private sealed class TestDataObject : Abstract.Object<DomainObject, DataMapper>() {
         override fun map(mapper: DataMapper): DomainObject {
            TODO("Not yet implemented")
        }
        class Success(private val textOwo: String,private val textTwo: String):TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(textOwo, textTwo)
            }
        }
        class Fail(private val exception:Exception):TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(exception)
            }
        }
        }



    private interface DataMapper : Abstract.Mapper {
        fun map(textOne: String, textTwo: String): DomainObject
        fun map(exception: java.lang.Exception): DomainObject
        class Base:DataMapper{
            override fun map(textOne: String, textTwo: String): DomainObject {
                return DomainObject.Success("$textOne,$textTwo")
            }

            override fun map(exception: java.lang.Exception): DomainObject {
                return DomainObject.Fail()
            }

        }
    }

    private sealed class DomainObject: Abstract.Object<UiObject,UiToDataMapper>(){
        class Success(private val textCombined:String):DomainObject() {
            override fun map(mapper: UiToDataMapper): UiObject {
                TODO("Not yet implemented")
            }
        }
        class Fail():DomainObject() {
            override fun map(mapper: UiToDataMapper): UiObject {
                TODO("Not yet implemented")
            }
        }
    }

    private interface UiToDataMapper : Abstract.Mapper {
        fun map(textOne: String, textTwo: String): DomainObject
        fun map(exception: java.lang.Exception): DomainObject
    }

    private sealed class UiObject: Abstract.Object<Unit, Abstract.Mapper.Empty>(){}
}