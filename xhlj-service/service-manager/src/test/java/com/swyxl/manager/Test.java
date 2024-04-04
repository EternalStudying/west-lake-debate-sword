package com.swyxl.manager;

import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        try {
            Credential cred = new Credential("AKIDyujOsCK99CP0WvENNA9CKVVJuLackjZI", "jIhR70rqogJjEgEh1CCJUbdq7WwJX1So");
            LiveClient client = new LiveClient(cred, "");
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DescribeLiveStreamOnlineListRequest req1 = new DescribeLiveStreamOnlineListRequest();

            // 返回的resp是一个DescribeLiveStreamOnlineListResponse的实例，与请求对象对应
            DescribeLiveStreamOnlineListResponse resp1 = client.DescribeLiveStreamOnlineList(req1);
            // 输出json格式的字符串回包
            System.out.println(AbstractModel.toJsonString(resp1));

            DescribeStreamPlayInfoListRequest req2 = getDescribeStreamPlayInfoListRequest();
            // 返回的resp是一个DescribeStreamPlayInfoListResponse的实例，与请求对象对应
            DescribeStreamPlayInfoListResponse resp2 = client.DescribeStreamPlayInfoList(req2);
            // 输出json格式的字符串回包
            System.out.println(AbstractModel.toJsonString(resp2));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static DescribeStreamPlayInfoListRequest getDescribeStreamPlayInfoListRequest() {
        DescribeStreamPlayInfoListRequest req2 = new DescribeStreamPlayInfoListRequest();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -1);
        Date lastDate = calendar.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        req2.setStartTime(simpleDateFormat.format(lastDate));
        req2.setEndTime(simpleDateFormat.format(date));
        return req2;
    }
}
