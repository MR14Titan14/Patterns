import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonCreator


class Student : StudentSuper {
   @field:JsonProperty("lastname") var lastname: String =""
        set(value)
        {
            if(validateNames(value))
            {
                field=value
            }
            else
            {
                field=""
            }
        }
        get()
        {
            return field
        }
    @field:JsonProperty("name") var name: String =""
        set(value)
        {
            if(validateNames(value))
            {
                field=value
            }
            else
            {
                field=""
            }
        }
        get()
        {
            return field
        }
    @field:JsonProperty("fathername") var fathername: String =""
        set(value)
        {
            if(validateNames(value))
            {
                field=value
            }
            else
            {
                field=""
            }
        }
        get()
        {
            return field
        }
    @field:JsonProperty("phone") var phone: String? =null
        set(value)
        {
            if(validatePhone(value)) {
                field = value
            }
        }
        get()
        {
            return field
        }

    @field:JsonProperty("telegram") var telegram: String? =null
        set(value)
        {
            if(validateTelegram(value))
            {
                field=value
            }
        }
        get()
        {
            return field
        }

    @field:JsonProperty("mail") var mail: String? =null
        set(value)
        {
            if(validateMail(value))
            {
                field=value
            }
        }
        get()
        {
            return field
        }

    fun validate() : Boolean
    {
        return this.hasGit()&&this.hasContact()
    }

    private fun hasGit() : Boolean
    {
          return this.git!=null
    }

    private fun hasContact() : Boolean
    {
        return this.phone!=null || this.telegram!=null || this.mail!=null
    }

    fun setContacts(_phone: String?=null,_telegram: String?=null,_mail: String?=null)
    {
        if(_phone!=null&&validatePhone(_phone))
        {
            phone = _phone
        }
        if(_telegram!=null&&validateTelegram(_telegram))
        {
            telegram = _telegram
        }
        if(_mail!=null&&validateMail(_mail))
        {
            mail = _mail
        }
    }

    fun info() : String
    {
        var res ="ФИО: "+shortName()
        if(hasGit())
        {
            res+= " Гит: "+git
        }
        if(hasContact())
        {
            res+=" "+contact()
        }
        return res
    }

    fun shortName(): String
    {
        var res=lastname+" "+name[0]+"."+fathername[0]+". "
        return res
    }

    fun contact(): String
    {
        if(mail!=null)
        {
            return "Почта: "+mail
        }
        if(telegram!=null)
        {
            return "Телеграм: "+telegram
        }
        if(phone!=null)
        {
            return "Телефон: "+phone
        }
        return ""
    }

    companion object
    {
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

    @JsonCreator constructor(
        @JsonProperty("id") _id: String = "0",
        @JsonProperty("git") _git: String? = "",
        @JsonProperty("lastname") _lastname: String = "",
        @JsonProperty("name")  _name: String = "",
        @JsonProperty("fathername")  _fathername: String = "",
        @JsonProperty("phone")  _phone: String? = null,
        @JsonProperty("telegram")  _telegram: String? = null,
        @JsonProperty("mail")  _mail: String? = null,)
    {
        id=_id.toInt()
        lastname=_lastname
        name=_name
        fathername=_fathername
        phone=_phone
        telegram=_telegram
        mail=_mail
        git=_git
    }
    constructor(_lastname:String,_name:String,_fathername:String)
    {
        id=ids
        lastname=_lastname
        name=_name
        fathername=_fathername
    }
    constructor(_lastname:String,_name:String,_fathername:String,_phone:String?=null,_telegram:String?=null,_mail:String?=null,_git:String?=null)
    {
        id=ids
        lastname=_lastname
        name=_name
        fathername=_fathername
        phone=_phone
        telegram=_telegram
        mail=_mail
        git=_git
    }

    constructor(_id:Int,_lastname:String,_name:String,_fathername:String,_phone:String?=null,_telegram:String?=null,_mail:String?=null,_git:String?=null)
    {
        id = _id
        lastname=_lastname
        name=_name
        fathername=_fathername
        phone=_phone
        telegram=_telegram
        mail=_mail
        git=_git
    }

    constructor(hashStud: HashMap<String,Any?>)
    {
        id=ids
        lastname=hashStud["lastname"].toString()
        name=hashStud["name"].toString()
        fathername=hashStud["fathername"].toString()
        phone=hashStud.getOrDefault("phone",null).toString()
        telegram=hashStud.getOrDefault("telegram",null).toString()
        mail=hashStud.getOrDefault("mail",null).toString()
        git=hashStud.getOrDefault("git",null).toString()

    }

    constructor(input:String): this (input.split(" ")[0],input.split(" ")[1],input.split(" ")[2],input.split(" ").getOrNull(3),input.split(" ").getOrNull(4),input.split(" ").getOrNull(5),input.split(" ").getOrNull(6))
    {

    }

    constructor(input:String,id_:Int): this (id_,input.split(" ")[0],input.split(" ")[1],input.split(" ")[2],input.split(" ").getOrNull(3),input.split(" ").getOrNull(4),input.split(" ").getOrNull(5),input.split(" ").getOrNull(6))
    {

    }

    override fun toString() : String
    {
        var out = "ID: $id, Фамиля: $lastname, Имя: $name, Отчество: $fathername"
        if(phone!=null)out+=", Телефон: $phone"
        if(telegram!=null)out+=", Телеграм: $telegram"
        if(mail!=null)out+=", Почта: $mail"
        if(git!=null)out+=", Гит: $git"
        return out
    }

    fun toStringRaw() : String
    {
        var out = "$id $lastname $name $fathername"
        if(phone!=null)out+=" $phone"
        if(telegram!=null)out+=" $telegram"
        if(mail!=null)out+=" $mail"
        if(git!=null)out+=" $git"
        return out
    }
}