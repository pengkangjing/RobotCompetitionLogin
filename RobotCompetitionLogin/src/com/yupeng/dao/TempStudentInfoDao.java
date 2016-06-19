package com.yupeng.dao;

import java.util.List;

import com.yupeng.entity.TempStudentInfo;

public interface TempStudentInfoDao extends BaseDao {

	//��ѧ���Ƕ�(����)
	public List<TempStudentInfo> getAllByStudentName();
	//��ѧ���Ƕ�(ѧ��)
	public List<TempStudentInfo> getAllByStudentId();
	//��ѧ���Ƕ�(ѧԺ)
	public List<TempStudentInfo> getAllByStudentInstitute();

	//����ӽǶ�
	public List<TempStudentInfo> getAllByTeamName();
	

}
