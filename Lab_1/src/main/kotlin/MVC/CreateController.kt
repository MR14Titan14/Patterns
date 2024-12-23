package MVC
import Student
import StudentLists.StudentList
import StudentSuper

class CreateController(var view:View,source:StudentList) {

    private val pg=source

    public fun addStudent(lastName:String,name:String,fatherName:String,phone:String,telegram:String,mail:String,git:String)
    {
        try {
            var isDo = true
            if(!StudentSuper.validateNames(lastName)){isDo=false}
            if(!StudentSuper.validateNames(name)){isDo=false}
            if(!StudentSuper.validateFatherName(fatherName)&&fatherName!=""){isDo=false}
            if(!StudentSuper.validatePhone(phone)&&phone!=""){isDo=false}
            if(!StudentSuper.validateTelegram(telegram)&&telegram!=""){isDo=false}
            if(!StudentSuper.validateMail(mail)&&mail!=""){isDo=false}
            if(!StudentSuper.validateGit(git)&&git!=""){isDo=false}
            if(isDo) {
                pg.addStudent(Student(_lastname = lastName, name, fatherName, phone, telegram, mail, git))
                view.errorLabel.text = ""
            }
            else
            {
                view.errorLabel.text="Ошибка валидации"
            }
        }
        catch(e:Exception){
            view.errorLabel.text="Ошибка валидации"
        }
    }
}