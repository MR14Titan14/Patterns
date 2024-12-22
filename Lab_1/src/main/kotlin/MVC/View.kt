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
    private val readController=ReadController(this)
    private val createController=CreateController(this);
    private val updateController=UpdateController(this);



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
                readController.currentPage-=1
                readController.refresh_data()
            }
        }
        val nextButton = Button(">").apply {
            setOnAction {
                readController.currentPage+=1
                readController.refresh_data()
            }
        }

        pageLabel = Label()

        val tableButtonBox = HBox(prevButton, pageLabel, nextButton)
        tableButtonBox.alignment = Pos.BASELINE_CENTER

        tableView.setPrefSize(415.0, 388.0)

        val table = VBox(tableView, tableButtonBox)


        nameField = TextField().apply { setOnAction { readController.refresh_data() } }
        nameField.promptText = "Фамилия Имя Отчетство"

        contactFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        contactComboBox = ComboBox(contactFilterVar).apply { setOnAction { readController.refresh_data() } }
        contactComboBox.value = "Не важно"

        val contactLabel = Label()
        contactLabel.text = "Контакт: "
        contactLabel.alignment = Pos.BASELINE_CENTER
        contactLabel.font = Font.font(14.0)
        contactLabel.setPrefSize(100.0, 13.0)
        val contactBox = HBox(contactLabel, contactComboBox)

        contactField = TextField().apply { setOnAction { readController.refresh_data() } }
        contactField.promptText = "Контакт студента"

        gitFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        gitComboBox = ComboBox(gitFilterVar).apply { setOnAction { readController.refresh_data() } }
        gitComboBox.value = "Не важно"

        val gitLabel = Label()
        gitLabel.text = "Гит: "
        gitLabel.alignment = Pos.BASELINE_CENTER
        gitLabel.font = Font.font(14.0)
        gitLabel.setPrefSize(100.0, 13.0)
        val gitBox = HBox(gitLabel, gitComboBox)

        gitField = TextField().apply { setOnAction { readController.refresh_data() } }
        gitField.promptText = "Гит студента"

        readController.refresh_data()

        val addButton = Button("Добавить").apply {
            setOnAction {
                openModalWindow(0)
            }
        }

        val changeButton = Button("Изменить").apply {
            setOnAction {
                val selected = tableView.selectionModel.selectedItem
                if (selected != null) {
                    openModalWindow(selected.id)
                }
            }
        }

        val deleteButton = Button("Удалить").apply {
//            setOnAction {
//                val selected = tableView.selectionModel.selectedItem
//                if (selected != null) {
//                    pg.deleteStudent(selected.id)
//                    readController.refresh_data()
//                }
//            }
        }
        val updateButton = Button("Обновить таблицу").apply {
            setOnAction {
                readController.refresh_data()
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

    private fun openModalWindow(
        id: Int = 0,
    ) {
        val modalStage = Stage()
        modalStage.initModality(Modality.APPLICATION_MODAL)
        modalStage.title = "Ввод данных"
        val grid = GridPane()
        grid.padding = javafx.geometry.Insets(10.0)
        grid.hgap = 10.0
        grid.vgap = 10.0
        val lastNameField = TextField()
        val nameField = TextField()
        val fatherNameField = TextField()
        val phoneField = TextField()
        val telegramField = TextField()
        val mailField = TextField()
        val gitField = TextField()
        if(id!=0)
        {
            var params=updateController.getStudent(id)
            lastNameField.text=params[0]
            nameField.text=params[1]
            fatherNameField.text=params[2]
            phoneField.text=params[3]
            telegramField.text=params[4]
            mailField.text=params[5]
            gitField.text=params[6]
        }
        grid.add(Label("Фамилия:"), 0, 1)
        grid.add(lastNameField, 1, 1)
        grid.add(Label("Имя:"), 0, 2)
        grid.add(nameField, 1, 2)
        grid.add(Label("Отчество:"), 0, 3)
        grid.add(fatherNameField, 1, 3)
        grid.add(Label("Номер телефона:"), 0, 4)
        grid.add(phoneField, 1, 4)
        grid.add(Label("Телеграмм:"), 0, 5)
        grid.add(telegramField, 1, 5)
        grid.add(Label("Почта:"), 0, 6)
        grid.add(mailField, 1, 6)
        grid.add(Label("Гит:"), 0, 7)
        grid.add(gitField, 1, 7)
        val submitButton = Button("Отправить")
        submitButton.setOnAction {
            // Здесь можно обработать данные из полей
            println("Фамилия: ${lastNameField.text}")
            println("Имя: ${nameField.text}")
            println("Отчество: ${fatherNameField.text}")
            println("Номер телефона: ${phoneField.text}")
            println("Телеграмм: ${telegramField.text}")
            println("Почта: ${mailField.text}")
            println("Гит: ${gitField.text}")
            if (id == 0) {
                createController.addStudent(lastNameField.text,nameField.text,fatherNameField.text,phoneField.text,telegramField.text,mailField.text,gitField.text)
                readController.refresh_data()
            } else {
                updateController.updateStudent(id,lastNameField.text, nameField.text,fatherNameField.text, phoneField.text, telegramField.text, mailField.text, gitField.text)
                readController.refresh_data()
            }
            modalStage.close()
        }
        grid.add(submitButton, 1, 8)
        val scene = Scene(grid, 400.0, 300.0)
        modalStage.scene = scene
        modalStage.showAndWait()
    }
}

fun main() {
    Application.launch(View::class.java)
}