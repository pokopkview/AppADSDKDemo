package ad.joyplus.com.myapplication.network;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import ad.joyplus.com.myapplication.AppUtil.AdExceptionUtil;
import ad.joyplus.com.myapplication.AppUtil.Consts;
import ad.joyplus.com.myapplication.AppUtil.glide.Glide;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.DiskCacheStrategy;
import ad.joyplus.com.myapplication.AppUtil.gson.Gson;
import ad.joyplus.com.myapplication.AppUtil.gson.reflect.TypeToken;
import ad.joyplus.com.myapplication.AppUtil.volley.DefaultRetryPolicy;
import ad.joyplus.com.myapplication.AppUtil.volley.RequestQueue;
import ad.joyplus.com.myapplication.AppUtil.volley.Response;
import ad.joyplus.com.myapplication.AppUtil.volley.VolleyError;
import ad.joyplus.com.myapplication.AppUtil.volley.toolbox.ImageRequest;
import ad.joyplus.com.myapplication.AppUtil.volley.toolbox.StringRequest;
import ad.joyplus.com.myapplication.AppUtil.volley.toolbox.Volley;
import ad.joyplus.com.myapplication.entity.AdModel;
import ad.joyplus.com.myapplication.entity.ConfigModel;
import ad.joyplus.com.myapplication.entity.EndMediaModel;
import ad.joyplus.com.myapplication.entity.ImageViewModel;
import ad.joyplus.com.myapplication.entity.OpeningAdModel;
import ad.joyplus.com.myapplication.entity.StartMediaModel;
import ad.joyplus.com.myapplication.entity.URLModel;

/**
 * 网络接口具体的实现类
 * 利用第三方Volley框架进行Http请求
 * Created by UPC on 2016/8/29.
 */
public class TrueHttpRequest implements IRequest {
    private RequestQueue mQueue;
    private Gson mGson;
    private AdExceptionUtil exceptionUtil;

    public TrueHttpRequest(Context context) {
        mQueue = Volley.newRequestQueue(context);
        mGson = new Gson();
        exceptionUtil = new AdExceptionUtil();
    }

