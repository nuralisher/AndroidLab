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

public class UpdateFragment extends Fragment {
    EditText title,status,description,duration,category_id;
    Button upd;
    AppDatabase db;
    ContactDao contactDao;
    CategoryDao categoryDao;
    int id;

    public UpdateFragment(Contact contact){
        this.id = contact.id;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.title);
        status = view.findViewById(R.id.status);
        description = view.findViewById(R.id.description);
        duration = view.findViewById(R.id.duration);
        category_id = view.findViewById(R.id.categoryID);
        upd = view.findViewById(R.id.upd);
        final int OurId = this.id;

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString().trim();
                String statusText = status.getText().toString().trim();
                String descriptionText = description.getText().toString().trim();
                String durationTExt = duration.getText().toString().trim();
                String categoryIDstr = category_id.getText().toString().trim();

                boolean hasEmpty = titleText.isEmpty()||statusText.isEmpty()
                        ||descriptionText.isEmpty()||durationTExt.isEmpty()||categoryIDstr.isEmpty();

                if(hasEmpty){
                    Toast.makeText(getContext(), "Fill all inputs", Toast.LENGTH_LONG).show();
                    return;
                }

                int categoryIDnum = Integer.valueOf(categoryIDstr);

                db = MyApplication.getInstance().getDatabase();
                contactDao = db.contactDao();
                categoryDao = db.categoryDao();
                List<Contact> contacts = contactDao.getContactList();
                List<Category> categories = categoryDao.getCategoryList();

                boolean isExist = false;

                for(Category category:categories){
                    if(category.id==categoryIDnum){
                        isExist = true;
                    }
                }

                if(!isExist){
                    Toast.makeText(getContext(), "Category ID not found", Toast.LENGTH_LONG).show();
                    return;
                }

                boolean isDeleted = true;

                for(Contact contact: contacts){
                    if(contact.id==OurId){
                        contact.title = titleText;
                        contact.status = statusText;
                        contact.description = descriptionText;
                        contact.duration = durationTExt;
                        contact.category_id = categoryIDnum;
                        contactDao.update(contact);
                        isDeleted = false;
                    }
                }

                if(isDeleted){
                    Toast.makeText(getContext(),"Contact was deleted",Toast.LENGTH_LONG).show();
                    return;
                }


                Toast.makeText(getContext(), "Contact successfully updated", Toast.LENGTH_LONG).show();
                title.setText("");
                status.setText("");
                description.setText("");
                duration.setText("");
                category_id.setText("");
            }
        });
    }
}
