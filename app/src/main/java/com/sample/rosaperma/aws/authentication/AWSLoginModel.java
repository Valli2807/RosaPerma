package com.sample.rosaperma.aws.authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ForgotPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler;
import com.amazonaws.regions.Regions;
import com.sample.rosaperma.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import static com.sample.rosaperma.Constants.SHARED_PREFERENCE;

/**
 * This represents a model for login operations on AWS Mobile Hub. It manages login operations
 * such as:
 * - Sign In
 * - Sign Up
 * - Confirm Sign Up
 * - Get User Name (current signed in)
 * - Get User E-mail (current signed in)
 *
 */
@SuppressWarnings("unused")
public class AWSLoginModel {
    // constants
    private final String ATTR_EMAIL = "email";
    private final String ATTR_MOB_NO = "phone_number";
    private final String ATTR_NAME = "name";

    public static final int PROCESS_SIGN_IN = 1;
    public static final int PROCESS_REGISTER = 2;
    public static final int PROCESS_CONFIRM_REGISTRATION = 3;
    public static final int CHANGE_PASSWORD = 4;
    public static final int FORGOT_PASSWORD = 5;
    // interface handler
    private AWSLoginHandler mCallback;

    // control variables
    private String mobileNumber, userPassword;
    private Context mContext;
    private CognitoUserPool mCognitoUserPool;
    private CognitoUser mCognitoUser;

    public AWSLoginModel ()
    {}
    private final AuthenticationHandler authenticationHandler = new AuthenticationHandler() {
        @Override
        public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
            // Get details of the logged user (in this case, only the e-mail)
            mCognitoUser.getDetailsInBackground(new GetDetailsHandler() {
                @Override
                public void onSuccess(CognitoUserDetails cognitoUserDetails) {
                    // Save in SharedPreferences
                    SharedPreferences.Editor editor = mContext.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).edit();

                    SharedPreferences sp = mContext.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);


                    String email = cognitoUserDetails.getAttributes().getAttributes().get(ATTR_EMAIL);
                    editor.putString(Constants.PREFERENCE_USER_EMAIL, email);

                    String name = cognitoUserDetails.getAttributes().getAttributes().get(ATTR_NAME);
                    editor.putString(Constants.PREFERENCE_USER_NAME, name);

                    editor.putBoolean(Constants.IS_USER_LOGGED_IN, true);
                    editor.apply();

                 //   NetworkInterceptor.getUserDetails(mContext, mobileNumber,null);

                    mCallback.onSignInSuccess();

                }

                @Override
                public void onFailure(Exception exception) {
                    exception.printStackTrace();
                }
            });
            // Save in SharedPreferences
            SharedPreferences.Editor editor = mContext.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).edit();
            editor.putString(Constants.PREFERENCE_MOBILE_NO, mobileNumber).toString();
            editor.apply();;

        }

        @Override
        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
            final AuthenticationDetails authenticationDetails = new AuthenticationDetails(mobileNumber, userPassword, null);
            authenticationContinuation.setAuthenticationDetails(authenticationDetails);
            authenticationContinuation.continueTask();
            userPassword = "";
        }

        @Override
        public void getMFACode(MultiFactorAuthenticationContinuation continuation) {
            // Not implemented for this Model
        }

        @Override
        public void authenticationChallenge(ChallengeContinuation continuation) {
            // Not implemented for this Model
        }

        @Override
        public void onFailure(Exception exception) {
            mCallback.onFailure(PROCESS_SIGN_IN, exception);
        }
    };


    /**
     * Constructs the model for login functions in AWS Mobile Hub.
     *
     * @param context         REQUIRED: Android application context.
     * @param callback        REQUIRED: Callback handler for login operations.
     *
     */
    public AWSLoginModel(Context context, AWSLoginHandler callback) {
        mContext = context;
        IdentityManager idm = new IdentityManager(mContext,
                new AWSConfiguration(mContext));
        IdentityManager.setDefaultIdentityManager(idm);

        IdentityManager identityManager = IdentityManager.getDefaultIdentityManager();
        try{
            JSONObject myJSON = identityManager.getConfiguration().optJsonObject("CognitoUserPool");
            final String COGNITO_POOL_ID = myJSON.getString("PoolId");
            final String COGNITO_CLIENT_ID = myJSON.getString("AppClientId");
            final String COGNITO_CLIENT_SECRET = myJSON.getString("AppClientSecret");
            final String REGION = myJSON.getString("Region");
            mCognitoUserPool = new CognitoUserPool(context, COGNITO_POOL_ID, COGNITO_CLIENT_ID, COGNITO_CLIENT_SECRET, Regions.fromName(REGION));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mCallback = callback;
    }

    /**
     * Registers new user to the AWS Cognito User Pool.
     `                                                                                                        *
     * This will trigger {@link AWSLoginHandler} interface defined when the constructor was called.
     *
     * @param mobileNo         REQUIRED: Username to be registered. Must be unique in the User Pool.
     * @param userEmail        REQUIRED: E-mail to be registered. Must be unique in the User Pool.
     * @param userPassword     REQUIRED: Password of this new account.
     * @param name
     */
    public void registerUser(final String mobileNo, final String userEmail, String userPassword, final String name) {
        final CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute(ATTR_EMAIL, userEmail);
        userAttributes.addAttribute(ATTR_NAME, name);
        //userAttributes.addAttribute(ATTR_MOB_NO, "+91"+mobileNo);
        userAttributes.addAttribute(ATTR_MOB_NO, "+971"+mobileNo);
        this.mobileNumber=mobileNo;
        SharedPreferences.Editor editor = mContext.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.PREFERENCE_USER_EMAIL, userEmail);
        editor.putString(Constants.PREFERENCE_MOBILE_NO, mobileNo);
        editor.putString(Constants.PREFERENCE_USER_NAME, name);
        editor.apply();
        final SignUpHandler signUpHandler = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                mCognitoUser = user;
                // Save in SharedPreferences
                SharedPreferences.Editor editor = mContext.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).edit();

                editor.putBoolean(Constants.IS_USER_LOGGED_IN, true);

                editor.apply();
                mCallback.onRegisterSuccess(false);
            }

            @Override
            public void onFailure(Exception exception) {
                mCallback.onFailure(PROCESS_REGISTER, exception);
            }
        };

        mCognitoUserPool.signUpInBackground(mobileNo, userPassword, userAttributes, null, signUpHandler);
    }

    /**
     * Sign in process. If succeeded, this will save the user name and e-mail in SharedPreference of
     * this context.
     *
     * This will trigger {@link AWSLoginHandler} interface defined when the constructor was called.
     *
     * @param mobileNumber        REQUIRED: Username or e-mail.
     * @param userPassword           REQUIRED: Password.
     */
    public void signInUser(String mobileNumber, String userPassword) {
        this.mobileNumber = mobileNumber;
        this.userPassword = userPassword;

        SharedPreferences.Editor editor = mContext.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.PREFERENCE_MOBILE_NO, mobileNumber);

        editor.putBoolean(Constants.IS_USER_LOGGED_IN, true);

        editor.apply();
        mCognitoUser = mCognitoUserPool.getUser(this.mobileNumber);
        mCognitoUser.getSessionInBackground(authenticationHandler);

    }
}