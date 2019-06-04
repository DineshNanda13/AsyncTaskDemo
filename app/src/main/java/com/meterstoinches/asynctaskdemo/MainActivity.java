package com.meterstoinches.asynctaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView output;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output=findViewById(R.id.t1);
        output.setMovementMethod(new ScrollingMovementMethod());
        pb=findViewById(R.id.pb);
        pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_do_task){
            MyTask task = new MyTask();
            task.execute("P1","P2","P3","P4","P5","P6");
        }
        return false;
    }
    protected void updateDisplay(String message){
        output.append(message+"\n");
    }
    private class MyTask extends AsyncTask<String, String ,String> {
        @Override
        protected void onPreExecute() {
            updateDisplay("starting task");
            pb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            for(int i=0;i<strings.length;i++){
                publishProgress("working with"+strings[i]);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return "Task Complete";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            updateDisplay(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            updateDisplay(s);
            pb.setVisibility(View.INVISIBLE);
        }
    }
}
