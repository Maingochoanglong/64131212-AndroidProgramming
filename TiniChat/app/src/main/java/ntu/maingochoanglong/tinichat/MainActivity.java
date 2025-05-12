package ntu.maingochoanglong.tinichat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import ntu.maingochoanglong.tinichat.menu.CallsFragment;
import ntu.maingochoanglong.tinichat.menu.ChatsFragment;
import ntu.maingochoanglong.tinichat.menu.StatusFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ChatsFragment());
        fragmentList.add(new StatusFragment());
        fragmentList.add(new CallsFragment());

        List<String> fragmentTitleList = new ArrayList<>();
        fragmentTitleList.add("Chats");
        fragmentTitleList.add("Status");
        fragmentTitleList.add("Calls");

        MainActivity.ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this, fragmentList);
        viewPager2.setAdapter(viewPager2Adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(fragmentTitleList.get(position));
            }
        }).attach();
    }

    public static class ViewPager2Adapter extends FragmentStateAdapter {
        private final List<Fragment> mFragmentList;

        public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> mFragmentList) {
            super(fragmentActivity);
            this.mFragmentList = mFragmentList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragmentList.size();
        }
    }
}