package snk.jfinal;

import java.util.Set;

import org.apache.log4j.Logger;

import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

import snk.common.Const;
import snk.common.ServerConfig;
import snk.common.uitls.ClassUtil;



/**  
 * @className: RouteScanner 
 * @projectName: snkConsole
 * @Description:  自动扫描controller工具
 * @author: liguanghao 
 * @date:2015年11月12日 下午8:56:09 
 * @version: ver 1.0
 */  
public class RouteScanner {
	private Routes me;
	private static Logger logger = Logger.getLogger(RouteScanner.class);
	private Set<Class<?>> classSet;
	public RouteScanner(Routes me ) {
		this.me = me;
	}
	
	/**  
	 * @Description: 
	 * 将controller包下的所有*Controller类全部注册
	 * @author: liguanghao 
	 * @version: 2015年11月12日 下午8:58:50
	 * @param fileName
	 */  
	@SuppressWarnings("unchecked")
	public void start(String fileName) {
		Const.PROP = loadPropertyFIle(fileName,Const.DEFALT_ENCODING);
		if(Const.PROP == null){
			logger.error("读取资源文件失败" + fileName);
			return;
		}
		//扫描controller包下的所有*Controller注册到JFinal
		classSet = ClassUtil.getClasses(ServerConfig.CONTROLLER_SCANING_PACK, false);
		if(classSet.isEmpty()||classSet == null){
			logger.info("没有扫描到controller");
			return;
		}
		for(Class<?> item:classSet){
			me.add("/" + item.getSimpleName().replace("Controller", "").toLowerCase(), (Class<? extends Controller>) item,"");
			logger.info(item.getSimpleName().replace("Controller", "").toLowerCase() + "已注册");
		}
	}
	
	
	/**  
	 * @Description: 读取配置文件
	 * @author: liguanghao 
	 * @version: 2015年11月12日 下午8:56:46
	 * @param fileName
	 * @param encoding
	 * @return Prop
	 */  
	private Prop loadPropertyFIle(String fileName,String encoding){
		return PropKit.use(fileName,encoding);
		
	}
}
