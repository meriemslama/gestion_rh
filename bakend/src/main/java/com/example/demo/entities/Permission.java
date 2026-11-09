package com.example.demo.entities;

import org.springframework.security.core.GrantedAuthority;

public class Permission implements GrantedAuthority{
  private String authority;
	public Permission(String authority) {
	super();
	this.authority = authority;
}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
