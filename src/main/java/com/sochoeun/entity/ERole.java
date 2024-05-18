package com.sochoeun.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public enum ERole {
  ROLE_USER,
  ROLE_MODERATOR,
  ROLE_ADMIN
  ;

  public Set<SimpleGrantedAuthority> authorities(){
    SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+ this.name());
    System.out.println(role);
    return null;
  }
}
