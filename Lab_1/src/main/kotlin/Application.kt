//import StudentLists.StudentList
//import javafx.application.Application
//import javafx.collections.FXCollections
//import javafx.geometry.Pos
//import javafx.scene.Scene
//import javafx.scene.control.*
//import javafx.scene.control.cell.PropertyValueFactory
//import javafx.scene.layout.HBox
//import javafx.scene.layout.VBox
//import javafx.stage.Stage
//import javafx.collections.ObservableList
//import javafx.scene.text.Font
//import javafx.scene.layout.GridPane
//import javafx.stage.Modality
//
//
//class StudentView : Application() {
//    private lateinit var tableView: TableView<Student>
//    private var currentPage = 0
//    private val itemsPerPage = 5
//    private lateinit var students: MutableList<Student>
//    private val pg = StudentList("pg")
//    private lateinit var pageLabel: Label
//    private lateinit var nameField: TextField
//    private lateinit var gitFilterVar: ObservableList<String>
//    private lateinit var gitComboBox: ComboBox<String>
//    private lateinit var gitField: TextField
//    private lateinit var phoneFilterVar: ObservableList<String>
//    private lateinit var phoneComboBox: ComboBox<String>
//    private lateinit var phoneField: TextField
//    private lateinit var telegramFilterVar: ObservableList<String>
//    private lateinit var telegramComboBox: ComboBox<String>
//    private lateinit var telegramField: TextField
//    private lateinit var mailFilterVar: ObservableList<String>
//    private lateinit var mailComboBox: ComboBox<String>
//    private lateinit var mailField: TextField
//
//    private var currentFilter: String = ""
//
//
//    override fun start(primaryStage: Stage) {
//        tableView = TableView<Student>()
//
//        val idColumn = TableColumn<Student, Int>("ID").apply {
//            cellValueFactory = PropertyValueFactory("id")
//        }
//        val lastnameColumn = TableColumn<Student, String>("Фамилия").apply {
//            cellValueFactory = PropertyValueFactory("lastname")
//        }
//        val nameColumn = TableColumn<Student, String>("Имя").apply {
//            cellValueFactory = PropertyValueFactory("name")
//        }
//        val fathernameColumn = TableColumn<Student, String>("Отчество").apply {
//            cellValueFactory = PropertyValueFactory("fathername")
//        }
//        val phoneColumn = TableColumn<Student, String>("Телефон").apply {
//            cellValueFactory = PropertyValueFactory("phone")
//        }
//        val telegramColumn = TableColumn<Student, String>("Телеграм").apply {
//            cellValueFactory = PropertyValueFactory("telegram")
//        }
//        val mailColumn = TableColumn<Student, String>("Почта").apply {
//            cellValueFactory = PropertyValueFactory("mail")
//        }
//        val gitColumn = TableColumn<Student, String>("Гит").apply {
//            cellValueFactory = PropertyValueFactory("git")
//        }
//
//        tableView.columns.addAll(
//            idColumn,
//            lastnameColumn,
//            nameColumn,
//            fathernameColumn,
//            phoneColumn,
//            telegramColumn,
//            mailColumn,
//            gitColumn
//        )
//
//        students = pg.getKNStudent(0, itemsPerPage * 10, currentFilter)
//
//
//        val prevButton = Button("<").apply {
//            setOnAction {
//                if (currentPage > 0) {
//                    currentPage--
//                    updateTable()
//                }
//            }
//        }
//        val nextButton = Button(">").apply {
//            setOnAction {
//                if ((currentPage + 1) * itemsPerPage < pg.getStudentShortCount()) {
//                    currentPage++
//                    updateTable()
//                }
//            }
//        }
//
//        pageLabel = Label()
//        updatePageLabel()
//
//        val tableButtonBox = HBox(prevButton, pageLabel, nextButton)
//        tableButtonBox.alignment = Pos.BASELINE_CENTER
//
//        tableView.setPrefSize(415.0, 388.0)
//
//        val table = VBox(tableView, tableButtonBox)
//
//
//        nameField = TextField().apply { setOnAction { updateTable() } }
//        nameField.promptText = "Фамилия Имя Отчетство"
//
//        phoneFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
//        phoneComboBox = ComboBox(phoneFilterVar).apply { setOnAction { updateTable() } }
//        phoneComboBox.value = "Не важно"
//
//        val phoneLabel = Label()
//        phoneLabel.text = "Телефон: "
//        phoneLabel.alignment = Pos.BASELINE_CENTER
//        phoneLabel.font = Font.font(14.0)
//        phoneLabel.setPrefSize(100.0, 13.0)
//        val phoneBox = HBox(phoneLabel, phoneComboBox)
//
//        phoneField = TextField().apply { setOnAction { updateTable() } }
//        phoneField.promptText = "Телефон студента"
//
//        telegramFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
//        telegramComboBox = ComboBox(telegramFilterVar).apply { setOnAction { updateTable() } }
//        telegramComboBox.value = "Не важно"
//
//        val telegramLabel = Label()
//        telegramLabel.text = "Телеграм: "
//        telegramLabel.alignment = Pos.BASELINE_CENTER
//        telegramLabel.font = Font.font(14.0)
//        telegramLabel.setPrefSize(100.0, 13.0)
//        val telegramBox = HBox(telegramLabel, telegramComboBox)
//
//        telegramField = TextField().apply { setOnAction { updateTable() } }
//        telegramField.promptText = "Телеграм студента"
//
//        mailFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
//        mailComboBox = ComboBox(mailFilterVar).apply { setOnAction { updateTable() } }
//        mailComboBox.value = "Не важно"
//
//        val mailLabel = Label()
//        mailLabel.text = "Почта: "
//        mailLabel.alignment = Pos.BASELINE_CENTER
//        mailLabel.font = Font.font(14.0)
//        mailLabel.setPrefSize(100.0, 13.0)
//        val mailBox = HBox(mailLabel, mailComboBox)
//
//        mailField = TextField().apply { setOnAction { updateTable() } }
//        mailField.promptText = "Почта студента"
//
//        gitFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
//        gitComboBox = ComboBox(gitFilterVar).apply { setOnAction { updateTable() } }
//        gitComboBox.value = "Не важно"
//
//        val gitLabel = Label()
//        gitLabel.text = "Гит: "
//        gitLabel.alignment = Pos.BASELINE_CENTER
//        gitLabel.font = Font.font(14.0)
//        gitLabel.setPrefSize(100.0, 13.0)
//        val gitBox = HBox(gitLabel, gitComboBox)
//
//        gitField = TextField().apply { setOnAction { updateTable() } }
//        gitField.promptText = "Гит студента"
//
//        updateTable()
//
//        val addButton = Button("Добавить").apply {
//            setOnAction {
//                openModalWindow(0, "", "", "", "", "", "", "")
//            }
//        }
//
//        val changeButton = Button("Изменить").apply {
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
//        }
//
//        val deleteButton = Button("Удалить").apply {
//            setOnAction {
//                val selected = tableView.selectionModel.selectedItem
//                if (selected != null) {
//                    pg.deleteStudent(selected.id)
//                    updateTable()
//                }
//            }
//        }
//        val updateButton = Button("Обновить таблицу").apply {
//            setOnAction {
//                updateTable()
//            }
//        }
//
//        addButton.setPrefSize(200.0, 13.0)
//        changeButton.setPrefSize(200.0, 13.0)
//        deleteButton.setPrefSize(200.0, 13.0)
//        updateButton.setPrefSize(200.0, 13.0)
//
//        val filterBox =
//            VBox(nameField, phoneBox, phoneField, telegramBox, telegramField, mailBox, mailField, gitBox, gitField)
//
//        val buttonBox = VBox(filterBox, addButton, changeButton, deleteButton, updateButton)
//
//        val mainBox = HBox(table, buttonBox)
//
//        val scene = Scene(mainBox)
//        primaryStage.title = "Таблица студентов"
//        primaryStage.scene = scene
//        primaryStage.show()
//    }
//
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
//    private fun updatePageLabel() {
//        pageLabel.text = "${currentPage + 1}/${
//            Math.ceil(
//                pg.getKNStudent(
//                    0,
//                    pg.getStudentShortCount(),
//                    currentFilter
//                ).size.toDouble() / itemsPerPage.toDouble()
//            ).toInt()
//        }"
//    }
//
//    private fun openModalWindow(
//        id: Int = 0,
//        lasName: String,
//        name: String,
//        fatherName: String,
//        phone: String,
//        telegram: String,
//        mail: String,
//        git: String
//    ) {
//        val modalStage = Stage()
//        modalStage.initModality(Modality.APPLICATION_MODAL)
//        modalStage.title = "Ввод данных"
//
//        val grid = GridPane()
//        grid.padding = javafx.geometry.Insets(10.0)
//        grid.hgap = 10.0
//        grid.vgap = 10.0
//
//        val lastNameField = TextField()
//        lastNameField.text = lasName
//        val nameField = TextField()
//        nameField.text = name
//        val fatherNameField = TextField()
//        fatherNameField.text = fatherName
//        val phoneField = TextField()
//        phoneField.text = phone
//        val telegramField = TextField()
//        telegramField.text = telegram
//        val mailField = TextField()
//        mailField.text = mail
//        val gitField = TextField()
//        gitField.text = git
//
//        grid.add(Label("Фамилия:"), 0, 1)
//        grid.add(lastNameField, 1, 1)
//        grid.add(Label("Имя:"), 0, 2)
//        grid.add(nameField, 1, 2)
//        grid.add(Label("Отчество:"), 0, 3)
//        grid.add(fatherNameField, 1, 3)
//        grid.add(Label("Номер телефона:"), 0, 4)
//        grid.add(phoneField, 1, 4)
//        grid.add(Label("Телеграмм:"), 0, 5)
//        grid.add(telegramField, 1, 5)
//        grid.add(Label("Почта:"), 0, 6)
//        grid.add(mailField, 1, 6)
//        grid.add(Label("Гит:"), 0, 7)
//        grid.add(gitField, 1, 7)
//
//        val submitButton = Button("Отправить")
//        submitButton.setOnAction {
//            // Здесь можно обработать данные из полей
//            println("Фамилия: ${lastNameField.text}")
//            println("Имя: ${nameField.text}")
//            println("Отчество: ${fatherNameField.text}")
//            println("Номер телефона: ${phoneField.text}")
//            println("Телеграмм: ${telegramField.text}")
//            println("Почта: ${mailField.text}")
//            println("Гит: ${gitField.text}")
//            if (id == 0) {
//                pg.addStudent(
//                    Student(
//                        _lastname = lastNameField.text,
//                        nameField.text,
//                        fatherNameField.text,
//                        phoneField.text,
//                        telegramField.text,
//                        mailField.text,
//                        gitField.text
//                    )
//                )
//                updateTable()
//            } else {
//                pg.replaceStudent(
//                    id,
//                    Student(
//                        _lastname = lastNameField.text,
//                        nameField.text,
//                        fatherNameField.text,
//                        phoneField.text,
//                        telegramField.text,
//                        mailField.text,
//                        gitField.text
//                    )
//                )
//                updateTable()
//            }
//            modalStage.close()
//        }
//
//        grid.add(submitButton, 1, 8)
//
//        val scene = Scene(grid, 400.0, 300.0)
//        modalStage.scene = scene
//        modalStage.showAndWait()
//    }
//
//}
//
//fun main() {
//    Application.launch(StudentView::class.java)
//}