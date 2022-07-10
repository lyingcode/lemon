package com.bitsoft.lemon.demo;

import com.bitsoft.lemon.base.BaseUnitTest;
import com.bitsoft.lemon.model.Student;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class RedisTemplateTest extends BaseUnitTest {
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void redisUserTest() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name", "brucelee");
        valueOperations.set("age", 39);
        Integer age = Integer.parseInt(valueOperations.get("age").toString());
        assertEquals(39,age);
    }

    @Test
    public void pipelineTest() {
        redisTemplate.executePipelined((RedisCallback) connection -> {
            log.info("开始执行pipeline");
            connection.openPipeline();
            byte[] key = "name".getBytes();
            byte[] value = "bruceLee".getBytes();
            connection.set(key, value);
            connection.lPush("plist".getBytes(), "key1".getBytes());
            log.info("pipeline执行完成");
            return null;
        });

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Map<String, String> mutiValue = Maps.newHashMap();
        mutiValue.put("name", "jamesZhang");
        mutiValue.put("age", "39");
        mutiValue.put("address", "shenzhen");
        valueOperations.multiSet(mutiValue);

        valueOperations.setIfAbsent("name", "kingOfLee");
        String encodeValue = valueOperations.get("name");
        assertEquals("jamesZhang",encodeValue);
    }

    @Test
    public void redisMemTest() {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String hkey = "demo";
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 500; j++) {
                hashOperations.put(hkey, pad(i, 10, "0"), String.valueOf(j));
            }
        }
        assertEquals(20,hashOperations.size(hkey));
    }


    private String pad(int num, int expectSize, String padChar) {
        int len = String.valueOf(num).length();
        int padNum = expectSize - len;
        StringBuilder padStr = new StringBuilder();
        for (int i = 0; i < padNum; i++) {
            padStr.append(padChar);
        }
        padStr.append(num);
        return padStr.toString();
    }

    @Test
    public void putObjectTest() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Student student = new Student();
        student.setId(123456);
        student.setRealName("jameszhang");
        valueOperations.set("jameszhang", student);

        Student redisStudent = (Student) valueOperations.get("jameszhang");
        assertEquals(redisStudent.getRealName(),student.getRealName());
    }

    @Test
    public void setObjectTest() {
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("user:all", "jameszhang");
        setOperations.add("user:all", "bruceLee");

        setOperations.add("user:20220709", "jamesKing");
        setOperations.add("user:20220709", "kkBar");
        setOperations.add("user:20220709", "bruceLee");

        Set<String> diffSet = setOperations.difference("user:all", "user:20220709");
        assertTrue(diffSet.contains("jameszhang"));

        Set intersectSet = setOperations.intersect("user:all", "user:20220709");
        assertTrue(intersectSet.contains("bruceLee"));


        Set unionSet = setOperations.union("user:all", "user:20220709");
        Set<String> expectSet = Sets.set("jameszhang", "bruceLee", "kkBar", "jamesKing");
        assert unionSet != null;
        assertTrue(unionSet.containsAll(expectSet));
    }
    @Test
    public void setUseTest(){
        SetOperations setOperations = redisTemplate.opsForSet();
        String userId = "JAMES";
        String allUserKey = "user:id";
        boolean isContains = setOperations.isMember(allUserKey,userId);
        if(!isContains){
            setOperations.add(allUserKey,userId);
        }
        String dateUserKey = "user:id" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(!setOperations.isMember(dateUserKey,userId)){
            setOperations.add(dateUserKey,userId);
        }
        setOperations.add(dateUserKey,"GOE");
        String diffKey = "user:id:diff";
        Long result = setOperations.differenceAndStore(dateUserKey,allUserKey,diffKey);
        assertEquals(1,result);
        assertTrue(setOperations.isMember(diffKey,"GOE"));
    }

}
