package snk.jfinal;

import java.util.Set;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;

import snk.common.uitls.ClassUtil;
import snk.common.uitls.StringUtils;

/**  
 * @className: ModelScanner 
 * @projectName: snkConsole
 * @Description: 扫描注册Model类 
 * @author: liguanghao 
 * @date:2015年11月27日 下午10:01:16 
 * @version: ver 1.0
 */  
public class ModelScanner {
	private static Logger logger = Logger.getLogger(ModelScanner.class);
	private Set<Class<?>> classSet;
	/**  
	 * @Description: 扫描注册model的起始
	 * @author: liguanghao 
	 * @version: 2015年11月27日 下午10:06:29
	 * @param arp 
	 * @param packName 扫描的包名
	 */  
	@SuppressWarnings("unchecked")
	public void start(ActiveRecordPlugin arp,String packName){
		if(StringUtils.isBlank(packName)){
			logger.warn("没有找到需要扫描的model包");
			return;
		}
		classSet = ClassUtil.getClasses(packName, false);
		if(classSet == null || classSet.isEmpty()){
			logger.warn("没有找到需要扫描的model");
			return;
		}
		for(Class<?> item:classSet){
			arp.addMapping(item.getSimpleName().replace("Model", "").toLowerCase(), item.getSimpleName().replace("Model", "_ID").toUpperCase(), (Class<? extends Model<?>>)item);
			logger.info(item.getSimpleName().replace("Model", "").toLowerCase() + "已注册");
		}
	}
	
}
