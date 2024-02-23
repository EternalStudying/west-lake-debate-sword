package com.swyxl.active.config;

import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class QRConfig {

    @Bean
    public QrConfig qrConfig(){
        //初始宽度和高度
        QrConfig qrConfig=new QrConfig(300,300);
        //设置边距，即二维码和边框的距离
        qrConfig.setMargin(3);
        //设置前景色
        qrConfig.setForeColor(Color.BLACK.getRGB());
        //设置背景色
        qrConfig.setBackColor(Color.WHITE.getRGB());

        return qrConfig;
    }
}
