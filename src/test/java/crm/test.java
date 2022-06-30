package crm;

import com.sun.crm.commons.utils.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class test {

    @Test
    public void t1(){
        String s = DateUtils.formatDateTimeSec(new Date());
        System.out.println(s);
        System.out.println("---------------------");
        String s1 = DateUtils.formatDate(new Date());
        System.out.println(s1);
    }

    @Test
    public void t2(){
        //创建javaSE自带的工具类
        String s = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(s);
    }
}
