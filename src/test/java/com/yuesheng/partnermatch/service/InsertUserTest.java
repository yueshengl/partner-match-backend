package com.yuesheng.partnermatch.service;

import com.yuesheng.partnermatch.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: Dai
 * @date: 2024/11/17
 * @Description:    用户插入单元测试，注意打包时要删掉或忽略，不然打一次包就插入一次
 */
@SpringBootTest
public class InsertUserTest {

    @Resource
    private UserService userService;

    //CPU密集型：分配的核心线程数 = CPU-1
    //IO密集型：分配的核心线程数可以大于CPU核数
    private ExecutorService executorService = new ThreadPoolExecutor(16, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));
    /**
     *
     * 批量插入用户   1000  耗时： 4487ms
     */
//    @Test
//    public void doInsertUser() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 1000;
//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i < INSERT_NUM; i++) {
//            User user = new User();
//            user.setUsername("假月生");
//            user.setUserAccount("yuesheng");
//            user.setAvatarUrl("https://img2.woyaogexing.com/2022/07/04/2514244426169f7a!400x400.jpg");
//            user.setUserProfile("一条咸鱼");
//            user.setGender(0);
//            user.setUserPassword("12345678");
//            user.setPhone("123456789108");
//            user.setEmail("yuesheng_shengyue@qq.com");
//            user.setUserStatus(0);
//            user.setUserRole(0);
//            user.setPlanetCode("931");
//            user.setTags("[]");
//            userList.add(user);
//        }
//        userService.saveBatch(userList,100);
//        stopWatch.stop();
//        System.out.println( stopWatch.getLastTaskTimeMillis());
//
//    }
//
//    /**
//     *
//     * 并发批量插入用户   100000  10组  耗时： 43299ms
//     */
//    @Test
//    public void doConcurrencyInsertUser() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 100000;
//        // 分十组
//        int j = 0;
//        //批量插入数据的大小
//        int batchSize = 10000;
//        List<CompletableFuture<Void>> futureList = new ArrayList<>();
//        // i 要根据数据量和插入批量来计算需要循环的次数。（鱼皮这里直接取了个值，会有问题,我这里随便写的）
//        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
//            List<User> userList = new ArrayList<>();
//            while (true){
//                j++;
//                User user = new User();
//                user.setUsername("假月生");
//                user.setUserAccount("yuesheng");
//                user.setAvatarUrl("https://img2.woyaogexing.com/2022/07/04/2514244426169f7a!400x400.jpg");
//                user.setUserProfile("一条咸鱼");
//                user.setGender(0);
//                user.setUserPassword("12345678");
//                user.setPhone("123456789108");
//                user.setEmail("yuesheng_shengyue@qq.com");
//                user.setUserStatus(0);
//                user.setUserRole(0);
//                user.setPlanetCode("931");
//                user.setTags("[]");
//                userList.add(user);
//                if (j % batchSize == 0 ){
//                    break;
//                }
//            }
//            //异步执行
//            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
//                System.out.println("ThreadName：" + Thread.currentThread().getName());
//                userService.saveBatch(userList,batchSize);
//            },executorService);
//                futureList.add(future);
//        }
//        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
//
//        stopWatch.stop();
//        System.out.println( stopWatch.getLastTaskTimeMillis());
//
//    }
}
