package MVC
import DataListStudentShort
import StudentLists.StudentList

class ReadController(var view:View,source:StudentList) {

    private val pg=source
    public var currentPage=0
        set(value)
        {
            if(value<=pg.getStudentShortCount()/studentsPerPage&&value>=0)
            {
                field=value
            }
        }
    private var studentsPerPage=5
    public var currentFilter =""
    private lateinit var studentShorts:DataListStudentShort


    public fun refresh_data()
    {
        studentShorts=pg.getKNStudentShort(currentPage,studentsPerPage,"")
        view.updatePageLabel( "${currentPage + 1}/${Math.ceil(pg.getKNStudent(0, pg.getStudentShortCount(), currentFilter).size.toDouble() / studentsPerPage.toDouble()).toInt()}")
    }
//    private fun updateTable() {
//        currentFilter = ""
//        if (nameField.text != "" || gitComboBox.value != "Не важно" || phoneComboBox.value != "Не важно" || telegramComboBox.value != "Не важно" || mailComboBox.value != "Не важно") {
//            currentFilter += "WHERE"
//        }
//        if (nameField.text != "") {
//            currentFilter += " lastname = '${nameField.text.split(" ")[0]}' AND name = '${nameField.text.split(" ")[1]}' AND fathername = '${
//                nameField.text.split(
//                    " "
//                )[2]
//            }'"
//        }
//        if (gitComboBox.value != "Не важно") {
//            if (currentFilter != "WHERE") {
//                currentFilter += " AND"
//            }
//            if (gitComboBox.value == "Нет") {
//                currentFilter += " git is NULL"
//            }
//
//            if (gitComboBox.value == "Да") {
//                currentFilter += " git is NOT NULL"
//                if (gitField.text != "") {
//                    currentFilter += " AND git = '${gitField.text}'"
//                }
//            }
//
//        }
//
//        if (phoneComboBox.value != "Не важно") {
//            if (currentFilter != "WHERE") {
//                currentFilter += " AND"
//            }
//            if (phoneComboBox.value == "Нет") {
//                currentFilter += " phone is NULL"
//            }
//
//            if (phoneComboBox.value == "Да") {
//                currentFilter += " phone is NOT NULL"
//                if (phoneField.text != "") {
//                    currentFilter += " AND phone = '${phoneField.text}'"
//                }
//            }
//        }
//
//        if (telegramComboBox.value != "Не важно") {
//            if (currentFilter != "WHERE") {
//                currentFilter += " AND"
//            }
//            if (telegramComboBox.value == "Нет") {
//                currentFilter += " telegram is NULL"
//            }
//
//            if (telegramComboBox.value == "Да") {
//                currentFilter += " telegram is NOT NULL"
//                if (telegramField.text != "") {
//                    currentFilter += " AND telegram = '${telegramField.text}'"
//                }
//            }
//        }
//
//        if (mailComboBox.value != "Не важно") {
//            if (currentFilter != "WHERE") {
//                currentFilter += " AND"
//            }
//            if (mailComboBox.value == "Нет") {
//                currentFilter += " mail is NULL"
//            }
//
//            if (mailComboBox.value == "Да") {
//                currentFilter += " mail is NOT NULL"
//                if (mailField.text != "") {
//                    currentFilter += " AND mail = '${mailField.text}'"
//                }
//            }
//        }
//
//        students = pg.getKNStudent(currentPage, itemsPerPage, currentFilter)
//        updatePageLabel()
//        tableView.items.setAll(students)
//    }
//
}