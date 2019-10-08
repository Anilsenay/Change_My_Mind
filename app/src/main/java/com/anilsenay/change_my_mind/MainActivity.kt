package com.anilsenay.change_my_mind

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var lastClickedButtonIndex: Int = 0
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        mTitle.setText("HOME")
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            val intent = Intent(applicationContext, NewDiscussion::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_messages,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Custom Menu icon
        supportActionBar?.setHomeAsUpIndicator(R.drawable.drawer_icon)

        //firebase auth
        mAuth = FirebaseAuth.getInstance()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun goToProfilePage(view: View){
        val intent = Intent(applicationContext, ProfilePage::class.java)
        startActivity(intent)
    }

    fun changeCategory(view: View){
        var categoryName = view.getTag().toString()
        val buttons = arrayOf(all_button, education_button, movies_button, news_button, religion_button, science_button, technology_button, society_button)
        var categoryIndex = Integer.parseInt(categoryName)
        buttons[lastClickedButtonIndex].setBackgroundResource(R.drawable.category_button_background)
        buttons[categoryIndex].setBackgroundResource(R.drawable.category_btn_bg_onclick)
        lastClickedButtonIndex = categoryIndex

    }

    fun logOut(view: View){
        mAuth.signOut()
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
