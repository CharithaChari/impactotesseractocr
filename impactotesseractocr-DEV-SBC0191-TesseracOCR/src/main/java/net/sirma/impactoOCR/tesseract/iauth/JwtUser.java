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
      ----------------------------------------------------------------------------------------------
      
*/
// #00000001 Begins
package net.sirma.impactoOCR.tesseract.iauth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * All user information handled by the JWT token
 */
public class JwtUser implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3129597353854210211L;

	private String username;

    private Collection<? extends GrantedAuthority> authorities;

    private Date creationDate;

    public JwtUser(String username, Date creationDate) {
        this(username, creationDate, Collections.emptyList());
    }

    public JwtUser(String username, Date creationDate, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.setCreationDate(creationDate);
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        // no password inside JWT token.
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // A token is never locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // == token expiration
        return true;
    }

    @Override
    public boolean isEnabled() {
        // always enabled in JWT case.
        return true;
    }

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
//#00000001 Ends