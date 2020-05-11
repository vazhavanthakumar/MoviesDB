package com.baseproject.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.Settings
import android.text.format.Time
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.squareup.moshi.Moshi
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.charset.Charset
import java.security.SecureRandom
import java.text.*
import java.util.*
import java.util.regex.Pattern

class CodeSnippet(private val mContext: Context) {

    //Time AM or PM
    private val PM = "PM"
    private val AM = "AM"
    private val TAG = javaClass.simpleName


    // Do something for marshmallow and above versions
    // do something for phones running an SDK before marshmallow
    val isAboveMarshmallow: Boolean
        get() {
            val currentapiVersion = Build.VERSION.SDK_INT
            return currentapiVersion >= Build.VERSION_CODES.M
        }

    // Do something for marshmallow and above versions
    // do something for phones running an SDK before marshmallow
    val isAboveLollipop: Boolean
        get() {
            val currentapiVersion = Build.VERSION.SDK_INT
            return currentapiVersion >= Build.VERSION_CODES.LOLLIPOP
        }

    /**
     * Returns the current date.
     *
     * @return Current date
     */

    val currentDate: Date
        get() = Date(System.currentTimeMillis())


    /*
       public ByteArrayBody getCompressedImage(String path) {

           Bitmap imageBitmap = getBitmap(path);

           if (imageBitmap != null) {
               ByteArrayOutputStream bos = new ByteArrayOutputStream();
               ByteArrayBody bab;
               imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
               byte[] data = bos.toByteArray();
               bab = new ByteArrayBody(data, "" + System.currentTimeMillis() + "displayPicture.jpg");
               return bab;
           }
           return null;
       }*/


    fun convert24To12Hr(hour: Int, min: Int): String {
        var hour = hour
        //String timeHour;
        val timeMin: String
        var isAm = true
        if (hour > 12) {
            isAm = false
            hour = hour - 12
        } else if (hour == 12)
            isAm = false
        // timeHour = hour > 9 ? String.valueOf(hour) : "0" + hour;
        timeMin = if (min > 9) min.toString() else "0$min"
        return hour.toString() + ":" + timeMin + if (isAm) " am" else " pm"


    }


    fun <T> getObjectFromJsonString(jsonString: String?, classType: Class<T>): T? {
        if (jsonString == null)
            return null
        try {
            val moshi: Moshi = Moshi.Builder().build()
            return moshi.adapter(classType).fromJson(jsonString)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun prepareFilePart(partName: String, file: File): MultipartBody.Part {
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

    fun createRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }

    //    private Bitmap getBitmap(final String imagePath) {
    //
    //        Bitmap bitmap = null;
    //        try {
    //
    //           bitmap = Glide.with(mContext).load("file://" + imagePath).asBitmap().into(500, 500).get();
    //
    //        } catch (InterruptedException | ExecutionException e) {
    //            e.printStackTrace();
    //        }
    //        return bitmap;
    //    }

    @SuppressLint("MissingPermission")
            /**
             * Check which type of connection the device is connected to
             */


    fun convertDateToString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        return df.format(date)
    }

    fun convertDateToShortString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("dd/MM/yy")
        return df.format(date)
    }

    fun convertTodayDateToString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("MMMM dd")
        return df.format(date)
    }

    fun requestBodyStringConversion(details: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), details)
    }
//    fun requestBodyStringConversion(details: JSONObject): RequestBody {
//        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), details)
//    }

