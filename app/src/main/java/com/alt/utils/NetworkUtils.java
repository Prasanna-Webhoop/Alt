package com.alt.utils;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Capternal on 03/02/16.
 */
public class NetworkUtils {


    public static String postData(String strURL, String strJsonObject) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        String strResultFromJson = "";
        try {
            int TIMEOUT_MILLISEC = 60000; // = 10 seconds
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
//            HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

            HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
            HttpClient httpclient = new DefaultHttpClient(httpParams);
            HttpPost httppost = new HttpPost(strURL);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");

            // CLEAR LOG PROGRAMATICALLY
            Process process = new ProcessBuilder().command("logcat", "-c").redirectErrorStream(true).start();
            Utils.d("API INPUT : CALLED URL : ", strURL);
            Utils.d("API INPUT : JSON OBJECT : ", strJsonObject);

            httppost.setEntity(new ByteArrayEntity(strJsonObject.getBytes("UTF8")));
            HttpResponse objHttpResponse = httpclient.execute(httppost);
            System.out.println("FINAL HTTP STATUS : " + objHttpResponse.getStatusLine());
            InputStream objInputStream = objHttpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(objInputStream, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            objInputStream.close();
            strResultFromJson = sb.toString();
            Utils.d("API OUTPUT : JSON OBJECT : ", strResultFromJson);
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResultFromJson;
    }

    public static String getData(String strURL) {
        System.out.println("GET URL : "+strURL);
        String strResultFromJson = "";
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            int TIMEOUT_MILLISEC = 60000; // = 10 seconds
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams,
                    TIMEOUT_MILLISEC);
            HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
            HttpClient objHttpClient = new DefaultHttpClient(httpParams);
            HttpGet objHttpGet = new HttpGet(strURL
            );
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpGet);

            System.out.println("Status Code " + objHttpResponse.getStatusLine());

            InputStream objInputStream = objHttpResponse.getEntity().getContent();


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    objInputStream, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            objInputStream.close();

            strResultFromJson = sb.toString();
            System.out.println("Final Error Response Body Non==>" + strResultFromJson);
            System.out.println("Final Response From Server = "
                    + strResultFromJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResultFromJson;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String uploadResources(Context context, String strURL, String strShoutId, ArrayList<String> arrResourceType, ArrayList<Uri> arrResourcePath) {

        String strResult = "";

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(strURL);
            HttpResponse response = null;
            MultipartEntity entityBuilder = new MultipartEntity();

            entityBuilder.addPart("shout_id", new StringBody(strShoutId));

            for (int index = 0; index < arrResourcePath.size(); index++) {

                File file = new File(arrResourcePath.get(index).getPath());
                FileBody objFile = new FileBody(file);
                if (arrResourceType.get(index).equals("C")) {
                    entityBuilder.addPart("Image-" + index, objFile);
                } else if (arrResourceType.get(index).equals("V")) {
                    entityBuilder.addPart("Video-" + index, objFile);
                } else if (arrResourceType.get(index).equals("D")) {
                    entityBuilder.addPart("File-" + index, objFile);
                }
            }
            post.setEntity(entityBuilder);
            response = client.execute(post);
            HttpEntity httpEntity = response.getEntity();
            strResult = EntityUtils.toString(httpEntity);
            Utils.d("MULTIPART RESPONSE", strResult);
                    /*JSONObject objJsonObject = new JSONObject(result);
                    if (objJsonObject.has("result") && objJsonObject.getBoolean("result")) {
                        objDatabaseHelper.updateDOImageUploadStatus(arrobjDoImageModels.get(index).getIntId(), "Y");
                    }*/
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return strResult;
    }

    private static String getRealPathFromURI(Uri contentUri, Context context) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        System.out.println("REAL PATH : " + result);
        return result;
    }
}
