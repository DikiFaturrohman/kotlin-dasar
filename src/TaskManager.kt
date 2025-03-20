import java.io.File

// Data class untuk merepresentasikan tugas
data class Task(val id: Int, val description: String, var isDone: Boolean = false)

// List untuk menyimpan tugas
val taskList = mutableListOf<Task>()

fun addTask() {
    print("Masukkan deskripsi tugas: ")
    val description = readLine()?.trim()
    if (!description.isNullOrEmpty()) {
        val newTask = Task(taskList.size + 1, description)
        taskList.add(newTask)
        println("✅ Tugas berhasil ditambahkan!")
    } else {
        println("⚠️ Deskripsi tugas tidak boleh kosong.")
    }
}

fun viewTasks() {
    if (taskList.isEmpty()) {
        println("📭 Tidak ada tugas.")
    } else {
        println("\n📋 Daftar Tugas:")
        taskList.forEach { task ->
            val status = if (task.isDone) "✔" else "❌"
            println("${task.id}. [$status] ${task.description}")
        }
    }
}

fun deleteTask() {
    print("Masukkan ID tugas yang ingin dihapus: ")
    val id = readLine()?.toIntOrNull()
    val taskToRemove = taskList.find { it.id == id }
    if (taskToRemove != null) {
        taskList.remove(taskToRemove)
        println("🗑️ Tugas berhasil dihapus!")
    } else {
        println("⚠️ Tugas tidak ditemukan.")
    }
}

fun markTaskAsDone() {
    print("Masukkan ID tugas yang ingin ditandai selesai: ")
    val id = readLine()?.toIntOrNull()
    val task = taskList.find { it.id == id }
    if (task != null) {
        task.isDone = true
        println("🎉 Tugas berhasil ditandai selesai!")
    } else {
        println("⚠️ Tugas tidak ditemukan.")
    }
}

fun saveTasks() {
    val file = File("tasks.txt")
    file.writeText(taskList.joinToString("\n") { "${it.id},${it.description},${it.isDone}" })
}

fun loadTasks() {
    val file = File("tasks.txt")
    if (file.exists()) {
        file.readLines().forEach { line ->
            val (id, description, isDone) = line.split(",")
            taskList.add(Task(id.toInt(), description, isDone.toBoolean()))
        }
    }
}



fun main() {
    loadTasks() // Load tugas dari file saat program dimulai

    while (true) {
        println("\n📌 Aplikasi Manajemen Tugas 📌")
        println("1. Tambah Tugas")
        println("2. Lihat Tugas")
        println("3. Hapus Tugas")
        println("4. Tandai Tugas Selesai")
        println("5. Keluar")
        print("Pilih menu: ")

        when (readLine()?.toIntOrNull()) {
            1 -> addTask()
            2 -> viewTasks()
            3 -> deleteTask()
            4 -> markTaskAsDone()
            5 -> {
                saveTasks()
                println("Sampai jumpa! 👋")
                return
            }
            else -> println("⚠️ Pilihan tidak valid!")
        }
    }
}
