import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class StudentListJson : StudentListSuper() {
    fun readFromFile(path:String)
    {
        val listType = object : TypeToken<MutableList<Student>>() {}.type
        var gson = Gson()
        val file = File(path)
        var text:String = ""
        try {
            text = file.readText()
        } catch (e: FileNotFoundException) {
            println("File not found")
        } catch (e: IOException) {
            println("Error reading file")
        }
        data = gson.fromJson(text,listType) ?: mutableListOf()
    }

    fun writeToFile(path:String)
    {
        var gson = Gson()
        var json = gson.toJson(data)
        val file = File(path)
        file.writeText(json)
    }
}