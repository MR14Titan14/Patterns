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
                field=value
        }
        get()
        {
            return field
        }
    var name: String =""
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }
    var fathername: String =""
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }
    var phone: String? =null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }

    var mail: String? =null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }

    var git: String? =null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }
    constructor(_lastname:String,_name:String,_fathername:String)
    {
        lastname=_lastname
        name=_name
        fathername=_fathername
    }

    fun write()
    {
        var out = "ID: $id, Фамиля: $lastname, Имя: $name, Отчество: $fathername"
        if(phone!=null)out+=", Телефон: $phone"
        if(mail!=null)out+=", Почта: $mail"
        if(git!=null)out+=", Гит: $git"
        println(out)
    }
}