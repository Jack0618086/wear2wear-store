package tw.Final.FinalS1.controller;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 跳轉
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final AllInOne allInOne;

    // 使用 @Value 注入配置文件路徑
    public CheckoutController() {
        try {
            this.allInOne = new AllInOne("");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize AllInOne with config path: " +  e);
        }
    }

    @PostMapping("/create")
    @ResponseBody
    public String checkout() {
    	
        AioCheckOutOneTime obj = new AioCheckOutOneTime();
        obj.setMerchantID("EEIT2024");
        obj.setMerchantTradeNo("EEIT85test08123");
        obj.setMerchantTradeDate("2024/09/12 15:45:30");
        obj.setTotalAmount("1000");
        obj.setTradeDesc("EEIT專案");
        obj.setItemName("Test Product");       
        obj.setReturnURL("http://3de1-218-32-102-243.ngrok-free.app/ecpayresponse/receiveECPayResponse");
        obj.setClientBackURL("http://localhost:8080/Home.html");
        obj.setNeedExtraPaidInfo("N");
        obj.setChooseSubPayment("ALL");

        // 產生金流頁面的表單 HTML
        String form = allInOne.aioCheckOut(obj, null);
        return form;
    }
}
