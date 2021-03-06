package com.example.comp304_group1_microproject6;

import com.example.comp304_group1_microproject6.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	EditText USER_NAME, USER_PASS, CON_PASS;
	String user_name, user_pass, con_pass;
	Button REG;
	Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		USER_NAME = (EditText) findViewById(R.id.reg_user);
		USER_PASS = (EditText) findViewById(R.id.reg_pass);
		CON_PASS = (EditText) findViewById(R.id.con_pass);
		REG = (Button) findViewById(R.id.user_reg);
		REG.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				user_name = USER_NAME.getText().toString();
				user_pass = USER_PASS.getText().toString();
				con_pass = CON_PASS.getText().toString();

				if (!(user_pass.equals(con_pass))) {
					Toast.makeText(getBaseContext(),
							"Passwords are not matching", Toast.LENGTH_LONG).show();
					USER_NAME.setText("");
					USER_PASS.setText("");
					CON_PASS.setText("");

				} else if (user_name.equalsIgnoreCase("") || user_pass.equalsIgnoreCase("")) {
					Toast.makeText(getBaseContext(),
							"Please enter a user name and password", Toast.LENGTH_LONG).show();
				} else {
					DatabaseOperations DB = new DatabaseOperations(ctx);
					if (DB.duplicateAccount(DB, user_name) == false)
					{
						DB.putInformation(DB, user_name, user_pass);
						Toast.makeText(getBaseContext(), "Registration Success",
								Toast.LENGTH_LONG).show();
						finish();
						startActivity(new Intent(RegisterActivity.this,
								FirstPageActivity.class));
					}
					else
					{
						Toast.makeText(getBaseContext(), "Account name taken!",
								Toast.LENGTH_LONG).show();
					}
					
				}
			}

		});
	}
}
