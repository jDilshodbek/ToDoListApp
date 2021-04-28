package com.example.todolistapp.ui.task;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.todolistapp.R;
import com.example.todolistapp.model.Category;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.viewmodel.CategoryViewModel;
import com.example.todolistapp.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTaskFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditTaskFragment newInstance(String param1, String param2) {
        EditTaskFragment fragment = new EditTaskFragment();
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
        return inflater.inflate(R.layout.fragment_edit_task, container, false);
    }

    private TaskViewModel taskViewModel;
    private TextInputEditText titleInputEditText, descriptionInputEditText;
    private AppCompatSpinner catSpinner;
    private CategoryViewModel categoryViewModel;
    private ArrayList<String> categoriesList;
    private Task task;
    private ArrayAdapter spinnerAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoriesList = new ArrayList<>();

        int task_id = getArguments().getInt("id");
        boolean isCheckedArg = getArguments().getBoolean("isCompleted");
        String catArg = getArguments().getString("category");


        task = new Task();

        catSpinner = view.findViewById(R.id.chooseCatSpinner);
        categoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            categoriesList.clear();
            for (Category category : categories) {
                categoriesList.add(category.getTitle());
            }

            spinnerAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, categoriesList.toArray(new String[categoriesList.size()]));
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            catSpinner.setAdapter(spinnerAdapter);
            catSpinner.setOnItemSelectedListener(this);

            if (catArg != null && !catArg.isEmpty()) {
                catSpinner.setSelection(categoriesList.indexOf(catArg));
            }


        });

        titleInputEditText = view.findViewById(R.id.titleInputEdit);
        descriptionInputEditText = view.findViewById(R.id.descriptionInputEdit);

        String title = getArguments().getString("title");

        titleInputEditText.setText(title);

        String description = getArguments().getString("description");

        descriptionInputEditText.setText(description);

        CheckBox checkBox = view.findViewById(R.id.taskCompletedCheckBox);

        checkBox.setChecked(isCheckedArg);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
        });

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabTask);

        floatingActionButton.setOnClickListener(v -> updateTask(task_id));
    }


    private void updateTask(int taskId) {

        String title = titleInputEditText.getText().toString();
        String description = descriptionInputEditText.getText().toString();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(requireContext(), "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        task.setTitle(title);
        task.setDescription(description);
        task.setId(taskId);
        taskViewModel.update(task);
        Toast.makeText(requireContext(), "Task updated", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigateUp();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        task.setCategoryName(categoriesList.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (categoriesList.size() <= 0) {
            task.setCategoryName(null);
        } else {
            task.setCategoryName(categoriesList.get(0));
        }

    }
}