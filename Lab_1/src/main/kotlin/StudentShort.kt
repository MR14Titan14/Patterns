class StudentShort {
    private var id: Int = -1
        get()
        {
            return field
        }
    private var fio: String? = null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }
    private var git: String? = null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }
    private var contact: String? = null
        set(value)
        {
            field=value
        }
        get()
        {
            return field
        }
    companion object
    {
        var ids=0
    }
    init
    {
        ids++
    }

    constructor(stud:Student)
    {
        id=ids
        fio=stud.getShortName()
        git=stud.git
        contact=stud.getContact().split(" ").getOrNull(1)
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
        var out = "ID: $id, ФИО: $fio, "
        if(git!=null)out+="Гит: $git "
        if(contact!=null)out+=", Контакт: $contact"

        return out
    }
}