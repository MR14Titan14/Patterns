import java.lang.reflect.Field

class DataList(val data: List<Any?>) {
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

    fun getNames(id:Int):Array<String>
    {
        var res=data[id]!!::class.java.declaredFields.map { it.name }.toTypedArray()
        res[0]=id.toString()
        return res
    }

    fun getData():DataTable
    {
        var args= mutableListOf<List<Any?>>()
        for (id in selected)
        {
            var arg= mutableListOf<Any?>(id)
            arg.add(data[id]!!::class.java.declaredFields.map{it}.toList())
            args.add(arg)
        }
        return DataTable(args)
    }
}