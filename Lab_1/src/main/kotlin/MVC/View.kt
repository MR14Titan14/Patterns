package MVC

import StudentShort
import StudentLists.StudentList
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.collections.ObservableList
import javafx.scene.text.Font
import javafx.scene.layout.GridPane
import javafx.stage.Modality


class View : Application() {
    private val controller=Controller(this);

    private lateinit var tableView: TableView<StudentShort>
    private lateinit var pageLabel: Label
    private lateinit var nameField: TextField
    private lateinit var gitFilterVar: ObservableList<String>
    private lateinit var gitComboBox: ComboBox<String>
    private lateinit var gitField: TextField
    private lateinit var contactFilterVar: ObservableList<String>
    private lateinit var contactComboBox: ComboBox<String>
    private lateinit var contactField: TextField


    override fun start(primaryStage: Stage) {
        tableView = TableView<StudentShort>()

        val idColumn = TableColumn<StudentShort, Int>("ID").apply {
            cellValueFactory = PropertyValueFactory("id")
        }
        val fioColumn = TableColumn<StudentShort, String>("ФИО").apply {
            cellValueFactory = PropertyValueFactory("fio")
        }

        val contactColumn = TableColumn<StudentShort, String>("Контакт").apply {
            cellValueFactory = PropertyValueFactory("contact")
        }
        val gitColumn = TableColumn<StudentShort, String>("Гит").apply {
            cellValueFactory = PropertyValueFactory("git")
        }

        tableView.columns.addAll(
            idColumn,
            fioColumn,
            contactColumn,
            gitColumn
        )

        val prevButton = Button("<").apply {
            setOnAction {
                controller.currentPage-=1
                controller.refresh_data()
            }
        }
        val nextButton = Button(">").apply {
            setOnAction {
                controller.currentPage+=1
                controller.refresh_data()
            }
        }

        pageLabel = Label()

        val tableButtonBox = HBox(prevButton, pageLabel, nextButton)
        tableButtonBox.alignment = Pos.BASELINE_CENTER

        tableView.setPrefSize(415.0, 388.0)

        val table = VBox(tableView, tableButtonBox)


        nameField = TextField().apply { setOnAction { controller.refresh_data() } }
        nameField.promptText = "Фамилия Имя Отчетство"

        contactFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        contactComboBox = ComboBox(contactFilterVar).apply { setOnAction { controller.refresh_data() } }
        contactComboBox.value = "Не важно"

        val contactLabel = Label()
        contactLabel.text = "Контакт: "
        contactLabel.alignment = Pos.BASELINE_CENTER
        contactLabel.font = Font.font(14.0)
        contactLabel.setPrefSize(100.0, 13.0)
        val contactBox = HBox(contactLabel, contactComboBox)

        contactField = TextField().apply { setOnAction { controller.refresh_data() } }
        contactField.promptText = "Контакт студента"

        gitFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        gitComboBox = ComboBox(gitFilterVar).apply { setOnAction { controller.refresh_data() } }
        gitComboBox.value = "Не важно"

        val gitLabel = Label()
        gitLabel.text = "Гит: "
        gitLabel.alignment = Pos.BASELINE_CENTER
        gitLabel.font = Font.font(14.0)
        gitLabel.setPrefSize(100.0, 13.0)
        val gitBox = HBox(gitLabel, gitComboBox)

        gitField = TextField().apply { setOnAction { controller.refresh_data() } }
        gitField.promptText = "Гит студента"

        controller.refresh_data()

        val addButton = Button("Добавить").apply {
//            setOnAction {
//                openModalWindow(0, "", "", "", "", "", "", "")
//            }
        }

        val changeButton = Button("Изменить").apply {
//            setOnAction {
//                val selected = tableView.selectionModel.selectedItem
//                if (selected != null) {
//                    openModalWindow(
//                        selected.id,
//                        selected.lastname,
//                        selected.name,
//                        selected.fathername,
//                        selected.phone ?: "",
//                        selected.telegram ?: "",
//                        selected.mail ?: "",
//                        selected.git ?: ""
//                    )
//                }
//            }
        }

        val deleteButton = Button("Удалить").apply {
//            setOnAction {
//                val selected = tableView.selectionModel.selectedItem
//                if (selected != null) {
//                    pg.deleteStudent(selected.id)
//                    controller.refresh_data()
//                }
//            }
        }
        val updateButton = Button("Обновить таблицу").apply {
            setOnAction {
                controller.refresh_data()
            }
        }

        addButton.setPrefSize(200.0, 13.0)
        changeButton.setPrefSize(200.0, 13.0)
        deleteButton.setPrefSize(200.0, 13.0)
        updateButton.setPrefSize(200.0, 13.0)

        val filterBox =
            VBox(nameField, contactBox, contactField, gitBox, gitField)

        val buttonBox = VBox(filterBox, addButton, changeButton, deleteButton, updateButton)

        val mainBox = HBox(table, buttonBox)

        val scene = Scene(mainBox)
        primaryStage.title = "Таблица студентов"
        primaryStage.scene = scene
        primaryStage.show()
    }

    public fun updateTables(studentList: List<StudentShort>)
    {
        tableView.items.setAll(studentList)
    }

    public fun updatePageLabel(text:String)
    {
        pageLabel.text=text
    }
}

fun main() {
    Application.launch(View::class.java)
}