
package com.java.config;

import com.java.domain.Access;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.java.service.AccessService;
import com.java.service.UsersService;
import org.springframework.security.core.GrantedAuthority;


@Component("CustomSecurityService")
public class CustomSecurityService {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private UsersService userService;
 
    @Autowired
    private AccessService accessService;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    public boolean hasPermission(Authentication authentication, HttpServletRequest request){
        String fullURI = request.getRequestURI();
        logger.info("USER:"+authentication.getName()+" URI:"+fullURI+" METHOD:" + request.getMethod());
        for(GrantedAuthority role:authentication.getAuthorities()){
            for(Access access:accessService.getByUserRoles(role.toString())){
               if (access.getPath().equals(fullURI) &&
                      (access.getType().equals(request.getMethod()) || access.getType().equals("*"))){
                   return true;
               }
            }
        }
        return false;
    }
}
