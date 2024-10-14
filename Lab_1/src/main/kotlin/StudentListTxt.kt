import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class StudentListTxt {
    fun readFromTxt(path:String): MutableList<Student>
    {
        val file = File(path)
        var res = mutableListOf<Student>()
        var text:List<String> = listOf()
        try {
            text = file.readLines()
            println(text)
        } catch (e: FileNotFoundException) {
            println("File not found")
        } catch (e: IOException) {
            println("Error reading file")
        }
        for (line in text)
        {
            res.add(Student(line))
        }
        return res
    }

    fun writeToTxt(path: String, studentList:MutableList<Student>)
    {
        val file = File(path)
        var text = ""
        for(stud in studentList)
        {
            text+=(stud.toString()+"\n")
        }
        file.writeText(text)
    }
}