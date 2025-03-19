package edu.ssafy.controller;

import java.io.IOException;
import java.util.List;

import edu.ssafy.dto.MemberDto;
import edu.ssafy.service.MemberService;
import edu.ssafy.service.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private MemberService service;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		service = new MemberServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String action = req.getParameter("action");
		String url = new String();
		if (action == null && action.isBlank()) {
			url = "index.html";
		} else {
			if (action.equals("memberinsert")) {
				url = memberInsert(req, resp);
			} else if (action.equals("memberupdate")) {
				url = memberUpdate(req, resp);
			} else if (action.equals("memberdelete")) {
				url = memberDelete(req, resp);
			} else if (action.equals("memberselect")) {
				if (req.getParameter("check")!=null&&req.getParameter("check").equals("on")) url = memberSelectAll(req, resp);
				else url = memberSelect(req, resp);
			}
		}

	}

	private String memberSelect(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1. 파라미터 처리
		String id = req.getParameter("id");

		MemberDto sel = service.MemberSelect(id);

		// 3. 화면처리
		if (sel!=null) {
			String res = """
					Select Success<br>
					ID : %s<br>
					PW : %s<br>
					Name : %s
					""".formatted(sel.getId(), sel.getPw(), sel.getName());
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		else {
			String res = "Select Failed";
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		
		return null;
	}

	private String memberSelectAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1. 파라미터 처리
		List<MemberDto> sel = service.MemberSelectAll();

		// 3. 화면처리
		if (sel!=null) {
			String res = "Select Success<br>";
			for (MemberDto s : sel) {
				res += """
						ID : %s<br>
						PW : %s<br>
						Name : %s<br>
						""".formatted(s.getId(), s.getPw(), s.getName());
			}
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		else {
			String res = "Select Failed";
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		
		return null;
	}

	private String memberDelete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1. 파라미터 처리
		String id = req.getParameter("id");

		int ret = service.MemberDelete(id);
		// 3. 화면처리
		if (ret>0) {
			String res = "Delete Success";
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		else {
			String res = "Delete Failed";
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		
		return null;
	}

	private String memberUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1. 파라미터 처리
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");

		// 2. 로직처리
		MemberDto before = service.MemberSelect(id);
		int ret = service.MemberUpdate(new MemberDto(id, pw, name));

		// 3. 화면처리
		if (ret>0) {
			String res = """
					Update Success<br>
					ID : %s(before) to %s(after)<br>
					PW : %s(before) to %s(after)<br>
					Name : %s(before) to %s(after)
					""".formatted(before.getId(), id, before.getPw(), pw, before.getName(), name);
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		else {
			String res = "Update Failed";
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}

		return null;
	}

	private String memberInsert(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		// 1. 파라미터 처리
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");

		// 2. 로직처리
		int ret = service.MemberInsert(new MemberDto(id, pw, name));

		// 3. 화면처리
		if (ret>0) {
			String res = """
					Insert Success<br>
					ID : %s<br>
					PW : %s<br>
					Name : %s
					""".formatted(id, pw, name);
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}
		else {
			String res = "Insert Failed";
			System.out.println(res);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(res);
		}

		return null;
	}
}
