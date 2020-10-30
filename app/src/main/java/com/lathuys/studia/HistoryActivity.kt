package com.lathuys.studia

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lathuys.studia.bmi.BmiHelper
import com.lathuys.studia.bmi.history.EntryDivider
import com.lathuys.studia.bmi.history.HistoryAdapter
import com.lathuys.studia.bmi.history.HistoryEntry
import com.lathuys.studia.databinding.ActivityHistoryBinding
import kotlinx.android.synthetic.main.activity_history.*
import java.util.ArrayList

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.historyTitle)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewManager = LinearLayoutManager(this)
        val sharedPreferences: SharedPreferences = getSharedPreferences(BmiHelper.HISTORY_PREF, 0)
        val entries = ArrayList<HistoryEntry>()

        for (i in 0..BmiHelper.HISTORY_MAX) {
            val serial =
                sharedPreferences.getString(BmiHelper.HISTORY_PREFIX + i.toString(), null) ?: break
            entries.add(HistoryEntry.createFromSerial(serial))
        }
        HistoryAdapter.context = this@HistoryActivity
        viewAdapter = HistoryAdapter(entries)

        recyclerView = historyRV.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        recyclerView.addItemDecoration(
            EntryDivider(this)
        )
    }
}