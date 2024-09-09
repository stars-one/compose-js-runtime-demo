package com.example.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.common.ui.MyModalNavigationDrawer
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.script.ScriptEngineManager

@Composable
internal fun App() {
    Surface {
        MyModalNavigationDrawer()

        Column {

            Button(onClick = {
                val manager = ScriptEngineManager()

                val engine = manager.getEngineByName("javascript")
                val hookJscode = """
	
	function test(){
		return 10;
	}
"""
                engine.eval(hookJscode)
                val result = engine.eval("test()")
                println(result.toString())
            }){
                Text("test JavaScript runtime")
            }

        }
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(getPlatformName())
//        }
    }
}

private fun getIpAddress(host:String):String{
    val index = 1
    var result = ""
    when (index) {
        0 -> {
            //window
            val cmd = "ping -n 1 $host"
            val process = Runtime.getRuntime().exec(cmd)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line = reader.readLine()
            while (line != null) {
                println(line)
                if (line.contains("[")) {
                    result = line.substringAfter("[").substringBefore("]").also { println("结果: $it") }
                    break
                }
                line = reader.readLine()
            }
        }
        1->{
            //linux和mac
            val cmd = "ping -c 1 $host"
            val process = Runtime.getRuntime().exec(cmd)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line = reader.readLine()
            while (line != null) {
                println(line)
                if (line.contains("(")) {
                    result = line.substringAfter("(").substringBefore(")").also { println("结果: $it") }
                    break
                }
                line = reader.readLine()
            }
        }
        else -> return  ""
    }
    return result

}