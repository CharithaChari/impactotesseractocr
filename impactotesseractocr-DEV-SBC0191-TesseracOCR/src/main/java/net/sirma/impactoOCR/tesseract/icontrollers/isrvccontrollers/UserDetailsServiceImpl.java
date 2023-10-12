/*
      Good Faith Statement & Confidentiality : The below code is part of IMPACTO Suite of products . 
      Sirma Business Consulting India reserves all rights to this code . No part of this code should 
      be copied, stored or transmitted in any form for whatsoever reason without prior written consent 
      of Sirma Business Consulting (India).Employees or developers who have access to this code shall 
      take all reasonable precautions to protect the source code and documentation, and preserve its
      confidential, proprietary and trade secret status in perpetuity.Any breach of the obligations 
      to protect confidentiality of IMPACTO may cause immediate and irreparable harm to Sirma Business 
      Consulting, which cannot be adequately compensated by monetary damages. Accordingly, any breach 
      or threatened breach of confidentiality shall entitle Sirma Business Consulting to seek preliminary
      and permanent injunctive relief in addition to such remedies as may otherwise be available.
 
      //But by the grace of God We are what we are, and his grace to us was not without effect. No, 
      //We worked harder than all of them--yet not We, but the grace of God that was with us.
      ----------------------------------------------------------------------------------------------
      |Version No  | Changed by | Date         | Change Tag  | Changes Done
      ----------------------------------------------------------------------------------------------
      |0.1 Beta    | Nye 		| Sep 4, 2018  | #00000001   | Initial writing
	  |0.1 Beta    | MAQ 		| Feb 6, 2019  | #MAQ00001   | Added active in filter
      ----------------------------------------------------------------------------------------------
      
*/
// #00000001 Begins
package net.sirma.impactoOCR.tesseract.icontrollers.isrvccontrollers;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import net.sirma.impactoOCR.tesseract.iconfig.PropLoader;
import net.sirma.impactoOCR.tesseract.springsecurity.SpringSecurityUser;
@Component
@Service(value = "userDetailsService")
@ComponentScan("net.sirma.impactoOCR.tesseract")
public class UserDetailsServiceImpl implements UserDetailsService {

    @SuppressWarnings("unused")
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //*******************REQ*FOR*MOST*CONTROLLER*OPERS***********************//
//        DBController db$Ctrl = new DBController();
//        IResManipulator i$ResM = new IResManipulator();
//        Ioutils I$utils = new Ioutils();
        final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class); //Nye- Change Class Name always
        
        //**********************************************************************//
    	
        // #MAQ00001 start
    	JsonObject filter = new JsonObject();
    	filter.addProperty("userId", username);
    	filter.addProperty("active", "A");
//    	JsonObject jUser = db$Ctrl.db$GetRow("ICOR_M_USER_PRF",filter);
    	JsonObject jUser = new JsonObject();
        // #MAQ00001 end
    	
        if (jUser == null) {
            throw new UsernameNotFoundException(String.format("No appUser found with username '%s'.", username));
        } else {
            return new SpringSecurityUser(
            		jUser.get("userId").getAsString(),
            		jUser.get("userPwd").getAsString(),
            		PropLoader.env.getProperty("impacto.db.type"),
                    null,
                    null);
        }
    }
}

