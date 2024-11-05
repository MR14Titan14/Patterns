import java.io.File
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.io.path.Path


class StudentListYaml {
    var data:MutableList<Student> = mutableListOf()
    fun readFromFile(path:String)
    {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        data = mapper.readValue(File(path), mapper.typeFactory.constructCollectionType(MutableList::class.java, Student::class.java))
    }

    fun writeToFile(path:String)
    {
        val file = File(path)
        val yamlMapper = ObjectMapper(YAMLFactory())
        yamlMapper.writeValue(file, data)
    }

    fun getById(id:Int):Student?
    {
        for(stud in data)
        {
            if(stud.id==id)
            {
                return stud
            }
        }
        return null
    }

    fun getKNStudentShort(k: Int, n: Int) : DataList<StudentShort>
    {
        var s = data.subList((k-1)*n+1,n)
        var ss = s.map{StudentShort(it)}
        return DataList(ss)
    }

    fun sortByShortname()
    {
        data.sortBy { it.shortName() }
    }

    fun addStudent(stud:Student)
    {
        data.add(stud)
    }

    fun replaceStudent(id:Int,stud: Student)
    {
        var st = getById(id)
        var i=data.indexOf(st)
        data.set(i,stud)
    }

    fun deleteStudent(id:Int)
    {
        var st = getById(id)
        var i=data.indexOf(st)
        data.removeAt(i)
    }

    fun getStudentShortCount():Int
    {
        return data.size
    }
}