package com.wkq.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

/**
 * Sharepreference操作类
 * Code by Conan
 * data:2018-10-12
 *
 * 使用方法：
 * SharepreferenceUtil sharepreference = SharepreferenceUtil.getInstance(String filename);  //这里传入null或者""都默认文件名为：AegisFile
 * sharepreference.putData("key",Object value);
 * sharepreference.getData("key",String.class); //返回值为第二个传入参数类型
 */
public class SimpleSharedPreferencesUtil {
    private static String FILE_NAME = "StrikeFile";
    private static SharedPreferences.Editor editor = null;
    private static SharedPreferences sp = null;
    private static volatile SimpleSharedPreferencesUtil instance = null;
    private static final String TAG = SimpleSharedPreferencesUtil.class.getName();

    public SimpleSharedPreferencesUtil(Context context,String filename){
        if(filename != null && !TextUtils.isEmpty(filename)){
            FILE_NAME = filename;
        }else{
            FILE_NAME = "StrikeFile";
        }
        if(context == null){
            return;
        }
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        editor = sp.edit();
    };

    /**
     * This method is used to set the data for sharepreference
     * @param key
     * @param value
     * @return void
     */
    public <T> void putData(String key,T value){
        //判空操作
        if(TextUtils.isEmpty(key) || value == null){
            Log.d(TAG,"保存的字符串为空，请检查");
            return;
        }
        if(editor == null){
            Log.d(TAG,"editor为空，请检查初始化时context环境变量是否为空");
        }
        //写入数据
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        }
        editor.commit();
    }

    /**
     * This method is used to get the data from sharepreference
     * @param key
     * @param cls
     * @return value
     */
    public <T> T getData(String key,Class<T> cls){
        T result = null;
        //判空操作
        if(TextUtils.isEmpty(key) || cls == null){
            Log.d(TAG,"读取的key为空，请检查");
            return result;
        }
        if(sp == null){
            Log.d(TAG,"sp为空，请检查初始化时context环境变量是否为空");
        }
        //获取数据
        if (cls.equals(String.class)){
            String str = sp.getString(key,"");
            result = (T)str;
        }else if(cls.equals(Integer.class)){
            Integer num = sp.getInt(key,0);
            result = (T)num;
        }else if(cls.equals(Float.class)){
            Float flo = sp.getFloat(key,0.0f);
            result = (T)flo;
        }else if(cls.equals(Long.class)){
            Long lon = sp.getLong(key,0l);
            result = (T)lon;
        }else if(cls.equals(Boolean.class)){
            Boolean flag = sp.getBoolean(key,false);
            result = (T)flag;
        }
        return result;
    }


    /**
     * This method is used to remove the data from sharepreference
     * @param key
     * @return value
     */
    public void removeData(String key){
        if(editor == null){
            Log.d(TAG,"editor为空，请检查初始化时context环境变量是否为空");
        }
        editor.remove(key).commit();
    }


    /**
     * 增加一个写数组的功能，读功能就不写了，读出来的数组根据需求自行split
     * 根据传入的key不覆盖，一直追加在后面。用"#"分离每个字符串
     */
    public void putStringArray(String key,String value){
        //判空操作
        if(TextUtils.isEmpty(key) || value == null){
            Log.d(TAG,"保存的字符串为空，请检查");
            return;
        }
        String result = getData(key,String.class);
        //用一个StringBuffer拼接字符串，但StringBuffer不是线程安全的，需要注意
        StringBuffer buffer = new StringBuffer();
        if(!TextUtils.isEmpty(result)){
            buffer.append(result);
            buffer.append("#");
        }
        buffer.append(value);
        putData(key,buffer.toString());
        //清理
        buffer = null;
        result = null;
    }

    /**
     * 从array列表里面删除某个值
     * @param key
     * @param value
     */
    public void removeStringArray(String key,String value){
        //判空操作
        if(TextUtils.isEmpty(key) || value == null){
            Log.d(TAG,"保存的字符串为空，请检查");
            return;
        }
        String result = getData(key,String.class);
        //用一个StringBuffer拼接字符串，但StringBuffer不是线程安全的，需要注意
        StringBuffer buffer = new StringBuffer();
        if(TextUtils.isEmpty(result)){return;}
        String temp[] = result.split("#");
        for(String args:temp){
            if(!value.equals(args)){
                buffer.append(args);
            }
        }
        putData(key,buffer.toString());
    }
}
