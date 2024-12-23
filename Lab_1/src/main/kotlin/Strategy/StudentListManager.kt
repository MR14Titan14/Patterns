package Strategy
import MVC.View
import Student

import StudentListSuper

interface StudentListStrategy {
    var data:MutableList<Student>
    fun readFromFile(path: String)
    fun writeToFile(path: String)
}

class StudentManager(private var strategy: StudentListStrategy,view: View):StudentListSuper(view) {

    fun setStrategy(strategy: StudentListStrategy) {
        this.strategy = strategy
    }

    fun readFromFile(path: String) {
        strategy.readFromFile(path)
        this.data = strategy.data
    }

    fun writeToFile(path:String) {
        this.data = strategy.data
        strategy.writeToFile(path)
    }
}