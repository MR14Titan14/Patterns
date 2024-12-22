import MVC.View

open class StudentListSuper {
    var data:MutableList<Student> = mutableListOf()
    var view: View = View()

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

    fun getKNStudentShort(k: Int, n: Int) : DataListStudentShort
    {
        var s = data.subList((k-1)*n+1,n)
        var ss = s.map{StudentShort(it)} as MutableList<StudentShort>
        return DataListStudentShort(ss,view)
    }

    fun getKNStudent(k: Int, n: Int) : MutableList<Student>
    {
        var s = data.subList((k-1)*n+1,n)
        return s
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