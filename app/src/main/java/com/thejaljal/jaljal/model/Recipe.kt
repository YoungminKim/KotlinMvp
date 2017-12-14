package com.thejaljal.jaljal.model

import kotlin.collections.ArrayList

/**
 * Created by no.1 on 2017-09-05.
 */

data class Recipe(val result: Boolean?, val message: String?, val data: Data?){

    data class Data(
        var rcipSeq: Int = 0,
        var prgrSeq: Int = 0,
        var menuName: String? = null,
        var ingrImg: String? = null,
        var mainImgFile: String? = null,
        var cookTime: String? = null,
        var cookAmnt: String? = null,
        var calorie: String? = null,
        var detailInst: String? = null,
        var mainIngr: String? = null,
        var simpleInst: String? = null,
        var thmbFile: String? = null,
        var mainVideo: String? = null,

        var ingredientList: ArrayList<Ingredient> = arrayListOf<Ingredient>(),
        var tipsList: ArrayList<Tip> = ArrayList<Tip>(),

        var tip: Tip? = null,
        var cookingStepList:ArrayList<Step> = ArrayList<Step>()
    )



    data class Ingredient (
            var rownum: Int = 0,
            var infrSeq: Int = 0,
            var rcipSeq: Int = 0,
            var ingrName: String? = null,
            var ingrOrigin: String? = null,
            var mesrUnit: String? = null,
            var mesr: String? = null,
            var ptnrName: String? = null,
            var regDt: String? = null
            )


    data class Tip (
        var tipSeq: Int = 0,
        var thmbFile: String? = null,
        var mainIngr: String? = null,
        var tipName: String? = null,
        var tipDetl: String? = null,
        var tipVideo: String? = null
    )



    data class Step (
        var cookSeq: Int = 0,
        var rcipSeq: Int = 0,
        var cookNo: Int = 0,
        var cookName: String? = null,
        var thmbFile: String? = null,
        var cookVideo: String? = null,
        var stepDetl: String? = null
    )
}