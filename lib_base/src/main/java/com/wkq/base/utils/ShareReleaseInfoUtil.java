package com.wkq.base.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import java.io.Serializable;

public class ShareReleaseInfoUtil {

    public static final String SHARE_TYPE = "strike.intent.extra.TYPE";
    public static final String SHARE_IMAGE = "strike.intent.extra.IMAGE";

    public static final String ACTION_SHARE_SEND = "netpp.intent.action.SHARE_SEND";
    public static final String ACTION_SHARE_RELEASE = "netpp.intent.action.SHARE_RELEASE";

    //分享类型
    public static final String TYPE_HTML = "strike.intent.type.HTML";

    private ShareConfig config;

    public static final class ShareConfig implements Serializable {

        public static ShareConfig newInstance() {
            return new ShareConfig();
        }

        public static ShareConfig parse(Intent intent) {
            if (intent == null) return new ShareConfig();

            String title = intent.getStringExtra(Intent.EXTRA_TITLE);
            String subTitle = intent.getStringExtra(Intent.EXTRA_TEXT);
            String url = intent.getStringExtra(Intent.EXTRA_SUBJECT);
            String type = intent.getStringExtra(ShareReleaseInfoUtil.SHARE_TYPE);
            String image = intent.getStringExtra(ShareReleaseInfoUtil.SHARE_IMAGE);

            return new ShareConfig(title, subTitle, image, url, type);
        }

        private ShareConfig() {

        }

        private ShareConfig(String title, String subTitle, String image, String url, String type) {
            this.title = title;
            this.subTitle = subTitle;
            this.image = image;
            this.url = url;
            this.type = type;
        }

        //分享标题
        private String title = "";
        //分享副标题
        private String subTitle = "";
        //分享图标
        private String image = "";
        //分享URL地址
        private String url = "";
        //分享类型
        private String type = "";
        //分享文件地址(通过输入流读取)
        private Uri uri;

        public ShareReleaseInfoUtil build() {
            return new ShareReleaseInfoUtil(this);
        }

        public void shareSend(Context context) {
            new ShareReleaseInfoUtil(this).share(context, ACTION_SHARE_SEND);
        }

        public void shareRelease(Context context) {
            new ShareReleaseInfoUtil(this).share(context, ACTION_SHARE_RELEASE);
        }

        public String getTitle() {
            return title;
        }

        public ShareConfig setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public ShareConfig setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public String getImage() {
            return image;
        }

        public ShareConfig setImage(String image) {
            this.image = image;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public ShareConfig setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getType() {
            return type;
        }

        public ShareConfig setType(String type) {
            this.type = type;
            return this;
        }

        public Uri getUri() {
            return uri;
        }

        public void setUri(Uri uri) {
            this.uri = uri;
        }
    }

    private ShareReleaseInfoUtil(ShareConfig config) {
        this.config = config;
    }

    public void shareSend(Context context) {
        share(context, ACTION_SHARE_SEND);
    }

    public void shareRelease(Context context) {
        share(context, ACTION_SHARE_RELEASE);
    }

    public void share(Context context, String action) {
//        Intent intent = new Intent(action);
//        intent.setPackage(AppConfig.getAppPackeg());
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TITLE, config.title);
//        intent.putExtra(Intent.EXTRA_TEXT, config.subTitle);
//        intent.putExtra(Intent.EXTRA_SUBJECT, config.url);
//        intent.putExtra(ShareReleaseInfoUtil.SHARE_TYPE, config.type);
//        intent.putExtra(ShareReleaseInfoUtil.SHARE_IMAGE, config.image);
//        context.startActivity(intent);
    }
}
