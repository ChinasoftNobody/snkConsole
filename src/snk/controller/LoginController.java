package snk.controller;

import com.jfinal.core.Controller;

public class LoginController extends Controller {
	public void index(){
		System.out.println("LoginController");
		renderText("0");
	}
}
