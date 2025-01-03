package com.islamic.prayertimesapp.presentation.splash.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.islamic.prayertimesapp.R
import java.util.concurrent.TimeUnit

class PrayerNotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    // قائمة الأحاديث المتنوعة
    private val ahadith = listOf(
        "اللهم صل وسلم على نبينا محمد",
        "من صلى عليَّ واحدة صلى الله عليه بها عشرا",
        "من أحب أن يلقى الله وهو عليه راضٍ فليكثر من الصلاة عليَّ",
        "إن أولى الناس بي يوم القيامة أكثرهم عليَّ صلاة",
        "اللهم صل على محمد وعلى آل محمد كما صليت على إبراهيم وعلى آل إبراهيم",
        "اللهم صل على محمد وعلى آل محمد كما باركت على إبراهيم وعلى آل إبراهيم",
        "من قال: سبحان الله وبحمده، غرست له نخلة في الجنة",
        "لا حول ولا قوة إلا بالله كنز من كنوز الجنة",
        "أفضل الذكر لا إله إلا الله، وأفضل الدعاء الحمد لله",
        "من قال: سبحان الله العظيم وبحمده، حُطّت خطاياه ولو كانت مثل زبد البحر",
        "اللهم إني أسألك الجنة وما قرب إليها من قول أو عمل",
        "اللهم إني أعوذ بك من النار وما قرب إليها من قول أو عمل",
        "اللهم اغفر للمسلمين والمسلمات، الأحياء منهم والأموات",
        "رب اغفر لي ولوالدي رب ارحمهما كما ربياني صغيرا",
        "اللهم إنك عفو كريم تحب العفو فاعف عني",
        "سبحان الله وبحمده عدد خلقه ورضا نفسه وزنة عرشه ومداد كلماته",
        "اللهم اجعلني مقيم الصلاة ومن ذريتي ربنا وتقبل دعاء",
        "اللهم أعني على ذكرك وشكرك وحسن عبادتك",
        "اللهم إني أعوذ بك من الهم والحزن والعجز والكسل والبخل والجبن وضلع الدين وغلبة الرجال",
        "اللهم اجعل خير عمري آخره وخير عملي خواتيمه وخير أيامي يوم ألقاك",
        "اللهم اجعلني من التوابين واجعلني من المتطهرين",
        "اللهم اجعل القرآن ربيع قلبي ونور صدري وجلاء حزني وذهاب همي",
        "اللهم إني أسألك العفو والعافية في الدنيا والآخرة",
        "اللهم ارزقني حسن الخاتمة واجعلني من عبادك الصالحين",
        "اللهم طهر قلبي من النفاق وعملي من الرياء ولساني من الكذب وعيني من الخيانة",
        "يا حي يا قيوم برحمتك أستغيث أصلح لي شأني كله ولا تكلني إلى نفسي طرفة عين",
        "اللهم لك الحمد كما ينبغي لجلال وجهك وعظيم سلطانك",
        "اللهم آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار",
        "لا إله إلا أنت سبحانك إني كنت من الظالمين",
        "اللهم إنك تعلم أني أحبك فأحبني واغفر لي"
    )

    override fun doWork(): Result {
        // إرسال إشعار الصلاة على النبي مع حديث عشوائي
        sendPrayerNotification()
        return Result.success()
    }

    private fun sendPrayerNotification() {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "prayer_notification_channel"

        // إنشاء قناة الإشعار إذا كانت غير موجودة
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(channelId) == null) {
                val channel = NotificationChannel(
                    channelId,
                    "Prayer Notifications",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Channel for sending prayer notifications"
                }
                notificationManager.createNotificationChannel(channel)
            }
        }

        // الحصول على SharedPreferences لتخزين فهرس الحديث الذي تم عرضه آخر مرة
        val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences("PrayerPrefs", Context.MODE_PRIVATE)
        val currentIndex = sharedPreferences.getInt("last_shown_hadith_index", -1)

        // تحديد فهرس الحديث التالي الذي سيتم عرضه
        val nextIndex = (currentIndex + 1) % ahadith.size // التكرار على الأحاديث بشكل دائري

        // اختيار الحديث التالي بناءً على الفهرس
        val selectedHadith = ahadith[nextIndex]

        // حفظ الفهرس الجديد في SharedPreferences
        sharedPreferences.edit().putInt("last_shown_hadith_index", nextIndex).apply()

        // تعيين الصوت المخصص للإشعار
        val soundUri: Uri = Uri.parse("android.resource://${applicationContext.packageName}/raw/azan") // تأكد من صحة اسم الملف ووجوده

        // بناء الإشعار
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("الصلاة على النبي")
            .setContentText(selectedHadith) // عرض الحديث المحدد
            .setSmallIcon(R.drawable.logoazan) // تأكد من وجود هذا المورد
            .setAutoCancel(true)
            .setSound(soundUri)
            .build()

        // عرض الإشعار بمعرف فريد لتجنب استبدال الإشعارات السابقة
        val notificationId = (System.currentTimeMillis() % Integer.MAX_VALUE).toInt() // معرف فريد لكل إشعار
        notificationManager.notify(notificationId, notification)
    }

    companion object {
        // دالة لجدولة إشعار الصلاة على النبي بشكل دوري كل 5 دقائق
        fun schedulePrayerNotification(context: Context) {
            val workRequest = PeriodicWorkRequestBuilder<PrayerNotificationWorker>(
                5, TimeUnit.MINUTES // تحديد العمل الدوري كل 5 دقائق
            ).build()

            // جدولة العمل الدوري
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "PrayerNotificationWorker", // اسم العمل لتجنب تكرار الجدولة
                ExistingPeriodicWorkPolicy.REPLACE, // استبدال العمل إذا كان موجوداً مسبقاً
                workRequest
            )
        }
    }
}
