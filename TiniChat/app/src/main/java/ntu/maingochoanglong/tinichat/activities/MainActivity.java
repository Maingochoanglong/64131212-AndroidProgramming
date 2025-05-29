package ntu.maingochoanglong.tinichat.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ntu.maingochoanglong.tinichat.adapters.RecentConversationsAdapter;
import ntu.maingochoanglong.tinichat.databinding.ActivityMainBinding;
import ntu.maingochoanglong.tinichat.listeners.ConversionListener;
import ntu.maingochoanglong.tinichat.models.ChatMessage;
import ntu.maingochoanglong.tinichat.models.User;
import ntu.maingochoanglong.tinichat.utilities.Constants;
import ntu.maingochoanglong.tinichat.utilities.PreferenceManager;

public class MainActivity extends BaseActivity implements ConversionListener {

    private ActivityMainBinding binding;
    private PreferenceManager preferenceManager;
    private List<ChatMessage> conversations;
    private RecentConversationsAdapter conversationsAdapter;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        init();
        loadUserDetails();
        setListeners();
        listenConversations();
    }

    private void init() {
        conversations = new ArrayList<>();
        conversationsAdapter = new RecentConversationsAdapter(conversations, this);
        binding.conversationsRecyclerView.setAdapter(conversationsAdapter);
        database = FirebaseFirestore.getInstance();
    }

    private void setListeners() {
        binding.imageSignOut.setOnClickListener(v -> signOut());
        binding.fabNewChat.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), UsersActivity.class)));
    }

    private void loadUserDetails() {
        binding.textName.setText(preferenceManager.getString(Constants.KEY_NAME));
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void listenConversations() {
        database.collection(Constants.KEY_COLLECTION_CONVERSATIONS)
                .addSnapshotListener(eventListener);
    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            String currentUserId = preferenceManager.getString(Constants.KEY_USER_ID);

            for (DocumentChange documentChange : value.getDocumentChanges()) {
                String senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                String receiverId = documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID);

                if (!currentUserId.equals(senderId) && !currentUserId.equals(receiverId)) {
                    continue;
                }

                String lastMessage = documentChange.getDocument().getString(Constants.KEY_LAST_MESSAGE);
                Date timestamp = documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP);
                String displayMessage = currentUserId.equals(senderId) ? "You: " + lastMessage : lastMessage;

                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    boolean exists = false;
                    for (ChatMessage cm : conversations) {
                        if ((cm.senderId.equals(senderId) && cm.receiverId.equals(receiverId)) ||
                                (cm.senderId.equals(receiverId) && cm.receiverId.equals(senderId))) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) continue;

                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = senderId;
                    chatMessage.receiverId = receiverId;

                    if (currentUserId.equals(senderId)) {
                        chatMessage.conversionImage = documentChange.getDocument().getString(Constants.KEY_RECEIVER_IMAGE);
                        chatMessage.conversionName = documentChange.getDocument().getString(Constants.KEY_RECEIVER_NAME);
                        chatMessage.conversionId = receiverId;
                    } else {
                        chatMessage.conversionImage = documentChange.getDocument().getString(Constants.KEY_SENDER_IMAGE);
                        chatMessage.conversionName = documentChange.getDocument().getString(Constants.KEY_SENDER_NAME);
                        chatMessage.conversionId = senderId;
                    }

                    chatMessage.message = displayMessage;
                    chatMessage.dataTime = getReadableDateTime(timestamp);
                    chatMessage.dateObject = timestamp;

                    conversations.add(0, chatMessage);
                    conversationsAdapter.notifyItemInserted(0);
                    binding.conversationsRecyclerView.smoothScrollToPosition(0);
                }

                else if (documentChange.getType() == DocumentChange.Type.MODIFIED) {
                    for (int i = 0; i < conversations.size(); i++) {
                        ChatMessage cm = conversations.get(i);
                        if ((cm.senderId.equals(senderId) && cm.receiverId.equals(receiverId)) ||
                                (cm.senderId.equals(receiverId) && cm.receiverId.equals(senderId))) {

                            cm.message = displayMessage;
                            cm.dataTime = getReadableDateTime(timestamp);
                            cm.dateObject = timestamp;

                            conversations.remove(i);
                            conversations.add(0, cm);
                            conversationsAdapter.notifyItemMoved(i, 0);
                            conversationsAdapter.notifyItemChanged(0);
                            binding.conversationsRecyclerView.smoothScrollToPosition(0);
                            break;
                        }
                    }
                }
            }

            binding.conversationsRecyclerView.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    };



    private String getReadableDateTime(Date date) {
        return new SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
    }

    private void signOut() {
        showToast("Signing out...");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        Map<String, Object> updates = new HashMap<>();
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clear();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> showToast("Unable to sign out"));
    }

    @Override
    public void onConversionClicked(User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
    }
}