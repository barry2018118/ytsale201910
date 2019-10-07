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
import com.yuntu.sale.product.po.SaleGroupEnterprise;
import com.yuntu.sale.product.po.SaleGroupEnterpriseVo;
import com.yuntu.sale.product.po.SaleGroupLog;
import com.yuntu.sale.product.po.SaleGroupProductVo;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SaleGroupEnterpriseService;
import com.yuntu.sale.user.dao.InfoEnterpriseDao;
import com.yuntu.sale.user.dao.InfoUserDao;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 分销组分销商Service
 * @author zy
 * @version 2018-04-02
 */
@Service("saleGroupEnterpriseService")
public class SaleGroupEnterpriseServiceImpl implements SaleGroupEnterpriseService {

    @Resource
    private EnterpriseProductDao enterpriseProductDao;

    @Resource
    private InfoEnterpriseDao infoEnterpriseDao;

    @Resource
    private InfoUserDao infoUserDao;

    @Resource
    private SaleGroupEnterpriseDao dao;

    @Resource
    private SaleGroupDao saleGroupDao;

    @Resource
    private SaleGroupLogDao saleGroupLogDao;

    @Resource
    private SaleGroupProductDao saleGroupProductDao;

    @Resource
    private SupplierProductDao supplierProductDao;

    @Override
    public Page<SaleGroupEnterpriseVo> getList(Page<SaleGroupEnterpriseVo> pager, String name, Long groupId, Long createGroupId) {
        // 启用语句拦截，进行分页设置
        PageHelper.startPage(pager.getStart(), pager.getSize());

        // 查询数据
        List<SaleGroupEnterpriseVo> lstData = dao.querySaleGroupEnterprise(name,groupId,createGroupId);

        // 获取分页后所需信息
        PageInfo<SaleGroupEnterpriseVo> pageInfo = new PageInfo<SaleGroupEnterpriseVo>(lstData);
        pager.setTotal(pageInfo.getTotal());
        pager.setResult(lstData);
        return pager;
    }

    @Override
    public SaleGroupEnterprise getById(Long id) {
        return dao.queryById(id);
    }

