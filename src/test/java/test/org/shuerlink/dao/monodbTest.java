package test.org.shuerlink.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shuerlink.dao.StudentDao;
import org.shuerlink.daoImpl.StudentDaoImpl;
import org.shuerlink.model.Student.Custom;
import org.shuerlink.model.Student.Shuerlink;
import org.shuerlink.model.Student.StudentInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})//加载spring配置文件
public class monodbTest {
    @Resource
    private StudentDaoImpl studentDaoImpl;

    @Test
    public void testFindOneById() {
        StudentInfo studentInfo = studentDaoImpl.findOneById("123");
        if (studentInfo != null)
            System.out.println(studentInfo.getAvatar());
    }

    @Test
    public void testSave() {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId("");
        studentInfo.setName("");
        studentInfo.setNickname("");
        studentInfo.setLogined(true);
        studentDaoImpl.save(studentInfo);
    }
}
