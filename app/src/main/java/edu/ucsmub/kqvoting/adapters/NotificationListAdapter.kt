package edu.ucsmub.kqvoting.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.UZawTextView
import edu.ucsmub.kqvoting.db.model.Notification
import edu.ucsmub.kqvoting.extra.dateToMilliSec
import edu.ucsmub.kqvoting.extra.dateToString
import edu.ucsmub.kqvoting.extra.milliToDate
import kotlinx.android.synthetic.main.notification_item.view.*

class NotificationListAdapter(var context: Context, var notification_list: List<Notification>) :
    RecyclerView.Adapter<NotificationListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = notification_list.size


    override fun onBindViewHolder(holder: NotificationListAdapter.MyViewHolder, position: Int) {
        with(holder) {
            title.text = notification_list[position].title
            body.text = notification_list[position].body
            time.text = dateToString(notification_list[position].time,"MMM dd, yyyy• hh:mm a")
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: UZawTextView = itemView.noti_title
        var body: UZawTextView = itemView.noti_detail
        var time: UZawTextView = itemView.noti_time
    }
}