package ad.joyplus.com.myapplication.entity;

/**
 * 返回信息的数据模型
 * Created by UPC on 2016/8/31.
 */
public class AdModel<T>{

    /**
     * 版本号
     */
    public String VersonCode;

    /**
     * 返回码（1为正确返回或0错误返回）
     */

    public String Code;

    /**
     * 返回为0时的错误信息
     * 错误信息
     */

    public String msg;

    /**
     * 返回正确时，返回的data数据
     */
    public T data;

}
