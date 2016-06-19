package com.yupeng.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yupeng.biz.TeamInfoBiz;
import com.yupeng.biz.TempStudentInfoBiz;
import com.yupeng.biz.impl.TeamInfoBizImpl;
import com.yupeng.biz.impl.TempStudentInfoBizImpl;
import com.yupeng.entity.Histogram;
import com.yupeng.entity.TempStudentInfo;

public class doAdminSelectStudent extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doAdminSelectStudent() {
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
		int totalPeople=0;
		int p=1; //ҳ��
		String whichList="";//��ǰΪ��һҳ
		String unit="��λ������"; //��״ͼ��λ
		int allRow=0;
		if(session.getAttribute("allRow")!=null){
			allRow=(Integer)(session.getAttribute("allRow"));
		}
		if(session.getAttribute("totalPeople")!=null){
			totalPeople=(Integer)(session.getAttribute("totalPeople"));
		}
		TempStudentInfoBiz tsib=new TempStudentInfoBizImpl();
		List<TempStudentInfo> listShow=new ArrayList<TempStudentInfo>();
		List<Histogram> listHistogram=new ArrayList<Histogram>();
		//�õ�listByStudentName�Ͳ���
		if(session.getAttribute("listByStudentName")==null){
			List<TempStudentInfo> listByStudentName=tsib.getAllByStudentName();
			totalPeople=listByStudentName.size();
			session.setAttribute("totalPeople", totalPeople);
			session.setAttribute("listByStudentName", listByStudentName);

		}
		//�õ�listByTeamName�Ͳ���
		if(session.getAttribute("listByTeamName")==null){
			List<TempStudentInfo> listByTeamName=tsib.getAllByTeamName();
			session.setAttribute("listByTeamName", listByTeamName);
			allRow=listByTeamName.size();
			session.setAttribute("allRow", allRow);
			List<Histogram> listHistogramByTeamName=new ArrayList<Histogram>();
			String subjectName=listByTeamName.get(0).getTsListSubjectName().get(0);
			if(subjectName==null){
				subjectName="";
			}
			int subjectPer=0;
			for(TempStudentInfo tsi:listByTeamName){
				if(subjectName.equals(tsi.getTsListSubjectName().get(0))){				
					subjectPer++;	
				}else{					
					Histogram h=new Histogram();
					h.setName(subjectName+":"+subjectPer);
					h.setPer(subjectPer);
					listHistogramByTeamName.add(h);
					subjectName=tsi.getTsListSubjectName().get(0);
					subjectPer=1;
				}
			}
			Histogram h=new Histogram();
			h.setName(subjectName+":"+subjectPer);
			h.setPer(subjectPer);
			listHistogramByTeamName.add(h);
			
			Histogram hTotal=new Histogram();
			hTotal.setName("������:"+totalPeople);
			hTotal.setPer(totalPeople);
			listHistogramByTeamName.add(hTotal);
			session.setAttribute("listHistogramByTeamName", listHistogramByTeamName);
			
			
	
		}
		//�õ�listByStudentId�Ͳ���
		if(session.getAttribute("listByStudentId")==null){
			List<TempStudentInfo> listByStudentId=tsib.getAllByStudentId();
			session.setAttribute("listByStudentId", listByStudentId);
			List<Histogram> listHistogramByStudentId=new ArrayList<Histogram>();
			int sNumber=0;
			int Number=0;
			int otherNumber=0;
			for(TempStudentInfo tsi:listByStudentId){
				if((tsi.getTsStudent().getStudentId().trim()).charAt(0)=='s'||(tsi.getTsStudent().getStudentId().trim()).charAt(0)=='S'){
					sNumber++;
				}else if((tsi.getTsStudent().getStudentId().trim()).charAt(0)=='0'||(tsi.getTsStudent().getStudentId().trim()).charAt(0)=='1'||(tsi.getTsStudent().getStudentId().trim()).charAt(0)=='2'||(tsi.getTsStudent().getStudentId().trim()).charAt(0)=='3'){
					Number++;	
				}else{
					otherNumber++;
				}
			}
			Histogram h1=new Histogram();
			h1.setName("�о���:"+sNumber);
			h1.setPer(sNumber);
			Histogram h2=new Histogram();
			h2.setName("������:"+Number);
			h2.setPer(Number);
			Histogram h3=new Histogram();
			h3.setName("����:"+otherNumber);
			h3.setPer(otherNumber);
			Histogram h4=new Histogram();
			h4.setName("������:"+totalPeople);
			h4.setPer(totalPeople);
			listHistogramByStudentId.add(h1);
			listHistogramByStudentId.add(h2);
			listHistogramByStudentId.add(h3);
			listHistogramByStudentId.add(h4);
			session.setAttribute("listHistogramByStudentId", listHistogramByStudentId);
		}
		//�õ�listByStudentInstitute�Ͳ���
		if(session.getAttribute("listByStudentInstitute")==null){
			List<TempStudentInfo> listByStudentInstitute=tsib.getAllByStudentInstitute();
			session.setAttribute("listByStudentInstitute", listByStudentInstitute);
			
			List<Histogram> listHistogramByStudentInstitute=new ArrayList<Histogram>();
			String instituteName=listByStudentInstitute.get(0).getTsStudent().getStudentInstitute();
			if(instituteName==null){
				instituteName="";
			}
			int institutePer=0;
			for(TempStudentInfo tsi:listByStudentInstitute){
				if(instituteName.equals(tsi.getTsStudent().getStudentInstitute())){				
					institutePer++;			
				}else{					
					Histogram h=new Histogram();
					h.setName(instituteName+":"+institutePer);
					h.setPer(institutePer);
					listHistogramByStudentInstitute.add(h);
					instituteName=tsi.getTsStudent().getStudentInstitute();
					institutePer=1;
				}
			}
			Histogram h=new Histogram();
			h.setName(instituteName+":"+institutePer);
			h.setPer(institutePer);
			listHistogramByStudentInstitute.add(h);
			Histogram hTotal=new Histogram();
			hTotal.setName("������:"+totalPeople);
			hTotal.setPer(totalPeople);
			listHistogramByStudentInstitute.add(hTotal);
			session.setAttribute("listHistogramByStudentInstitute", listHistogramByStudentInstitute);
		}
		
		
		String show=request.getParameter("show");
		if(show==null||(show.trim()).isEmpty()){
			listShow=(ArrayList<TempStudentInfo>)session.getAttribute("listByStudentName");
			listHistogram=(ArrayList<Histogram>)session.getAttribute("listHistogramByStudentId");
			whichList="";

		}else if((show.trim()).equals("byTeamName")){
			listShow=(ArrayList<TempStudentInfo>)session.getAttribute("listByTeamName");
			listHistogram=(ArrayList<Histogram>)session.getAttribute("listHistogramByTeamName");
			whichList="byTeamName";
			unit="��λ������";
		}else if((show.trim()).equals("byStudentId")){
			listShow=(ArrayList<TempStudentInfo>)session.getAttribute("listByStudentId");
			listHistogram=(ArrayList<Histogram>)session.getAttribute("listHistogramByStudentId");
			whichList="byStudentId";
			unit="��λ������";
		}else if((show.trim()).equals("byTeamCount")){
			if(session.getAttribute("listHistogramByTeamCount")==null){
				TeamInfoBiz tib=new TeamInfoBizImpl();
				List<Histogram> listHistogramByTeamCount=tib.getHistogram();
				session.setAttribute("listHistogramByTeamCount", listHistogramByTeamCount);
			}
			whichList="";
			unit="��λ������";
			listHistogram=(ArrayList<Histogram>)session.getAttribute("listHistogramByTeamCount");
		}else{
			listShow=(ArrayList<TempStudentInfo>)session.getAttribute("listByStudentInstitute");
			listHistogram=(ArrayList<Histogram>)session.getAttribute("listHistogramByStudentInstitute");
			whichList="byStudentInstitute";
			unit="��λ������";
		}
		session.setAttribute("whichList", whichList);
		if(request.getParameter("p")!=null){
			p=Integer.parseInt(request.getParameter("p"));
		}
		List<Integer> listP=new ArrayList<Integer>();
		if(session.getAttribute("listP")==null){
			int totalPage=0;
			if(allRow%10==0){
				totalPage=allRow/10;
			}else{
				totalPage=allRow/10+1;
			}
			for(int i=1; i<=totalPage;i++){
				listP.add(i);
			}
			session.setAttribute("listP", listP);
		}
		
		
		int begin=(p-1)*10;
		int end=(p-1)*10+10;
		if(end>listShow.size()){
			end=listShow.size();
		}
		List<TempStudentInfo> listShow3=listShow.subList(begin,end);
		
		session.setAttribute("listShow", listShow3);
		session.setAttribute("listHistogram", listHistogram);
		String info=request.getParameter("info");
		if(info!=null&&info.trim().equals("pic")){
			session.setAttribute("unit", unit);
			response.sendRedirect("adminSelectPic.jsp");
		}else{
			response.sendRedirect("adminSelectStudent2.jsp");
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
