package com.yupeng.test;

import java.util.List;

import org.junit.Test;

import com.yupeng.dao.TempTeamResultDao;
import com.yupeng.dao.impl.TempTeamResultDaoImpl;
import com.yupeng.entity.StudentInfo;
import com.yupeng.entity.TempTeamResult;

public class WebTest {
 
	@Test
	public void t(){
//		TempStudentInfoDao tsS=new TempStudentInfoDaoImpl();
//		List<TempStudentInfo> listTs=tsS.getAllByStudentInstitute();
//		for(TempStudentInfo tsI:listTs){
//			
//			System.out.println("ѧ��"+tsI.getTsStudent().getStudentName()+"������Ŀ:"+tsI.getTsStudent().getStudentInstitute()+"   ������Ŀ��:"+tsI.getTsListTeamName().size());
//		}
		
		TempTeamResultDao ttrd=new TempTeamResultDaoImpl();
		List<TempTeamResult> listTtr=ttrd.getAllTempTeamResult();
		for(TempTeamResult tt:listTtr){
			System.out.println(tt.getTtSubjectName()+"  haha  "+tt.getTtTeamName()+"  fefefe  "+tt.getTtTeamId());
			for(StudentInfo si:tt.getTtListStudentInfo()){
				System.out.println("ѧ��������"+si.getStudentName());
			}
			
		}
		
	}
}
