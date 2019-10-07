建议：先从企业部分开始做，修改的功能是最全的（包括了平台管理的功能），测试无误后COPY+修改实现平台管理员部分功能

具体功能--企业
    1. 企业银行卡管理：企业设置自己的银行卡信息，用于在“提现”时选择提现到哪个银行账户
                        程序入口：EnterpriseBankCardAction
                        页面文件：\jsp\capital\enterprise\bank
                        
    2. 企业资金管理：查看资金概览、进行充值、查看充值记录、申请提现、查看提现记录（根据提现状态过滤）
                        程序入口：EnterpriseCaptialAction
                        页面文件：\jsp\capital\enterprise\info     
                 \jsp\capital\enterprise\info\recharge 
                 \jsp\capital\enterprise\info\extract
                 
    3. 企业资金变动：查看企业的所有资金流水，按业务类型分为：充值、提现、下单、退款
                        程序入口：EnterpriseAccountLogAction
                        页面文件：\jsp\capital\enterprise\account_log
                        
    4. 企业付收款：查看上级对我的预收款、查看和设置我对下级的预收款
                        程序入口：EnterpriseStorageMoneyAction
                        页面文件：\jsp\capital\enterprise\storage_money


具体功能--平台管理
    1. 资金概览
    2. 充值资金
    3. 提现资金
    4. 交易资金
                功能描述参考：资金_0317.txt
----------------------------------------------------------------------------------------
SQL：
    # 企业资金表
    select * from t_enterprise_captial ;

    # 企业-银行卡
    select * from t_enterprise_bank_card ;

    # 充值
    select * from t_recharge ;

    # 提现
    select * from t_extract ;

    # 企业资金变动（记录）
    select * from t_enterprise_account_log ;