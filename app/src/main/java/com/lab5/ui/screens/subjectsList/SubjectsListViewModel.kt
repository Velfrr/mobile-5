package com.lab5.ui.screens.subjectsList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab5.data.db.Lab5Database
import com.lab5.data.entity.SubjectEntity
import com.lab5.data.entity.SubjectLabEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubjectsListViewModel(
    private val database: Lab5Database
) : ViewModel() {
    private val _subjectListStateFlow = MutableStateFlow<List<SubjectEntity>>(emptyList())
    val subjectListStateFlow: StateFlow<List<SubjectEntity>>
        get() = _subjectListStateFlow


    init {
        viewModelScope.launch {
            val subjects = database.subjectsDao.getAllSubjects()
            if (subjects.isEmpty()) {
                preloadData()
            } else {
                _subjectListStateFlow.value = subjects
            }
            Log.d("SubjectsListViewModel", "Subjects loaded: ${_subjectListStateFlow.value}")
        }
    }

    private suspend fun  preloadData() {
        val listOfSubject = listOf(
            SubjectEntity(id = 1, title = "Програмування мобільних додатків"),
            SubjectEntity(id = 2, title = "Економіка"),
            SubjectEntity(id = 3, title = "Мережева безпека"),
        )

        val listOfSubjectLabs = listOf(
            SubjectLabEntity(
                id = 1,
                subjectId = 1,
                title = "Встановлення Android Studio",
                description = "Встановлення середовища розробки",
                comment = "Виконано",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 2,
                subjectId = 1,
                title = "Вивчення навігації в Android-додатках",
                description = "Створити додаток з прикладом навігації між двома екранами",
                comment = "Потрібно поправити звіт",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 3,
                subjectId = 2,
                title = "Створення стартапу: бізнес план",
                description = "Придумати стартап-проект і створити бізнес план в додатку Miro",
                comment = "Оцінено",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 4,
                subjectId = 2,
                title = "Створення стартапу: Розрахунок витрат",
                description = "Розрахувати потенційні витрати на розробку стартапу, розробити план оптимізації витрат",
                comment = "",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 5,
                subjectId = 3,
                title = "Захист локальних мереж",
                description = "Створити локальну мережу і налаштувати її захист",
                comment = "",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 6,
                subjectId = 3 ,
                title = "Налаштування VLAN",
                description = "Налаштувати VLAN відповідно до стандартів безпеки. Налаштувати передачу даних в мережі з 2-ма комутаторами",
                comment = "Захищено",
                isCompleted = true
            )
        )

        listOfSubject.forEach { subject ->
            database.subjectsDao.addSubject(subject)
        }

        listOfSubjectLabs.forEach { lab ->
            database.subjectLabsDao.addSubjectLab(lab)
        }
    }
}