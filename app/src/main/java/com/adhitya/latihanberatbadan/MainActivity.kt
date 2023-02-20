package com.adhitya.latihanberatbadan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: LinearLayoutManager
    private var tb : Int = 0

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nama = findViewById<EditText>(R.id.EtNama)
        val tinggi = findViewById<EditText>(R.id.EtTinggi)
        val rbLaki = findViewById<RadioButton>(R.id.rbLaki)
        val rbPr = findViewById<RadioButton>(R.id.rbPerempuan)
        val hitung = findViewById<Button>(R.id.Hitung)
        val hasil = findViewById<TextView>(R.id.Hasil)
        val simpan = findViewById<ImageView>(R.id.simpan)

        val data = ArrayList<Data>()
        recyclerView = findViewById(R.id.listData)
        simpan.setOnClickListener {
            val nama = nama.text
            val tinggi = tinggi.text
            val berat = hasil.text
            val dataHitung = Data(nama,tinggi,berat)
            data.add(dataHitung)
            recyclerAdapter.notifyDataSetChanged()
        }
        hitung.setOnClickListener {
            var tinggi = tinggi.text.toString().toInt()
            if (rbLaki.isChecked){
                tb = (tinggi - 100) - ((tinggi - 100)* 10/100)
                hasil.setText(tb.toString()+"kg")
            } else if (rbPr.isChecked){
                tb = (tinggi - 100) - ((tinggi - 100)* 15/100)
                hasil.setText(tb.toString()+"kg")
            } else {
                Toast.makeText(this,"Pilih Jenis Kelamin Terlebih Dahulu",Toast.LENGTH_SHORT).show()
            }
        }
        recyclerAdapter = Adapter(data)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager
    }
}