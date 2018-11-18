package es.iessaladillo.alex.adm_pr06_recyclerview.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import es.iessaladillo.alex.adm_pr06_recyclerview.R;
import es.iessaladillo.alex.adm_pr06_recyclerview.ui.avatar.AvatarActivity;
import es.iessaladillo.alex.adm_pr06_recyclerview.utils.IntentsUtils;
import es.iessaladillo.alex.adm_pr06_recyclerview.utils.KeyboardUtils;
import es.iessaladillo.alex.adm_pr06_recyclerview.utils.SnackbarUtils;
import es.iessaladillo.alex.adm_pr06_recyclerview.utils.TextViewUtils;
import es.iessaladillo.alex.adm_pr06_recyclerview.utils.ValidationUtils;

public class MainActivity extends AppCompatActivity {

    private TextView lblAvatar;
    private ImageView imgAvatar;
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPhonenumber;
    private EditText txtAddress;
    private EditText txtWeb;
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblPhonenumber;
    private TextView lblAddress;
    private TextView lblWeb;
    private ImageView imgEmail;
    private ImageView imgPhonenumber;
    private ImageView imgAddress;
    private ImageView imgWeb;
    private final String VALID_NAME = "VALID_NAME";
    private final String VALID_EMAIL = "VALID_EMAIL";
    private final String VALID_PHONENUMBER = "VALID_PHONENUMBER";
    private final String VALID_ADDRESS = "VALID_ADDRESS";
    private final String VALID_WEB = "VALID_WEB";
    private final int RC_AVATAR = 12;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        initViews();
        if (savedInstanceState != null) {
            setupSaveData(savedInstanceState);
        }
    }

    private void setupSaveData(Bundle savedInstanceState) {
        if (savedInstanceState.getCharSequence(VALID_NAME) == getString(R.string.main_invalid_data)) {
            validateName();
        }
        if (savedInstanceState.getCharSequence(VALID_EMAIL) == getString(R.string.main_invalid_data)) {
            validateEmail();
        }
        if (savedInstanceState.getCharSequence(VALID_PHONENUMBER) == getString(R.string.main_invalid_data)) {
            validatePhonenumber();
        }
        if (savedInstanceState.getCharSequence(VALID_ADDRESS) == getString(R.string.main_invalid_data)) {
            validateAddress();
        }
        if (savedInstanceState.getCharSequence(VALID_WEB) == getString(R.string.main_invalid_data)) {
            validateWeb();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startOnchange();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finishOnChange();
    }

    private void startOnchange() {
        TextViewUtils.afterTextChanged(txtName, lblName, this);
        TextViewUtils.onTextChanged(txtEmail, lblEmail, imgEmail, this);
        TextViewUtils.onTextChanged(txtPhonenumber, lblPhonenumber, imgPhonenumber, this);
        TextViewUtils.afterTextChanged(txtAddress, lblAddress, imgAddress, this);
        TextViewUtils.onTextChanged(txtWeb, lblWeb, imgWeb, this);


    }

    private void finishOnChange() {
        TextViewUtils.removeOnTextChanged(txtName);
        TextViewUtils.removeOnTextChanged(txtEmail);
        TextViewUtils.removeOnTextChanged(txtPhonenumber);
        TextViewUtils.removeOnTextChanged(txtAddress);
        TextViewUtils.removeOnTextChanged(txtWeb);
    }

    private void initViews() {
        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);
        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        txtName = ActivityCompat.requireViewById(this, R.id.txtName);
        txtEmail = ActivityCompat.requireViewById(this, R.id.txtEmail);
        txtPhonenumber = ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        txtAddress = ActivityCompat.requireViewById(this, R.id.txtAddress);
        txtWeb = ActivityCompat.requireViewById(this, R.id.txtWeb);
        lblName = ActivityCompat.requireViewById(this, R.id.lblName);
        lblEmail = ActivityCompat.requireViewById(this, R.id.lblEmail);
        lblPhonenumber = ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        lblAddress = ActivityCompat.requireViewById(this, R.id.lblAddress);
        lblWeb = ActivityCompat.requireViewById(this, R.id.lblWeb);
        imgEmail = ActivityCompat.requireViewById(this, R.id.imgEmail);
        imgPhonenumber = ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        imgAddress = ActivityCompat.requireViewById(this, R.id.imgAddress);
        imgWeb = ActivityCompat.requireViewById(this, R.id.imgWeb);

        defaultAvatar();
        showAvatar();
        changeFocus();
        editorAction();
        imgAvatar.setOnClickListener(v -> changeImg());
        lblAvatar.setOnClickListener(v -> changeImg());
        imgEmail.setOnClickListener(v -> sendEmail());
        imgPhonenumber.setOnClickListener(v -> dialPhoneNumber());
        imgAddress.setOnClickListener(v -> searchInMap());
        imgWeb.setOnClickListener(v -> webSearch());
    }

    private void defaultAvatar() {
        if (viewModel.getAvatar() == null) {
            viewModel.setAvatar(viewModel.getDefaultAvatar());
        }
    }

    private void webSearch() {
        Intent intent;
        if (validateWeb()) {
            intent = IntentsUtils.newWebSearch(txtWeb.getText().toString());
            sendIntent(intent);
        }
    }

    private void searchInMap() {
        Intent intent;
        if (validateAddress()) {
            intent = IntentsUtils.newSearchInMap(txtAddress.getText().toString());
            sendIntent(intent);
        }
    }

    private void dialPhoneNumber() {
        Intent intent;
        if (validatePhonenumber()) {
            intent = IntentsUtils.newDial(txtPhonenumber.getText().toString());
            sendIntent(intent);
        }
    }

    private void sendEmail() {
        Intent intent;
        if (validateEmail()) {
            intent = IntentsUtils.newEmail(txtEmail.getText().toString());
            sendIntent(intent);
        }
    }

    private void sendIntent(Intent intent) {
        if (IntentsUtils.isAvailable(this, intent)) {
            startActivity(intent);
        } else {
            KeyboardUtils.hideSoftKeyboard(this);
            SnackbarUtils.snackbar(imgWeb, "Can not find an application to perform this action");
        }
    }

    private void editorAction() {
        txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                save();
                return true;
            }
            return false;
        });
    }

    private void changeFocus() {
        TextViewUtils.changeFocus(txtName, lblName);
        TextViewUtils.changeFocus(txtEmail, lblEmail);
        TextViewUtils.changeFocus(txtPhonenumber, lblPhonenumber);
        TextViewUtils.changeFocus(txtAddress, lblAddress);
        TextViewUtils.changeFocus(txtWeb, lblWeb);
    }

    private void showAvatar() {
        imgAvatar.setImageResource(viewModel.getAvatar().getImageResId());
        imgAvatar.setTag(viewModel.getAvatar().getImageResId());
        lblAvatar.setText(viewModel.getAvatar().getName());
    }

    private void changeImg() {
        AvatarActivity.startForResult(this, RC_AVATAR, viewModel.getAvatar());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == RC_AVATAR) {
            if (data != null && data.hasExtra(AvatarActivity.EXTRA_AVATAR)) {
                viewModel.setAvatar(data.getParcelableExtra(AvatarActivity.EXTRA_AVATAR));
                showAvatar();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateName() {
        if (txtName.getText().toString().isEmpty()) {
            invalidData(txtName, lblName);
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        if (!ValidationUtils.isValidEmail(txtEmail.getText().toString())) {
            invalidData(txtEmail, lblEmail, imgEmail);
            return false;
        }
        return true;
    }

    private boolean validatePhonenumber() {
        if (!ValidationUtils.isValidPhone(txtPhonenumber.getText().toString())) {
            invalidData(txtPhonenumber, lblPhonenumber, imgPhonenumber);
            return false;
        }
        return true;
    }

    private boolean validateAddress() {
        if (txtAddress.getText().toString().isEmpty()) {
            invalidData(txtAddress, lblAddress, imgAddress);
            return false;
        }
        return true;
    }

    private boolean validateWeb() {
        if (!ValidationUtils.isValidUrl(txtWeb.getText().toString())) {
            invalidData(txtWeb, lblWeb, imgWeb);
            return false;
        }
        return true;
    }

    private void invalidData(EditText txt, TextView lbl) {
        txt.setError(getString(R.string.main_invalid_data));
        lbl.setEnabled(false);
    }

    private void invalidData(EditText txt, TextView lbl, ImageView imageView) {
        txt.setError(getString(R.string.main_invalid_data));
        lbl.setEnabled(false);
        imageView.setEnabled(false);
    }

    private boolean validate() {
        boolean validName, validEmail, validPhonenumber, validAddress, validWeb;
        validName = validateName();
        validEmail = validateEmail();
        validPhonenumber = validatePhonenumber();
        validAddress = validateAddress();
        validWeb = validateWeb();

        return validName && validEmail && validPhonenumber && validAddress && validWeb;
    }

    private void save() {
        KeyboardUtils.hideSoftKeyboard(this);
        if (!validate()) {
            SnackbarUtils.snackbar(lblName, getString(R.string.main_error_saving));
        } else {
            SnackbarUtils.snackbar(lblName, getString(R.string.main_saved_succesfully));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(VALID_NAME, txtName.getError());
        outState.putCharSequence(VALID_EMAIL, txtEmail.getError());
        outState.putCharSequence(VALID_PHONENUMBER, txtPhonenumber.getError());
        outState.putCharSequence(VALID_ADDRESS, txtAddress.getError());
        outState.putCharSequence(VALID_WEB, txtWeb.getError());
    }
}
