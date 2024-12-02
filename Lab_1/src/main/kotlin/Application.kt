import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.Label
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import Student
import StudentLists.StudentList

class StudentView : Application() {
    private lateinit var tableView: TableView<Student>
    private var currentPage = 0
    private val itemsPerPage = 3
    private lateinit var students: List<Student>
    private val pg = StudentList("pg")
    private lateinit var pageLabel: Label



    override fun start(primaryStage: Stage) {
        tableView = TableView<Student>()

        // Создание колонок
        val idColumn = TableColumn<Student, Int>("ID").apply {
            cellValueFactory = PropertyValueFactory("id")
        }
        val lastnameColumn = TableColumn<Student, String>("Фамилия").apply {
            cellValueFactory = PropertyValueFactory("lastname")
        }
        val nameColumn = TableColumn<Student, String>("Имя").apply {
            cellValueFactory = PropertyValueFactory("name")
        }
        val fathernameColumn = TableColumn<Student, String>("Отчество").apply {
            cellValueFactory = PropertyValueFactory("fathername")
        }
        val phoneColumn = TableColumn<Student, String>("Телефон").apply {
            cellValueFactory = PropertyValueFactory("phone")
        }
        val telegramColumn = TableColumn<Student, String>("Телеграм").apply {
            cellValueFactory = PropertyValueFactory("telegram")
        }
        val mailColumn = TableColumn<Student, String>("Почта").apply {
            cellValueFactory = PropertyValueFactory("mail")
        }
        val gitColumn = TableColumn<Student, String>("Гит").apply {
            cellValueFactory = PropertyValueFactory("git")
        }

        // Добавление колонок в таблицу
        tableView.columns.addAll(idColumn, lastnameColumn, nameColumn, fathernameColumn, phoneColumn, telegramColumn, mailColumn, gitColumn)

        // Получение данных
        students = pg.getKNStudents(0, itemsPerPage * 10) // Загрузите больше студентов для тестирования

        // Инициализация таблицы
        updateTable()

        // Кнопки для навигации
        val prevButton = Button("Предыдущая").apply {
            setOnAction {
                if (currentPage > 0) {
                    currentPage--
                    updateTable()
                }
            }
        }
        val nextButton = Button("Следующая").apply {
            setOnAction {
                if ((currentPage+1)*itemsPerPage<pg.getStudentShortCount()) {
                    currentPage++
                    updateTable()
                }
            }
        }

        // Метка для отображения номера текущей страницы
        pageLabel = Label()
        updatePageLabel()

        // Панель с кнопками
        val buttonBox = HBox(prevButton, nextButton, pageLabel)

        // Настройка сцены и отображение
        val vbox = VBox(tableView, buttonBox)
        val scene = Scene(vbox)
        primaryStage.title = "Таблица студентов"
        primaryStage.scene = scene
        primaryStage.show()
    }

    private fun updateTable() {
        students = pg.getKNStudents(currentPage, itemsPerPage) // Загрузите больше студентов для тестирования
        tableView.items.setAll(students)
    }

    private fun updatePageLabel() {
        // Обновление текста метки с номером текущей страницы
        pageLabel.text = "Страница ${currentPage + 1} из ${Math.ceil(pg.getStudentShortCount().toDouble()/itemsPerPage.toDouble()).toInt()}"
    }
}

fun main() {
    Application.launch(StudentView::class.java)
}