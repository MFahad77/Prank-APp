package com.example.prankapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prankapp.Adapters.MessagesAdapter;
import com.example.prankapp.Adapters.QuestionsAdapter;
import com.example.prankapp.Model.Message;
import com.example.prankapp.Model.Question;
import com.example.prankapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private RecyclerView recyclerViewQuestions;
    private MessagesAdapter messageAdapter;
    private QuestionsAdapter questionsAdapter;
    private List<Message> messageList;
    private List<User> userList;
    private int userIndex;
    private ImageView back;
    private ImageView toolbarImage;
    private TextView toolbarTitle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        recyclerViewQuestions = findViewById(R.id.recyclerViewQuestions);
        back = findViewById(R.id.back_user);
        toolbarImage = findViewById(R.id.userprofile);
        toolbarTitle = findViewById(R.id.user_name);
        toolbar = findViewById(R.id.accounttoolbar);
        setSupportActionBar(toolbar);

        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));

        userList = createUserList();
        messageList = new ArrayList<>();
        messageAdapter = new MessagesAdapter(messageList);
        recyclerViewChat.setAdapter(messageAdapter);

        Intent intent = getIntent();
        userIndex = intent.getIntExtra("USER_INDEX", 0);

        if (userIndex >= 0 && userIndex < userList.size()) {
            User selectedUser = userList.get(userIndex);
            questionsAdapter = new QuestionsAdapter(selectedUser.getQuestions());
            recyclerViewQuestions.setAdapter(questionsAdapter);

            updateToolbar(selectedUser);
            toolbarImage.setImageResource(selectedUser.getImageResourceId());

            questionsAdapter.setOnItemClickListener(new QuestionsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String question) {
                    addMessageToChat(question, true);
                    getResponse(question);
                }
            });

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    private void updateToolbar(User user) {
        toolbarTitle.setText(user.getName());
    }

    private List<User> createUserList() {
        List<User> users = new ArrayList<>();

        List<Question> questionsUser1 = new ArrayList<>();
        questionsUser1.add(new Question("Hello", "User1"));
        questionsUser1.add(new Question("How are you?", "User1"));
        questionsUser1.add(new Question("What's your name?", "User1"));
        questionsUser1.add(new Question("What do you like?", "User1"));
        questionsUser1.add(new Question("Where are you from?", "User1"));

        List<Question> questionsUser2 = new ArrayList<>();
        questionsUser2.add(new Question("What's your age?", "User2"));
        questionsUser2.add(new Question("What's your profession?", "User2"));
        questionsUser2.add(new Question("Do you have any idea of what is happening?", "User2"));
        questionsUser2.add(new Question("What is your favorite color?", "User2"));
        questionsUser2.add(new Question("Do you like music?", "User2"));

        List<Question> questionsUser3 = new ArrayList<>();
        questionsUser3.add(new Question("Do you play sports?", "User3"));
        questionsUser3.add(new Question("What's your hobby?", "User3"));
        questionsUser3.add(new Question("What's your favorite food?", "User3"));
        questionsUser3.add(new Question("Where do you live?", "User3"));
        questionsUser3.add(new Question("Do you travel often?", "User3"));

        List<Question> questionsUser4 = new ArrayList<>();
        questionsUser4.add(new Question("What's your favorite book?", "User4"));
        questionsUser4.add(new Question("Do you have pets?", "User4"));
        questionsUser4.add(new Question("What's your dream job?", "User4"));
        questionsUser4.add(new Question("What's your favorite movie?", "User4"));
        questionsUser4.add(new Question("What's your favorite season?", "User4"));

        List<Question> questionsUser5 = new ArrayList<>();
        questionsUser5.add(new Question("Do you like coding?", "User5"));
        questionsUser5.add(new Question("What's your favorite language?", "User5"));
        questionsUser5.add(new Question("Do you play video games?", "User5"));
        questionsUser5.add(new Question("Do you like coffee?", "User5"));
        questionsUser5.add(new Question("What's your favorite app?", "User5"));

        users.add(new User("Santa", questionsUser1, R.drawable.santa));
        users.add(new User("Hacker", questionsUser2, R.drawable.hacker));
        users.add(new User("Anna", questionsUser3, R.drawable.princess));
        users.add(new User("Scientist", questionsUser4, R.drawable.scientist));
        users.add(new User("BTS", questionsUser5, R.drawable.bts));

        return users;
    }

    private void addMessageToChat(String message, boolean isUser) {
        messageList.add(new Message(message, isUser));
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerViewChat.scrollToPosition(messageList.size() - 1);
    }

    private void getResponse(String userMessage) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String response = getPredefinedResponse(userMessage);
                addMessageToChat(response, false);
            }
        }, 500);
    }

    private String getPredefinedResponse(String userMessage) {
        switch (userMessage) {
            case "Hello":
                return "Hi there!";
            case "How are you?":
                return "I'm good, thanks!";
            case "What's your name?":
                return "I'm a Bot.";
            case "What do you like?":
                return "I like chatting with you!";
            case "Where are you from?":
                return "I'm from the internet!";
            case "What's your age?":
                return "I am 20 years old";
            case "What's your profession?":
                return "I am here to chat with you.";
            case "Do you have any idea of what is happening?":
                return "I am aware of many things!";
            case "What is your favorite color?":
                return "I like all colors!";
            case "Do you like music?":
                return "Yes, music is great!";
            case "Do you play sports?":
                return "I like virtual sports!";
            case "What's your hobby?":
                return "Chatting with you!";
            case "What's your favorite food?":
                return "I like pizza!";
            case "Where do you live?":
                return "I live in California";
            case "Do you travel often?":
                return "Sometimes";
            case "What's your favorite book?":
                return "Games of Thrones";
            case "Do you have pets?":
                return "No";
            case "What's your dream job?":
                return "I'm already doing it!";
            case "What's your favorite movie?":
                return "I like sci-fi movies!";
            case "What's your favorite season?":
                return "I like all seasons equally!";
            case "Do you like coding?":
                return "Yes, coding is fun!";
            case "What's your favorite language?":
                return "I like all programming languages!";
            case "Do you play video games?":
                return "I like watching others play!";
            case "Do you like coffee?":
                return "I don't drink";
            case "What's your favorite app?":
                return "I like chat apps!";
            default:
                return "Sorry, I don't understand.";
        }
    }
}
