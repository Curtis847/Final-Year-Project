package com.messenger.practive.mentalhealthadvisordr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EnterIssueActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText issueName;
    private EditText issueDescription;
    private EditText issueCoping;
    private EditText linksOrReferences;
    private RecyclerView issueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_issue);


        issueList = (RecyclerView) findViewById(R.id.issueRec);
        issueList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Issues");
        issueList.setLayoutManager(linearLayoutManager);
        issueName = (EditText) findViewById(R.id.issueNameTxt);
        issueDescription = (EditText) findViewById(R.id.issueDetailsTxt);
        issueCoping = (EditText) findViewById(R.id.waysToCopeTxt);
        linksOrReferences = (EditText) findViewById(R.id.linksOrResourcesTxt);


    }
    public void saveIssueDetails () {
        final String issueNameTxt = issueName.getText().toString().trim();
        final String issueDescriptionTxt = issueDescription.getText().toString().trim();
        final String issueCopingTxt = issueCoping.getText().toString().trim();
        final String linksOrResourcesTxt = linksOrReferences.getText().toString().trim();
        IssueInformation issue = new IssueInformation(issueNameTxt, issueDescriptionTxt, issueCopingTxt, linksOrResourcesTxt);
        if ((!TextUtils.isEmpty(issueNameTxt )) && (!TextUtils.isEmpty(issueDescriptionTxt))) {
            final DatabaseReference newQuestion = mDatabase.push();
            //final DatabaseReference newAnswerOne = mDatabase.push();
            //final DatabaseReference newAnswerTwo = mDatabase.push();
            //final DatabaseReference newAnswerThree = mDatabase.push();
            mDatabase.child(issueNameTxt).setValue(issue);
            //newAnswerOne.child(questionValue).child("Answers").setValue(answerOneValue);
            //newAnswerTwo.child("Answers").setValue(answerTwoValue);
            //newAnswerThree.child("Answers").setValue(answerThreeValue);
        }
    }

    public void saveDetails (View view) {
        saveIssueDetails();
        issueName.getText().clear();
        issueDescription.getText().clear();
        issueCoping.getText().clear();
        linksOrReferences.getText().clear();

    }

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Issues")
            .limitToLast(1);

    FirebaseRecyclerOptions<IssueInformation> options =
            new FirebaseRecyclerOptions.Builder<IssueInformation>()
                    .setQuery(query, IssueInformation.class)
                    .build();

    FirebaseRecyclerAdapter FBRA = new FirebaseRecyclerAdapter<IssueInformation, IssueInformationViewHolder>(options) {

        @Override
        public IssueInformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.issue_layout, parent, false);

            return new IssueInformationViewHolder(view);
        }
        @Override
        protected void onBindViewHolder(IssueInformationViewHolder holder, int position, IssueInformation model) {
            holder.setIssueName(model.getIssueName());
            holder.setIssueDetails(model.getIssueText());
            holder.setWaysToCope(model.getWaysToCope());
            holder.setlinksOrResources(model.getLinksOrResources());
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        FBRA.startListening();
        issueList.setAdapter(FBRA);

    }

    @Override
    protected void onStop() {
        super.onStop();
        FBRA.stopListening();
    }



    private static class IssueInformationViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public IssueInformationViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setIssueName(String issueName) {
            TextView issueName_content = (TextView) mView.findViewById(R.id.issueNameTxt);
            issueName_content.setText(issueName);
        }
        public void setIssueDetails(String issueDetails) {
            TextView issueDetails_content = (TextView) mView.findViewById(R.id.issueDetailsTxt);
            issueDetails_content.setText(issueDetails);
        }

        public void setWaysToCope(String waysToCope) {
            TextView waysToCope_content = (TextView) mView.findViewById(R.id.waysToCopeTxt);
            waysToCope_content.setText(waysToCope);
        }

        public void setlinksOrResources(String linksOrResources) {
            TextView linksOrResources_content = (TextView) mView.findViewById(R.id.linksOrResources);
            linksOrResources_content.setText(linksOrResources);
        }
    }
}
