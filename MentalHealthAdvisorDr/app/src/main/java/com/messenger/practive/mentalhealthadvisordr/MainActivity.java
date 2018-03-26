package com.messenger.practive.mentalhealthadvisordr;

import android.*;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText quizNameTxt;
    private Button mButton;
    private EditText editText;
    private DatabaseReference mDatabase;
    private RecyclerView quizList;
    private Button saveNameBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizNameTxt = (EditText) findViewById(R.id.quizNameTxt);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Quiz");
        quizList = (RecyclerView) findViewById(R.id.quizRec);
        quizList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        quizList.setLayoutManager(linearLayoutManager);
        mButton = (Button) findViewById(R.id.addBtn);
        editText = (EditText) findViewById(R.id.questionTxtField);
        saveNameBtn = (Button) findViewById(R.id.saveNameBtn);

        saveNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.push().setValue(quizNameTxt.getText().toString());
            }
        });

       mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String questionValue = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(questionValue)) {
                    final DatabaseReference newQuestion = mDatabase.push();
                    newQuestion.child("Quiz").setValue(questionValue);
                }

            }
        });

    }

    /*public void submitChoice(View view) {
        final String questionValue = editText.getText().toString().trim();
        if (!TextUtils.isEmpty(questionValue)) {
            final DatabaseReference newQuestion = mDatabase.push();
            newQuestion.child("Question").setValue(questionValue);
        }
    }*/

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Quiz")
            .limitToLast(50);

    FirebaseRecyclerOptions<Question> options =
            new FirebaseRecyclerOptions.Builder<Question>()
                    .setQuery(query, Question.class)
                    .build();

    FirebaseRecyclerAdapter FBRA = new FirebaseRecyclerAdapter<Question, QuestionViewHolder>(options) {

        @Override
        public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.question_layout, parent, false);

            return new QuestionViewHolder(view);
        }
        @Override
        protected void onBindViewHolder(QuestionViewHolder holder, int position, Question model) {
            holder.setQuestion(model.getQuestion());
            holder.setAnswerOne(model.getAnswerOne());
        }
    };



    /*public void submitChoice(View view) {
        RadioButton radioButton = new RadioButton(this);
        radioButton.setTextSize(40);
        radioButton.setHint("question");





    }*/
    @Override
    protected void onStart() {
        super.onStart();
        FBRA.startListening();

        quizList.setAdapter(FBRA);

    }

    @Override
    protected void onStop() {
        super.onStop();
        FBRA.stopListening();
    }

    @Override
    public void onClick(View v) {

    }


    private static class QuestionViewHolder extends RecyclerView.ViewHolder {

        View mView;
        QuestionViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setQuestion(String sQuestion) {
            TextView question_content = (TextView) mView.findViewById(R.id.questionTxt);
            question_content.setText(sQuestion);
        }
        void setAnswerOne(String answerOne) {
            RadioButton answerOne_content = (RadioButton) mView.findViewById(R.id.answerOne);
            answerOne_content.setText(answerOne);
        }
    }
}
