package com.example.quizzapp

import java.util.Collections.shuffle

object Constants {

    fun getRandomQuestions(selectedFlagsList: List<MyFlagDataItem>, wholeFlagsList: MyFlagData): ArrayList<RandomQuestion> {
        val questionsList = ArrayList<RandomQuestion>()
        val q = "What country does this flag belong to?"

        selectedFlagsList.forEachIndexed { index, itr ->
            val optionsList: ArrayList<MyFlagDataItem> = wholeFlagsList.filter{ it.name != itr.name } as ArrayList<MyFlagDataItem>
            val finalOptionsList = optionsList.shuffled().take(3) as ArrayList<MyFlagDataItem>
            finalOptionsList.add(itr)
            shuffle(finalOptionsList)
            val idx = finalOptionsList.indexOf(itr)
            val que = RandomQuestion(
                index,
                q,
                R.drawable.flag_default,
                finalOptionsList[0].name.common,
                finalOptionsList[1].name.common,
                finalOptionsList[2].name.common,
                finalOptionsList[3].name.common,
                idx,
                itr.altSpellings[0]
            )
            questionsList.add(que)
        }
        return questionsList
    }

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val q = "What country does this flag belong to?"
        val que1 = Question(1,
            q,
            R.drawable.germany,
            "Austria",
            "Germany",
            "Armenia",
            "Austria",
            2)

        val que2 = Question(2,
            q,
            R.drawable.spain,
            "Mexico",
            "Netherlands",
            "Spain",
            "Sweden",
            3)

        questionsList.add(que1)
        questionsList.add(que2)

        return questionsList
    }
}