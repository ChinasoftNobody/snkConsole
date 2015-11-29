package snk.common;

import com.jfinal.core.JFinal;

/**  
 * @className: ServerConfig 
 * @projectName: snkConsole
 * @Description:  web的一些配置
 * @author: liguanghao 
 * @date:2015年11月21日 下午6:40:10 
 * @version: ver 1.0
 */  
public class ServerConfig {
	/** 需要扫描的controller包 */
	public static final String CONTROLLER_SCANING_PACK = "snk.controller";
	/** 需要扫描的model包 */
	public static final String MODEL_SANNING_PACK = "snk.model";
	/** 服务器的context目录 */
	private static final String PATH_CONTEXT = JFinal.me().getServletContext().getRealPath("/");
	
	
	public static String getPATH_CONTEXT() {
		return PATH_CONTEXT;
	}

}