    @Override
    public void getBaseModel(final int type, String url, final RequestIterface.IADmodel models) {
        if (type == Consts.OPEN) {
            TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
                @Override
                public void onSuccess(Object reqeust) {
                    if (!reqeust.toString().isEmpty()) {
                        String tempjson = "{ \"Code\":\"1\", \"VersonCode\":\"1.0\", \"msg\":\"ok\", \"data\":{ \"imageurl\":\"http://fwcdn.joyplus.tv/m/1920-1080V2.jpg\", \"impressionurl\":\"http://fwcdn.joyplus.tv/m/zhibodi.jpg\", \"clickurl\":\"\", \"showcount\":\"http://rapi.joyplus.tv/r.php?z=21&s=34\",\"html5_url\":\"\",\"thridreport\":[\"http://g.dtv.cn.miaozhen.com/x/k=4003363&p=2hhY3&ns=__IP__&nx=__LAB__&sn=__SN__&ni=__IESID__&m1=__ANDROIDID1__&m1a=__ANDROIDID__&m4=__AAID__&m6=__MAC1__&m6a=__MAC__&rt=2&nd=__DRA__&nt=__TIME__&rfm=__RFM__&tdt=__TDT__&tdr=__TDR__&o=\",\"http://g.dtv.cn.miaozhen.com/x/k=4003363&p=2hhY3&ns=__IP__&nx=__LAB__&sn=__SN__&ni=__IESID__&m1=__ANDROIDID1__&m1a=__ANDROIDID__&m4=__AAID__&m6=__MAC1__&m6a=__MAC__&rt=2&nd=__DRA__&nt=__TIME__&rfm=__RFM__&tdt=__TDT__&tdr=__TDR__&o=\"] } }";
                        Type type1 = new TypeToken<AdModel<OpeningAdModel>>() {
                        }.getType();
                        AdModel<OpeningAdModel> openingAdModel = mGson.fromJson(tempjson, type1);
                        models.onAdModelBack(openingAdModel);
                    } else {
                        models.onAdModelBack(null);
                    }
                }
            }, new RequestIterface.IFaileRequest() {
                @Override
                public void onFailed(Object request) {
                    Log.e("APPADSDK_HttpError", request.toString());
                }
            });
        } else if (type == Consts.STARTMEDIA) {
            TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
                @Override
                public void onSuccess(Object reqeust) {
                    if (!reqeust.toString().isEmpty()) {
                        Type type1 = new TypeToken<AdModel<StartMediaModel>>() {
                        }.getType();
                        AdModel<StartMediaModel> openingAdModel = mGson.fromJson(reqeust.toString(), type1);
                        models.onAdModelBack(openingAdModel);
                    } else {
                        models.onAdModelBack(null);
                    }
                }
            }, new RequestIterface.IFaileRequest() {
                @Override
                public void onFailed(Object request) {
                    Log.e("APPADSDK_HttpError", request.toString());
                }
            });
        } else if (type == Consts.ENDMEDIA) {
            TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
                @Override
                public void onSuccess(Object reqeust) {
                    if (!reqeust.toString().isEmpty()) {
                        Type type1 = new TypeToken<AdModel<EndMediaModel>>() {
                        }.getType();
                        AdModel<EndMediaModel> openingAdModel = mGson.fromJson(reqeust.toString(), type1);
                        models.onAdModelBack(openingAdModel);
                    } else {
                        models.onAdModelBack(null);
                    }
                }
            }, new RequestIterface.IFaileRequest() {
                @Override
                public void onFailed(Object request) {
                    Log.e("APPADSDK_HttpError", request.toString());
                }
            });
        } else if (type == Consts.VIDEOIMG) {
            TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
                @Override
                public void onSuccess(Object reqeust) {
                    if (!reqeust.toString().isEmpty()) {
                        Type type1 = new TypeToken<AdModel<ImageViewModel>>() {
                        }.getType();
                        AdModel<ImageViewModel> openingAdModel = mGson.fromJson(reqeust.toString(), type1);
                        models.onAdModelBack(openingAdModel);
                    } else {
                        models.onAdModelBack(null);
                    }
                }
            }, new RequestIterface.IFaileRequest() {
                @Override
                public void onFailed(Object request) {
                    Log.e("APPADSDK_HttpError", request.toString());
                }
            });
        } else if (type == Consts.CORNERIMG) {
            TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
                @Override
                public void onSuccess(Object reqeust) {
                    if (!reqeust.toString().isEmpty()) {
                        Type type1 = new TypeToken<AdModel<ImageViewModel>>() {
                        }.getType();
                        AdModel<ImageViewModel> openingAdModel = mGson.fromJson(reqeust.toString(), type1);
                        models.onAdModelBack(openingAdModel);
                    } else {
                        models.onAdModelBack(null);
                    }
                }
            }, new RequestIterface.IFaileRequest() {
                @Override
                public void onFailed(Object request) {
                    Log.e("APPADSDK_HttpError", request.toString());
                }
            });
        } else if (type == Consts.SIMPLEIMG) {
            TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
                @Override
                public void onSuccess(Object reqeust) {
                    if (!reqeust.toString().isEmpty()) {
                        Type type1 = new TypeToken<AdModel<ImageViewModel>>() {
                        }.getType();
                        AdModel<ImageViewModel> openingAdModel = mGson.fromJson(reqeust.toString(), type1);
                        models.onAdModelBack(openingAdModel);
                    } else {
                        models.onAdModelBack(null);
                    }
                }
            }, new RequestIterface.IFaileRequest() {
                @Override
                public void onFailed(Object request) {
                    Log.e("APPADSDK_HttpError", request.toString());
                }
            });
        }
    }

    @Override
    public void getImgFromGlide(ImageView view, String url, Object object) {
        LoadImgFromGlide(view, url, object);
    }

    @Override
    public void getGifFromGlide(ImageView view, String url, Context context) {
        Glide.with(context).load(Uri.fromFile(new File(url))).into(view);
    }

    @Override
    public void getHitmlInfo(String url, final RequestIterface.IHtmlInfoCallBack callBack) {
        TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
            @Override
            public void onSuccess(Object reqeust) {
                URLModel model = mGson.fromJson(reqeust.toString(), URLModel.class);
                callBack.OnHtmlBack(model);
            }
        }, new RequestIterface.IFaileRequest() {
            @Override
            public void onFailed(Object request) {
                Log.e("APPADSDK_HttpError", request.toString());
            }
        });
    }

    @Override
    public void getConfigInfo(String url, final RequestIterface.IModelCallBack callBack) {
        TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
            @Override
            public void onSuccess(Object reqeust) {
                AdModel<ConfigModel<Map<String, String>>> adModel = mGson.fromJson(reqeust.toString(), new TypeToken<AdModel<ConfigModel<Map<String, String>>>>() {
                }.getType());
                callBack.onConfigModelBack(adModel);
            }
        }, new RequestIterface.IFaileRequest() {
            @Override
            public void onFailed(Object request) {
                Log.e("APPADSDK_HttpError", request.toString());
            }
        });
    }

    /**
     * 获取开屏对应资源的信息
     *
     * @param url
     * @param callBack
     */
    @Override
    public void getOpeningInfo(String url, final RequestIterface.IOpeningInfoCallBack callBack) {
        TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
            @Override
            public void onSuccess(Object reqeust) {
                AdModel<OpeningAdModel> model = mGson.fromJson(reqeust.toString(), new TypeToken<AdModel<OpeningAdModel>>() {
                }.getType());
                callBack.onOpeningBack(model.data);
            }
        }, new RequestIterface.IFaileRequest() {
            @Override
            public void onFailed(Object request) {
                Log.e("APPADSDK_HttpError", request.toString());
            }
        });
    }

    /**
     * 获取配置信息的方法
     *
     * @param url
     */


    @Override
    public void DownLoadFiles(String targetFilepath, String url, RequestIterface.IDownLoadProgress progress, RequestIterface.IDownLoadSuccess success) {
        DownLodResource(targetFilepath, url, progress, success);
    }

    /**
     * @param url
     * @param callback
     */
    @Override
    public void getStartMediaInfo(String url, final RequestIterface.IStartMediaCallBack callback) {
        TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
            @Override
            public void onSuccess(Object reqeust) {
                if (!reqeust.toString().isEmpty()) {
                    AdModel<StartMediaModel> model = mGson.fromJson(reqeust.toString(), new TypeToken<AdModel<StartMediaModel>>() {
                    }.getType());
                    callback.onStartMediaBack(model);
                } else {
                    callback.onStartMediaBack(null);
                }
            }
        }, new RequestIterface.IFaileRequest() {
            @Override
            public void onFailed(Object request) {
                Log.e("APPADSDK_HttpError", request.toString());
            }
        });
    }

    /**
     * 获取后贴片的视频资源
     *
     * @param url
     * @param callBack
     */
    @Override
    public void getEndMediaInfo(String url, final RequestIterface.IEndMediaCallBack callBack) {
        TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
            @Override
            public void onSuccess(Object reqeust) {
                if (!reqeust.toString().isEmpty()) {
                    AdModel<StartMediaModel> model = mGson.fromJson(reqeust.toString(), new TypeToken<AdModel<StartMediaModel>>() {
                    }.getType());
                    callBack.onEndMediaBack(model);
                } else {
                    callBack.onEndMediaBack(null);
                }
            }
        }, new RequestIterface.IFaileRequest() {
            @Override
            public void onFailed(Object request) {
                Log.e("APPADSDK_HttpError", request.toString());
            }
        });
    }

    @Override
    public void reportInfo(String url, final RequestIterface.IReportInfoCallBack callBack) {
        TrueConfigInfomation(url, new RequestIterface.ISuccessRequest() {
            @Override
            public void onSuccess(Object reqeust) {
                callBack.onReportBack(true);
            }
        }, new RequestIterface.IFaileRequest() {
            @Override
            public void onFailed(Object request) {
                callBack.onReportBack(false);
            }
        });
    }

    @Override
    public void getImageBitMap(String url, RequestIterface.IImageBitMap callBack) {
        ThregetBitMap(url, callBack);
    }

    private void ThregetBitMap(String url, final RequestIterface.IImageBitMap callBack) {
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                callBack.onImageBitMap(response);
            }
        }, 1920, 1080, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //交给exceptionUtil来处理
                exceptionUtil.checkErrorFactory(error);
            }
        });
        mQueue.add(request);
    }


    private void TrueConfigInfomation(String url, final RequestIterface.ISuccessRequest successRequest, final RequestIterface.IFaileRequest faileRequest) {
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                successRequest.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exceptionUtil.checkErrorFactory(error);
                faileRequest.onFailed(error);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }


    private void DownLodResource(final String targetfilepath, final String sourceurl, final RequestIterface.IDownLoadProgress pro, final RequestIterface.IDownLoadSuccess success) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                try {
                    File file = new File(targetfilepath);
                    if (file.exists()) {
                        //对已存在的本地文件做处理
                    }
                    URL urls = new URL(sourceurl);
                    URLConnection urlConnection = urls.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                    httpURLConnection.connect();
                    int contentLength = httpURLConnection.getContentLength();
                    RandomAccessFile accessFile = new RandomAccessFile(targetfilepath, "rwd");
                    byte[] bytes = new byte[1024 * 50];
                    int length = -1;
                    long downsize = 0;
                    InputStream is = httpURLConnection.getInputStream();
                    while ((length = is.read(bytes)) != -1) {
                        accessFile.write(bytes, 0, length);
                        downsize += length;
                        pro.onDownLoadProgress((int) downsize);
                        if (contentLength == downsize) {
                            success.onDownLoadSuccess("success");
                            return "success";
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void LoadImgFromGlide(ImageView imageView, String url, Object context) {
        if (context instanceof Context) {
            Glide.with((Context) context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else if (context instanceof Activity) {
            Glide.with((Activity) context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else if (context instanceof Fragment) {
            Glide.with((Fragment) context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else if (context instanceof android.support.v4.app.Fragment) {
            Glide.with((android.support.v4.app.Fragment) context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else if (context instanceof FragmentActivity) {
            Glide.with((FragmentActivity) context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }
    }
}
