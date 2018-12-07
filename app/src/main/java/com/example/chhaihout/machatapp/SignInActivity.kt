package com.example.chhaihout.machatapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.quickblox.auth.session.QBSettings
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.toast

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initializeFramework()

        txtCreateAcc.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnSSignIn.setOnClickListener {
            signInprogressBar.visibility = View.VISIBLE
            var qbUser = QBUser(etSName.text.toString(),etSPass.text.toString())
            QBUsers.signIn(qbUser).performAsync(object: QBEntityCallback<QBUser> {
                override fun onError(p0: QBResponseException?) {
                    signInprogressBar.visibility = View.GONE
                    if (p0 != null) {
                        toast(" " + p0.message).show()
                    }
                }

                override fun onSuccess(p0: QBUser?, p1: Bundle?) {
                    signInprogressBar.visibility = View.GONE
                    toast("SignIn Successfully").show()
                }
            })
        }

        relativel.setOnClickListener {
            loginWithFacebook();
        }
    }

    private fun loginWithFacebook() {

    }

    private fun initializeFramework() {
        val qbSetting = QBSettings.getInstance()
        qbSetting.init(applicationContext, R.string.APP_ID.toString(),R.string.AUTH_KEY.toString(),R.string.AUTH_SECRET.toString())
        QBSettings.getInstance().accountKey = R.string.ACCOUNT_KEY.toString()
    }
}
