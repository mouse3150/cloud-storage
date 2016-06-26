package com.buaa.cloudstore.utils;

public class PrettyByteUnit {

	public enum Unit {
		BYTE("Byte"), KILOBYTE("Kilo Byte"), MEGABYTE("Mega Byte"), GIGABYTE("Giga Byte"), 
		TERABYTE("Tera Byte"), PETABYTE("Peta Byte"), EXABYTE("Exa Byte"), ZETTABYTE("Zetta Byte");
		private String unit;
		
		private Unit(String unit) {
			this.unit = unit;
		}
		
		public String getUnit() {
			return unit;
		}
		
	}

	private Unit unit;
	private double value;

	public static final int UNIT = 1024;
	public static final double KILO = 1024d;
	public static final double ONE = 1d;

	// 字节
	private String bytes = "0";
	// 千字节
	private String kiloByte = "0";
	// 兆字节
	private String megaByte = "0";
	// 吉字节
	private String gigaByte = "0";
	// 太字节
	private String teraByte = "0";
	// 拍字节
	private String petaByte = "0";
	private String exaByte = "0";
	private String zettaByte = "0";

	public PrettyByteUnit(double num, Unit unit) {
		this.value = num;
		this.unit = unit;
		
		double bt = 0d;
		double kb = 0d;
		double mb = 0d;
		double gb = 0d;
		double tb = 0d;
		double pb = 0d;
		double eb = 0d;
		double zb = 0d;

		if (num != 0 && Unit.ZETTABYTE == unit) {
			zb = num;

			eb = zb * UNIT;
			pb = eb * UNIT;
			tb = pb * UNIT;
			gb = tb * UNIT;
			mb = gb * UNIT;
			kb = mb * UNIT;
			bt = kb * UNIT;
		} else if (num != 0 && Unit.EXABYTE == unit) {
			eb = num;

			zb = eb / UNIT;

			pb = eb * UNIT;
			tb = pb * UNIT;
			gb = tb * UNIT;
			mb = gb * UNIT;
			kb = mb * UNIT;
			bt = kb * UNIT;
		} else if (num != 0 && Unit.PETABYTE == unit) {
			pb = num;

			eb = pb / UNIT;
			zb = eb / UNIT;

			tb = pb * UNIT;
			gb = tb * UNIT;
			mb = gb * UNIT;
			kb = mb * UNIT;
			bt = kb * UNIT;
		} else if (num != 0 && Unit.TERABYTE == unit) {
			tb = num;

			pb = tb / UNIT;
			eb = pb / UNIT;
			zb = eb / UNIT;

			gb = tb * UNIT;
			mb = gb * UNIT;
			kb = mb * UNIT;
			bt = kb * UNIT;
		} else if (num != 0 && Unit.GIGABYTE == unit) {
			gb = num;

			tb = gb / UNIT;
			pb = tb / UNIT;
			eb = pb / UNIT;
			zb = eb / UNIT;

			mb = gb * UNIT;
			kb = mb * UNIT;
			bt = kb * UNIT;
		} else if (num != 0 && Unit.MEGABYTE == unit) {
			mb = num;

			gb = mb / UNIT;
			tb = gb / UNIT;
			pb = tb / UNIT;
			eb = pb / UNIT;
			zb = eb / UNIT;

			kb = mb * UNIT;
			bt = kb * UNIT;
		} else if (num != 0 && Unit.KILOBYTE == unit) {
			kb = num;

			mb = kb / UNIT;
			gb = mb / UNIT;
			tb = gb / UNIT;
			pb = tb / UNIT;
			eb = pb / UNIT;
			zb = eb / UNIT;

			bt = kb * UNIT;
		} else if (num != 0 && Unit.BYTE == unit) {
			bt = num;

			kb = bt / UNIT;
			mb = kb / UNIT;
			gb = mb / UNIT;
			tb = gb / UNIT;
			pb = tb / UNIT;
			eb = pb / UNIT;
			zb = eb / UNIT;
		}

		if (bt != 0) {
			if(bt > ONE && bt < KILO) {
				this.value = bt;
				this.unit = Unit.BYTE;
			}
			
			String sValue = String.valueOf(bt);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setBytes(sValue);
			} else {
				this.setBytes(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setBytes(String.valueOf(bt));
		}

		if (kb != 0) {
			if(kb >= ONE && kb < KILO) {
				this.value = kb;
				this.unit = Unit.KILOBYTE;
			}
			
			String sValue = String.valueOf(kb);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setKiloByte(sValue);
			} else {
				this.setKiloByte(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setKiloByte(String.valueOf(kb));
		}

		if (mb != 0) {
			if(mb >= ONE && mb < KILO) {
				this.value = mb;
				this.unit = Unit.MEGABYTE;
			}
			String sValue = String.valueOf(mb);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setMegaByte(sValue);
			} else {
				this.setMegaByte(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setMegaByte(String.valueOf(mb));
		}

		if (gb != 0) {
			if(gb >= ONE && gb < KILO) {
				this.value = gb;
				this.unit = Unit.GIGABYTE;
			}
			String sValue = String.valueOf(gb);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setGigaByte(sValue);
			} else {
				this.setGigaByte(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setGigaByte(String.valueOf(gb));
		}

		if (tb != 0) {
			if(tb >= ONE && tb < KILO) {
				this.value = tb;
				this.unit = Unit.TERABYTE;
			}
			String sValue = String.valueOf(tb);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setTeraByte(sValue);
			} else {
				this.setTeraByte(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setTeraByte(String.valueOf(tb));
		}

		if (pb != 0) {
			if(pb >= ONE && pb < KILO) {
				this.value = pb;
				this.unit = Unit.PETABYTE;
			}
			String sValue = String.valueOf(pb);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setPetaByte(sValue);
			} else {
				this.setPetaByte(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setPetaByte(String.valueOf(pb));
		}

		if (eb != 0) {
			if(eb >= ONE && eb < KILO) {
				this.value = eb;
				this.unit = Unit.EXABYTE;
			}
			String sValue = String.valueOf(eb);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setExaByte(sValue);
			} else {
				this.setExaByte(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setExaByte(String.valueOf(eb));
		}

		if (zb != 0) {
			if(zb >= ONE && zb < KILO) {
				this.value = zb;
				this.unit = Unit.ZETTABYTE;
			}
			String sValue = String.valueOf(zb);
			if (!"0".equals(sValue.substring(sValue.indexOf(".") + 1))) {
				this.setZettaByte(sValue);
			} else {
				this.setZettaByte(sValue.substring(0, sValue.indexOf(".")));
			}
		} else {
			this.setZettaByte(String.valueOf(zb));
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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public static void main(String[] args) {
		int k = 1024;
		PrettyByteUnit bu = new PrettyByteUnit(k, Unit.KILOBYTE);
		System.out.println(bu.getBytes());
		
		System.out.println(Unit.KILOBYTE.getUnit());
		
		System.out.println(bu.getValue() + " " + bu.getUnit());
		
		 double g = 2000.2d;
		 PrettyByteUnit gb = new PrettyByteUnit(g, Unit.GIGABYTE);
		 System.out.println(gb.getValue() + " " + gb.getUnit().getUnit());
	}

}
