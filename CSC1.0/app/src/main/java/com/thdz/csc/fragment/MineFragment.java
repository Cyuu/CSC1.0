package com.thdz.csc.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thdz.csc.R;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.base.BaseFragment;
import com.thdz.csc.ui.ImageActivity;
import com.thdz.csc.ui.LoginActivity;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.Finals;
import com.thdz.csc.util.SpUtil;
import com.thdz.csc.util.VUtils;

import org.json.JSONException;

import java.io.File;

import butterknife.BindView;


/**
 * 我的 -- 关于， 升级, 清理缓存，注销<br>
 * --------------<br>
 *  * 接口：<br>
 *  * 1 获取最新告警列表(?)
 */
public class MineFragment extends BaseFragment {

    // 版本号
    @BindView(R.id.mine_logo_tv)
    TextView mine_logo_tv;

    // 清除缓存
    @BindView(R.id.clear_cache_layout)
    RelativeLayout clear_cache_layout;

    /**
     * 检查更新
     */
    @BindView(R.id.check_update_layout)
    RelativeLayout check_update_layout;

    // 版本号
    @BindView(R.id.version_tv)
    TextView version_tv;

    // 用户信息
    @BindView(R.id.user_info_layout)
    LinearLayout user_info_layout;

    // 用户名
    @BindView(R.id.user_name_tv)
    TextView user_name_tv;

    //  退出登录
    @BindView(R.id.logout_btn)
    Button logout_btn;


    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup arg1, Bundle arg2) {
        return inflater.inflate(R.layout.fragment_mine, arg1, false);
    }

    @Override
    public void initView(Bundle savedInstanceState, View view, LayoutInflater inflater) {

        check_update_layout.setOnClickListener(this);
        clear_cache_layout.setOnClickListener(this);
        logout_btn.setOnClickListener(this);

        String logoStr = "v" + DataUtils.getVersion(context);
        mine_logo_tv.setText(logoStr);
        user_name_tv.setText(SpUtil.getData(context.getApplicationContext(), Finals.SP_USERNAME));

    }


    /**
     * 清除缓存
     */
    private void clearAppCache() {
        showProgressDialog("正在清理");
        // Glide框架清除内存缓存
        // Glide.get(context).clearMemory();
        toast("缓存清除完毕"); // 如果需要异步清理，需要注释本行
        hideProgressDialog();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "缓存路径cacheDir=" + Finals.imageCachePath);
//                File file = new File(Finals.imageCachePath);
//                if (file.exists()) {
//                    deleteFilesByDirectory(file);
//
//                } else {
//                    // toast("缓存目录不存在");
//                }
//
////                Glide.get(context).clearDiskCache(); // 磁盘缓存
//                // showTextFromCacheState();
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        hideProgressDialog();
//                        toast("缓存清除完毕");
//                    }
//                });
//            }
//        }).start();

    }

//    /**
//     * 判断缓存目录下，缓存是否已清空
//     */
//    private void showTextFromCacheState() {
//        try {
//            File file = new File(Finals.imageCachePath);
//            if (!file.exists()) {//已清空
//                // clear_cache_count.setText("0 M");
//            } else {
//                // if (file.isDirectory()) {
//                // String count = CacheUtils.getTotalCacheSize(context);
//                // Log.i(TAG, "缓存目录大小：" + count);
//                // clear_cache_count.setText(count);
//                // } else {
//                // file.length();
//                // }
//
//                String count = CacheUtils.getTotalCacheSize(context, file);
//                Log.i(TAG, "缓存目录大小：" + count);
//                // clear_cache_count.setText(count);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // clear_cache_count.setText("0 M");
//        }
//    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    private void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }


    /**
     * 清除缓存
     */
    private void doClearCache() {
        if (Finals.IS_TEST) {
            ((BaseActivity)context).goActivity(ImageActivity.class, null);
            return;
        }


        showSureDialog("确定要清除缓存吗?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAppCache();
            }
        });
    }

    /**
     * 检查更新
     */
    private void doCheckUpdate() {
        toast("检查更新");
    }


    // 请求 检查新版本接口
    private void Request4CheckUpdate() {
//        hideProgressDialog();
//        showSureDialog("发现新版本，要下载更新吗?", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // TODO 执行下载更新安装
//            }
//        });

    }


    /**
     * 退出登录
     */
    private void doLogout() {
        showSureDialog("确定要退出当前帐号吗?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request4Logout();

            }
        });
    }


    /**
     * 请求 退出接口，关闭推送
     */
    private void Request4Logout() {
//        showProgressDialog();
        String url = DataUtils.createReqUrl4Get(
                application.getIP(),
                application.getUid(),
                Finals.CMD_AppLogout,
                createParams());

        SpUtil.save(context, Finals.SP_AUTOLOGIN, "0");
        SpUtil.save(context, Finals.SP_PWD, "");
        SpUtil.save(context, Finals.SP_UID, "");
//        SpUtil.save(context, Finals.SP_IP, "");
//        SpUtil.save(context, Finals.SP_USERNAME, "");

        application.setIP("");
        application.setUid("");

        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
        doRequestGet(url, null);
//        doRequestGet(url, new StringCallback() {
//            @Override
//            public void onError(Call call, final Exception e, int id) {
////                hideProgressDialog();
//                e.printStackTrace();
//                Log.i(TAG, "注销失败, 请重试");
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
////                hideProgressDialog();
//                String value = DataUtils.getRespString(response);
//                Log.i(TAG, "解析出json，返回参数是：" + value);
//                String dataStr = "";
//                try {
//                    dataStr = DataUtils.getReturnData(value);
//                    if (DataUtils.isReturnOK(dataStr)) {
//                        String uid = DataUtils.getReturnUserId(dataStr);
//                        application.setUid(uid);
//                        SpUtil.save(context, Finals.SP_AUTOLOGIN, "0");
//                        SpUtil.save(context, Finals.SP_USERNAME, "");
//                        SpUtil.save(context, Finals.SP_PWD, "");
//                        SpUtil.save(context, Finals.SP_UID, "");
//
//                        Intent intent = new Intent(context, LoginActivity.class);
//                        startActivity(intent);
//                        getActivity().finish();
//
//                    } else {
//                        Log.i(TAG, DataUtils.getReturnMsg(dataStr));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    if (!TextUtils.isEmpty(dataStr)) {
//                        try {
//                            org.json.JSONObject failObj = new org.json.JSONObject(dataStr);
//                            String msgStr = failObj.getString("mMsg");
//                            Log.i(TAG, msgStr);
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                            Log.i(TAG, "注销失败, 请重试");
//                        }
//                    } else {
//                        Log.i(TAG, "注销失败, 请重试");
//                    }
//                }
//            }
//        });

    }


    private String createParams() {
        try {
            org.json.JSONObject jsonObj = new org.json.JSONObject();
            jsonObj.put("UserName", "0");
            jsonObj.put("UserPwd", "0");
            jsonObj.put("ClientId", SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID));
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {
                case R.id.clear_cache_layout:
                    doClearCache();
                    break;
                case R.id.check_update_layout:
                    doCheckUpdate();
                    break;
                case R.id.logout_btn:
                    doLogout();
                    break;
                default:
                    break;
            }
        }
    }

}
