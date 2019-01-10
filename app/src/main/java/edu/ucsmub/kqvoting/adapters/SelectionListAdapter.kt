package edu.ucsmub.kqvoting.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import edu.ucsmub.kqvoting.customUI.UZawTextView
import edu.ucsmub.kqvoting.db.model.Selection
import kotlinx.android.synthetic.main.list_card.view.*
import android.view.LayoutInflater
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.GlideApp
import edu.ucsmub.kqvoting.extra.Ratio
import android.app.Activity
import android.content.Intent
import edu.ucsmub.kqvoting.Activity.DetailActivity
import android.R.attr.transitionName
import android.app.ActivityOptions
import android.os.Build
import androidx.annotation.RequiresApi


class SelectionListAdapter(var context: Context, var selection_list: List<Selection>) :
    RecyclerView.Adapter<SelectionListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = selection_list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            val selection = selection_list[position]

            GlideApp.with(context)
                .load(selection.profile_image)
                .placeholder(R.drawable.pending_people)
                .error(R.drawable.errorimage1)
                .into(profile)

            name.text = selection.name
            section.text = "Section - ${selection.section}"

            val ratio = Ratio(context)
            ratio.calculateSize(holder.profile, context as Activity, 340, 340)

            card.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("selection", selection)

                val sharedView = profile
                val transitionName = "profile_image"
                val transitionActivityOptions =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions.makeSceneTransitionAnimation(context as Activity, sharedView, transitionName)
                    } else {
                        null
                    }
                context.startActivity(intent, transitionActivityOptions!!.toBundle())
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var profile: ImageView = itemView.selection_profile
        var name: UZawTextView = itemView.selection_name
        var section: UZawTextView = itemView.selection_section
        var card: LinearLayout = itemView.selection_card
    }
}