package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-10-10.
 */

data class WeekRecipe(val result: Boolean?, val message: String?, val data: Data){
    data class Data(
            var recipeList: ArrayList<Recipe.Data> = arrayListOf<Recipe.Data>(),
            var weekMenu: WeekMenu?){
        data class WeekMenu(val dispYn: String?, val stopImageFile: String)
    }


}