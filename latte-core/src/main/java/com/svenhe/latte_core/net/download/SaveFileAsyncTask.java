package com.svenhe.latte_core.net.download;

import android.os.AsyncTask;

import com.svenhe.latte_core.net.callback.IRequest;
import com.svenhe.latte_core.net.callback.ISuccess;
import com.svenhe.latte_core.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.net.download
 * @创建者: svenhe
 * @创建时间: 2017/11/4 17:01
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/4 17:01 $
 * @更新描述: TODO
 */
public class SaveFileAsyncTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileAsyncTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }


    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
//            autoInstallApk(file);

    }


//        private void autoInstallApk(File file) {
//            if (FileUtil.getExtension(file.getPath()).equals("apk")) {
//                final Intent install = new Intent();
//                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                install.setAction(Intent.ACTION_VIEW);
//                install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//                Latte.getApplicationContext().startActivity(install);
//            }
//        }

}