    @Override
    public void save(Long id,String[] box,Long userId,Long myEnterpriseId){
        //定义
        EnterpriseProduct  myEnterpriseProduct;             //当前商户与商品关系
        List<SaleGroupEnterprise> saleGroupEnterpriseList ; //分销商是否分配
        SaleGroupEnterprise saleGroupEnterprise;            //新 分销组 - 分销商 分配记录
        List<SaleGroupProductVo> supplierProductList;       //检索商品列表
        Iterator<SaleGroupProductVo> iterator ;             //商品列表迭代
        SaleGroupProductVo saleGroupProductVo;              //迭代元素
        Long productId;                                     //商品id
        List<EnterpriseProduct> getenterpriseProduct;       //检索 商品 - 企业  关系 是否存在
        EnterpriseProduct enterpriseProduct;                //商品 - 企业 关系对象
        Long groupId = id;                                  //分销组id
        SupplierProduct supplierProduct;                    //商品信息
        EnterpriseProduct trajectory;                       //轨迹
        String ids = "";
        //上级企业id
        Long parentId = infoEnterpriseDao.queryById(myEnterpriseId).getParentId();
        //分销组
        SaleGroup enpity = saleGroupDao.queryById(id);
        //创建分销组企业id
        InfoUser infoUser=infoUserDao.queryById(enpity.getCreateId());
        long createGroupId = infoUser.getEnterpriseId();
        if(box!=null){
            //循环选中的分销商 id
            for(String boId : box) {
                saleGroupEnterpriseList = dao.queryOne(null,createGroupId,Long.parseLong(boId));
                if(BaseUtil.isEmpty(saleGroupEnterpriseList)) {
                    //分销企业尚未分配
                    saleGroupEnterprise = new SaleGroupEnterprise();
                    saleGroupEnterprise.setGroupId(id);
                    saleGroupEnterprise.setCreateEnterpriseId(createGroupId);
                    saleGroupEnterprise.setChildEnterpriseId(Long.parseLong(boId));
                    saleGroupEnterprise.setCreateId(userId);
                    saleGroupEnterprise.setCreateTime(new Date());
                    saleGroupEnterprise.setUpdateId(userId);
                    saleGroupEnterprise.setUpdateTime(new Date());
                    dao.insert(saleGroupEnterprise);
                    //分销组 设置 商品数
                    Integer num = enpity.getUserNumber();
                    num += 1;
                    enpity.setUserNumber(num);
                    enpity.setUpdateId(userId);
                    enpity.setUpdateTime(new Date());
                    saleGroupDao.update(enpity);
                    //分销商 - 商品 关系
                    supplierProductList =  saleGroupProductDao.getProductList(groupId,createGroupId);
                    if(!BaseUtil.isEmpty(supplierProductList)){
                        iterator = supplierProductList.iterator();
                        while(iterator.hasNext()){
                            saleGroupProductVo = iterator.next();
                            productId = saleGroupProductVo.getProductId();
                            myEnterpriseProduct = enterpriseProductDao.queryOne(productId,null,myEnterpriseId);
                            supplierProduct = supplierProductDao.queryById(productId);
                            //检索 商品 - 企业  关系 是否存在
                            getenterpriseProduct = enterpriseProductDao.getSearch(productId,Long.parseLong(boId),groupId);//商品id,上级企业id,下级企业id,分销组id
                            if(BaseUtil.isEmpty(getenterpriseProduct)) {
                                //不存在,新建
                                enterpriseProduct = new EnterpriseProduct();
                                enterpriseProduct.setProductId(productId);
                                enterpriseProduct.setParentId(myEnterpriseId);
                                enterpriseProduct.setChildId(Long.parseLong(boId));
                                enterpriseProduct.setGroupId(id);

                                enterpriseProduct.setIsSupplier(0);
                                if(myEnterpriseProduct.getGroupTracks() == null){
                                    enterpriseProduct.setGroupTracks(groupId + "");//分销组 轨迹
                                }else{
                                    enterpriseProduct.setGroupTracks(myEnterpriseProduct.getGroupTracks() + ","+ groupId);//分销组 轨迹
                                }
                                if(myEnterpriseProduct.getUserTracks() == null) {
                                    enterpriseProduct.setUserTracks(Long.parseLong(boId) + ""); //用户分销轨迹
                                }else{
                                    enterpriseProduct.setUserTracks(myEnterpriseProduct.getUserTracks() + "," + Long.parseLong(boId)); //用户分销轨迹
                                }
                                enterpriseProduct.setEnterpriseId(enterpriseProduct.getChildId());
                                enterpriseProduct.setCreateId(userId);
                                enterpriseProduct.setCreateTime(new Date());
                                enterpriseProduct.setUpdateId(userId);
                                enterpriseProduct.setUpdateTime(new Date());
                                enterpriseProductDao.save(enterpriseProduct);
                                ids = ids + productId + ",";
                                enterpriseProduct = null;
                            }else{
                                ids = ids + productId + ",";
                            }
                            saleGroupProductVo = null;
                            productId = null;
                            getenterpriseProduct = null;
                            trajectory = null;
                            supplierProduct = null;
                        }
                        iterator = null;
                    }
                    //分销组操作日志
                    SaleGroupLog saleGroupLog = new SaleGroupLog();
                    saleGroupLog.setOperateFlag(11);//11：添加分销商、10：删除分销商、21：添加商品、20：删除商品
                    saleGroupLog.setUserIds(Long.parseLong(boId)+"");// 影响路径
                    saleGroupLog.setProductIds(ids);
                    saleGroupLog.setCreateId(userId);
                    saleGroupLog.setCreateTime(new Date());
                    saleGroupLogDao.insert(saleGroupLog);

                    //释放
                    saleGroupEnterprise = null;
                    supplierProductList = null;
                    ids = "";
                }
                saleGroupEnterpriseList = null;
            }
        }

    }

    @Override
    public void update(SaleGroupEnterprise entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id,Long enterpriseId,SaleGroup entity,SaleGroupLog saleGroupLog) {
        SaleGroupEnterprise saleGroupEnterprise = dao.queryById(id);
        //分销组 - 分销商 - 关系
        dao.delete(id);
        //分销组设置
        saleGroupDao.update(entity);
        //分销组 操作日志
        saleGroupLogDao.insert(saleGroupLog);
        //分销商 - 商品 关系
        List<EnterpriseProduct> getenterpriseProduct = enterpriseProductDao.getDelSearch(null,enterpriseId, saleGroupEnterprise.getChildEnterpriseId(),saleGroupEnterprise.getGroupId());
        Iterator<EnterpriseProduct> iter = getenterpriseProduct.iterator();
        while(iter.hasNext()) {
            enterpriseProductDao.delete(iter.next().getId());
        }
    }

    @Override
    public List<SaleGroupEnterprise> getOne(Long groupId, Long createGroupId, Long childEnterpriseId) {
        return dao.queryOne(groupId,createGroupId,childEnterpriseId);
    }

    @Override
    public List<SaleGroupEnterpriseVo> getEnterPriseList(Long groupId, Long createGroupId) {
        return dao.getEnterPriseList(groupId,createGroupId);
    }

	@Override
	public List<SaleGroupEnterprise> getIsExistEnterprise(Long groupId) {
		return dao.queryIsExistEnterprise(groupId);
	}

	@Override
	public int getChildIsBePartGroup(Long enterpriseId) {
		return dao.queryChildIsBePartGroup(enterpriseId);
	}

}