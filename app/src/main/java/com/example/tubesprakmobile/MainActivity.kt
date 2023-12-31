package com.example.tubesprakmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var listNote: RecyclerView
    private lateinit var fabCreate: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupList()
        setupListener()

    }


    override fun onStart() {
        super.onStart()
        getNote()
    }

    private fun setupView(){
        listNote = findViewById(R.id.list_note)
        fabCreate = findViewById(R.id.fab_create)
    }


    private fun setupList(){

        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.OnAdapterListener{
            override fun onUpdate(note: NoteModel.Data){
                startActivity(Intent(this@MainActivity, EditActivity::class.java)
                    .putExtra("note", note)
                )

            }

            override fun onDelete(note: NoteModel.Data) {
                api.delete(note.id!!)
                    .enqueue(object : Callback<SubmitModel>{
                        override fun onResponse(
                            call: Call<SubmitModel>,
                            response: Response<SubmitModel>
                        ) {
                            if (response.isSuccessful){
                                val submit = response.body()
                                Toast.makeText(applicationContext, submit!!.message, Toast.LENGTH_SHORT).show()
                                getNote()
                            }
                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
            }

        })
        listNote.adapter = noteAdapter
    }

    private fun setupListener(){
        fabCreate.setOnClickListener{
            startActivity(Intent(this, CreateActivity::class.java))

        }
    }



    private fun getNote(){
        api.data().enqueue(object : Callback<NoteModel> {

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }

            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
                if (response.isSuccessful){
                    val listData  = response.body()!!.notes
                    noteAdapter.setData(listData)
                    //listData.forEach{
                    //    Log.e("MainActivity", "note ${it.note}")
                    //}


                }
            }
        })
    }
}