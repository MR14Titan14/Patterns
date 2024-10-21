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
            var splited=line.split(" ")
            res.add(Student(splited.get(0).toInt(),splited.get(1),splited.get(2),splited.get(3),splited.getOrNull(4),splited.getOrNull(5),splited.getOrNull(6),splited.getOrNull(7)))
        }
        return res
    }

    fun writeToTxt(path: String, studentList:MutableList<Student>)
    {
        val file = File(path)
        var text = ""
        for(stud in studentList)
        {
            text+=(stud.toStringRaw()+"\n")
        }
        file.writeText(text)
    }

    fun getById(id:Int, path: String):Student?
    {
        var list = readFromTxt(path)
        for(stud in list)
        {
            if(stud.id==id)
            {
                return stud
            }
        }
        return null
    }
}