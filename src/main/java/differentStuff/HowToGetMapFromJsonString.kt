package differentStuff

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class HowToGetMapFromJsonString {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val jsonAnswer = JsonObject()
            jsonAnswer.addProperty("Queue 1", 1)
            jsonAnswer.addProperty("Queue 2", 2)
            jsonAnswer.addProperty("Queue 3", 3)
            jsonAnswer.addProperty("Queue 4", "kakaha")
            println(jsonAnswer)
            jsonAnswer.keySet().map { println(it) }

            val jsonString: String = GsonBuilder().setPrettyPrinting().create().toJson(jsonAnswer)
            println(jsonString)

            val map : Map<String, String> = Gson().fromJson(jsonString, object : TypeToken<Map<String, String>>(){}.type)
            map.forEach{(key,value) -> println("key: $key, value: $value") }

        }
        //data class JsonStrings(val str1:String,val str2:String,val str3:String)
    }
}