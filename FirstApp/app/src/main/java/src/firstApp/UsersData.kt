package src.firstApp

class UsersData {
    private var users: Array<User> = arrayOf(User("Ivan", "Petrov", "test@test.com", "Qwerty1"),
                                            User("Petr", "Ivanov", "PetrPetr@test.ru", "Password1"),
                                            User("Dmitry", "Sidorov", "App@kpfu.ru", "Parol123"))

    fun containsUser(email: String): User? {
        for (i in 0..users.size - 1) {
            if (users[i].email.equals(email))
                return users[i]
        }
        return null
    }
}