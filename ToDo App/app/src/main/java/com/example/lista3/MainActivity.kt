package com.example.lista3

import android.graphics.PorterDuff
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        dbHelper = DbHelper(this)

  //      lstTask = findViewById(R.id.lstTask) as ListView

    //    loadTaskList()
    }

 /*   private fun loadTaskList() {
        val taskList = dbHelper.getTaskList()
        if (mAdapter == null) {
            mAdapter = ArrayAdapter<String>(this, R.layout.row, R.id.task_title, taskList)
            lstTask.adapter = mAdapter
        } else {
            mAdapter!!.clear()
            mAdapter!!.addAll(taskList)
            mAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        //Change menu icon color
        val icon = menu.getItem(0).icon
        icon.mutate()
        icon.setColorFilter(resources.getColor(android.R.color.white), PorterDuff.Mode.SRC_IN)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_task -> {
                val taskEditText = EditText(this)
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Add New Task")
                    .setMessage("What do you want to do next?")
                    .setView(taskEditText)
                    .setPositiveButton("Add") { dialog, which ->
                        val task = taskEditText.text.toString()
                        dbHelper.insertNewTask(task)
                        loadTaskList()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                dialog.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteTask(view: View) {
        val parent = view.parent as View
        val taskTextView = parent.findViewById(R.id.task_title) as TextView
        Log.e("String", taskTextView.text as String)
        val task = taskTextView.text.toString()
        dbHelper.deleteTask(task)
        loadTaskList()
    }
*/}