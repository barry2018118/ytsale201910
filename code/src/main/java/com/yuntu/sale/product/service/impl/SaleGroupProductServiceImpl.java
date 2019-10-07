package com.yuntu.sale.product.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.product.dao.EnterpriseProductDao;
import com.yuntu.sale.product.dao.SaleGroupDao;
import com.yuntu.sale.product.dao.SaleGroupEnterpriseDao;
import com.yuntu.sale.product.dao.SaleGroupLogDao;
import com.yuntu.sale.product.dao.SaleGroupProductDao;
import com.yuntu.sale.product.dao.SupplierProductDao;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.po.SaleGroupEnterpriseVo;
import com.yuntu.sale.product.po.SaleGroupLog;
import com.yuntu.sale.product.po.SaleGroupProduct;
import com.yuntu.sale.product.po.SaleGroupProductVo;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SaleGroupProductService;
import com.yuntu.sale.user.dao.InfoEnterpriseDao;
import com.yuntu.sale.user.dao.InfoUserDao;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 分销组商品Service
 * @author zy
 * @version 2018-04-02
 */
@Service("saleGroupProductServiceImpl")
public class SaleGroupProductServiceImpl implements SaleGroupProductService {

    @Resource
    private EnterpriseProductDao enterpriseProductDao;

    @Resource
    private InfoEnterpriseDao infoEnterpriseDao;

    @Resource
    private InfoUserDao infoUserDao;

    @Resource
    private SaleGroupProductDao dao;

    @Resource
    private SaleGroupDao saleGroupDao;

    @Resource
    private SaleGroupLogDao saleGroupLogDao;

    @Resource
    private SupplierProductDao supplierProductDao;

    @Resource
    private SaleGroupEnterpriseDao saleGroupEnterpriseDao;

    @Override
    public SaleGroupProduct getById(Long id) {
        return dao.queryById(id);
    }

