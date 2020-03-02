package com.hanyixin.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanyixin.common.utils.StringUtil;
import com.hanyixin.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class TestUser {

	@Autowired
	RedisTemplate redisTemplate;
	
	@Test
	public void show() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i <=100000; i++) {
			User user = new User();
			//获得1-10万个顺序号
			user.setId(String.valueOf(i));
			//姓名使用三个随机汉字
			String randChinese = StringUtil.randomChineseString(3);
			user.setName(randChinese);
			//生成随机手机号
			String ss = "13";
			for (int d = 0; d <9; d++) {
				int s = (int)(0+Math.random()*(9-0+1));
				ss = ss+s;
			}
			user.setPhone(ss);
			//随机性别
			int sexnum = (int)(0+Math.random()*(1-0+1));
			if(sexnum==0) {
				user.setSex("男");
				user.setSex("男");
			}else {
				user.setSex("女");
			}
			//随机生成邮箱
			//生成3-20个随机字母
			String email = "";
			int rns = (int)(3+Math.random()*(9-0+1));
			for (int q = 0; q <rns; q++) {
				email = email+StringUtil.getRandomChar();
			}
			String[] emailsz = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			int szRandom = (int)(0+Math.random()*(6-0+1));
			email = email+emailsz[szRandom];
			user.setEmail(email);
			//生成随机生日
			int bdyear = (int)(1949+Math.random()*(2001-1949+1));
			int bdmouth = (int)(1+Math.random()*(12-0+1));
			int twoday = 0;
			if(bdmouth==2) {
				twoday = (int)(1+Math.random()*(29-0+1));
			}else if(bdmouth!=2 && bdmouth%2==0) {
				twoday = (int)(1+Math.random()*(31-0+1));
			}else {
				twoday = (int)(1+Math.random()*(30-0+1));
			}
			String birthday = bdyear+"-"+bdmouth+"-"+twoday;
			user.setBirthday(birthday);
			list.add(user);
		}
		System.out.println("序列化方式JdkSerializationRedisSerializer");
		System.out.println("保存数量100000条");
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPush("myliist", list.toArray());
		long end = System.currentTimeMillis();
		System.out.println("所耗时间"+(end-start));
	}
	
	@Test
	public void JSON() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i <=100000; i++) {
			User user = new User();
			//获得1-10万个顺序号
			user.setId(String.valueOf(i));
			//姓名使用三个随机汉字
			String randChinese = StringUtil.randomChineseString(3);
			user.setName(randChinese);
			//生成随机手机号
			String ss = "13";
			for (int d = 0; d <9; d++) {
				int s = (int)(0+Math.random()*(9-0+1));
				ss = ss+s;
			}
			user.setPhone(ss);
			//随机性别
			int sexnum = (int)(0+Math.random()*(1-0+1));
			if(sexnum==0) {
				user.setSex("男");
			}else {
				user.setSex("女");
			}
			//随机生成邮箱
			//生成3-20个随机字母
			String email = "";
			int rns = (int)(3+Math.random()*(9-0+1));
			for (int q = 0; q <rns; q++) {
				email = email+StringUtil.getRandomChar();
			}
			String[] emailsz = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			int szRandom = (int)(0+Math.random()*(6-0+1));
			email = email+emailsz[szRandom];
			user.setEmail(email);
			//生成随机生日
			int bdyear = (int)(1949+Math.random()*(2001-1949+1));
			int bdmouth = (int)(1+Math.random()*(12-0+1));
			int twoday = 0;
			if(bdmouth==2) {
				twoday = (int)(1+Math.random()*(29-0+1));
			}else if(bdmouth!=2 && bdmouth%2==0) {
				twoday = (int)(1+Math.random()*(31-0+1));
			}else {
				twoday = (int)(1+Math.random()*(30-0+1));
			}
			String birthday = bdyear+"-"+bdmouth+"-"+twoday;
			user.setBirthday(birthday);
			list.add(user);
		}
		System.out.println("key系列化器StringRedisSerializer。value系列化器为Jackson2JsonRedisSerializer。");
		System.out.println("保存数量100000条");
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPush("myliist", list.toArray());
		long end = System.currentTimeMillis();
		System.out.println("所耗时间"+(end-start));
	}
	
	@Test
	public void HashRedis() {
		HashMap<String, User> map = new HashMap<String, User>();
		for (int i = 0; i <=100000; i++) {
			User user = new User();
			//获得1-10万个顺序号
			user.setId(String.valueOf(i));
			//姓名使用三个随机汉字
			String randChinese = StringUtil.randomChineseString(3);
			user.setName(randChinese);
			//生成随机手机号
			String ss = "13";
			for (int d = 0; d <9; d++) {
				int s = (int)(0+Math.random()*(9-0+1));
				ss = ss+s;
			}
			user.setPhone(ss);
			//随机性别
			int sexnum = (int)(0+Math.random()*(1-0+1));
			if(sexnum==0) {
				user.setSex("男");
				user.setSex("男");
			}else {
				user.setSex("女");
			}
			//随机生成邮箱
			//生成3-20个随机字母
			String email = "";
			int rns = (int)(3+Math.random()*(9-0+1));
			for (int q = 0; q <rns; q++) {
				email = email+StringUtil.getRandomChar();
			}
			String[] emailsz = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			int szRandom = (int)(0+Math.random()*(6-0+1));
			email = email+emailsz[szRandom];
			user.setEmail(email);
			//生成随机生日
			int bdyear = (int)(1949+Math.random()*(2001-1949+1));
			int bdmouth = (int)(1+Math.random()*(12-0+1));
			int twoday = 0;
			if(bdmouth==2) {
				twoday = (int)(1+Math.random()*(29-0+1));
			}else if(bdmouth!=2 && bdmouth%2==0) {
				twoday = (int)(1+Math.random()*(31-0+1));
			}else {
				twoday = (int)(1+Math.random()*(30-0+1));
			}
			String birthday = bdyear+"-"+bdmouth+"-"+twoday;
			user.setBirthday(birthday);
			map.put("us"+i,user);
		}
		System.out.println("hashKey系列化器StringRedisSerializer，hashValue系列化器StringRedisSerializer");
		System.out.println("保存数量100000条");
		long start = System.currentTimeMillis();
		redisTemplate.opsForHash().putAll("myhash",map);
		long end = System.currentTimeMillis();
		System.out.println("所耗时间"+(end-start));
	}
	
}
