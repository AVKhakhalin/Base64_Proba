package com.example.base64_proba

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    //region ОБЪЯВЛЕНИЕ ПЕРЕМЕННЫХ
    // Переменные для отображение результата
    var resultEncoded: TextView? = null
    var resultDecoded: TextView? = null
    // Переменная с оригинальным ключём Yandex API
    val oriString = "ebbee072-d212-420e-9f62-4d716b0499e9"
    // Переменная с текстовой версией переменной encoded, т.е. stringEncoded
//    var codedOriString: String? = "[B@ee88682"
    // Раскодировано по ASCI коду
    var codedOriString: String? = "ZWJiZWUwNzItZDIxMi00MjBlLTlmNjItNGQ3MTZiMDQ5OWU5Char(10)"
    var exampleCodeText: String = "ZWJiZ WUwN zItZ DIxM i00M jBlL TlmN jItN GQ3M TZiM DQ5O WU5${Char(10)} "
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов для отображения результата
        resultEncoded = findViewById(R.id.result_encode)
        resultDecoded = findViewById(R.id.result_decode)

        // КОДИРОВАНИЕ
        val oldEncoded: ByteArray? = Base64.encode(oriString.toByteArray(Charset.defaultCharset()), Base64.DEFAULT)
        val oldStringEncoded: String? = oldEncoded?.let { it.toString(Charset.defaultCharset())}
        Log.d("mylogs", "oldStringEncoded (Кодировка не известна): \"$oldStringEncoded\"")
        val encoded: ByteArray? = Base64.encode(oriString.toByteArray(Charsets.ISO_8859_1), Base64.DEFAULT)
        val stringEncoded: String? = encoded?.let { it.toString(Charsets.ISO_8859_1)}
        Log.d("mylogs", "stringEncoded (ISO_8859_1): \"$stringEncoded\"")

        // ЗДЕСЬ ОСНОВНОЙ ВОПРОС: Почему это условие не выполняется?
        // Мне бы очень хотелось вместо кода из переменной exampleCodeText
        // (как это сделано сейчас в 8 ДЗ) подставить в моё погодное приложение
        // код из переменной codedOriString (он более компактный и не понятный для зловредителя)
        Log.d("mylogs",
            "\"${stringEncoded?.toByteArray(Charsets.ISO_8859_1)}\"\n${encoded?.
            toString(Charsets.ISO_8859_1)}")
        val temp = stringEncoded?.toByteArray(Charsets.ISO_8859_1)
        if (stringEncoded?.toByteArray(Charsets.ISO_8859_1) == encoded) { // ПОЧЕМУ ЗДЕСЬ НЕ ВЫПОЛНЯЕТСЯ УСЛОВИЕ???
            Toast.makeText(this, "РАВНЫ!!!", Toast.LENGTH_LONG).show()
            Log.d("mylogs", "Условие ВЫПОЛНЕНО!!!")
        } else {
            Toast.makeText(this, "НЕ равны!!!", Toast.LENGTH_LONG).show()
            Log.d("mylogs", "Условие НЕ выполняется")
        }

        // Вывод закодированного ключа в виде String
//        resultEncoded?.let {
//            it.text = "\"$stringEncoded\""
//        }
//        Toast.makeText(this, "$stringEncoded", Toast.LENGTH_LONG).show()

        Toast.makeText(this, "encoded: $encoded", Toast.LENGTH_LONG).show()
//        Toast.makeText(this, "codedOriString: $codedOriString", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "codedOriString: " +
                "${stringEncoded?.toByteArray(Charsets.US_ASCII)?.toString(
            Charsets.US_ASCII)}", Toast.LENGTH_LONG).show()

        // ДЕКОДИРОВАНИЕ
        // СПОСОБ ПОЛУЧЕНИЯ ЗНАЧЕНИЯ В ПЕРЕМЕННОЙ exampleCodeText
        // ПО ТАБЛИЦАМ ASCI Я ВРУЧНУЮ ПОЛУЧИЛ ЭТО ЗНАЧЕНИЕ
        // "ZWJiZ WUwN zItZ DIxM i00M jBlL TlmN jItN GQ3M TZiM DQ5O WU5${Char(10)} "
        /*        exampleCodeText = ""
        resultEncoded?.let {
//            encoded?.forEachIndexed { index, element ->
            codedOriString?.toByteArray(Charsets.ISO_8859_1)?.forEachIndexed { index, element ->
                it.text = "${it.text}\"${element}\"\n"
                exampleCodeText = "$exampleCodeText${Char(element.toInt())}"
                if ((index != 0) && (index % 4 == 0)) {
                    it.text = "${it.text}\"32\"\n"
                    exampleCodeText = "$exampleCodeText${Char(32)}"
                }
            }
        }*/

//        val decoded: ByteArray? = Base64.decode(encoded, Base64.DEFAULT)
        val decoded: ByteArray? = Base64.decode(exampleCodeText, Base64.DEFAULT)
        val decodedString: String? = decoded?.let { String(it) }
        resultDecoded?.let {
            it.text = "Исходный ключ: \"$decodedString\""
            Log.d("mylogs", "Исходный код: $decodedString")
        }
    }
}