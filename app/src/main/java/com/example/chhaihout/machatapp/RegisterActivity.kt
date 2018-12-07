package com.example.chhaihout.machatapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.quickblox.auth.QBAuth
import com.quickblox.auth.session.QBSession
import com.quickblox.auth.session.QBSettings
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity() {

    val APP_ID = "74911"
    val AUTH_KEY = "RRAfh7fukrCLTKN"
    val AUTH_SECRET = "Jsuj22CkeTwsjxM"
    val ACCOUNT_KEY = "VRNQis1G-V_T_Bp7_Cdy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(mainToolbar)
        progressBar.visibility = View.GONE
        initializeFramework()

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "MaChat"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

        registerSession()

        btnRRegister.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            var qbUser = QBUser(etRName.text.toString(), etRPass.text.toString(), etREmail.text.toString())
            QBUsers.signUp(qbUser).performAsync(object : QBEntityCallback<QBUser>{
                override fun onError(p0: QBResponseException?) {
                    progressBar.visibility = View.GONE
                    if (p0 != null) {
                        toast("" + p0.message).show()
                    }
                }

                override fun onSuccess(p0: QBUser?, p1: Bundle?) {
                    toast("Register Success").show()
                    progressBar.visibility = View.GONE
                }

            })
        }
    }
    private fun registerSession() {
        QBAuth.createSession().performAsync(object : QBEntityCallback<QBSession>{
            override fun onError(p0: QBResponseException?) {
                Log.e("", p0!!.message.toString())
            }

            override fun onSuccess(p0: QBSession?, p1: Bundle?) {

            }

        })
    }

    private fun initializeFramework() {
        val qbSetting = QBSettings.getInstance()
        qbSetting.init(applicationContext, APP_ID,AUTH_KEY,AUTH_SECRET)
        QBSettings.getInstance().accountKey = ACCOUNT_KEY
    }
}
