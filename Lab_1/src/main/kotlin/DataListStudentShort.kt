import MVC.View

class DataListStudentShort(studentList: List<StudentShort>, var view: View) : DataList<StudentShort>(studentList) {



    init{
        notifyView()
    }

    public fun notifyView()
    {
        view.updateTables(data)
    }

    override fun getNames():Array<String>
    {
        return arrayOf("№","FIO","Git","Contact")
    }

    override fun getDataOfRows():MutableList<MutableList<Any?>>
    {
        var args= mutableListOf<MutableList<Any?>>()
        args.add(mutableListOf())
        var count=1
        for (row in data)
        {
            args.add(mutableListOf(count,row.fio,row.git,row.contact))
            count++
        }
        return args
    }

}