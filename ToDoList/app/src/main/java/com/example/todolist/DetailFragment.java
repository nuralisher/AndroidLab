package com.example.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class DetailFragment extends Fragment {
    Contact contact;

    AppDatabase db;
    ContactDao contactDao;
    CategoryDao categoryDao;
    Button upd, delete;

    public DetailFragment(Contact contact){
        this.contact = contact;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.title);
        TextView status = view.findViewById(R.id.status);
        TextView description = view.findViewById(R.id.description);
        TextView duration = view.findViewById(R.id.duration);
        TextView categoryView = view.findViewById(R.id.category);
        upd = view.findViewById(R.id.update);
        delete = view.findViewById(R.id.delete);
        final int ourId = this.contact.id;

        title.setText("Title: "+contact.title+", id: " + contact.id);
        status.setText("Status: "+ contact.status);
        description.setText("Description: "+ contact.description);
        duration.setText("Duration: " + contact.duration);

        db = MyApplication.getInstance().getDatabase();
        categoryDao = db.categoryDao();
        contactDao = db.contactDao();
        Category category = categoryDao.getCategory(contact.category_id);
        categoryView.setText("Category Title: " + category.title+ ", id: "+category.id);

        upd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("tag","upd clicked");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.mainPage, new UpdateFragment(contact)).addToBackStack("update")
                        .commit();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Contact> contacts = contactDao.getContactList();
                for(Contact contact: contacts){
                    if(contact.id==ourId){
                        contactDao.delete(contact);
                        Toast.makeText(getContext(),"Contact was deleted",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Toast.makeText(getContext(),"Contact was already deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

}
