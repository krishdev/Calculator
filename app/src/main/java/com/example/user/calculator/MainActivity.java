package com.example.user.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.math.BigInteger;

public class MainActivity extends ActionBarActivity {
    boolean clear_screen = true;
    float vari1 =   0f;
    float vari2 =   0f;
    float answ  =   0f;
    String Operator="";
    boolean operator_state = false ;
    boolean insert_state = false;
    boolean last_click = false;
    int numbincerease = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void insert_text(String text){
        EditText screen = (EditText) findViewById(R.id.screen);
        if (this.clear_screen){
            screen.setText("");
            this.clear_screen = false;
        }
        this.insert_state = true;
        this.last_click = true;
        screen.append(text);
    }

    public void set_operator(String operator){
        EditText screen = (EditText) findViewById(R.id.screen);
        if (screen.getText().toString().equals(".")) screen.setText("0");
        if (this.insert_state && this.operator_state && this.last_click){
            calculator();
        }
        if (screen.getText().toString().length() > 0 ){
            this.vari1 = Float.parseFloat(screen.getText().toString());
        }
        this.operator_state = true;
        this.clear_screen = true;
        this.last_click = false;
        if (operator.equals("+"))		this.Operator = "+";
        else if (operator.equals("-"))	this.Operator = "-";
        else if (operator.equals("*"))	this.Operator = "*";
        else if (operator.equals("/"))	this.Operator = "/";
        else if (operator.equals("√")){
            this.answ = (float) Math.sqrt(Float.parseFloat(screen.getText().toString()));
            screen.setText(this.answ + "");
            this.clear_screen = true;
            this.vari1 = 0f;
            this.vari2 = 0f;
            this.Operator = "";
            this.last_click = true;
            this.operator_state = false ;
        }else if (operator.equals("d")){
            this.answ = 1 / Float.parseFloat(screen.getText().toString());
            screen.setText(this.answ + "");
            this.clear_screen = true;
            this.vari1 = 0f;
            this.vari2 = 0f;
            this.Operator = "";
            this.last_click = true;
            this.operator_state = false ;
        }
        else if (operator.equals("^"))	this.Operator = "^";
        else if (operator.equals("%"))	this.Operator = "%";
    }


    public void calculator(){
        EditText screen = (EditText) findViewById(R.id.screen);
        Intent intent = getIntent();
        String opera = intent.getStringExtra("opera");
        String activi2 = intent.getStringExtra("action");
        int varia1 = intent.getIntExtra("varrr",0);
        if (screen.getText().toString().equals(".")){
            screen.setText("0");
        }
        if (screen.getText().toString().length() > 0){
            this.vari2 = Float.parseFloat(screen.getText().toString());
        }
        if (activi2==null) {
            if (this.Operator.equals("+")) {
                this.answ = this.vari1 + this.vari2;
            } else if (this.Operator.equals("-")) {
                this.answ = this.vari1 - this.vari2;
            } else if (this.Operator.equals("*")) {
                this.answ = this.vari1 * this.vari2;
            } else if (this.Operator.equals("/")) {
                this.answ = this.vari1 / this.vari2;
            } else if (this.Operator.equals("^")) {
                this.answ = (float) Math.pow(this.vari1, this.vari2);
            } else if (this.Operator.equals("%")) {
                this.answ = vari1 % this.vari2;
            } else {
                this.answ = Float.parseFloat(screen.getText().toString());
            }
        }
        else if (activi2.equals("true")){
            this.vari1 = Float.parseFloat(screen.getText().toString());
           double vari11 = vari1;
            if (opera.equals("sin")){
                this.answ = (float) Math.sin(vari11);
            }
            else if (opera.equals("cos")){
                this.answ = (float) Math.cos(vari11);
            }
            else if (opera.equals("tan")){
                this.answ = (float) Math.tan(vari11);
            }
            else if (opera.equals("log")){
                this.answ = (float) Math.log10(vari11);
            }
            else if (opera.equals("ln")){
                this.answ = (float) Math.log(vari11);
            }
            else if (opera.equals("sqr")){
                this.answ = (float) Math.sqrt(vari11);
            }
            else if (opera.equals("^")) {
                this.answ = (float) Math.pow(this.vari1, this.vari2);
            } else if (opera.equals("%")) {
                this.answ = varia1 % this.vari1;
            }else if (opera.equals("expo")) {
                this.answ = (float) Math.exp(vari11);
            }else if (opera.equals("pi")) {
                this.answ = (float) Math.PI*vari1;
            }else if (opera.equals("excla")) {
                this.answ = 0;
            }else if (opera.equals("pow")) {
                this.answ = (float) Math.pow(varia1, this.vari1);
            }

        }

        screen.setText(this.answ + "");
    }
    public void ButtonClickHandler(View v){
        EditText screen = (EditText) findViewById(R.id.screen);
        Intent intent = getIntent();
        String opera = intent.getStringExtra("opera");
        String activi2 = intent.getStringExtra("action");
        switch(v.getId()){
            case R.id.buttonsin : insert_text("0"); break;
            case R.id.button1 : insert_text("1"); break;
            case R.id.button2 : insert_text("2"); break;
            case R.id.button3 : insert_text("3"); break;
            case R.id.button4 : insert_text("4"); break;
            case R.id.button5 : insert_text("5"); break;
            case R.id.button6 : insert_text("6"); break;
            case R.id.button7 : insert_text("7"); break;
            case R.id.button8 : insert_text("8"); break;
            case R.id.button9 : insert_text("9"); break;
            case R.id.buttonPoint :
                if (!screen.getText().toString().contains(".") || this.operator_state){
                    insert_text(".");
                }
                break;
            case R.id.buttonAdd : 	set_operator("+"); break;
            case R.id.buttonSub :	set_operator("-"); break;
            case R.id.buttonMulti:	set_operator("*"); break;
            case R.id.buttonDiv:	set_operator("/"); break;
           // case R.id.buttonSqr:	set_operator("√"); break;
          //  case R.id.buttonPow:	set_operator("^"); break;
            //case R.id.buttonMod:	set_operator("%"); break;
         //   case R.id.buttonOnediv:	set_operator("d"); break;
            case R.id.buttonExe:
                if(screen.getText().toString().length() > 0 && this.Operator != ""){
                    calculator();
                    this.clear_screen = true;
                    this.vari1 = 0f;
                    this.vari2 = 0f;
                    this.Operator = "";
                    this.operator_state = false ;
                }
                else if(activi2 != null){
                    calculator();
                    this.clear_screen = true;
                    this.vari1 = 0f;
                    this.Operator = "";
                    this.operator_state = false ;
                }
                break;
            case R.id.buttondel:
                if(screen.getText().toString().length() > 1){
                    String screen_new = screen.getText().toString().substring(0, screen.getText().toString().length()-1);
                    screen.setText(screen_new);
                    this.clear_screen = false;
                }else{
                    screen.setText("0");
                    this.clear_screen = true;
                }
                break;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        EditText screen = (EditText) findViewById(R.id.screen);
        this.vari1 = Float.parseFloat(screen.getText().toString());
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,Activity2.class);
            intent.putExtra("varia1", vari1);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