//    fun requestBodyArraylistConversion(details: MutableList<String>): RequestBody {
//        return RequestBody.create(MediaType.parse("text/plain"), details)
//    }

    fun convertTodayDateToYearString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("yyyy")
        return df.format(date)
    }

    fun convertShortDateToString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("MMM dd")
        return df.format(date)
    }

    fun convertTimeToString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("hh:mm aa")
        return df.format(date)
    }

    fun convertLongDateToString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("MMM yyyy")
        return df.format(date)
    }

    fun convertSpecificDateFormatToString(date: Date): String {
        val df: DateFormat = SimpleDateFormat("ddMMyyyyHHmmss")
        return df.format(date)
    }

    fun getWeekMonthNumber(timeStamp: Long, isWeek: Boolean): String {
        val c = Calendar.getInstance()
        c.timeInMillis = timeStamp

        return if (isWeek)
            c.get(Calendar.WEEK_OF_YEAR).toString()
        else
            (c.get(Calendar.MONTH) + 1).toString()
    }



    fun isTodayLieInBetween(str1: String, str2: String): Boolean {
        try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val todayStr = formatter.format(Calendar.getInstance().time)
            val todayDate = formatter.parse(todayStr)
            val date1 = formatter.parse(str1)
            val date2 = formatter.parse(str2)

            return date1.compareTo(todayDate) <= 0 && date2.compareTo(todayDate) >= 0
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    fun getCalendarTime(time: String): Calendar? {
        try {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val calendar = Calendar.getInstance()
            calendar.time = formatter.parse(time)
            return calendar
        } catch (e: Exception) {
           e.printStackTrace()
        }

        return null
    }

    fun getCalendarTime(time: Calendar): String? {
        try {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            return formatter.format(time)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getCalendarUTCFormat(time: String): String? {
        try {
            val customFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            customFormat.timeZone = TimeZone.getTimeZone("UTC")
            //return new SimpleDateFormat("dd yyyy MMM  hh:mm a", Locale.getDefault()).format(customFormat.parse(time));
            return SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(customFormat.parse(time))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


    fun getCalendarUTCFormat(time: String, fromFormat: String, toFormat: String): String? {
        try {
            val customFormat = SimpleDateFormat(fromFormat, Locale.getDefault())
            return SimpleDateFormat(toFormat, Locale.getDefault()).format(customFormat.parse(time))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getCalendarForYear(time: String): Calendar? {
        try {

            val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.time = formatter.parse(time)
            return calendar
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getCalendarToStandard(time: String): Calendar? {
        try {
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.time = formatter.parse(time)
            return calendar
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getCalendarToStandard(timeStamp: Long): Calendar? {
        try {
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeStamp
            return calendar
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getCalendarWithTimeOnly(time: String): Calendar {
        try {
            val formatter = SimpleDateFormat("hh:mm aa", Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.time = formatter.parse(time)
            return calendar
        } catch (e: Exception) {
            e.printStackTrace()
            val formatter = SimpleDateFormat("hh:mm aa", Locale.getDefault())
            val calendar = Calendar.getInstance()
            try {
                calendar.time = formatter.parse(time)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }

            return calendar
        }

    }

    fun getDayOfMonthMonthAndYear(calendar: Calendar): String {
        //TODO mention
        var dateString = ""
        val formatter = DecimalFormat("00")
        dateString = formatter.format(calendar.get(Calendar.DAY_OF_MONTH).toLong()) + " " +
                monthNameFromInt(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR)
        return dateString
    }

    fun getDayOfMonthMonthAndYearStd(calendar: Calendar): String {
        var dateString = ""
        val month = calendar.get(Calendar.MONTH) + 1
        val formatter = DecimalFormat("00")
        dateString = formatter.format(calendar.get(Calendar.DAY_OF_MONTH).toLong()) + "-" +
                formatter.format(month.toLong()) + "-" + calendar.get(Calendar.YEAR)
        return dateString
    }

    private fun monthNameFromInt(monthInt: Int): String {
        var month = ""
        val dfs = DateFormatSymbols()
        val months = dfs.months
        if (monthInt in 0..11) {
            month = months[monthInt]
        }
        return month.substring(0, 3)
    }

    fun getPastTimerString(calendar: Calendar?): String {
        val time = System.currentTimeMillis() - (calendar?.timeInMillis ?: 0)
        val mins = time / 60000
        if (mins > 59L) {
            val hours = mins / 60
            if (hours > 24) {
                val days = hours / 24
                return if (days > 1) {
                    (days).toString() + "days ago"
                } else {
                    (days).toString() + "days ago"
                }
            } else {
                return (hours).toString() + " hours ago"
            }
        } else {
            return "less than a minute"
        }
    }

    fun getTimeFromMilisecond(timeStamp: Long): Date {
        return Date(timeStamp)
    }

    fun getOrdinaryTime(time: Time): String {
        return if (time.hour > 12) {
            (formatTime(time.hour - 12) + ":" + formatTime(time.minute)
                    + " " + getAMorPM(time))
        } else ((if (time.hour == 0) (12).toString() else formatTime(time.hour)) + ":"
                + formatTime(time.minute) + " " + getAMorPM(time))

    }


    fun getOrdinaryDate(calendar: Calendar): String {

        val month = calendar.get(Calendar.MONTH) + 1
        val formatter = DecimalFormat("00")
        Timber.d( "getOrdinaryDate : ${formatter.format(month.toLong())}")
        return formatter.format(calendar.get(Calendar.DAY_OF_MONTH).toLong()) + "/" + formatter.format(month.toLong()) + "/" + calendar.get(
            Calendar.YEAR
        )
    }

    fun getOrdinaryTime(calendar: Calendar): String {

        val min = calendar.get(Calendar.MINUTE)
        //Log.d(TAG,"hours :"+calendar.get(Calendar.HOUR_OF_DAY));
        var meridian = "AM"
        if (calendar.get(Calendar.HOUR_OF_DAY) > 11) {
            meridian = "PM"
        }
        val formatter = DecimalFormat("00")
        return formatter.format(calendar.get(Calendar.HOUR).toLong()) + ":" + formatter.format(min.toLong()) + " " + meridian
    }

    fun getOrdinaryDateWithFipe(date: Time): String {

        return (date.monthDay).toString() + " | " + date.month + " | " + date.year
    }

    private fun formatTime(time: Int): String {
        return if ((time).toString().length < 2)
            "0" + time
        else
            (time).toString()
    }

    private fun getAMorPM(time: Time): String {
        return if (time.hour > 11) {
            PM
        } else
            AM
    }

    @SuppressLint("MissingPermission")
            /**
             * Checking the internet connectivity
             *
             * @return true if the device has a network connection; false otherwise.
             */
    fun hasNetworkConnection(): Boolean {
        /* val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         val info = connectivityManager.activeNetworkInfo
             if (info != null) {
                 if (info.state == NetworkInfo.State.CONNECTED) {
                     return true
                 }
             }
         return false*/
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    /*public boolean checkPlayServices(Context context, OnGooglePlayServiceListener googlePlayServiceListener) {
           int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
           if (status != ConnectionResult.SUCCESS) {
               if (GoogleApiAvailability.getInstance().isUserResolvableError(status)) {
                   showGooglePlayDialog(context, googlePlayServiceListener);
               } else {
                   googlePlayServiceListener.onCancelServiceInstallation();
               }
               return false;
           }
           return true;
       }*/

    private fun showGooglePlayDialog(context: Context, googlePlayServiceListener: OnGooglePlayServiceListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Get Google Play Service")
        builder.setMessage("This app won't run without Google Play ServicesData, which are missing from your phone")
        builder.setPositiveButton(
            "Get Google Play Service"
        ) { dialog, which ->
            googlePlayServiceListener.onInstallingService()
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(("https://play.google.com/store/apps/details?" + "id=com.google.android.gms"))
                )
            )
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                googlePlayServiceListener.onCancelServiceInstallation()
            }
        })
        builder.setCancelable(false)
        val alert = builder.create()
        alert.show()
    }

    private fun getSettingsIntent(settings: String): Intent {
        return Intent(settings)
    }

    private fun startActivityBySettings(context: Context, settings: String) {
        context.startActivity(getSettingsIntent(settings))
    }

    private fun startActivityBySettings(context: Context, intent: Intent) {
        context.startActivity(intent)
    }

    fun showGpsSettings(context: Context) {
        startActivityBySettings(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    }

    fun showNetworkSettings() {
        val chooserIntent = Intent.createChooser(
            getSettingsIntent(Settings.ACTION_DATA_ROAMING_SETTINGS),
            "Complete action using"
        )
        val networkIntents = ArrayList<Intent>()
        networkIntents.add(getSettingsIntent(Settings.ACTION_WIFI_SETTINGS))
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, networkIntents.toTypedArray<Parcelable>())
        startActivityBySettings(mContext, chooserIntent)
    }

    fun isSpecifiedDelay(exisingTime: Long, specifiedDelay: Long): Boolean {
        return specifiedDelay >= (Calendar.getInstance().timeInMillis - exisingTime)
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus)
            imm.hideSoftInputFromWindow(
                activity.currentFocus!!
                    .applicationWindowToken, 0
            )
    }

    fun showKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus)
            imm.showSoftInputFromInputMethod(
                activity.currentFocus!!
                    .applicationWindowToken, 0
            )
    }

    fun isNull(`object`: Any?): Boolean {
        return null == `object` || `object`.toString().compareTo("null") == 0
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    /**
     * Fetch the drawable object for the given resource id.
     *
     * @param resourceId to which the value is to be fetched.
     * @return drawable object for the given resource id.
     */

    fun getDrawable(resourceId: Int): Drawable? {
        return ResourcesCompat.getDrawable(mContext.resources, resourceId, null)
    }

    /**
     * Fetch the string value from a xml file returns the value.
     *
     * @param resId to which the value has to be fetched.
     * @return String value of the given resource id.
     */

    fun getString(resId: Int): String {
        return mContext.resources.getString(resId)
    }

    /**
     * Fetch the color value from a xml file returns the value.
     *
     * @param colorId to which the value has to be fetched.
     * @return Integer value of the given resource id.
     */

    fun getColor(colorId: Int): Int {
        return ContextCompat.getColor(mContext, colorId)
    }


    private interface OnGooglePlayServiceListener {
        fun onInstallingService()

        fun onCancelServiceInstallation()
    }


    /*

     fun <T> getObjectFromJsonString(jsonString: String, classType: Class<T>): T? {
        try {
            return LoganSquare.parse(jsonString, classType)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return null
    }

    fun <T> getJsonStringFromObject(obj: T): String? {
        try {
            return LoganSquare.serialize(obj)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return null
    }


    fun <T> getListFromJsonString(jsonString: String, classType: Class<T>): List<T>? {
        try {
            return LoganSquare.parseList(jsonString, classType)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return null
    }*/


   /* fun <T> getJsonStringFromObject(`object`: T, classType: Class<T>): String {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        return gson.toJson(`object`, classType)
    }

    fun <T> getObjectFromJson(json: String, classType: Class<T>): T {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        return gson.fromJson(json, classType)
    }
*/
    fun loadJSONFromAsset(fileName: String): String? {
        val json: String?
        try {
            val `is` = mContext.assets.open("config/" + fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setImageRes(iv: ImageView, @DrawableRes res: Int, @ColorInt colorRes: Int) {
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && iv.background is RippleDrawable)) {
            val rd = iv.background as RippleDrawable
            rd.setColor(ColorStateList.valueOf(adjustAlpha(colorRes, 0.3f)))
        }
        var d = AppCompatResources.getDrawable(iv.context, res)
        d = DrawableCompat.wrap(d!!.mutate())
        DrawableCompat.setTint(d!!, colorRes)
        iv.setImageDrawable(d)
    }




    companion object {

        private fun adjustAlpha(color: Int, factor: Float): Int {
            val alpha = Math.round(Color.alpha(color) * factor)
            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)
            return Color.argb(alpha, red, green, blue)
        }
    }


    fun setCircleImage(imageView: ImageView, url: String) {
        Glide.with(mContext).load(url)
            .apply(
                RequestOptions.circleCropTransform()
                    .priority(Priority.HIGH)
            )
            .into(imageView)


    }

    fun setImage(imageView: ImageView, url: String) {
        Glide.with(mContext).load(url)
            .apply(
                RequestOptions.centerCropTransform()
                    .priority(Priority.HIGH)
            ).into(imageView)

    }

    fun loadImageFromDrawable(imageView: ImageView, imageDrawable: Int, isSquare: Boolean, drawable: Int) {
        if (isSquare) {
            Glide.with(imageView.context)
                .load(imageDrawable)
                .apply(
                    RequestOptions.fitCenterTransform()
                        .placeholder(drawable)
                        .error(drawable)
                )
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(imageDrawable) // Uri of the picture
                .apply(
                    RequestOptions.circleCropTransform()
                        .placeholder(drawable)
                        .error(drawable)
                )
                .into(imageView)
        }
    }

    fun loadImage(imageView: ImageView, imagePath: String, isSquare: Boolean, drawable: Int) {
        if (isSquare) {
            Glide.with(imageView.context)
                .load(imagePath)
                .apply(
                    RequestOptions.fitCenterTransform()
                        .placeholder(drawable)
                        .error(drawable)
                )
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(imagePath) // Uri of the picture
                .apply(
                    RequestOptions().centerCrop()
                        .placeholder(drawable)
                        .error(drawable)
                )
                .into(imageView)
        }
    }

    fun loadImageFromSDCard(imageView: ImageView, imagePath: String, drawable: Int) {
        Glide.with(imageView.context)
            .load(imagePath)
            .apply(
                RequestOptions.bitmapTransform(RoundedCorners(25))
                    .placeholder(drawable)
                    .error(drawable)
            )
            .into(imageView)
    }

    fun loadImageFromSDCard(imageView: ImageView, imagePath: Uri, drawable: Int) {
        Glide.with(imageView.context)
            .load(imagePath)
            .apply(
                RequestOptions.bitmapTransform(RoundedCorners(25))
                    .placeholder(drawable)
                    .error(drawable)
            )
            .into(imageView)
    }

    fun loadImageFromSDCardCircleBitmap(imageView: ImageView, imagePath: Uri, drawable: Int) {
        Glide.with(imageView.context)
            .load(imagePath) // Uri of the picture
            .apply(
                RequestOptions().centerCrop()
                    .placeholder(drawable)
                    .error(drawable)
            )
            .into(imageView)
    }

    fun getCalendarTimeToUTC(time: Calendar): String? {
        try {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            // formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            return formatter.format(time.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


    fun randomColor(): Int {
        val rnd = SecureRandom()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        return color
    }

    private fun getPixelValueFromDimension(i: Int): Int {
        val r = mContext.resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            i.toFloat(),
            r.displayMetrics
        ).toInt()

        return px
    }

    fun recyclerViewHorizontalSpace(
        params: RelativeLayout.LayoutParams, adapterPosition: Int,
        spanCount: Int,
        leftWithOutSpace: Int,
        topWithOutSpace: Int,
        rightWithOutSpace: Int,
        bottomWithOutSpace: Int,
        leftWithSpace: Int,
        topWithSpace: Int,
        rightWithSpace: Int,
        bottomWithSpace: Int
    ): RelativeLayout.LayoutParams {
        if ((adapterPosition + 1) % spanCount == 0) {
            params.setMargins(
                getPixelValueFromDimension(leftWithOutSpace),
                getPixelValueFromDimension(topWithOutSpace),
                getPixelValueFromDimension(rightWithOutSpace),
                getPixelValueFromDimension(bottomWithOutSpace)
            )
        } else {
            params.setMargins(
                getPixelValueFromDimension(leftWithSpace),
                getPixelValueFromDimension(topWithSpace),
                getPixelValueFromDimension(rightWithSpace),
                getPixelValueFromDimension(bottomWithSpace)
            )


        }

        return params
    }

     fun getFileFromBitmap(bitmap: Bitmap, name:String ) : File{
        val filesDir = mContext.cacheDir;
        val imageFile =  File(filesDir, name + ".jpg");

        var os:OutputStream
        try {
            os =  FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (e:Exception) {

        }
         return imageFile
     }


    fun prepareFilePart(partName: String, filepath: String?): MultipartBody.Part? {
        return if (filepath != null && filepath.isNotEmpty()) {
            val file = File(filepath)
            val requestFile = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                file
            )
            MultipartBody.Part.createFormData(partName, file.name, requestFile)
        } else {
            null
        }
    }

    fun getBitmapRotateImage(image:String?) : Bitmap? {
        val photo = BitmapFactory.decodeFile(image)


        return photo
    }
    fun getAddress(lat:Double,long:Double): MutableList<Address>? {

        val geocoder = Geocoder(mContext)
         return geocoder.getFromLocation(lat,long,1)
    }


    fun getImageFromUri(selectedImage:Uri?) : String?{
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = mContext.contentResolver
            ?.query(selectedImage!!, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val imgDecodableString = cursor?.getString(columnIndex!!)
        cursor?.close()
        return imgDecodableString
    }

    fun preparePartFileFromFile(file:File?,partName: String): MultipartBody.Part?{
        return if (file != null ) {
            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

             MultipartBody.Part.createFormData(partName, file.name, requestFile)
        } else {
            null
        }

    }

    fun isContainsUppercase(value : String) : Boolean {
        return Pattern.matches(".*[A-Z].*",value)
    }

    fun isContainsLowercase(value : String) : Boolean {
        return  Pattern.matches(".*[a-z].*",value)
    }

    fun isContainsNumber(value : String) : Boolean {
        return Pattern.matches(".*\\d.*",value)
    }

    fun isCanadianPostalCode(value: String): Boolean {
        return Pattern.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$", value)
    }

    fun isContainsSymbol(value : String) : Boolean {
        return Pattern.matches(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*",value)
    }

    fun bitmapToFile(bitmap: Bitmap, mContext: Context): File {
        val wrapper = ContextWrapper(mContext)
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.png")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

}