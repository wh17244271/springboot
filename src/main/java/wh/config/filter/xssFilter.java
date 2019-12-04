package wh.config.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class xssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest requests, ServletResponse responses, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) requests;
		HttpServletResponse response = (HttpServletResponse) responses;
		
		
		//给parameter过滤
		RestHttpServletRequestParameter httpServletRequestParameter = setParameter(request);
		chain.doFilter(httpServletRequestParameter, response);

	}

	@Override
	public void destroy() {

	}
	
	/**
	 * 给parameter中的参数重新赋值
	 * @param request
	 * @return
	 */
	private RestHttpServletRequestParameter setParameter(HttpServletRequest request) {
		RestHttpServletRequestParameter amHttpServletRequestWrapper = new RestHttpServletRequestParameter(request,request.getParameterMap());
		Map<String, String[]> parameterMap = amHttpServletRequestWrapper.getParameterMap();
		for (Map.Entry<String,  String[]> entry : parameterMap.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			values = cleanXssStrings(values);
			amHttpServletRequestWrapper.setParameter(key, values);
		}
		return amHttpServletRequestWrapper;
	}
	
	/**
	 * 变量 getParameterMap();中的值String[]
	 * @param values
	 * @return
	 */
	private String[] cleanXssStrings(String[] values) {
		
		if(values.length>0) {
			String[] newStr = new String[values.length];
			int i=0;
			for(String value:values) {
				value = cleanXssString(value);
				newStr[i]=value;
				i++;
			}
			return newStr;
		}
		return values;
		
	}
	/**
	 * 对getParameter();中的值进行真正顾虑；
	 * @param value
	 * @return
	 */
	private String cleanXssString(String value) {
		value = value.replaceAll("(?i)script", "替换成功");
		return value;
		
	}

}
