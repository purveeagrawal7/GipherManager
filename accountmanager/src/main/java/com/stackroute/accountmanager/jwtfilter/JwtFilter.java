package com.stackroute.accountmanager.jwtfilter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.models.HttpMethod;
public class JwtFilter extends GenericFilterBean{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
    	HttpServletResponse httpResponse = (HttpServletResponse) response;
    	
    	httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
    	httpResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,OPTIONS");
    	httpResponse.setHeader("Access-Control-Allow-Credential", "true");
    	httpResponse.setHeader("Access-Control-Allow-Headers", "*");
    	String authheader=httpRequest.getHeader("Authorization");
    	
    	System.out.println(authheader);
    	
    	if(httpRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
    	{
             chain.doFilter(httpRequest, httpResponse);
    	}
        else
        {
    	        if((authheader==null) || (!authheader.startsWith("Bearer")))
    			  throw new ServletException("JWT Token is missing");
    			String mytoken=authheader.substring(7);
    			
    			try
    			{
    				JwtParser jparser=Jwts.parser().setSigningKey("ibmfsd");
    			    Jwt jwtobj=jparser.parse(mytoken);
    			    Claims claim=(Claims)jwtobj.getBody();
    			}
    			catch(SignatureException e)
    			{
    				throw new ServletException("signature mismatch");
    			}
    				
    			catch(MalformedJwtException e)
    			{
    				throw new ServletException("malformed mismatch");
    			}
    			
    			chain.doFilter(httpRequest, httpResponse);
    			}
        }
}





