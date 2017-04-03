package com.alt.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.alt.R;

import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import static com.alt.utils.Constants.uri;


/**
 * Created by student on 11/06/16.
 */
public class ApplicationUtils {
    Activity context;


    public ApplicationUtils(Activity context) {
        this.context = context;
    }

    public Uri saveImageOnSdCard(byte[] imageBytes) {
        File parentFolder = new File(Constants.APPLICATION_PATH);
        if (!parentFolder.exists()) {
            parentFolder.mkdirs();
        }
        File destination = new File(Constants.APPLICATION_PATH, System.currentTimeMillis() + ".png");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(imageBytes);
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imageUri = Uri.fromFile(destination);
        return imageUri;
    }

    private static void createDirectory(String applicationPath) {
        if (!new File(Constants.APPLICATION_PATH).exists()) {
            //create directory if not exist's
            File dir = new File(Constants.APPLICATION_PATH);
            dir.mkdir();
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        int width = 300; // - Dimension in pixels
        int height = 300;  // - Dimension in pixels
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    public static Uri onCaptureImageResult(Intent data) {
        if (data != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //assert thumbnail != null;
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 80, bytes);

            InputStream in = new ByteArrayInputStream(bytes.toByteArray());
            ContentBody foto = new InputStreamBody(in, "image/jpeg", "filename");

            //CREATE FOLDER IF NOT EXIST'S
            createDirectory(Constants.APPLICATION_PATH);
            File destination = new File(Constants.APPLICATION_PATH, System.currentTimeMillis() + ".jpeg");
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri imageUri = Uri.fromFile(destination);
            return imageUri;
        }
        return null;
    }


    public static Uri saveVideoThumbnail(Bitmap thumbnailImage) {
        if (thumbnailImage != null) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //assert thumbnail != null;
            thumbnailImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            //CREATE FOLDER IF NOT EXIST'S
            createDirectory(Constants.APPLICATION_PATH);
            File destination = new File(Constants.APPLICATION_PATH, System.currentTimeMillis() + ".png");
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri imageUri = Uri.fromFile(destination);
            return imageUri;
        }
        return null;
    }

    //capture image from gallery
    /*
    just pass onActivityResult's intent object to this method.
    * */
    public Uri onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());

//                profileImageUri
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Uri imageUri = getImageUri(context, bm);
        Utils.d("debug", "PROFILE IMAGE URI 12:" + getImageUri(context, bm));
        return imageUri;
    }

    //to ge uri
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        String path = "";
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Intent getTwitterIntent(Context context, String shareText) {
        Intent shareIntent;

        if (isPackageExisted(context, "com.twitter")) {
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setClassName("com.twitter.android",
                    "com.twitter.android.PostActivity");
            shareIntent.setType("text/*");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
            return shareIntent;
        } else {
            String tweetUrl = "https://twitter.com/intent/tweet?text=" + shareText;
            Uri uri = Uri.parse(tweetUrl);
            shareIntent = new Intent(Intent.ACTION_VIEW, uri);
            return shareIntent;
        }
    }

    public boolean isPackageExisted(Context context, String targetPackage) {
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }

    private void launchGallery() {
        try {
            Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            context.startActivityForResult(intentGallery, Constants.REQUEST_MADE_FOR_GALLERY);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*    private void lanchCamera() {
        try {
            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "CRASH_" + System.currentTimeMillis());
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            System.out.println("APPLICATION PATH IN LAUNCH CAMERA :" + Constants.APPLICATION_PATH);
            context.uri = objActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, CreateShoutActivity.mCapturedImageURI);
            objActivity.startActivityForResult(intentCamera, Constants.REQUEST_MADE_FOR_CAMERA);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*
        * Gets the file path of the given Uri.
        */
    @SuppressLint("NewApi")
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{split[1]};
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static void openGallery(int REQUEST_CODE, Activity context) {
        try {
            Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            context.startActivityForResult(intentGallery, REQUEST_CODE);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchCamera(int REQUEST_CODE, Activity context) {
        try {
            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, context.getResources().getString(R.string.app_name) + "_IMG_" + System.currentTimeMillis());
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            context.startActivityForResult(intentCamera, REQUEST_CODE);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * OPENS NEW CHOOSER TO OPEN FILE INTO PERTICULAR VIEWER
     **/
    public static void openDocument(Context context, String strFilePath, String strFileType) {
        try {
            File file = new File(strFilePath);
            Intent intent = new Intent(Intent.ACTION_VIEW);
           /* if (strFileType.equals("pdf")) {
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            } else if (strFileType.equals("doc") || strFileType.equals("docx")) {
                MimeTypeMap myMime = MimeTypeMap.getSingleton();
                String mimeType = myMime.getMimeTypeFromExtension(strFilePath);
                intent.setDataAndType(Uri.fromFile(file), mimeType);
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/*");
            }*/
            intent.setDataAndType(Uri.fromFile(file), "application/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            activityNotFoundException.printStackTrace();
            throw activityNotFoundException;
        } catch (Exception otherException) {
            otherException.printStackTrace();
            throw otherException;
        }
    }

    /**
     * Starts the intent to get the document.
     */
    public static void launchDocumentPickerInActivity(Activity context, int REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        context.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts the intent to get the document.
     */
    public static void launchDocumentPickerInFragment(Fragment fragment, int REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }
}
