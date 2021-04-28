package com.example.todolistapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();

        NavigationView navigationView = findViewById(R.id.nav_view);


        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.tasksFragment:
                    getSupportActionBar().setTitle(getString(R.string.tasks));
                    break;
                case R.id.categoriesFragment:
                    getSupportActionBar().setTitle(getString(R.string.categories));
                    break;
                case R.id.createCategoryFragment:
                    getSupportActionBar().setTitle(getString(R.string.create_category));
                    break;
                case R.id.updateCategoryFragment:
                    getSupportActionBar().setTitle(getString(R.string.update_category));
                    break;
                case R.id.createTaskFragment:
                    getSupportActionBar().setTitle(getString(R.string.create_task));
                    break;
                case R.id.editTaskFragment:
                    getSupportActionBar().setTitle(getString(R.string.edit_task));
                    break;
                default:
                    getSupportActionBar().setTitle(getString(R.string.tasks));
                    break;

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}