package com.advisor.health.mental.mentalhealthadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class QuizActivity extends AppCompatActivity {

    public DatabaseReference mDatabase;
    private RecyclerView quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Quiz");
        quizList = (RecyclerView) findViewById(R.id.quizRec);
        quizList.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        quizList.setLayoutManager(linearLayoutManager);
    }

    public void submitResults(View view) {
        Toast.makeText(getApplicationContext(), "Answers Submitted", Toast.LENGTH_SHORT).show();

    }

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Quiz")
            .limitToLast(10);



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
            holder.setAnswerTwo(model.getAnswerTwo());
            holder.setAnswerThree(model.getAnswerThree());
        }
    };

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


    private static class QuestionViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setQuestion(String sQuestion) {
            TextView question_content = (TextView) mView.findViewById(R.id.questionTxt);
            question_content.setText(sQuestion);
        }
        public void setAnswerOne(String answerOne) {
            RadioButton answerOne_content = (RadioButton) mView.findViewById(R.id.answerOne);
            answerOne_content.setText(answerOne);
        }

        public void setAnswerTwo(String answerTwo) {
            RadioButton answerTwo_content = (RadioButton) mView.findViewById(R.id.answerTwo);
            answerTwo_content.setText(answerTwo);
        }

        public void setAnswerThree(String answerThree) {
            RadioButton answerThree_content = (RadioButton) mView.findViewById(R.id.answerThree);
            answerThree_content.setText(answerThree);
        }
    }
}
