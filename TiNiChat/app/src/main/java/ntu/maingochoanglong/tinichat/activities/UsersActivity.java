package ntu.maingochoanglong.tinichat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import ntu.maingochoanglong.tinichat.adapters.UsersAdapter;
import ntu.maingochoanglong.tinichat.databinding.ActivityUsersBinding;
import ntu.maingochoanglong.tinichat.listeners.UserListener;
import ntu.maingochoanglong.tinichat.models.User;
import ntu.maingochoanglong.tinichat.utilities.Constants;
import ntu.maingochoanglong.tinichat.utilities.PreferenceManager;

public class UsersActivity extends BaseActivity implements UserListener {

    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;
    private List<User> allUsers;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
        getUsers();
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        binding.imageSearch.setOnClickListener(v -> {
            if (binding.inputSearch.getVisibility() == View.VISIBLE) {
                binding.inputSearch.setVisibility(View.GONE);
                binding.inputSearch.setText(null);
                binding.textSelectUser.setVisibility(View.VISIBLE);
                showAllUsers();
            } else {
                binding.inputSearch.setVisibility(View.VISIBLE);
                binding.textSelectUser.setVisibility(View.GONE);
                binding.inputSearch.requestFocus();
            }
        });

        binding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterUsers(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getUsers() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentUserId = preferenceManager.getString(Constants.KEY_USER_ID);
                    if (task.isSuccessful() && task.getResult() != null) {
                        allUsers = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            if (currentUserId.equals(queryDocumentSnapshot.getId())) {
                                continue;
                            }
                            User user = new User();
                            user.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            user.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            user.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            user.id = queryDocumentSnapshot.getId();
                            allUsers.add(user);
                        }
                        if (!allUsers.isEmpty()) {
                            showAllUsers();
                        } else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }

    private void showAllUsers() {
        usersAdapter = new UsersAdapter(allUsers, this);
        binding.usersRecyclerView.setAdapter(usersAdapter);
        binding.usersRecyclerView.setVisibility(View.VISIBLE);
    }

    private void filterUsers(String keyword) {
        if (keyword != null) {
            List<User> filteredList = new ArrayList<>();
            for (User user : allUsers) {
                if (user.name.toLowerCase().contains(keyword.toLowerCase()) ||
                        user.email.toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(user);
                }
            }
            if (!filteredList.isEmpty()) {
                usersAdapter = new UsersAdapter(filteredList, this);
                binding.usersRecyclerView.setAdapter(usersAdapter);
            }
        }
    }

    private void showErrorMessage() {
        binding.textErrorMessage.setText(String.format("%s","No user available"));
        binding.textErrorMessage.setVisibility(View.VISIBLE);
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onUserClicked(User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
        finish();
    }
}