    @Override
    public void save(Long id, String[] box, Long userId, Long myEnterpriseId) {
        //定义
        List<SaleGroupProduct> saleGroupProductList ;       //商品是否分配
        SaleGroupProduct saleGroupProduct;                  //新 分销组 - 商品 分配记录
        List<SaleGroupEnterpriseVo> supplierEnterpriseList; //检索企业列表
        Iterator<SaleGroupEnterpriseVo> iterator ;          //企业列表迭代
        SaleGroupEnterpriseVo saleGroupEnterprise;          //迭代元素
        Long enterpriseId;                                  //企业id
        List<EnterpriseProduct> getenterpriseProduct;       //检索 商品 - 企业  关系 是否存在
        EnterpriseProduct enterpriseProduct;                //商品 - 企业 关系对象
        Long groupId = id;                                  //分销组id
        SupplierProduct supplierProduct;                    //商品信息
        InfoEnterprise infoEnterprise;                      //企业信息
        EnterpriseProduct trajectory;                       //轨迹
        EnterpriseProduct  myEnterpriseProduct;             //当前商户与商品关系
        String ids = "";
        //上级企业id
        Long parentId = infoEnterpriseDao.queryById(myEnterpriseId).getParentId();
        //分销组
        SaleGroup enpity = saleGroupDao.queryById(id);
        //创建分销组企业id
        InfoUser infoUser= infoUserDao.queryById(enpity.getCreateId());
        long createGroupId = infoUser.getEnterpriseId();

        if(box!=null){
            // 循环选中的商品 id
            for(String boId : box) {
                saleGroupProductList = dao.queryOne(id,createGroupId,Long.parseLong(boId));
                supplierProduct = supplierProductDao.queryById(Long.parseLong(boId));
                myEnterpriseProduct = enterpriseProductDao.queryOne(Long.parseLong(boId),null,myEnterpriseId);
                if(BaseUtil.isEmpty(saleGroupProductList)) {
                    saleGroupProduct = new SaleGroupProduct();
                    saleGroupProduct.setGroupId(id);
                    // 增加产品组分销轨迹	at 2018-06-01 by snps
                    if(myEnterpriseProduct.getGroupTracks() == null){
                    	saleGroupProduct.setGroupTracks(groupId + "");//分销组 轨迹
                    }else{
                    	saleGroupProduct.setGroupTracks(myEnterpriseProduct.getGroupTracks() + ","+ groupId);//分销组 轨迹
                    }
                    saleGroupProduct.setCreateEnterpriseId(createGroupId);
                    saleGroupProduct.setProductId(Long.parseLong(boId));
                    saleGroupProduct.setCreateId(userId);
                    saleGroupProduct.setCreateTime(new Date());
                    saleGroupProduct.setUpdateId(userId);
                    saleGroupProduct.setUpdateTime(new Date());
                    // 设置商品组中商品的分销状态为：未分销		at 2018-05-30 by snps
                    saleGroupProduct.setIsDelete(0);
                    dao.insert(saleGroupProduct);
                    // 分销组 设置 商品数
                    Integer num = enpity.getProductNumber();
                    num += 1;
                    enpity.setProductNumber(num);
                    enpity.setUpdateId(userId);
                    enpity.setUpdateTime(new Date());
                    saleGroupDao.update(enpity);
                    //分销商 - 商品 关系
                    supplierEnterpriseList =  saleGroupEnterpriseDao.getEnterPriseList(groupId,createGroupId);
                    if(!BaseUtil.isEmpty(supplierEnterpriseList)) {
                        iterator = supplierEnterpriseList.iterator();
                        while (iterator.hasNext()) {
                            saleGroupEnterprise = iterator.next();
                            enterpriseId = saleGroupEnterprise.getChildEnterpriseId();
                            System.out.println("分销组-分销商品-遍历-分销组-分销商:"+enterpriseId);
                            //检索 商品 - 企业  关系 是否存在
                                getenterpriseProduct = enterpriseProductDao.getSearch(Long.parseLong(boId), enterpriseId, groupId);//商品id,下级企业id,分销组id
                            if (BaseUtil.isEmpty(getenterpriseProduct)) {
                                //不存在,新建
                                enterpriseProduct = new EnterpriseProduct();
                                enterpriseProduct.setProductId(Long.parseLong(boId));
                                enterpriseProduct.setParentId(myEnterpriseId);
                                enterpriseProduct.setChildId(enterpriseId);
                                enterpriseProduct.setGroupId(id);
                                System.out.println("分销组-分销商品-创建企业 id:"+supplierProduct.getEnterpriseId());

                                enterpriseProduct.setIsSupplier(0);
                                if(myEnterpriseProduct.getGroupTracks() == null){
                                    enterpriseProduct.setGroupTracks(groupId + "");//分销组 轨迹
                                }else{
                                    enterpriseProduct.setGroupTracks(myEnterpriseProduct.getGroupTracks() + ","+ groupId);//分销组 轨迹
                                }
                                if(myEnterpriseProduct.getUserTracks() == null) {
                                    enterpriseProduct.setUserTracks(Long.parseLong(boId) + ""); //用户分销轨迹
                                }else{
                                    enterpriseProduct.setUserTracks(myEnterpriseProduct.getUserTracks() + "," + enterpriseId); //用户分销轨迹
                                }

                                enterpriseProduct.setEnterpriseId(enterpriseProduct.getChildId());
                                enterpriseProduct.setCreateId(userId);
                                enterpriseProduct.setCreateTime(new Date());
                                enterpriseProduct.setUpdateId(userId);
                                enterpriseProduct.setUpdateTime(new Date());
                                enterpriseProduct.setIsDistribution(0);
                                enterpriseProductDao.save(enterpriseProduct);
                                ids = ids + enterpriseId + ",";
                                enterpriseProduct = null;
                            }else{
                                ids = ids + enterpriseId + ",";
                            }
                            saleGroupEnterprise = null;
                            enterpriseId = null;
                            getenterpriseProduct = null;
                            trajectory = null;
                        }
                        iterator = null;
                    }
                    //分销组操作日志
                    SaleGroupLog saleGroupLog = new SaleGroupLog();
                    saleGroupLog.setOperateFlag(21);//11：添加分销商、10：删除分销商、21：添加商品、20：删除商品
                    saleGroupLog.setUserIds(ids);
                    saleGroupLog.setProductIds(Long.parseLong(boId)+"");
                    saleGroupLog.setCreateId(userId);
                    saleGroupLog.setCreateTime(new Date());
                    saleGroupLogDao.insert(saleGroupLog);
                    //释放
                    saleGroupProduct = null;
                    supplierEnterpriseList = null;
                    saleGroupLog = null;
                    ids = "";
                }
                saleGroupProductList = null;
                supplierProduct = null;
            }
        }
    }

