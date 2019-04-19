package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.NoticeDAO;
import di.MvcAction;
import di.MvcForward;

public class List implements MvcAction{

	@Override
	public MvcForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		/*int page =1 ;
		int limit = 4;
		int pageLimit =4;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}*/
		
		NoticeDAO dao = new NoticeDAO();
		
		/*int total = dao.total();		
		int totalPage = total/limit;
		
		if(total%limit>0)
			totalPage ++;
		
		int startPage = (page-1)/pageLimit*pageLimit+1;
		int endPage = startPage+pageLimit-1;
		int start = (page-1)*limit*/;
		
		request.setAttribute("data", new NoticeDAO().list());
		//request.setAttribute("nowPage", page);
		/*request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("start", start);
		request.setAttribute("totalPage",totalPage);
		*/
		System.out.println("리스트 서비스 진입");
		
		return null;
	}

}
