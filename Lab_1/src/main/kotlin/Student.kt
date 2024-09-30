import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class Student : StudentSuper {
    var lastname: String =""
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
    var name: String =""
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
    var fathername: String =""
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
    var phone: String? =null
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

    var telegram: String? =null
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

    var mail: String? =null
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

    var git: String? =null
        set(value)
        {
            if(validateGit(value))
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

    fun getInfo() : String
    {
        var res ="ФИО: "+getShortName()
        if(hasGit())
        {
            res+= " Гит: "+git
        }
        if(hasContact())
        {
            res+=" "+getContact()
        }
        return res
    }

    fun getShortName(): String
    {
        var res=lastname+" "+name[0]+"."+fathername[0]+". "
        return res
    }

    fun getContact(): String
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

    override fun toString() : String
    {
        var out = "ID: $id, Фамиля: $lastname, Имя: $name, Отчество: $fathername"
        if(phone!=null)out+=", Телефон: $phone"
        if(telegram!=null)out+=", Телеграм: $telegram"
        if(mail!=null)out+=", Почта: $mail"
        if(git!=null)out+=", Гит: $git"
        return out
    }
}