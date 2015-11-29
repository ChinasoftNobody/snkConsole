package snk.jfinal;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

import snk.common.Const;
import snk.common.ServerConfig;

public class Configer extends JFinalConfig {
	private static Logger log = Logger.getLogger(Configer.class);
	@Override
	public void configConstant(Constants me) {
		//配置log4J
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		log.info("configConstant 设置字符集");
		me.setEncoding(Const.DEFALT_ENCODING);
		log.info("configConstant 视图error page设置");
		me.setError401View("/common/401.html");
		me.setError403View("/common/403.html");
		me.setError404View("/common/404.html");
		me.setError500View("/common/500.html");
	}

	@Override
	public void configRoute(Routes me) {
		/**
		 * getPATH_CONTEXT
		 * 这里使用File.seperate到时候注意：如果环境是linux没有问题，
		 * 如果是windows环境的时候需要这里会变成“\”，这时不替换掉会找不到文件
		 */
		PropertyConfigurator.configure(ServerConfig.getPATH_CONTEXT() + "log4j.properties");
		RouteScanner rs = new RouteScanner(me);
		rs.start(Const.CONFIG_FILE_NAME);
	}

	@Override
	public void configPlugin(Plugins me) {
		//这里loadProperties方法默认从PathKit.getRootClassPath()开始查找文件
		loadPropertyFile(Const.CONFIG_FILE_NAME); 
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("mysql.jdbcUrl"),  getProperty("mysql.userName"), getProperty("mysql.passWord"));  
		me.add(c3p0Plugin);  
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin); 
		me.add(arp);  
		ModelScanner ms = new ModelScanner();
		ms.start(arp,ServerConfig.MODEL_SANNING_PACK);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
