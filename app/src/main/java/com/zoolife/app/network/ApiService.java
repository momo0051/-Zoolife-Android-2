package com.zoolife.app.network;

import com.google.gson.JsonObject;
import com.zoolife.app.ResponseModel.AddComment.AddCommentResponseModel;
import com.zoolife.app.ResponseModel.AddPost.AddPostResponseModel;
import com.zoolife.app.ResponseModel.AllPost.AllPostResponseModel;
import com.zoolife.app.ResponseModel.Articles.AllArticlesResponseModel;
import com.zoolife.app.ResponseModel.Category.CategoryResponseModel;
import com.zoolife.app.ResponseModel.CategoryPost.AllCategoryPostResponseModel;
import com.zoolife.app.ResponseModel.ChangePassword.ChangePasswordResponseModel;
import com.zoolife.app.ResponseModel.FavModel.FavResponseModel;
import com.zoolife.app.ResponseModel.GetFavourites.GetFavouritesResponse;
import com.zoolife.app.ResponseModel.GetPost.GetPostResponseModel;
import com.zoolife.app.ResponseModel.GetUserProfile.GetUserProfileResponseModel;
import com.zoolife.app.ResponseModel.NoDataResponseModel;
import com.zoolife.app.ResponseModel.Notifications.NotificationsResponseModel;
import com.zoolife.app.ResponseModel.OTP.OTPResponseModel;
import com.zoolife.app.ResponseModel.Reset.ResetResponseModel;
import com.zoolife.app.ResponseModel.SearchPost.SearchResponseModel;
import com.zoolife.app.ResponseModel.ShowComment.ViewCommentsResponseModel;
import com.zoolife.app.ResponseModel.SignInResponse.SignInResponseModel;
import com.zoolife.app.ResponseModel.SignupResponseModel;
import com.zoolife.app.ResponseModel.SubCategory.SubCategoryResponseModel;
import com.zoolife.app.ResponseModel.UpdateDeviceInfo.UpdateDeviceInfoResponse;
import com.zoolife.app.ResponseModel.UpdateProfile.UpdateProfileResponseModel;
import com.zoolife.app.ResponseModel.UserPost.UserAllPostResponseModel;
import com.zoolife.app.models.notification.NotificationModel;
import com.zoolife.app.models.related_ad_home.RelatedAdHomeModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {


    @FormUrlEncoded
    @POST("/api/public/auth")
    Call<JsonObject> signIn(
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("password") String password
            );

    @FormUrlEncoded
    @POST("/api/public/auth")
    Call<JsonObject> signUp(
            @Field("pass") String pass,
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/public/auth")
    Call<OTPResponseModel> otpVerify(
            @Field("pass") String pass,
            @Field("otp") String otp,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("/api/public/auth")
    Call<JsonObject> resetPassword(
            @Field("pass") String pass,
            @Field("username") String username
    );

 @FormUrlEncoded
    @POST("/api/public/auth")
    Call<ChangePasswordResponseModel> updatePassword(
            @Field("pass") String pass,
            @Field("password") String password,
            @Field("username") String username

    );

 @FormUrlEncoded
//    @POST("/api/public/category")
    @POST("/api/category")
    Call<CategoryResponseModel> getCategory(
            @Field("pass") String pass

    );



 @FormUrlEncoded
    @POST("/api/public/home")
    Call<AllPostResponseModel> getAllPost(
            @Field("pass") String pass

    );

    @FormUrlEncoded
    @POST("/api/public/home")
    Call<AllCategoryPostResponseModel> getPostByCategory(
            @Field("pass") String pass


    );


 @FormUrlEncoded
    @POST("/api/public/home")
    Call<SearchResponseModel> getSearch(
            @Field("pass") String pass,
            @Field("search") String search

    );


 @FormUrlEncoded
    @POST("/api/public/category")
    Call<SubCategoryResponseModel> getSubCategory(
            @Field("pass") String pass,
            @Field("category_id") int category_id

    );

 @FormUrlEncoded
    @POST("/api/public/notification")
    Call<NotificationsResponseModel> getNotifications(
            @Field("pass") String pass,
            @Field("username") String username

    );


    @FormUrlEncoded
    @POST("/api/get_all_notify")
    Call<NotificationModel> getNotifications(
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST("/api/reportapi")
    Call<AddPostResponseModel> reportApi(
            @Field("pass") String pass,
            @Field("ads_id") String adsId,
            @Field("user_id") String userId,
            @Field("content") String content
    );


    @Multipart
    @POST("/api/public/item")
    Call<AddPostResponseModel> addPost(
            @Query("pass") String pass,
            @Query("username") String username,
            @Query("location") String location,
            @Query("title") String title,
            @Query("description") String description,
            @Query("category") int category,
            @Query("sub_category") int sub_category,
            @Query("showComments") int showComments,
            @Query("showPhone") int showPhone,
            @Query("showMessage") int showMessage,
            @Part MultipartBody.Part MediaFile
    );

    @POST("/api/public/item")
    Call<AddPostResponseModel> addPost1(
         @Body RequestBody file
    );


    @POST("/api/sliders")
    Call<RelatedAdHomeModel> getRelatedAdds(
         @Body RequestBody file
    );


    @Multipart
    @POST("/api/public/home")
    Call<String> uploadImage(
            @Query("pass") String pass,
            @Query("username") String username,
            @Part("images[]") String image,
            @Part MultipartBody.Part MediaFile
    );


    @FormUrlEncoded
    @POST("/api/public/item")
    Call<UserAllPostResponseModel> getAllUserPost(
            @Field("pass") String pass,
            @Field("username") String username

    );


    @FormUrlEncoded
    @POST("/api/public/user")
    Call<UpdateProfileResponseModel> updateProfile(
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("surname") String surname,
            @Field("fullname") String fullname,
            @Field("language") String language,
            @Field("bYear") String bYear,
            @Field("bMonth") String bMonth,
            @Field("bDay") String bDay,
            @Field("country") String country,
            @Field("country_id") int country_id,
            @Field("city") String city,
            @Field("city_id") int city_id

    );

    @FormUrlEncoded
    @POST("/api/public/user")
    Call<GetUserProfileResponseModel> getProfile(
            @Field("pass") String pass,
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("/api/public/home")
    Call<UpdateDeviceInfoResponse> updateDeviceInfo(
            @Field("pass") String pass,
            @Field("user_id") String userId,
            @Field("device_token") String deviceToken,
            @Field("device_type") String deviceType

    );


    @FormUrlEncoded
    @POST("/api/public/comments")
    Call<AddCommentResponseModel> addComment(
            @Field("pass") String pass,
            @Field("userId") int userId,
            @Field("itemId") int itemId,
            @Field("message") String message

    );

    @FormUrlEncoded
    @POST("/api/public/likes")
    Call<AddCommentResponseModel> updateLikeStatus(
            @Field("pass") String pass,
            @Field("userId") int userId,
            @Field("itemId") int itemId,
            @Field("status") String status

    );


   @FormUrlEncoded
    @POST("/api/public/comments")
    Call<ViewCommentsResponseModel> viewComments(
            @Field("pass") String pass,
            @Field("itemId") String itemId
    );


 @FormUrlEncoded
    @POST("/api/public/home")
    Call<GetPostResponseModel> getPost(
            @Field("pass") String pass,
            @Field("id") String itemId
    );



    @FormUrlEncoded
    @POST("/api/public/home")
    Call<GetPostResponseModel> getPostWithLogin(
            @Field("pass") String pass,
            @Field("id") String itemId,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("/api/public/home")
    Call<UserAllPostResponseModel> getAllDelivery(
            @Field("pass") String pass,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("/api/public/home")
    Call<UserAllPostResponseModel> deleteDelivery(
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("id") String id

    );

    @FormUrlEncoded
    @POST("/api/public/item")
    Call<UserAllPostResponseModel> addDelivery(
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("location") String location,
            @Field("title") String itemTitle,
            @Field("description") String description,
            @Field("category") String category,
            @Field("sub_category") String subcategory,
            @Field("showComments") String showComments,
            @Field("showPhone") String showPhone,
            @Field("showMessage") String showMessage,
            @Field("country") String country,
            @Field("city") String city

    );

    @FormUrlEncoded
    @POST("/api/articles")
    Call<AllArticlesResponseModel> getAllArticles(
            @Field("pass") String pass
    );


 @FormUrlEncoded
    @POST("/api/public/favorites")
    Call<FavResponseModel> doFavAd(
            @Field("pass") String pass,
            @Field("userId") String userId,
            @Field("itemId") String itemId,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("/api/public/favorites")
    Call<GetFavouritesResponse> getMyFav(
            @Field("pass") String pass,
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("/api/public/item")
    Call<NoDataResponseModel> deleteItem(
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("/api/public/item")
    Call<NoDataResponseModel> deleteItemImage(
            @Field("pass") String pass,
            @Field("id") String id
    );
}
