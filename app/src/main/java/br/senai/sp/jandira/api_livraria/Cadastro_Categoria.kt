package br.senai.sp.jandira.api_livraria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.create

class Cadastro_Categoria : AppCompatActivity() {

    // criação do atributo que vai representar a api service
    private lateinit var apiService: ApiService





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_categoria)

        // criação da instancia ativa do retrofit
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        // recuperação do objeto de editText do formulario de cadastro de categoria
        val txtCategory = findViewById<EditText>(R.id.txtCategoria)

        // trata o evento de click ou toque no botão cadastrar
        findViewById<Button>(R.id.btnCadastrarCategoria).setOnClickListener{

            // recupera o valor digitado peo usuario
            val nameCategory = txtCategory.text
            createCategory(nameCategory.toString())
            // envia o dado de categoria para cadastro na api
        }
    }

    private fun createCategory(nome_categoria: String){

        // trata de forma assincrona a saida e resposta
        lifecycleScope.launch {
            // Criação do corpo de dados em formato JSON
            val body = JsonObject().apply {
                addProperty("nome_categoria", nome_categoria)
            }
            // Envio de dados para a rota de cadastrar categoria
            val result = apiService.createCategory(body)

            if(result.isSuccessful){
                val msg = result.body()?.get("mensagemStatus")
                Log.e("CREATE-CATEGORY", "STATUS: ${msg}")
            }else{
                Log.e("CREATE-CATEGORY", "STATUS: ${result.message()}")
            }
        }
    }
}