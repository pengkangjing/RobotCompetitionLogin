package com.yupeng.biz;

import java.util.List;

import com.yupeng.entity.TempStudentInfo;

public interface TempStudentInfoBiz {

	//��ѧ���Ƕ�(����)
	public List<TempStudentInfo> getAllByStudentName();
	//��ѧ���Ƕ�(ѧ��)
	public List<TempStudentInfo> getAllByStudentId();
	//��ѧ���Ƕ�(ѧԺ)
	public List<TempStudentInfo> getAllByStudentInstitute();

	//����ӽǶ�
	public List<TempStudentInfo> getAllByTeamName();
}
