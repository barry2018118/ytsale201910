package com.yuntu.sale.chiefly.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description 景区销售主图Vo
 * @author snps
 * @date 2018年5月9日 下午2:42:43
 */
public class ScenicSaleColumnChartVo implements Comparable, Serializable {

	private static final long serialVersionUID = -5766594281872527052L;

	/**
	 * 景区Id
	 */
	private Long id;

	/**
	 * 景区名称
	 */
	private String name;

	/**
	 * 销售数量
	 */
	private Integer number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof ScenicSaleColumnChartVo) {
			ScenicSaleColumnChartVo obj = (ScenicSaleColumnChartVo) o;
            return obj.getNumber().compareTo(this.number);
        } else {
        	 throw new ClassCastException("不能转换为ScenicSaleColumnChartVo类型的对象...");
        }
	}

	public static void main(String[] args) {
		ScenicSaleColumnChartVo v1 = new ScenicSaleColumnChartVo();
		v1.setName("AAA");
		v1.setNumber(100);
		ScenicSaleColumnChartVo v2 = new ScenicSaleColumnChartVo();
		v2.setName("BBB");
		v2.setNumber(98);
		ScenicSaleColumnChartVo v3 = new ScenicSaleColumnChartVo();
		v3.setName("CCC");
		v3.setNumber(101);
		
		List<ScenicSaleColumnChartVo> lstVo = new ArrayList<ScenicSaleColumnChartVo>();
		lstVo.add(v1);
		lstVo.add(v2);
		lstVo.add(v3);

		Collections.sort(lstVo);
		for(ScenicSaleColumnChartVo vo : lstVo) {
			System.out.println(vo.getName());
		}
		
	}
	
}