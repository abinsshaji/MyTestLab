package com.example.mylab.fbtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "message";
    Query query;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter adapter;
    EditText editText;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        editText=findViewById(R.id.editText);
        reference=FirebaseDatabase.getInstance().getReference("uitest").child("new");
        reference.removeValue();
        init();



    }
    public void initFirebaseRecycler()
    {

        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("uitest/new")
                .limitToLast(50);


        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(query, Model.class)
                        .build();
        adapter=new FirebaseRecyclerAdapter<Model,DHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DHolder holder, int position, @NonNull Model model) {
                Log.e(TAG, "onBindViewHolder: "+model.getContent().toString());
                holder.text.setText(model.getContent().toString());

            }

            @Override
            public DHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_layout, parent, false);
                return new DHolder(view);
            }
        };


    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(adapter==null)
        {
            initFirebaseRecycler();
            recyclerView.setAdapter(adapter);
        }
    }

    public void addData(View view) {

        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("content","hjfjf");
        if(!editText.getText().toString().isEmpty())
            reference.push().setValue(new Model(editText.getText().toString()));
        Log.e(TAG, "addData: "+reference.getKey() );

    }


    public class DHolder extends RecyclerView.ViewHolder
    {
        TextView text;
        public DHolder(View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
