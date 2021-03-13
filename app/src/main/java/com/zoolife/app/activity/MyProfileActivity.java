package com.zoolife.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.UpdateProfile.UpdateProfileResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends AppBaseActivity {

    ImageView firstNameE,lastNameE;
    EditText firstNameEt,lastNameEt;
    TextView emailTV;
    boolean first=false,last=false;
    ImageView profileBtn;
    ScrollView scrollable;
    Button btnSave;
    ImageView profileImage;
    private static final String IMAGE_DIRECTORY = "/PIMP";
    private int GALLERY = 1, CAMERA = 2;
    ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        progress_circular = findViewById(R.id.progress_circular);

        profileUpdate();
//        requestMultiplePermissions();
//
//        Glide
//                .with(getActivity())
//                .load("https://low-tels.com/survey/public/uploads/"+session.getImage())
//                .centerCrop()
//                .placeholder(R.drawable.profile_placeholder)
//                .into(profileImage);
//
//        emailTV.setText(session.getEmail());
//        firstNameEt.setText(session.getFirstName());
//        lastNameEt.setText(session.getLastName());
//
//        view.findViewById(R.id.settingBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), SettingsActivity.class));
//            }
//        });
//
//        view.findViewById(R.id.firstNameE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!first)
//                {
//                    btnSave.setVisibility(View.VISIBLE);
//                    firstNameE.setVisibility(View.GONE);
//                    first=true;
//                    firstNameEt.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.login_field));
//                    firstNameEt.setEnabled(true);
//                    //  firstNameEt.setText(firstNameEt.getHint());
//                    bottom_navigation.setVisibility(View.GONE);
//                }
//                else
//                {
//                    first=false;
//                    firstNameEt.setBackground(null);
//                    firstNameEt.setEnabled(false);
//                }
//
//            }
//        });
//        view.findViewById(R.id.lastNameE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!last)
//                {
//                    btnSave.setVisibility(View.VISIBLE);
//                    lastNameE.setVisibility(View.GONE);
//                    last=true;
//
//                    lastNameEt.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.login_field));
//                    lastNameEt.setEnabled(true);
//                    // lastNameEt.setText(lastNameEt.getText());
//                    bottom_navigation.setVisibility(View.GONE);
//
//
//                }
//                else
//                {
//                    last=false;
//                    lastNameEt.setBackground(null);
//                    lastNameEt.setEnabled(false);
//
//                }
//
//            }
//        });
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(isValid()){
//
//                    btnSave.setVisibility(View.GONE);
//                    firstNameE.setVisibility(View.VISIBLE);
//                    lastNameE.setVisibility(View.VISIBLE);
//                    first=false;
//                    last=false;
//                    firstNameEt.setBackground(null);
//                    firstNameEt.setEnabled(false);
//                    lastNameEt.setBackground(null);
//                    lastNameEt.setEnabled(false);
//                    bottom_navigation.setVisibility(View.VISIBLE);
//
//                    updateProfile();
//                }
//
//
//
//
//            }
//        });
//
//
//        profileBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                captureImage(profileImage);
//            }
//        });
//        firstNameEt.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                bottom_navigation.setVisibility(View.GONE);
//                return false;
//            }
//        });
////        firstNameEt.setOnTouchListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////
////            }
////        });
//        lastNameEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottom_navigation.setVisibility(View.GONE);
//
//            }
//        });
    }
    private void captureImage(ImageView profileImage) {
      //  showPictureDialog();
    }


    private boolean isValid() {

        if(firstNameEt.getText().toString().isEmpty()){
            firstNameEt.setError("Please enter Name");
            return false;
        }
        else if(lastNameEt.getText().toString().isEmpty()){
            lastNameEt.setError("Please enter Last Name");
            return false;
        }
//        if(!isValidEmail(emailET.getText().toString()))
//        {
//            emailET.setError("Please enter valid Email");
//            return false;
//        } else if(passwordET.getText().toString().isEmpty())
//        {
//            passwordET.setError("Please enter valid Password");
//            return false;
//        }
        return true;
    }


    private void initMe(View v) {
        emailTV = v.findViewById(R.id.emailTV);
        firstNameE = v.findViewById(R.id.firstNameE);
        lastNameE = v.findViewById(R.id.lastNameE);
        firstNameEt = v.findViewById(R.id.firstNameET);
        lastNameEt = v.findViewById(R.id.lastNameET);
        profileBtn = v.findViewById(R.id.profileBtn);
        profileImage = v.findViewById(R.id.profile_image);
        btnSave = v.findViewById(R.id.btnSave);
        scrollable = v.findViewById(R.id.scrollable);

    }

//    private void requestMultiplePermissions() {
//        Dexter.withActivity(getActivity())
//                .withPermissions(
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {  // check if all permissions are granted
//                            //  Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        if (report.isAnyPermissionPermanentlyDenied()) { // check for permanent denial of any permission
//                            // show alert dialog navigating to Settings
//                            //openSettingsDialog();
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).
//                withErrorListener(new PermissionRequestErrorListener() {
//                    @Override
//                    public void onError(DexterError error) {
//                        Toast.makeText(getActivity(), "Some Error! ", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .onSameThread()
//                .check();
//    }
//
//    private void showPictureDialog() {
//        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
//        pictureDialog.setTitle("Select Action");
//        String[] pictureDialogItems = {"Select photo from gallery", "Capture photo from camera"};
//        pictureDialog.setItems(pictureDialogItems,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                choosePhotoFromGallary();
//                                break;
//                            case 1:
//                                takePhotoFromCamera();
//                                break;
//                        }
//                    }
//                });
//        pictureDialog.show();
//    }
//    public void choosePhotoFromGallary() {
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(galleryIntent, GALLERY);
//    }
//    private void takePhotoFromCamera() {
//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA);
//
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == getActivity().RESULT_CANCELED) {
//            return;
//        }
//        if (requestCode == GALLERY) {
//            if (data != null) {
//                Uri contentURI = data.getData();
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
//                    profileImage.setImageBitmap(bitmap);
//                    uploadFileAPI(UriUtils.uri2File(contentURI));
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        } else if (requestCode == CAMERA) {
//            Uri contentURI = data.getData();
//            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//            profileImage.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            uploadFileAPI(UriUtils.uri2File(contentURI));
//            Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//        if (!wallpaperDirectory.exists()) {  // have the object build the directory structure, if needed.
//            wallpaperDirectory.mkdirs();
//        }
//
//        try {
//            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//            MediaScannerConnection.scanFile(getActivity(),
//                    new String[]{f.getPath()},
//                    new String[]{"image/jpeg"}, null);
//            fo.close();
//            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());
//
//            return f.getAbsolutePath();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "";
//    }
//
//    private void updateProfile() {
//
//        //  progress_bar.setVisibility(View.VISIBLE);
//
//
//        Api api = getRetrofitClientLogin().create(Api.class);
//        retrofit2.Call<ProfileResponseModel> call = null;
//
//        call = api.updateProfile(
//                session.getAccessKey(), firstNameEt.getText().toString(),lastNameEt.getText().toString()
//        );
//
//        call.enqueue(new Callback<ProfileResponseModel>() {
//            @Override
//            public void onResponse(retrofit2.Call<ProfileResponseModel> call, Response<ProfileResponseModel> response) {
//
//                ProfileResponseModel model  = response.body();
//
//                if(model!=null) {
//
//                    if (!model.isError()) {
//                        //  progressDialog.dismiss();
//                        session.setIsLogin(true);
//                        session.setFirstName(firstNameEt.getText().toString());
//                        session.setLastName(lastNameEt.getText().toString());
//                        session.setAccessKey(model.getData().getAccessKey());
//                        startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    }
//                    Toast.makeText(getActivity(), model.getMessage(), Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProfileResponseModel> call, Throwable throwable) {
//                // progress_bar.setVisibility(View.GONE);
//
//                // progressDialog.dismiss();
//                Toast.makeText(getActivity(), "" + throwable.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//        });
//    }
//
//
//    private void uploadFileAPI(File fileToSend) {
//        final Api api = RetrofitClient.getRetrofitClientLogin().create(Api.class);
//
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileToSend);
//        MultipartBody.Part media = MultipartBody.Part.createFormData("image", FileUtils.getFileName(fileToSend), requestFile);
//
//        api.uploadImage(
//                session.getAccessKey(),session.getFirstName()+" "+session.getLastName(),FileUtils.getFileName(fileToSend),media
//        ).enqueue(new Callback<ImageUploadResponseModel>() {
//
//            @Override
//            public void onResponse(Call<ImageUploadResponseModel> call, Response<ImageUploadResponseModel> response) {
//                ImageUploadResponseModel model = response.body();
//                if(model!=null && !model.isError()){
//                    Toast.makeText(getActivity(), "Image Uploaded!", Toast.LENGTH_LONG).show();
//                    session.setImage(model.getData().getImage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ImageUploadResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }


    private void profileUpdate() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<UpdateProfileResponseModel> call = apiService.updateUserProfile(session.getUserId(), session.getCountry(), session.getCity(), session.getFullName());
               // session.getSurName(),session.getFullName(),session.getLanguage(),session.getYear(),session.getMonth(),session.getDay(), Integer.parseInt(session.getCountryId()),
               // session.getCity(),
               // Integer.parseInt(session.getCityId()));
        call.enqueue(new Callback<UpdateProfileResponseModel>() {
            @Override
            public void onResponse(Call<UpdateProfileResponseModel> call, Response<UpdateProfileResponseModel> response) {
                UpdateProfileResponseModel responseModel = response.body();
                if (responseModel!=null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);



                }else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<UpdateProfileResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage()!=null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}