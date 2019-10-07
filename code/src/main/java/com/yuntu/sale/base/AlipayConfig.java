package com.yuntu.sale.base;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102602341655";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCnsZl+eTNf84eE/pk3SRkuXL2puzttWHT5UpxrU/iAAx0p7+U4l/0pqpcONyDQcb99BVYRtGdA6YcybHFSSJVJQCyLb6kUatGLt6pfMGyCpCjOzqaFWNj+FE8m2C28taEE2Kjr5L+HuidCBQw8BX74sq2SeYgDvtLlUKS4c1foCpPtTgXBkLLpfkFFwDks/xMo+/5QA3FS6w7zn2CJWKtkp3vKzIs+YJ2C+8/TV+K+IYg2IC5jaJSH0KWaoUneabxBcKdmR9POm9RP1bn42oxGjHIrutbbXLSxLEmE0bTj6notT6kO4noW1F7ndYlK8iBnIJo+1Coq/hiRr5OkFE2TAgMBAAECggEAea9CNqg0Q3gv6q3k+F0uzJm3dybKJLp/hzwPDULNm/amfvv5VWvU5RtIqaQPR7KCxXHN1uMV2eCbnldak97flVvf9v57rrkkfUI1ewVkeBgvFIKYC0NBkn2iQgm0hq81vJKLYUGISjEms51+Fz/VtxnRIezWagFaf9RY8w+fxlxUTWSGnZRo4rdA6toJAF7AdtyMEh7V1eh9caSj0wVvarncJJVEgQlEJRkDxhyMqrfLORR4OVtcSp7fzlUXSKLWKi/K6WzJfnWMRMInZXyopZ8o+H2ZueCQ0whzpx7ZV7kKrI1jCUFWheviUN0kwqpVKDaZbc1pqMCOdwC5yByzQQKBgQD7YUvyvXwDFxmKyzqt+tBWod0GIVOJtYiOXFpTKFWACtVhQs/LUFXhf4IrOzIKIgAZ/rsdVT//HOwhLQpNRsBotCIgkD+l9xCsLfPgSsXOfof2VeGAT+0ynDW92Fpo4Qsez/MQ91cu0i2lRFzSuxzQ5amxMFLQ2ymK/eVf2QPCCQKBgQCqxpJzIC+cVvJgjXRRimGMdWTvMF9flwUpkQkekFABeCGxohlAvxdup/SszYv1uczGmkYTBsZt+xmwhSm+WquB8vOAvF3n4wwOpaBphOOXuJntSsG0eOfArdZGg3nmXSrANuH9fvDzP0xaF+RLVLs8U/04qQHbl+jBNvIvGDlJuwKBgHxViHocjJz+rOyATprE5o0i7C5fB3Gib1NyjF57Iab+NMIxiJOYPZIWPizhz4NBg+lXm/cLNmcZSBW0sFa+euaOyh5vGnwdMornA6vZb/MNlHQb0Dhlz/1rVA19WGJthNGNqzymCeInpNoMcRjt4XfVUFRKgXu6Gtcbc3sv3HL5AoGAC/HnvHW73suWGHrMcAel2xU7Ah/4C8+cZNPoN2MQtYmZkbss2QKEuRC2ZH/cEkv0rs+LpzkKGBdALMCjstTUujosHg0O5HFVHpEnLzpEURBwc9COy/jI/Qkru7Lmi95oZxlvpTOD1v/Ob17McRh0IDwfsh7gKmPtu9YECpc/KpECgYA9kHJG4F1AK8/btI/4aVxCUVLHg07FtVprXHsMcQ7O01udh3EwVHSy81BvaVbPXQUTKky67IEFe9rv4L6xUb0w9kFFTGVcO53cVq9kQymJTD74ggHkkF/LiplFjyLnnga3881vLjXytCWUg4UZ3w2OSO4ale1U7CgGSDBHjbZXIQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlubQlncE72sjTV5oFFTwMwQrDIKT+XDRJ7oiiPFXCkMynKPPewCvOGiNFiIKlRyZERgEXIOkVa9lJ470C1GTfrxlWl48oU6pwCPE6wx1FSAU/boo8DcByi7zrGNNCVfB0aoj1rmMotA3erAtQCXrhWW/DeraAVQAEiVFpDkAVter2Cg6eZzexDypWiyftToKiIPTvsr/WZS+daKnYPo5tbBIcYw/7UVRR4Lv1m3w6D3yhopf4KWRxOL8t4xpJzheTYJqby61AO3S888FGl4mljnbETQ2/1t8FH0yZs5xq76ZWSIFKB7zJaDyLOvODZ8WWQWsdoOien1pNB1SXJh9DQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.yunduanxing.com/pay/alipay/payNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.yunduanxing.com/pay/alipay/payReturn";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\alipay";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

