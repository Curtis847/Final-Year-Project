package com.messenger.practive.mentalhealthadvisordr;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class CreateQuizActivity extends AppCompatActivity {


    private Button mButton;
    private EditText questionTxt;
    private DatabaseReference mDatabase;
    private RecyclerView quizList;
    private EditText answerOneTxt;
   // private Button saveNameBtn;
   private EditText answerTwoTxt;
   private EditText answerThreeTxt;
   



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Quiz");//creates first child of the firebase database called Quiz
        quizList = (RecyclerView) findViewById(R.id.quizRec);
        quizList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        quizList.setLayoutManager(linearLayoutManager);
        mButton = (Button) findViewById(R.id.addBtn);
        questionTxt = (EditText) findViewById(R.id.questionTxtField);
        answerOneTxt = (EditText) findViewById(R.id.answerOneTxt);
        answerTwoTxt = (EditText) findViewById(R.id.answerTwoTxt);
        answerThreeTxt = (EditText) findViewById(R.id.answerThreeTxt);



    }
    public void saveQuestionInformation () {
        final String questionValue = questionTxt.getText().toString().trim();
        final String answerOneValue = answerOneTxt.getText().toString().trim();
        final String answerTwoValue = answerTwoTxt.getText().toString().trim();
        final String answerThreeValue = answerThreeTxt.getText().toString().trim();
        Question question = new Question(questionValue, answerOneValue, answerTwoValue, answerThreeValue);

        if ((!TextUtils.isEmpty(questionValue )) && (!TextUtils.isEmpty(answerOneValue))) {
            final DatabaseReference newQuestion = mDatabase.push();

            mDatabase.child(questionValue).setValue(question);
        }

    }

    public void submitChoice(View view) {
        saveQuestionInformation();
        questionTxt.getText().clear();
        answerOneTxt.getText().clear();
        answerTwoTxt.getText().clear();
        answerThreeTxt.getText().clear();

    }

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
