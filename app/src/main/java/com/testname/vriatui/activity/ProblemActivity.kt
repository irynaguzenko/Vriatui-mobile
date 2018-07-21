package com.testname.vriatui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.testname.vriatui.R
import com.testname.vriatui.api.IncidentApi
import com.testname.vriatui.model.IncidentRequest

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ProblemActivity : AppCompatActivity() {

    @Inject
    lateinit var incidentApi: IncidentApi

//    val profileApi = ApiClient.getClient().create(ProfileApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val incidentRequest = IncidentRequest()
        incidentApi.incidentHappend(incidentRequest)
                .execute()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
