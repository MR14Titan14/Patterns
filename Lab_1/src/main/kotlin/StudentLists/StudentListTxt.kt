import MVC.View
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import Strategy.StudentListStrategy

class StudentListTxt(view: View) : StudentListSuper(view),StudentListStrategy {
    override fun readFromFile(path:String)
    {
        val file = File(path)
        var res = mutableListOf<Student>()
        var text:List<String> = listOf()
        try {
            text = file.readLines()
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
        data= res
    }

    override fun writeToFile(path: String)
    {
        val file = File(path)
        var text = ""
        for(stud in data)
        {
            text+=(stud.toStringRaw()+"\n")
        }
        file.writeText(text)
    }
}