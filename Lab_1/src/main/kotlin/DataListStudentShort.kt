class DataListStudentShort(studentList: List<StudentShort>) : DataList<StudentShort>(studentList) {

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