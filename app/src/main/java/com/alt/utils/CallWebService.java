package com.alt.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;

import com.alt.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * Created by CapternalSystems on 8/30/2016.
 */
public class CallWebService extends AsyncTask<String, Void, String> {

    private OnGetUrlResponse onGetUrlResponse;
    private JSONArray objImages = null;
    private JSONArray objFiles = null;
    String URL = "";
    JSONObject objJsonObject = null;
    Activity objActivity;
    WebserviceResponse objWebserviceResponse;
    public boolean showLoader = false;
    public static ProgressDialog progressDialog;
    boolean isMultiPart = false;

    //    Variable to be used in GET METHOD response.
    boolean isGet = false;
    private String urlId = "";


    public CallWebService(String URL, JSONObject objJsonObject, Activity objActivity, WebserviceResponse objWebserviceResponse, boolean showLoader) {
        this.URL = URL;
        this.objJsonObject = objJsonObject;
        this.objActivity = objActivity;
        this.objWebserviceResponse = objWebserviceResponse;
        this.showLoader = showLoader;
    }

    public CallWebService(String URL, JSONObject objJsonObject, Activity objActivity, WebserviceResponse objWebserviceResponse) {
        this.URL = URL;
        this.objJsonObject = objJsonObject;
        this.objActivity = objActivity;
        this.objWebserviceResponse = objWebserviceResponse;
        this.showLoader = false;
    }

    public CallWebService(String URL, JSONObject objJsonObject, WebserviceResponse objWebserviceResponse) {
        this.URL = URL;
        this.objJsonObject = objJsonObject;
        this.objActivity = objActivity;
        this.objWebserviceResponse = objWebserviceResponse;
        this.showLoader = false;
    }

    public CallWebService(String URL, JSONArray objImages, JSONArray objFiles, JSONObject objJsonObject, Activity objActivity, WebserviceResponse objWebserviceResponse, boolean showLoader, boolean isMultiPart) {
        this.URL = URL;
        this.objImages = objImages;
        this.objFiles = objFiles;
        this.objJsonObject = objJsonObject;
        this.objActivity = objActivity;
        this.objWebserviceResponse = objWebserviceResponse;
        this.showLoader = showLoader;
        this.isMultiPart = isMultiPart;
    }

   /* public CallWebService(String URL, Activity objActivity, WebserviceResponse objWebserviceResponse, boolean showLoader, boolean isGet) {
        this.URL = URL;
        this.objActivity = objActivity;
        this.objWebserviceResponse = objWebserviceResponse;
        this.showLoader = showLoader;
        this.isGet = isGet;
    }*/

    public CallWebService(String URL, Activity objActivity, OnGetUrlResponse onGetUrlResponse, boolean showLoader, boolean isGet, String urlId) {
        this.URL = URL;
        this.objActivity = objActivity;
        this.onGetUrlResponse = onGetUrlResponse;
        this.showLoader = showLoader;
        this.isGet = isGet;
        this.urlId = urlId;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (showLoader) {
            progressDialog = new ProgressDialog(objActivity, R.style.custom_progress_dialog_style);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            progressDialog.show();
            progressDialog.setContentView(R.layout.custom_progress_layout);

        }
    }

    public static void dismissDialog() {
        try {
            if (progressDialog != null) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String strResult = "";
        try {
            if (isMultiPart) {
                Utils.d("MULTIPART_API INPUT : IMAGES : ", " " + objImages);
                Utils.d("MULTIPART_API INPUT : FILES : ", " " + objFiles);
                Utils.d("MULTIPART_API INPUT : DATA : ", " " + objJsonObject);
                Utils.d("MULTIPART_API INPUT : URL : ", " " + URL);

                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(URL);
                    HttpResponse response = null;
                    MultipartEntity entityBuilder = new MultipartEntity();

                    Iterator<String> iter = objJsonObject.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            Object value = objJsonObject.get(key);
                            Utils.out("DATA : KEY " + key + " VALUE : " + value);
                            entityBuilder.addPart(key, new StringBody(String.valueOf(value)));
                        } catch (JSONException e) {
                            // Something went wrong!
                        }
                    }
                    if (objFiles != null) {
                        for (int fileIndex = 0; fileIndex < objFiles.length(); fileIndex++) {
                            File file = new File((objFiles.getJSONObject(fileIndex).getString("Files")));
                            FileBody objFile = new FileBody(file);
                            entityBuilder.addPart("File" + fileIndex, objFile);
                        }
                    }
                    if (objImages != null) {
                        for (int imageIndex = 0; imageIndex < objImages.length(); imageIndex++) {
                            File file = new File((objImages.getJSONObject(imageIndex).getString("Images")));
                            FileBody objFile = new FileBody(file);
                            entityBuilder.addPart("Image" + imageIndex, objFile);
                        }
                    }
                    post.setEntity(entityBuilder);
                    response = client.execute(post);
                    HttpEntity httpEntity = response.getEntity();
                    strResult = EntityUtils.toString(httpEntity);
                    Utils.d("UPLOAD PROFILE IMAGE MULTIPART RESPONSE", strResult);

                    return strResult;
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (ClientProtocolException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return null;
            } else if (isGet) {
                strResult = NetworkUtils.getData(this.URL);
            } else {
                strResult = NetworkUtils.postData(this.URL, this.objJsonObject.toString());
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();

            if (progressDialog != null)
                progressDialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
            if (progressDialog != null)
                progressDialog.dismiss();

        }
        return strResult;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog != null)
            progressDialog.dismiss();
        if (isGet) {
            onGetUrlResponse.onGetUrlResponse(this.urlId, this.URL, s);
        } else {
            objWebserviceResponse.onWebserviceResponse(this.URL, s);
        }

    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        if (isGet) {
            onGetUrlResponse.onGetUrlCancelled(this.urlId, this.URL, s);

        } else {
            objWebserviceResponse.onWebserviceResponse(this.URL, s);

        }
    }

    public interface WebserviceResponse {
        public void onWebserviceResponse(String strUrl, String strResult);
    }

    public interface OnGetUrlResponse {
        public void onGetUrlResponse(String urlId, String strUrl, String strResult);

        public void onGetUrlCancelled(String urlId, String strUrl, String cancelledResult);
    }
}
