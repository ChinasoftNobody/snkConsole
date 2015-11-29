package snk.common.uitls;

/**  
 * @className: StringUtils 
 * @projectName: snkConsole
 * @Description: 处理字符串的工具类
 * @author: liguanghao 
 * @date:2015年11月27日 下午10:09:21 
 * @version: ver 1.0
 */  
public class StringUtils {
	
	/**  
	 * @Description: 判断字符串是否为空或者""
	 * @author: liguanghao 
	 * @version: 2015年11月27日 下午10:10:38
	 * @param str
	 * @return
	 */  
	public static boolean isBlank(String str) {
		return str.equals("")||str == null;
	}
}
