package co.crazytech.ggqr.ui;

import java.math.BigInteger;

public class GGObject {
	private BigInteger id;
	private String name;
	private String code;
	private String dcode;
	
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ((this.getDcode()!=null)?(this.getDcode()+":"):"")+this.name+":"+this.code;
	}
	
}
