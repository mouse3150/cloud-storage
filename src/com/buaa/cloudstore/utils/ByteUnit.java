package com.buaa.cloudstore.utils;


public class ByteUnit {

	//字节
	public static final String B = "BYTE";
	//千字节
	public static final String K = "KB";
	//兆字节
	public static final String M = "MB";
	//吉字节
	public static final String G = "GB";
	//千吉字节
	public static final String T = "TB";
	public static final String P = "PB";
	public static final String E = "EB";
	public static final String Z = "ZB";
	public static final String Y = "YB";
	public static final String N = "NB";
	public static final String D = "DB";
	
	public static final int UNIT = 1024;
	
	//字节
	private String bytes = "0";
	//千字节
	private String kiloByte = "0";
	//兆字节
	private String megaByte = "0";
	//吉字节
	private String gigaByte = "0";
	//太字节
	private String teraByte = "0";
	//拍字节
	private String petaByte = "0";
	private String exaByte = "0";
	private String zettaByte = "0";
	private String yottaByte = "0";
	private String nonaByte = "0";
	private String doggaByte = "0";
	
	public ByteUnit(double num,String unit){
		double bt = 0d;
		double kb = 0d;
		double mb = 0d;
		double gb = 0d;
		double tb = 0d;
		double pb = 0d;
		double eb = 0d;
		double zb = 0d;
		double yb = 0d;
		double nb = 0d;
		double db = 0d;
		
		if(0<num&&unit!=null&&"DB".equals(unit)){
			db = num;
			nb = db*ByteUnit.UNIT;
			yb = nb*ByteUnit.UNIT;
			zb = yb*ByteUnit.UNIT;
			eb = zb*ByteUnit.UNIT;
			pb = eb*ByteUnit.UNIT;
			tb = pb*ByteUnit.UNIT;
			gb = tb*ByteUnit.UNIT;
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"NB".equals(unit)){
			nb = num;
			
			db = nb/ByteUnit.UNIT;
			
			yb = nb*ByteUnit.UNIT;
			zb = yb*ByteUnit.UNIT;
			eb = zb*ByteUnit.UNIT;
			pb = eb*ByteUnit.UNIT;
			tb = pb*ByteUnit.UNIT;
			gb = tb*ByteUnit.UNIT;
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"YB".equals(unit)){
			yb = num;
			
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			zb = yb*ByteUnit.UNIT;
			eb = zb*ByteUnit.UNIT;
			pb = eb*ByteUnit.UNIT;
			tb = pb*ByteUnit.UNIT;
			gb = tb*ByteUnit.UNIT;
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"ZB".equals(unit)){
			zb = num;
			
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			eb = zb*ByteUnit.UNIT;
			pb = eb*ByteUnit.UNIT;
			tb = pb*ByteUnit.UNIT;
			gb = tb*ByteUnit.UNIT;
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"EB".equals(unit)){
			eb = num;
			
			zb = eb/ByteUnit.UNIT;
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			pb = eb*ByteUnit.UNIT;
			tb = pb*ByteUnit.UNIT;
			gb = tb*ByteUnit.UNIT;
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"PB".equals(unit)){
			pb = num;
			
			eb = pb/ByteUnit.UNIT;
			zb = eb/ByteUnit.UNIT;
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			tb = pb*ByteUnit.UNIT;
			gb = tb*ByteUnit.UNIT;
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"TB".equals(unit)){
			tb = num;
			
			pb = tb/ByteUnit.UNIT;
			eb = pb/ByteUnit.UNIT;
			zb = eb/ByteUnit.UNIT;
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			gb = tb*ByteUnit.UNIT;
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"GB".equals(unit)){
			gb = num;
			
			tb = gb/ByteUnit.UNIT;
			pb = tb/ByteUnit.UNIT;
			eb = pb/ByteUnit.UNIT;
			zb = eb/ByteUnit.UNIT;
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			mb = gb*ByteUnit.UNIT;
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"MB".equals(unit)){
			mb = num;
			
			gb = mb/ByteUnit.UNIT;
			tb = gb/ByteUnit.UNIT;
			pb = tb/ByteUnit.UNIT;
			eb = pb/ByteUnit.UNIT;
			zb = eb/ByteUnit.UNIT;
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			kb = mb*ByteUnit.UNIT;
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"KB".equals(unit)){
			kb = num;
			
			mb = kb/ByteUnit.UNIT;
			gb = mb/ByteUnit.UNIT;
			tb = gb/ByteUnit.UNIT;
			pb = tb/ByteUnit.UNIT;
			eb = pb/ByteUnit.UNIT;
			zb = eb/ByteUnit.UNIT;
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
			
			bt = kb*ByteUnit.UNIT;
		}else if(num!=0&&unit!=null&&"BYTE".equals(unit)){
			bt = num;
			
			kb = bt/ByteUnit.UNIT;
			mb = kb/ByteUnit.UNIT;
			gb = mb/ByteUnit.UNIT;
			tb = gb/ByteUnit.UNIT;
			pb = tb/ByteUnit.UNIT;
			eb = pb/ByteUnit.UNIT;
			zb = eb/ByteUnit.UNIT;
			yb = zb/ByteUnit.UNIT;
			nb = yb/ByteUnit.UNIT;
			db = nb/ByteUnit.UNIT;
		}
		
		if(bt!=0){
			String sValue = String.valueOf(bt);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setBytes(sValue);
			}else{
				this.setBytes(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setBytes(String.valueOf(bt));
		}
		
		if(kb!=0){
			String sValue = String.valueOf(kb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setKiloByte(sValue);
			}else{
				this.setKiloByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setKiloByte(String.valueOf(kb));
		}
		
		if(mb!=0){
			String sValue = String.valueOf(mb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setMegaByte(sValue);
			}else{
				this.setMegaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setMegaByte(String.valueOf(mb));
		}
		
		if(gb!=0){
			String sValue = String.valueOf(gb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setGigaByte(sValue);
			}else{
				this.setGigaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setGigaByte(String.valueOf(gb));
		}
		
		if(tb!=0){
			String sValue = String.valueOf(tb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setTeraByte(sValue);
			}else{
				this.setTeraByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setTeraByte(String.valueOf(tb));
		}
		
		if(pb!=0){
			String sValue = String.valueOf(pb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setPetaByte(sValue);
			}else{
				this.setPetaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setPetaByte(String.valueOf(pb));
		}
		
		if(eb!=0){
			String sValue = String.valueOf(eb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setExaByte(sValue);
			}else{
				this.setExaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setExaByte(String.valueOf(eb));
		}
		
		if(zb!=0){
			String sValue = String.valueOf(zb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setZettaByte(sValue);
			}else{
				this.setZettaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setZettaByte(String.valueOf(zb));
		}
		
		if(yb!=0){
			String sValue = String.valueOf(yb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setYottaByte(sValue);
			}else{
				this.setYottaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setYottaByte(String.valueOf(yb));
		}
		
		if(nb!=0){
			String sValue = String.valueOf(nb);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setNonaByte(sValue);
			}else{
				this.setNonaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setNonaByte(String.valueOf(nb));
		}
		
		if(db!=0){
			String sValue = String.valueOf(db);
			if(!"0".equals(sValue.substring(sValue.indexOf(".")+1))){
				this.setDoggaByte(sValue);
			}else{
				this.setDoggaByte(sValue.substring(0,sValue.indexOf(".")));
			}
		}else{
			this.setDoggaByte(String.valueOf(db));
		}
		
	}
	
	public String getBytes() {
		return bytes;
	}

	private void setBytes(String bytes) {
		this.bytes = bytes;
	}

	public String getKiloByte() {
		return kiloByte;
	}

	private void setKiloByte(String kiloByte) {
		this.kiloByte = kiloByte;
	}

	public String getMegaByte() {
		return megaByte;
	}

	private void setMegaByte(String megaByte) {
		this.megaByte = megaByte;
	}

	public String getGigaByte() {
		return gigaByte;
	}

	private void setGigaByte(String gigaByte) {
		this.gigaByte = gigaByte;
	}

	public String getTeraByte() {
		return teraByte;
	}

	private void setTeraByte(String teraByte) {
		this.teraByte = teraByte;
	}

	public String getPetaByte() {
		return petaByte;
	}

	private void setPetaByte(String petaByte) {
		this.petaByte = petaByte;
	}

	public String getExaByte() {
		return exaByte;
	}

	private void setExaByte(String exaByte) {
		this.exaByte = exaByte;
	}

	public String getZettaByte() {
		return zettaByte;
	}

	private void setZettaByte(String zettaByte) {
		this.zettaByte = zettaByte;
	}

	public String getYottaByte() {
		return yottaByte;
	}

	private void setYottaByte(String yottaByte) {
		this.yottaByte = yottaByte;
	}

	public String getNonaByte() {
		return nonaByte;
	}

	private void setNonaByte(String nonaByte) {
		this.nonaByte = nonaByte;
	}

	public String getDoggaByte() {
		return doggaByte;
	}

	private void setDoggaByte(String doggaByte) {
		this.doggaByte = doggaByte;
	}

	public static void main(String[] args){
		int k = 3000;
		ByteUnit bu = new ByteUnit(k,ByteUnit.K);
		
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getBytes()+" "+ByteUnit.B);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getMegaByte()+" "+ByteUnit.M);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getGigaByte()+" "+ByteUnit.G);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getTeraByte()+" "+ByteUnit.T);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getPetaByte()+" "+ByteUnit.P);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getExaByte()+" "+ByteUnit.E);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getZettaByte()+" "+ByteUnit.Z);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getYottaByte()+" "+ByteUnit.Y);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getNonaByte()+" "+ByteUnit.N);
		System.out.println(k+" "+ByteUnit.K+" = "+bu.getDoggaByte()+" "+ByteUnit.D);
	}

}
