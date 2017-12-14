package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-11-15.
 */

data class RecipeBook(val result: Boolean?, val message: String?, val data: Data?){

    data class Data(val recipeList: ArrayList<Recipe.Data>)
}