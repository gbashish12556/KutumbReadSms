package com.example.kutumbreadsms


import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.kutumbreadsms.data.SmsData
import com.example.kutumbreadsms.ui.MainActivity


class SMSBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action.equals(SMS_RECEIVED)) {
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<Any>?
                val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(pdus!!.size)
                for (i in pdus!!.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                }
                if (messages.size > -1) {
                    var smsMessag:SmsMessage = messages[0]!!
                    sendNotification(
                        SmsData(messages[0]!!.indexOnIcc,
                            smsMessag.displayOriginatingAddress,
                            smsMessag.messageBody),
                        context)
                }
            }
        }
    }

    companion object {
        private const val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
        private const val TAG = "SMSBroadcastReceiver"
    }

    private fun sendNotification(smsData: SmsData, context: Context?) {
        try {
            val pendingIntent: PendingIntent = getPendingIntent(smsData, context)
            val builder: NotificationCompat.Builder = getBuilder(smsData, pendingIntent, context)
            setPriorityToBuilder(builder)
            notifyUser(builder,context)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    @Throws(ClassNotFoundException::class)
    private fun getPendingIntent(smsData: SmsData, context: Context?): PendingIntent {
        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        var bundle = Bundle()
        bundle.putSerializable("smsData",smsData)
        activityIntent.putExtras(bundle)
        val taskStackBuilder = TaskStackBuilder.create(context!!.getApplicationContext())
        taskStackBuilder.addNextIntentWithParentStack(activityIntent)
        return taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun notifyUser(builder: NotificationCompat.Builder, context: Context?) {
        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.notify(0, builder.build())
    }

    private fun setPriorityToBuilder(
        builder: NotificationCompat.Builder
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.priority = NotificationManager.IMPORTANCE_HIGH
        } else {
            builder.priority = NotificationCompat.PRIORITY_HIGH
        }
    }

    private fun getBuilder(
        smsData: SmsData,
        pendingIntent: PendingIntent,
        context: Context?
    ): NotificationCompat.Builder {
        val notificationbuilder = NotificationCompat.Builder(context!!.getApplicationContext(), "SmsChannel"!!)
            .setSmallIcon(R.drawable.ic_baseline_person_24)
            .setContentTitle(smsData.sender)
            .setContentText(smsData.message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setChannelId("SmsChannel")
        return notificationbuilder
    }
}
