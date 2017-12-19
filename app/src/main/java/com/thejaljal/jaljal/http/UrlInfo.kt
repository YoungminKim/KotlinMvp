package test.ym.kotilnpj.http

/**
 * Created by no.1 on 2017-08-25.
 */

object UrlInfo{
        const val SSL: Boolean = true

        @JvmStatic fun getHttp() :String{
            if(SSL) return "https://"
            else return "http://"
        }
        @JvmStatic val URL_HEADER: String = UrlInfo.getHttp()

        const val DOMAIN: String = "www.thejaljal.com/api/"
        @JvmStatic var API_HEADER: String = ""


        @JvmStatic var HEADER: String = URL_HEADER + DOMAIN + API_HEADER

        const val CHECK_SERVICE = "checkVersion"
        const val LOGIN = "login"
        const val WEEK_RECIPE = "weekRecipe"
        const val RECIPE_CALENDAR = "recipeCalendar"
        const val RECIPE_BOOK = "recipeBook"
        const val RECIPE = "recipe"
        const val FAQ = "faq"
        const val USER_INFO = "userInfo"
        const val WRITE_INQUIRY = "writeInquiry"
        const val MAININGR_LIST = "mainIngrList"
        const val PROGRAM_LIST = "programList"
        const val CHECK_MODIFY_STATUS = "checkModifyStatus"
        const val SET_DELIVERY_STATUS = "setDeliveryStatus"
        const val PREMIUM_ITEMS = "premiumItems"
        const val CANCEL_PREMIUM_ITEMS = "cancelPremiumItems"




        const val IMAGE_HEARDER = "http://image.thejaljal.com/"

        val RECIPE_PATH = IMAGE_HEARDER + "recipe/"
        const val COOK_STEP_PATH = IMAGE_HEARDER + "cookingStep/"
        const val TIP_PATH = IMAGE_HEARDER + "tips/"
        const val PREMIUM_ITEM_PATH = IMAGE_HEARDER + "additionalOrder/"
        const val UPDATE_PUSH_TOKEN = "updatePushToken/"

}