    @Override
    public void update(SaleGroupProduct entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id, Long enterpriseId, SaleGroup entity, SaleGroupLog saleGroupLog) {
        SaleGroupProduct saleGroupProduct =  dao.queryById(id);
        //分销组 - 分销商 - 关系
        dao.delete(id);
        //分销组设置
        saleGroupDao.update(entity);
        //分销组 操作日志
        saleGroupLogDao.insert(saleGroupLog);
        //分销商 - 商品 关系
        List<EnterpriseProduct> getenterpriseProduct = enterpriseProductDao.getDelSearch(saleGroupProduct.getProductId(),null,null,saleGroupProduct.getGroupId());
        Iterator<EnterpriseProduct> iter = getenterpriseProduct.iterator();
        while(iter.hasNext()) {
            enterpriseProductDao.delete(iter.next().getId());
        }
    }

    @Override
    public Page<SaleGroupProductVo> getList(Page<SaleGroupProductVo> pager, String no, String name, Long groupId, Long createGroupId) {
        // 启用语句拦截，进行分页设置
        PageHelper.startPage(pager.getStart(), pager.getSize());
        // 查询数据
        List<SaleGroupProductVo> lstData = dao.querySaleGroupProduct(no,name,groupId,createGroupId);
        // 获取分页后所需信息
        PageInfo<SaleGroupProductVo> pageInfo = new PageInfo<SaleGroupProductVo>(lstData);
        pager.setTotal(pageInfo.getTotal());
        pager.setResult(lstData);
        return pager;
    }

    @Override
    public List<SaleGroupProductVo> getProductList(Long groupId, Long createGroupId) {
        return dao.getProductList(groupId,createGroupId);
    }

    @Override
    public List<SaleGroupProduct> getOne(Long groupId, Long createGroupId, Long productId) {
        return dao.queryOne(groupId,createGroupId,productId);
    }

	@Override
	public List<SaleGroupProduct> getIsExistProduct(Long groupId) {
		return dao.queryIsExistProduct(groupId) ;
	}

	@Override
	public void saveDescribution(Long groupId, Long sproductId) {
		// Step_1: 设置商品组中的商品的分销状态-开启分销
		dao.updateDescribution(groupId, sproductId);
		
		// Step_2: 设置用户商品的分销状态-开启分销
		enterpriseProductDao.updateDescribution(groupId, sproductId);
	}

	@Override
	public void saveStopDescribution(Long groupId, Long sproductId) {
		// Step_1: 设置用户商品的分销状态-停止分销
		enterpriseProductDao.updateStopDescribution(String.valueOf(groupId), sproductId);
		
		// Step_2: 设置商品组中我的商品的分销状态-停止分销
		dao.updateStopDescribution(groupId, sproductId);
		
		// Step_3: 删除商品组中下级的分销状态
		dao.deleteChildDescribution(String.valueOf(groupId), sproductId);
	}

}