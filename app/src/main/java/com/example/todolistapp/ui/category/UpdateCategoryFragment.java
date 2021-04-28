package com.example.todolistapp.ui.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolistapp.viewmodel.CategoryViewModel;
import com.example.todolistapp.R;
import com.example.todolistapp.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateCategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateCategoryFragment newInstance(String param1, String param2) {
        UpdateCategoryFragment fragment = new UpdateCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_category, container, false);
    }

    private TextInputEditText titleInputEditText,descriptionInputEditText;
    private CategoryViewModel categoryViewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryViewModel= new ViewModelProvider(this).get(CategoryViewModel.class);

        titleInputEditText=view.findViewById(R.id.titleInputEdit);
        descriptionInputEditText=view.findViewById(R.id.descriptionInputEdit);

        FloatingActionButton floatingActionButton=view.findViewById(R.id.fabCategory);

        String title=getArguments().getString("title");
        String description=getArguments().getString("description");


       int cat_id=getArguments().getInt("id");

        titleInputEditText.setText(title);

        descriptionInputEditText.setText(description);



        floatingActionButton.setOnClickListener(v -> updateCategory(cat_id));
    }

    private void updateCategory(int catId){
        String title=titleInputEditText.getText().toString();
        String description=descriptionInputEditText.getText().toString();
        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(requireContext(),"Please insert title and description",Toast.LENGTH_SHORT).show();
            return;
        }
        Category category=new Category(title,description);
        category.setId(catId);
        categoryViewModel.update(category);
        Toast.makeText(requireContext(),"Category updated",Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigateUp();
    }
}