package edu.ucsmub.kqvoting.extra

import edu.ucsmub.kqvoting.db.model.Selection

data class ConfirmItem(
    var selection: Selection,
    var category: Category,
    var icon: Int
)