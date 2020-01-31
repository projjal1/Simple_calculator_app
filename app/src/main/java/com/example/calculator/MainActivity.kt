package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var opt:String=""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buevent(view:View)
    {
        val button_id=view as Button
        var string_button:String=editText.text.toString()
        editText2.setText("")

        when(button_id.id)
        {
            b0.id->{string_button+="0"}
            b1.id->{string_button+="1"}
            b2.id->{string_button+="2"}
            b3.id->{string_button+="3"}
            b4.id->{string_button+="4"}
            b5.id->{string_button+="5"}
            b6.id->{string_button+="6"}
            b7.id->{string_button+="7"}
            button25.id->{string_button+="8"}
            button26.id->{string_button+="9"}
            b_dot.id->{string_button+="."}
            b_clear.id->{string_button=" "
                editText2.setText("All Clear")}
            b_sign.id->{
                try {
                    var x=string_button.toDouble()
                    x=-x
                    string_button=x.toString()
                }
                catch(e:NumberFormatException)
                {
                    editText2.setText("Format Error")
                }
            }
            b_del.id->{
                val len:Int=string_button.length
                if(len==0) {
                    string_button = ""
                    editText2.setText("All Clear")
                }
                else
                    string_button=string_button.substring(0,len-1)
            }
            b_modulo.id->{
                if (opt.length!=0)
                    string_button=calc()+"%"
                else
                    string_button+="%"
                opt="%" }
            b_div.id->{
                if (opt.length!=0)
                    string_button=calc()+"/"
                else
                    string_button+="/"
                opt="/" }
            b_minus.id->{
                if (opt.length!=0)
                    string_button=calc()+"-"
                else
                    string_button+="-"
                opt="-" }
            b_mul.id->{
                if (opt.length!=0)
                    string_button=calc()+"*"
                else
                    string_button+="*"
                opt="*"}
            b_plus.id->{
                if (opt.length!=0)
                    string_button=calc()+"+"
                else
                    string_button+="+"
                opt="+"}
        }

        editText.setText(string_button)
    }

    fun calc():String
    {
        var output:String=""
        try {
        val get_exp=editText.text.toString()
        val pos_op=get_exp.indexOf(opt)
        val first_operand=get_exp.substring(0,pos_op).toDouble()
        val second_operand=get_exp.substring(pos_op+1,get_exp.length).toDouble()

        when(opt)
        {
            "*"->{ output=(first_operand*second_operand).toString()}
            "%"->{ output=(first_operand%second_operand).toString()}
            "/"->{ output=(first_operand/second_operand).toString()}
            "+"->{ output=(first_operand+second_operand).toString()}
            "-"->{ output=(first_operand-second_operand).toString()}
        }
        }
        catch(exp:Exception)
        {
            editText2.setText("Expression error")
        }
        return output
    }

    fun result(view:View)
    {
        if (opt.length==0)
            return
        val output=calc()
        editText.setText(output)
        opt=""
    }
}
