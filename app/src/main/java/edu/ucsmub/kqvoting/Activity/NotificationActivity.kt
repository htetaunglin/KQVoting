package edu.ucsmub.kqvoting.Activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.adapters.NotificationListAdapter
import edu.ucsmub.kqvoting.viewModel.NotificationViewModel
import kotlinx.android.synthetic.main.activity_notification.*
import androidx.recyclerview.widget.DividerItemDecoration


class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setSupportActionBar(notification_tool_bar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Notification"

        val notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)
        notificationViewModel.getAllNotification().observe(this, Observer {
            val adapter = NotificationListAdapter(this, it)
            recycler_notification.layoutManager = LinearLayoutManager(this)
            recycler_notification.itemAnimator = DefaultItemAnimator()
            recycler_notification.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            recycler_notification.addItemDecoration(dividerItemDecoration)
            adapter.notifyDataSetChanged()
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}