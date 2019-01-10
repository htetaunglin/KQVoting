package edu.ucsmub.kqvoting.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.db.model.Selection

class SelectionViewModel(application: Application) : AndroidViewModel(application) {

    fun getAllSelection(): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getSelections()
    }

    fun getBoySelections(): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getSelectionBoys()
    }

    fun getGirlSelections(): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getSelectionGirls()
    }

    fun getPopularSelections(kingID: String): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getPopularList(kingID)
    }

    fun getInnocenceSelections(queenID: String): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getInnocenceList(queenID)
    }

    fun getCurrentKing(): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getCurrentKing()
    }

    fun getCurrentQueen(): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getCurrentQueen()
    }

    fun getPopularList(): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getPopularList()
    }

    fun getInnocenceList(): LiveData<List<Selection>>{
        val db = AppDatabase.getInstance(this.getApplication())
        return db.selectionDao().getInnocenceList()
    }
}