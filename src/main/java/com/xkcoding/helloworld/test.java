package com.xkcoding.helloworld;

import cn.com.jit.ida.util.pki.encoders.Base64;
import cn.com.jit.new_vstk.Bean.SignResult;
import cn.com.jit.new_vstk.Bean.VerifyResult;
import cn.com.jit.new_vstk.SignClient;
import cn.com.jit.new_vstk.exception.NewCSSException;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.boen.miniostarter.service.FileStorageService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: wangbowen
 * @date: 2023-11-27 10:31
 * @Description:
 */
@Service
public class test {

  @Autowired
  private FileStorageService fileStorageService;

  @Autowired
  private Environment environment;

  /**
   * 对byte[]类型的原文做验签操作
   * @param signData 签名结果
   * @param plain 签名原文
   * @return 对象SignResult
   * @throws NewCSSException
   */
  @PostMapping("virifyForText")
  public VerifyResult virifyForText(byte[] signData,byte[] plain) throws NewCSSException {
    //如果找不到类路径下的cssconfig.properties文件，请写绝对路径
    SignClient client = new SignClient("cssconfig.properties");
    VerifyResult verify = client.verify(signData, plain);
    return verify;
  }

  /**
   * 对文件类型的原文做验签操作
   * @param signData 签名结果
   * @param plainPath 签名原文路径
   * @return 对象SignResult
   * @throws NewCSSException
   * 对象SignResult
   * 			boolean getSuccess():调用是否成功
   * 			long getErrorCode():	错误码。0代表成功，其他为失败
   * 			String getErrorMsg():	错误描述。
   * 			String getVersion():版本号
   * 			String getSn():证书序列号
   * 			String getSubjectdn():证书主题
   * 			String getIssure():证书颁发者主题
   * 			String getNotBefore():证书有效开始时间
   * 			String getNotAfter():证书有效结束时间
   * 			String getDsCert():base64后的证书实体
   */
  public VerifyResult virifyForData(byte[] signData, String plainPath) throws NewCSSException {
    //如果找不到类路径下的cssconfig.properties文件，请写绝对路径
    SignClient client = new SignClient("cssconfig.properties");
    VerifyResult verify = client.verify(signData, plainPath);
    return verify;
  }



  public static void main(String[] args) throws NewCSSException {
    String strData_D = "待签名的原文";
    SignResult result = null;
    String certId = "rsa";//证书标识
    try {
      //如果找不到类路径下的cssconfig.properties文件，请写绝对路径
      SignClient client = new SignClient("cssconfig.properties");

      result = client.sign(certId, strData_D.getBytes());
      if (result.isSuccess()) { // 签名成功
        System.out.println("****签名成功****");
        byte[] signData = result.getSignData();//获取未做base64的签名结果

        String base64Str = new String(Base64.encode(signData));
        System.out.println("签名结果为：" + base64Str);
      }else{
        System.out.println("****签名失败****");
        System.out.println("错误号为：" + result.getErrorCode());
        System.out.println("错误描述为: " + result.getErrorMsg());
      }
    } catch (NewCSSException e) {
      System.out.println("****签名失败****");
      System.out.println("错误号为：" + e.getCode());
      System.out.println("错误描述为: " + e.getDescription());
    }




    ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    TransmittableThreadLocal<String> USER_CONTEXT = new TransmittableThreadLocal<>();
    USER_CONTEXT.set("wang");
    new Thread(()->{
      System.out.println(USER_CONTEXT.get());
    }).start();

  }
}
