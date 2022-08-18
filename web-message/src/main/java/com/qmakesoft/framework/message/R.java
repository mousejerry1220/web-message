package com.qmakesoft.framework.message;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class R implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public R() {}
	
	private R(int code,Object data ,Object message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	int code;
	
	Object data;
	
	Object message;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	transient boolean onlyData = false;

	public static R successOnlyData(Object data) {
		R r = new R(0,data,null);
		r.onlyData = true;
		return r;
	}
	
	public static R success(Object data) {
		
		return new R(0,data,null);
	}
	
	public static R  success() {
		return new R (0,null,null);
	}
	
	public static R error(int code,Object message) {
		return new R (code,null,message);
	}
	
}
