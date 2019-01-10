package edu.ucsmub.kqvoting.Activity.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source

import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.GlideApp
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.viewModel.SelectionViewModel
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.fragment_result.view.*
import org.jetbrains.anko.doAsync

class ResultFragment : Fragment() {

    var currentCount = 0
    var totalCount = 0

    var kingID = "-100"
    var queenID = "-100"
    val db = FirebaseFirestore.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val appDatabase = AppDatabase.getInstance(context!!)


        val voted_document_collection = db.collection("key_collection")

        val result_collection= db.collection("result_controller")
        result_collection.document("result").addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            val up = documentSnapshot!!.data!!["up"].toString().toInt()
            val down = documentSnapshot.data!!["down"].toString().toInt()

            view.donut_progress.max = down
            view.donut_progress.progress = up.toFloat()
            totalCount = down
            currentCount = up
            view.super_progress_text.text = "$up\n/\n$down"

        }
//        voted_document_collection.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//            val i = querySnapshot!!.documents.size
//            view.donut_progress.max = i
//            totalCount = i
//            view.super_progress_text.text = "$currentCount\n/\n$totalCount"
//        }
//
//        voted_document_collection.whereEqualTo("isVoted", true)
//            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                val i = querySnapshot!!.documents.size
//                view.donut_progress.progress = i.toFloat()
//                currentCount = i
//                view.super_progress_text.text = "$currentCount\n/\n$totalCount"
//            }

        val selectionCollection = db.collection("selection")
        selectionCollection.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            //UPDATE
            querySnapshot!!.documents.forEach {
                val b1ID = it["id"].toString()
                val bk1 = it["firstCount"].toString().toInt()
                val bp1 = it["secondCount"].toString().toInt()
                doAsync {
                    appDatabase.selectionDao().updateCountsByID(bk1, bp1, b1ID)
                }
            }
        }

        val controller = db.collection("controller")
        controller.document("votingresultonoff").addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            val onoff = documentSnapshot!!.data!!["onoff"].toString().toBoolean()
            if (onoff) {
                showResult(view)
            } else {
                initResultUI(view)
            }
        }


        return view
    }

    private fun Float.toPercentIn(baseNum: Float): String = "%.1f".format(this * (100.0f / baseNum))


    private fun initResultUI(view: View) {
        with(view) {
            king_name.text = "?"
            queen_name.text = "?"
            popular_name.text = "?"
            innocence_name.text = "?"

            king_progress_value.text = "0/0"
            queen_progress_value.text = "0/0"
            popular_progress_value.text = "0/0"
            innocence_progress_value.text = "0/0"


            king_progressBar.progress = 0
            queen_progressBar.progress = 0
            male_progressBar.progress = 0
            female_progressBar.progress = 0

            king_profile_image.setImageResource(R.drawable.placeholder)
            queen_profile_image.setImageResource(R.drawable.placeholder)
            male_profile_image.setImageResource(R.drawable.placeholder)
            female_profile_image.setImageResource(R.drawable.placeholder)

            king_percent.text = "0%"
            queen_percent.text = "0%"
            male_percent.text = "0%"
            female_percent.text = "0%"

            liveRtext.visibility = View.VISIBLE

        }
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(view: View) {
        liveRtext.visibility = View.GONE
        with(view) {
            val mViewModel = ViewModelProviders.of(this@ResultFragment).get(SelectionViewModel::class.java)

            mViewModel.getCurrentKing().observe(this@ResultFragment, Observer {
                if (currentCount != 0) {

                    kingID = it[0].id

                    king_name.text = it[0].name
                    GlideApp.with(this)
                        .load(it[0].profile_image)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.errorimage1)
                        .into(view.king_profile_image)
                    view.king_progress_value.text = "${it[0].firstCount}/$currentCount"
                    view.king_progressBar.max = currentCount
                    view.king_progressBar.progress = it[0].firstCount

                    king_percent.text = "${it[0].firstCount.toFloat().toPercentIn(currentCount.toFloat())}%"
                }
            })

            mViewModel.getCurrentQueen().observe(this@ResultFragment, Observer {
                if (currentCount != 0) {

                    queenID = it[0].id

                    queen_name.text = it[0].name
                    GlideApp.with(this)
                        .load(it[0].profile_image)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.errorimage1)
                        .into(view.queen_profile_image)
                    queen_progress_value.text = "${it[0].firstCount}/$currentCount"
                    queen_progressBar.max = currentCount.toInt()
                    queen_progressBar.progress = it[0].firstCount

                    queen_percent.text = "${it[0].firstCount.toFloat().toPercentIn(currentCount.toFloat())}%"
                }
            })


            mViewModel.getPopularList().observe(this@ResultFragment, Observer {
                if (currentCount != 0) {
                    val index =
                        if (kingID == it[0].id)
                            1
                        else
                            0

                    popular_name.text = it[index].name
                    GlideApp.with(this)
                        .load(it[index].profile_image)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.errorimage1)
                        .into(view.male_profile_image)
                    popular_progress_value.text = "${it[index].secondCount}/$currentCount"
                    male_progressBar.max = currentCount
                    male_progressBar.progress = it[index].secondCount
                    male_percent.text = "${it[index].secondCount.toFloat().toPercentIn(currentCount.toFloat())}%"
                }
            })


            mViewModel.getInnocenceList().observe(this@ResultFragment, Observer {
                if (currentCount != 0) {
                    val index =
                        if (queenID == it[0].id)
                            1
                        else
                            0

                    innocence_name.text = it[index].name
                    GlideApp.with(this)
                        .load(it[index].profile_image)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.errorimage1)
                        .into(view.female_profile_image)
                    innocence_progress_value.text = "${it[index].secondCount}/$currentCount"
                    female_progressBar.max = currentCount
                    female_progressBar.progress = it[index].secondCount

                    female_percent.text = "${it[index].secondCount.toFloat().toPercentIn(currentCount.toFloat())}%"
                }
            })
        }
    }
}
