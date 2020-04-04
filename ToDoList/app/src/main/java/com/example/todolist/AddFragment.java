package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class AddFragment extends Fragment {
    Button add;
    EditText title,status,description,duration,categoryID;

    AppDatabase db;
    ContactDao contactDao;
    CategoryDao categoryDao;
    Context context;

    public AddFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.title);
        status = view.findViewById(R.id.status);
        description = view.findViewById(R.id.description);
        duration = view.findViewById(R.id.duration);
        categoryID = view.findViewById(R.id.categoryID);
        add = view.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString().trim();
                String statusText = status.getText().toString().trim();
                String descriptionText = description.getText().toString().trim();
                String durationTExt = duration.getText().toString().trim();
                String categoryIDstr = categoryID.getText().toString().trim();
                if(categoryIDstr.isEmpty()){
                    Toast.makeText(context, "Fill all inputs", Toast.LENGTH_LONG).show();
                    return;
                }

                int categoryIDnum = Integer.valueOf(categoryIDstr);

                db = MyApplication.getInstance().getDatabase();
                categoryDao = db.categoryDao();
                contactDao = db.contactDao();
                List<Category> categories = categoryDao.getCategoryList();
                boolean isExist = false;
                for(Category category: categories){
                    if(category.id==categoryIDnum){
                        isExist = true;
                        break;
                    }
                }

                if(!isExist){
                    Toast.makeText(context, "Not Found Category ID", Toast.LENGTH_LONG).show();
                    return;
                }

                boolean hasEmpty = titleText.isEmpty()||statusText.isEmpty()||descriptionText.isEmpty()||durationTExt.isEmpty();

                if(hasEmpty){
                    Toast.makeText(context, "Fill all inputs", Toast.LENGTH_LONG).show();
                    return;
                }
                Contact contact = new Contact();
                contact.title = titleText;
                contact.status = statusText;
                contact.description = descriptionText;
                contact.duration = durationTExt;
                contact.category_id = categoryIDnum;
                contactDao.insert(contact);
                Toast.makeText(context, "Contact successfully added", Toast.LENGTH_LONG).show();
                title.setText("");
                status.setText("");
                description.setText("");
                duration.setText("");
                categoryID.setText("");
            }
        });
    }
}
