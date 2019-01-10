package edu.ucsmub.kqvoting.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import edu.ucsmub.kqvoting.Pref.MyPreference
import edu.ucsmub.kqvoting.R
import kotlinx.android.synthetic.main.activity_votingroom.*
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edu.ucsmub.kqvoting.customUI.customRatioBtn.PresetRadioGroup
import edu.ucsmub.kqvoting.customUI.customRatioBtn.PresetValueButton
import edu.ucsmub.kqvoting.db.model.Selection
import edu.ucsmub.kqvoting.extra.Category
import edu.ucsmub.kqvoting.viewModel.SelectionViewModel


class VotingRoomActivity : AppCompatActivity() {
    private var count = 0

    private var kingID: String = "-100"
    private var queenID: String = "-100"
    private var voted = false
    lateinit var pref: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votingroom)
        setSupportActionBar(main_tool_bar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Voting Room"

        pref = MyPreference(this)
        resetPref()
        initial()
    }

    fun initial() {

        radioGroup.onCheckedChangeListener =
                PresetRadioGroup.OnCheckedChangeListener { radioGroup, radioButton, isChecked, checkedId ->
                    val selectionID = (radioButton as PresetValueButton).selection.id
                    when (count) {
                        0 -> {
                            kingID = selectionID
                            pref.setKingID(selectionID)
                            Log.d("MyKQVote", "this is king $selectionID")
                        }
                        1 -> {
                            queenID = selectionID
                            pref.setQueenID(selectionID)
                        }
                        2 -> {
                            pref.setPopularID(selectionID)
                            Log.d("MyKQVote", "this is popular $selectionID")
                        }
                        3 -> {
                            pref.setInnocenceID(selectionID)
                            Log.d("MyKQVote", "this is innocence $selectionID")
                        }
                    }
                    voted = true
                }
        val arr = arrayOf(Category.King, Category.Queen, Category.Popularity, Category.Innocence)

        nextBtn.setOnClickListener {
            if (voted) {
                count++
                Log.d("MyCount", "$count")

                if (count < 4) {
                    radioGroup.removeAllViews()
                    createPresetValueButton()
                }

                if (count == 4) {
                    Log.d("MyKQVote >> KING ", pref.getKingID().toString())
                    Log.d("MyKQVote >> QUEEN ", pref.getQueenID().toString())
                    Log.d("MyKQVote >> POPULAR ", pref.getPopularID().toString())
                    Log.d("MyKQVote >> INNOCENCE ", pref.getInnocenceID().toString())
                    Log.d(
                        "Hello",
                        "${pref.getKingID()} and ${pref.getQueenID()} and ${pref.getPopularID()} and ${pref.getInnocenceID()}"
                    )
                    startActivity(Intent(this, ConfirmResultActivity::class.java))
                    finish()
                }
                if (count == 3) {
                    nextBtn.text = "Finish"

                }
            } else {
                val notice = "ကျေးဇူးပြုပြီး ${arr[count]} ကိုရွေးချယ်ပေးပါ"
                Toast.makeText(this, "$notice", Toast.LENGTH_SHORT).show()
            }
        }

        createPresetValueButton()
    }

    fun bindViewToRG(selectionStudentList: List<Selection>) {
        for (student in selectionStudentList) {
            val presetValueButton = PresetValueButton(this, student)
            radioGroup.addView(presetValueButton)
            val scaleAnimation = ScaleAnimation(
                0.0f, 1f, 0.0f, 1f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            )
            scaleAnimation.duration = 700
            presetValueButton.animation = scaleAnimation
        }
        voted = false
    }

    @SuppressLint("SetTextI18n")
    private fun createPresetValueButton() {
        val arr = arrayOf(Category.King, Category.Queen, Category.Popularity, Category.Innocence)
        if (count < arr.size) {
            guide_vote.text = "${arr[count]} ရွေးချယ်ပေးပါ"
            val selectionViewModel = ViewModelProviders.of(this).get(SelectionViewModel::class.java)
            when (count) {
                0 -> {
                    selectionViewModel.getBoySelections().observe(this, Observer {
                        bindViewToRG(it)
                    })
                }
                1 -> {
                    selectionViewModel.getGirlSelections().observe(this, Observer {
                        bindViewToRG(it)
                    })
                }
                2 -> {
                    selectionViewModel.getPopularSelections(kingID).observe(this, Observer {
                        bindViewToRG(it)
                    })
                }
                3 -> {
                    selectionViewModel.getInnocenceSelections(queenID).observe(this, Observer {
                        bindViewToRG(it)
                    })
                }
            }
        }
    }

    override fun onBackPressed() {
        resetPref()
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                resetPref()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun resetPref() {
        pref.setKingID("")
        pref.setQueenID("")
        pref.setPopularID("")
        pref.setInnocenceID("")
    }
}