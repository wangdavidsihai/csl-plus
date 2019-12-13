package com.macro.mall.portal;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.ums.entity.UmsMember;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = MallPortalApplicationTests.class)
@RunWith(SpringRunner.class)
@Log4j2
public class MallPortalApplicationTests {

	@Resource
	IUmsMemberService sysAdminLogMapper;

	@Test
	public void contextLoads() {
		String ids = "1,2,3,4";
		List<UmsMember> ll = (List<UmsMember>) sysAdminLogMapper.listByIds(Arrays.asList(ids.split(",")));
		for (UmsMember log : ll) {
			System.out.println(log.getUsername());
		}
	}

}
