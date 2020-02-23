import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

class PostFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(PostFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		logger.info("This is a dynamic load post filter: Receive response");
		response.getOutputStream().print(", I am xuhongbao");
		response.flushBuffer();
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 2000;
	}
}