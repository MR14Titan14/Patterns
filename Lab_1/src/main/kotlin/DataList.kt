open class DataList<T>(val data: List<T>) {
    private var selected : MutableList<Int> = mutableListOf()

    fun select(ind:Int)
    {
        if(ind<data.size)
        {
            selected.add(ind)
        }
    }

    fun getSelected():MutableList<Int>
    {
        return selected
    }

    open fun getNames():Array<String>
    {
        return arrayOf("Abstract","method")
    }

    open fun getDataOfRows():MutableList<MutableList<Any?>>
    {
        return mutableListOf(mutableListOf<Any?>("Abstract","method",123))
    }

    fun getTable():DataTable
    {
        var names=getNames()
        var args=getDataOfRows()
        args[0]=names.toMutableList()
        return DataTable(args)
    }
}