package net.i2p.i2pcontrol.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.i2p.I2PAppContext;
import net.i2p.util.Log;

public class SettingsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4018705582081424641L;
	private static Log _log;

	
	@Override
	public void init(){
		_log = I2PAppContext.getGlobalContext().logManager().getLog(SettingsServlet.class);
	}
	
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
    	httpServletResponse.setContentType("text/html");
        PrintWriter out = httpServletResponse.getWriter();
        out.println("Settings be here");
        out.close();
    }

}