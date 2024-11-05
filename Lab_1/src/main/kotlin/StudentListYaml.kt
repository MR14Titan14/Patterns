import java.io.File
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.io.path.Path


class StudentListYaml : StudentListSuper() {
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
}