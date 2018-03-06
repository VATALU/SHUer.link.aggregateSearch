package test.org.shuerlink.Service;

import org.junit.runner.RunWith;
import org.shuerlink.service.serviceImpl.SHULoginServiceImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})//加载spring配置文件
public class ServiceTest {
    @Resource
    private SHULoginServiceImpl shuLoginService;

    public void testSHUClient() {
        String userName = "";
        String password = "";
        shuLoginService.loginSHUStudent(userName,password);
    }
}
