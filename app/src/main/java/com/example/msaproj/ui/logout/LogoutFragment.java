package com.example.msaproj.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.msaproj.AddActivity;
import com.example.msaproj.LogInActivity;
import com.example.msaproj.NavigationActivity;
import com.example.msaproj.R;
import com.example.msaproj.databinding.FragmentGalleryBinding;
import com.example.msaproj.databinding.FragmentLogoutBinding;
import com.example.msaproj.ui.gallery.GalleryViewModel;

public class LogoutFragment extends Fragment {
    private LogoutViewModel logoutViewModel;
    private FragmentLogoutBinding binding;
    private Button buttonLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModel =
                new ViewModelProvider(this).get(LogoutViewModel.class);

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonLogout = (Button) root.findViewById(R.id.btn_logout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
            }
        });

        final TextView textView = binding.textLogout;
        logoutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
