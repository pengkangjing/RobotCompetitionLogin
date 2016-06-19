package com.yupeng.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yupeng.biz.StudentInfoBiz;
import com.yupeng.biz.TeamInfoBiz;
import com.yupeng.biz.impl.StudentInfoBizImpl;
import com.yupeng.biz.impl.TeamInfoBizImpl;
import com.yupeng.entity.StudentInfo;

public class doTeamUpdateStudent extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doTeamUpdateStudent() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		StudentInfoBiz sib=new StudentInfoBizImpl();
		TeamInfoBiz tib=new TeamInfoBizImpl();
		int teamId=0;
		String teamResult="";
		boolean flag=true;
		if(session.getAttribute("resultId")!=null){
			teamId=Integer.parseInt(session.getAttribute("resultId").toString().trim());
		}else{
			response.sendRedirect("teamLogin.jsp");
		}
		
		int subjectId=tib.getTeamSubjectId(teamId);
		
		String oldStudentId=request.getParameter("oldStudentId").trim();

		String	studentId=request.getParameter("studentId").trim();

		String studentName=request.getParameter("studentName");
		String studentInstitute=request.getParameter("studentInstitute");
		String studentPhone=request.getParameter("studentPhone");
		String studentEmail=request.getParameter("studentEmail");
		
		StudentInfo studentInfo =new StudentInfo();
		studentInfo.setStudentId(studentId);
		studentInfo.setStudentName(studentName);
		studentInfo.setStudentInstitute(studentInstitute);
		studentInfo.setStudentPhone(studentPhone);
		studentInfo.setStudentEmail(studentEmail);
		studentInfo.setRemark("");
		//�ύ��ѧ��ѧ����Ժѧ��ѧ����ͬ
		if(studentId.equals(oldStudentId)){
			if(sib.update(studentInfo)>0){
				teamResult="�޸�ѧ����Ϣ�ɹ���";
				request.setAttribute("teamResult", teamResult);
				request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
			}
		}else{
			List<Integer> listNewId=tib.getSubjcetId(studentId);
			List<Integer> listOldStudentId=null;
			//�ύ��ѧ��ѧ�Ų����κζ���
			if(listNewId==null||listNewId.size()==0){
				//�½���ѧ��
				if(sib.add(studentInfo)>0){
					//�滻ԭ��ѧ����Ϣ
					if(tib.updateStudentId(teamId, studentId, oldStudentId)>0){
						teamResult="�޸�ѧ����Ϣ�ɹ���";
						request.setAttribute("teamResult", teamResult);
						//���ԭ��ѧ����Ϣδ������������Ŀ��ɾ��ԭ��ѧ����Ϣ
						listOldStudentId=tib.getSubjcetId(oldStudentId);
						if(listOldStudentId==null||listOldStudentId.size()==0){
							
							sib.delete(oldStudentId);
						}
						
						request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
						
					}else{
						teamResult="�޸�ѧ����Ϣʧ�ܣ��޸ĵ�ѧ����Ϣ���󡭡�";
						request.setAttribute("teamResult", teamResult);
						request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
					}
					
					
				}else{
					teamResult="�޸�ѧ����Ϣʧ�ܣ��޸ĵ�ѧ����Ϣ���󡭡�";
					request.setAttribute("teamResult", teamResult);
					request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
				}
				
			}else{
				//������ύ��ѧ���Ѿ������������ұ�������ͬ��Ŀ
				for(Integer subId:listNewId){
					if(subjectId==subId){
						flag=false;
						teamResult="�޸�ѧ����Ϣʧ�ܣ��޸ĵ�ѧ���ѱ����˿�Ŀ����˲顭��";
						request.setAttribute("teamResult", teamResult);
						request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
					}else{
						continue;
					}
				}
				//������ύѧ���Ѿ���������������ͬ��Ŀ
				if(flag){
					//�������ύ��ѧ����Ϣ�����滻ԭѧ���ڴζ��е���Ϣ
					if(sib.update(studentInfo)>0){
						if(tib.updateStudentId(teamId, studentId, oldStudentId)>0){
							teamResult="�޸�ѧ����Ϣ�ɹ���";
							request.setAttribute("teamResult", teamResult);
							
							//ɾ���ɵĲ����κζ��е�ѧ����Ϣ
							listOldStudentId=tib.getSubjcetId(oldStudentId);
							if(listOldStudentId==null||listOldStudentId.size()==0){
								
								sib.delete(oldStudentId);
							}
							
							request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
						}else{
							teamResult="�޸�ѧ����Ϣʧ�ܣ��޸ĵ�ѧ����Ϣ����";
							request.setAttribute("teamResult", teamResult);
							request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
						}
						
					}else{
						teamResult="�޸�ѧ����Ϣʧ�ܣ��޸ĵ�ѧ����Ϣ����";
						request.setAttribute("teamResult", teamResult);
						request.getRequestDispatcher("teamUpdateResult.jsp").forward(request, response);
					}
					
				}
				
			}
			
			
		}
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
