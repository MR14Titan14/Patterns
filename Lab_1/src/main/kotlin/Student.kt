class Student {
    var id: Int =-1
        set(value)
        {
            if(value>0)
                field=value
        }
        get()
        {
            return field
        }
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

    companion object
    {
        var ids=0

        fun validatePhone(value:String?): Boolean
        {
            return value?.matches(Regex("""\+?\d{11}""")) ?: true
        }

        fun validateNames(value:String): Boolean
        {
            return value.matches(Regex("""[A-Я]{1}[a-я]*"""))
        }

        fun validateTelegram(value:String?): Boolean
        {
            return value?.matches(Regex("""\@{1}.*""")) ?: true
        }
        fun validateMail(value:String?): Boolean
        {
            return value?.matches(Regex("""\w*\@\w*\.\w*""")) ?: true
        }
        fun validateGit(value:String?): Boolean
        {
            return value?.matches(Regex("""https://github.com/.*""")) ?: true
        }
    }

    init
    {
        ids++
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