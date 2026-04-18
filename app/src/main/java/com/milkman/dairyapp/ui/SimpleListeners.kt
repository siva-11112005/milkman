package com.milkman.dairyapp.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView

class SimpleTextWatcher(
    private val afterChange: () -> Unit
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    override fun afterTextChanged(s: Editable?) {
        afterChange.invoke()
    }
}

class SimpleItemSelectedListener(
    private val onSelect: () -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onSelect.invoke()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}
