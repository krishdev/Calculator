package com.example.user.calculator;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;



public class Activity2 extends ActionBarActivity {

 boolean clear_screen = true;
    float vari1 =   0f;
    float vari2 =   0f;
    float answ  =   0f;
    String Operator="";
    boolean operator_state = false ;
    boolean insert_state = false;
    boolean last_click = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

    }
    public void insert_text(String text){
        EditText screen = (EditText) findViewById(R.id.screen);
        Intent intent1 = getIntent();
        int varr = intent1.getIntExtra("varia1",0);

        if (this.clear_screen){
            screen.setText("");
            this.clear_screen = false;
        }
        this.insert_state = true;
        this.last_click = true;
        screen.append(text);
        Intent intent = new Intent(Activity2.this, MainActivity.class);
        //intent.putExtra("score", score);//to show on the message
        intent.putExtra("opera", text);
        intent.putExtra("action", "true");
        intent.putExtra("varrr",varr);
        //intent.putExtra("scorep2", scorep2);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

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
        if (operator.equals("sin"))		{
            this.answ = (float) Math.sin(Float.parseFloat(screen.getText().toString()));
            screen.setText(this.answ + "");
            this.clear_screen = true;
            this.vari1 = 0f;
            this.vari2 = 0f;
            this.Operator = "";
            this.last_click = true;
            this.operator_state = false ;
        }
        else if (operator.equals("cos"))	this.Operator = "cos";
        else if (operator.equals("tan"))	this.Operator = "tan";
        else if (operator.equals("log"))	this.Operator = "log";
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
        if (screen.getText().toString().equals(".")){
            screen.setText("0");
        }
        if (screen.getText().toString().length() > 0){
            this.vari2 = Float.parseFloat(screen.getText().toString());
        }
       /* if (this.Operator.equals("sin")) {
            this.answ = Math.sin(this.vari1);
        } else*/ if (this.Operator.equals("-")){
            this.answ = this.vari1 - this.vari2;
        } else if (this.Operator.equals("*")){
            this.answ =this.vari1 * this.vari2;
        } else if (this.Operator.equals("/")){
            this.answ = this.vari1 / this.vari2;
        }else if (this.Operator.equals("^")){
            this.answ = (float) Math.pow(this.vari1, this.vari2);
        }else if (this.Operator.equals("%")){
            this.answ = vari1 % this.vari2;
        }else{
            this.answ = Float.parseFloat(screen.getText().toString());
        }

        screen.setText(this.answ + "");
    }
    public void ButtonClickHandler(View v){
        EditText screen = (EditText) findViewById(R.id.screen);
        switch(v.getId()){
            case R.id.buttonsin : insert_text("sin"); break;
            case R.id.buttoncos : insert_text("cos"); break;
            case R.id.buttontan : insert_text("tan"); break;
            case R.id.buttonln : insert_text("ln"); break;
            case R.id.buttonlog : insert_text("log"); break;
            case R.id.buttonSqr : insert_text("sqr"); break;
            case R.id.buttonexpo : insert_text("exp0"); break;
            case R.id.buttonpi : insert_text("pi"); break;
            case R.id.buttonPow : insert_text("pow"); break;
            case R.id.button9 : insert_text("9"); break;
            case R.id.buttonPoint :
                if (!screen.getText().toString().contains(".") || this.operator_state){
                    insert_text(".");
                }
                break;
           // case R.id.buttonsin : 	set_operator("sin"); break;
           // case R.id.buttoncos :	set_operator("cos"); break;
           // case R.id.buttontan:	set_operator("tan"); break;
           // case R.id.buttonDiv:	set_operator("/"); break;
           // case R.id.buttonSqr:	set_operator("√"); break;
           // case R.id.buttonPow:	set_operator("^"); break;
            case R.id.buttonMod:	set_operator("%"); break;
            //case R.id.buttonOnediv:	set_operator("d"); break;
            case R.id.buttonExe:
                if(screen.getText().toString().length() > 0 && this.Operator != ""){
                    calculator();
                    this.clear_screen = true;
                    this.vari1 = 0f;
                    this.vari2 = 0f;
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
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}