package com.example.androidkotlin

import com.example.androidkotlin.R
import java.util.*

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESITONS: String = "total_questions"
    const val CORRECT_ANSWER: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val que1 = Question(
            1, "What county does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Australia", "Croatia", "germany",
            1
        )

        questionList.add(que1)

        val que2 = Question(
            2, "What county does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Argentina", "Australia", "Brazil", "germany",
            3
        )

        questionList.add(que2)

        val que3 = Question(
            3, "What county does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Argentina", "New Zeland", "Croatia", "germany",
            2
        )

        questionList.add(que3)

        return  questionList
    }
}