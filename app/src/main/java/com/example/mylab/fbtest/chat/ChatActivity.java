package com.example.mylab.fbtest.chat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylab.fbtest.Model;
import com.example.mylab.fbtest.Notif.MyFirebaseInstanceIdService;
import com.example.mylab.fbtest.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {
    public static final String TAG = "message";
    @BindView(R.id.recycler_chat)RecyclerView recyclerView;
@BindView(R.id.chatEditText)EditText chatText;
    DatabaseReference reference;
    Query query;
    FirebaseRecyclerAdapter chatAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        Log.e(TAG, "Firbase Token: "+ FirebaseInstanceId.getInstance().getToken());

        reference= FirebaseDatabase.getInstance().getReference().child("mychat");
        //reference.removeValue();
        initRecycler();

    }
    public void initRecycler()
    {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        //mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);


    recyclerView.setLayoutManager(mLinearLayoutManager);
    if(chatAdapter==null)
    {
        initChatAdapter();
        recyclerView.setAdapter(chatAdapter);
    }

    }
    public void initChatAdapter()
    {
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("mychat")
                .limitToLast(50);
        FirebaseRecyclerOptions<ModelChat> options =
                new FirebaseRecyclerOptions.Builder<ModelChat>()
                        .setQuery(query, ModelChat.class)
                        .build();
        chatAdapter=new FirebaseRecyclerAdapter<ModelChat,chatData>(options) {
            @Override
            protected void onBindViewHolder(@NonNull chatData holder, int position, @NonNull ModelChat model) {

                if(!model.getMsgUser().toString().equalsIgnoreCase("abi"))
                {
                    holder.chat_head_relative.setGravity(Gravity.LEFT);
                   // recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);

                }
                else
                {
                    holder.chat_head_relative.setGravity(Gravity.RIGHT);
                    //recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);

                }
                holder.msg.setText(model.getMsgText());


                //recyclerView.scrollToPosition(position);


            }

            @Override
            public chatData onCreateViewHolder(ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_head_layout,parent,false);
                return new chatData(view);
            }
        };
        chatAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = chatAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });
    }
    public class chatData extends RecyclerView.ViewHolder
    {
        @BindView(R.id.chat_head_relative)RelativeLayout chat_head_relative;
        @BindView(R.id.chat_message)TextView msg;
        public chatData(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

    }

    public void sendChat(View view) {
        if(!chatText.getText().toString().isEmpty())
        {
            reference.push().setValue(new ModelChat(chatText.getText().toString(),"abi"));
            chatText.setText("");

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        chatAdapter.startListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatAdapter.stopListening();
    }
}
