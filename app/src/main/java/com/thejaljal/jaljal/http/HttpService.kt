package test.ym.kotilnpj.http

/**
 * Created by no.1 on 2017-08-25.
 */



import com.thejaljal.jaljal.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import kotlin.collections.HashMap

interface HttpService{


    @GET(UrlInfo.LOGIN)
    fun login(@QueryMap params:HashMap<String, Any>): Observable<Login>

    @GET(UrlInfo.WEEK_RECIPE)
    fun weekRecipe(@QueryMap params:HashMap<String, Any>): Observable<WeekRecipe>

    @GET(UrlInfo.RECIPE_CALENDAR)
    fun recipeCalendar(@QueryMap params:HashMap<String, Any>): Observable<RecipeCalendar>


    @POST(UrlInfo.UPDATE_PUSH_TOKEN)
    fun updateToken(@QueryMap params: java.util.HashMap<String, Any>): Observable<Common>

    @GET(UrlInfo.CHECK_SERVICE)
    fun checkService(@QueryMap params: java.util.HashMap<String, Any>): Observable<VersionService>


    @GET(UrlInfo.RECIPE_BOOK)
    fun recipeBook(@QueryMap params: java.util.HashMap<String, Any>): Observable<RecipeBook>

    @GET(UrlInfo.RECIPE)
    fun recipe(@QueryMap params: java.util.HashMap<String, Any>): Observable<Recipe>

    @GET(UrlInfo.FAQ)
    fun faqList(@QueryMap params: java.util.HashMap<String, Any>): Observable<Faq>

    @GET(UrlInfo.USER_INFO)
    fun getUserInfo(@Query("accessKey") params: String): Observable<UserInfo>


    @GET(UrlInfo.WRITE_INQUIRY)
    fun writeInquiry(@QueryMap params: java.util.HashMap<String, Any>): Observable<Common>

    @GET(UrlInfo.MAININGR_LIST)
    fun getMainIngrList(@QueryMap params: java.util.HashMap<String, Any>): Observable<MainIngr>

    @GET(UrlInfo.PROGRAM_LIST)
    fun getProgramList(@QueryMap params: java.util.HashMap<String, Any>): Observable<Program>

    @GET(UrlInfo.CHECK_MODIFY_STATUS)
    fun checkModifyStatus(@QueryMap params: java.util.HashMap<String, Any>): Observable<Common>

    @GET(UrlInfo.SET_DELIVERY_STATUS)
    fun setDeliveryStatus(@QueryMap params: java.util.HashMap<String, Any>): Observable<Common>

    @GET(UrlInfo.PREMIUM_ITEMS)
    fun getPremiumItems(@QueryMap params: java.util.HashMap<String, Any>): Observable<PremiumItem>

    @GET(UrlInfo.CANCEL_PREMIUM_ITEMS)
    fun cancelPremiumItem(@QueryMap params: java.util.HashMap<String, Any>): Observable<Common>




}