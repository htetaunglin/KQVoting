package edu.ucsmub.kqvoting.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import edu.ucsmub.kqvoting.customUI.UZawTextView
import kotlinx.android.synthetic.main.list_card.view.*
import android.view.LayoutInflater
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.GlideApp
import edu.ucsmub.kqvoting.extra.ConfirmItem
import kotlinx.android.synthetic.main.confirm_list_item.view.*
import kotlinx.android.synthetic.main.voted_item.view.*


class ConfirmListAdapter(var context: Context, var list: List<ConfirmItem>) :
    RecyclerView.Adapter<ConfirmListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.confirm_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            val item = list[position]

            GlideApp.with(context)
                .load(item.icon)
                .into(categoryIcon)


//                .load(item.selection.profile_image)
            GlideApp.with(context)
                .load(item.selection.profile_image)
                .placeholder(R.drawable.pending_people)
                .error(R.drawable.errorimage1)
                .into(profile)
            name.text = item.selection.name
            categoryName.text = item.category.name
//            section.text = "Section - "+item.selection.section
//            categorytxt.text = item.category.name
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profile: ImageView = itemView.profile_
        var name: UZawTextView = itemView.selected_name
        //        var categorytxt: UZawTextView = itemView.voted_cate
        var categoryIcon: ImageView = itemView.cate_icon
        var categoryName: UZawTextView = itemView.category_name
    }
}