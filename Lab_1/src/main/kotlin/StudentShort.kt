class StudentShort : StudentSuper {
    var fio: String? = null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }
    var contact: String? = null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }

    constructor(stud:Student)
    {
        id=stud.id
        fio=stud.shortName()
        git=stud.git
        contact=stud.contact().split(" ").getOrNull(1)
    }
    constructor(input:String)
    {
        id=ids
        fio=input.split(" ").getOrNull(0)
        git=input.split(" ").getOrNull(1)
        contact=input.split(" ").getOrNull(2)
    }

    override fun toString() : String
    {
        var out = "ID: $id, ФИО: $fio"
        if(git!=null)out+=", Гит: $git"
        if(contact!=null)out+=", Контакт: $contact"

        return out
    }

    fun toStringRaw() : String
    {
        var out = "$id $fio"
        if(git!=null)out+=" $git "
        if(contact!=null)out+=" $contact"

        return out
    }
}