package com.example.coffeeorder;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private final DataManager dataManager = DataManager.getInstance();
    private DataManager.UserInformationChangedListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvUserName = findViewById(R.id.user_name);
        TextView tvUserPhoneNumber = findViewById(R.id.user_phone_number);
        TextView tvUserMail = findViewById(R.id.user_email);
        TextView tvUserAddress = findViewById(R.id.user_address);
        ImageButton btUserName = findViewById(R.id.edit_name_button);
        ImageButton btUserPhoneNumber = findViewById(R.id.edit_phone_number_button);
        ImageButton btUserMail = findViewById(R.id.edit_mail_button);
        ImageButton btUserAddress = findViewById(R.id.edit_address_button);
        ImageButton btBackButton = findViewById(R.id.back_button);

        tvUserName.setText(dataManager.getUserName());
        tvUserPhoneNumber.setText(dataManager.getUserPhoneNumber());
        tvUserMail.setText(dataManager.getUserEmail());
        tvUserAddress.setText(dataManager.getUserAddress());

        btBackButton.setOnClickListener(v -> finish());
        btUserName.setOnClickListener(v -> showEditDialog("Edit Name", tvUserName, "name"));
        btUserPhoneNumber.setOnClickListener(v -> showEditDialog("Edit Phone Number", tvUserPhoneNumber, "phone"));
        btUserMail.setOnClickListener(v -> showEditDialog("Edit Email", tvUserMail, "email"));
        btUserAddress.setOnClickListener(v -> showEditDialog("Edit Address", tvUserAddress, "address"));

        listener = () -> {
            tvUserName.setText(dataManager.getUserName());
            tvUserPhoneNumber.setText(dataManager.getUserPhoneNumber());
            tvUserMail.setText(dataManager.getUserEmail());
            tvUserAddress.setText(dataManager.getUserAddress());
        };
        dataManager.addUserInfoListener(listener);
    }

    private void showEditDialog(String title, TextView textView, String field) {
        EditText editText = new EditText(this);
        editText.setText(textView.getText().toString());

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setView(editText) // Đặt EditText vào Dialog
                .setPositiveButton("Save", (dialog, which) -> {
                    String updatedValue = editText.getText().toString();
                    switch (field) {
                        case "name":
                            dataManager.setUserName(updatedValue);
                            break;
                        case "phone":
                            dataManager.setUserPhoneNumber(updatedValue);
                            break;
                        case "email":
                            dataManager.setUserEmail(updatedValue);
                            break;
                        case "address":
                            dataManager.setUserAddress(updatedValue);
                            break;
                    }
                    Toast.makeText(ProfileActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataManager.removeUserInfoListener(listener);
    }
}

