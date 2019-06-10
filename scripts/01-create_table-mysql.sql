#商户信息表
DROP TABLE IF EXISTS MSA_BASE_MCH_INFO ;
CREATE TABLE MSA_BASE_MCH_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
MCH_NO                                            VARCHAR(40) NULL, #商户号
MCH_STATUS                                        CHAR(1) NOT NULL, #商户状态
MCH_NAME                                          VARCHAR(40) NOT NULL, #商户名称
MCH_ABBRE                                         VARCHAR(40) NOT NULL, #商户简称
SECRET                                            VARCHAR(40) NULL, #商户密钥
APPID                                             VARCHAR(20) NULL, #应用ID
METHOD_TYPE                                       CHAR(2) NOT NULL, #渠道类型
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #费率
CONSULT_LIMIT                                     DECIMAL(16,2) default 0 NOT NULL, #费率上限
PAY_CHANNEL                                       CHAR(2) NOT NULL, #支付渠道
LINK_MAN                                          VARCHAR(80) NOT NULL, #联系人
CERT_NO                                           VARCHAR(40) NOT NULL, #证件号
TEL                                               VARCHAR(20) NOT NULL, #联系电话
EMAIL                                             VARCHAR(20) NOT NULL, #联系人邮箱
CUSTOMER_TEL                                      VARCHAR(20) NOT NULL, #客服电话
MCH_ADDR                                          VARCHAR(128) NULL, #商户地址
INDUSTRY_TYPE                                     VARCHAR(10) NOT NULL, #行业类别
REMARK                                            VARCHAR(256) NULL, #备注
DATA_SOURCE                                       CHAR(2) NOT NULL, #所属平台
CONSTRAINT MSA_BASE_MCH_INFO_CHK9 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_BASE_MCH_INFO_PK PRIMARY KEY(ID)   );

#客户商户关系表
DROP TABLE IF EXISTS MSA_RELATE_MCH_CUST ;
CREATE TABLE MSA_RELATE_MCH_CUST(
MCH_ID                                            BIGINT default 0 NOT NULL, #ID
CUST_ID                                           BIGINT default 0 NOT NULL, #客户ID
COMPANY_ID                                        BIGINT default 0 NULL, #客户归属企业ID
CUST_NAME                                         VARCHAR(160) NULL, #客户名称
ENTITY_ID                                         BIGINT default 0 NULL, #ID
CONSTRAINT MSA_RELATE_MCH_CUST_PK PRIMARY KEY(MCH_ID,CUST_ID)   );

#原始对账信息表
DROP TABLE IF EXISTS MSA_BASE_ORIGIN_RECON_FILE ;
CREATE TABLE MSA_BASE_ORIGIN_RECON_FILE(
ID                                                BIGINT default 0 NOT NULL, #ID
FILE_CREATE_DATE                                  CHAR(8) NULL, #文件生成日期
TRAN_DATE                                         CHAR(8) NOT NULL, #交易日期
TRAN_TIME                                         CHAR(6) NOT NULL, #交易时间
SYS_MCH_NO                                        VARCHAR(256) NOT NULL, #支付商户号
MCH_NAME                                          VARCHAR(40) NULL, #商户名称
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #交易金额
FEE_AMT                                           DECIMAL(16,2) default 0 NOT NULL, #手续费
PAY_PRODUCT                                       VARCHAR(20) NULL, #支付产品
TRAN_TYPE                                         VARCHAR(20) NULL, #交易类型
ORDER_ID                                          BIGINT default 0 NOT NULL, #业务订单号
ORIGIN_ORDER_ID                                   BIGINT default 0 NULL, #原业务订单号
ORIGIN_TRAN_DATE                                  CHAR(8) NULL, #原交易日期
CHECK_FLAG                                        CHAR(1) NULL, #对账标识
PAY_CHANNEL                                       CHAR(2) NOT NULL, #支付渠道
REMARK                                            VARCHAR(256) NULL, #备注
CONSTRAINT MSA_BASE_ORIGIN_RECON_FILE_PK PRIMARY KEY(ID)   );

#自主清算明细表
DROP TABLE IF EXISTS MSA_LIQUIDATE_OWNER_DETAIL ;
CREATE TABLE MSA_LIQUIDATE_OWNER_DETAIL(
ID                                                BIGINT default 0 NOT NULL, #ID
ORDER_ID                                          BIGINT default 0 NOT NULL, #业务订单号
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #交易金额
TRAN_DATE                                         CHAR(8) NOT NULL, #交易日期
TRAN_TIME                                         CHAR(6) NOT NULL, #交易时间
PAY_METHOD                                        CHAR(2) NOT NULL, #支付方式
PLAT_CHANLE                                       CHAR(1) NOT NULL, #支付平台
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #费率
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #费率金额
CHANNEL_RATE                                      DECIMAL(5,2) NOT NULL, #成本费率
CHANNEL_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #成本费率金额
MCH_NO                                            VARCHAR(40) NOT NULL, #商户号
SECT_ID                                           BIGINT default 0 NULL, #小区ID
SECT_NAME                                         VARCHAR(80) NULL, #小区名称
CSP_ID                                            BIGINT default 0 NULL, #公司ID
CSP_NAME                                          VARCHAR(80) NULL, #公司名称
LIQUIDATE_ID                                      BIGINT default 0 NOT NULL, #ID
CONSTRAINT MSA_LIQUIDATE_OWNER_DETAIL_CHK8 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_LIQUIDATE_OWNER_DETAIL_CHK10 CHECK(CHANNEL_RATE<=100.00),
CONSTRAINT MSA_LIQUIDATE_OWNER_DETAIL_PK PRIMARY KEY(ID)   );

#自主清算汇总表
DROP TABLE IF EXISTS MSA_LIQUIDATE_OWNER_SUM ;
CREATE TABLE MSA_LIQUIDATE_OWNER_SUM(
ID                                                BIGINT default 0 NOT NULL, #ID
BANK_NAME                                         VARCHAR(40) NULL, #开户行名称
ACCT_NAME                                         VARCHAR(40) NULL, #结算账户名称
ACCT_NO                                           VARCHAR(40) NULL, #结算账号
LINK_MAN                                          VARCHAR(80) NULL, #联系人
PROVINCE                                          VARCHAR(20) NULL, #开户行所在省份
CITY                                              VARCHAR(20) NULL, #开户行所在城市
PHONE                                             VARCHAR(20) NULL, #结算账户预留手机号
ENTITY_NAME                                       VARCHAR(40) NULL, #实体名称
LIQUIDATE_DATE                                    CHAR(8) NOT NULL, #结算日期
LIQUIDATE_TIME                                    CHAR(6) NOT NULL, #结算时间
LIQUIDATE_AMT                                     DECIMAL(16,2) default 0 NOT NULL, #结算金额
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #交易金额
LIQUIDATE_STATUS                                  CHAR(1) NOT NULL, #清算状态
LIQUIDATE_COUNT                                   INTEGER default 0 NOT NULL, #清算笔数
OPER_NAME                                         VARCHAR(40) NULL, #清算人
LIQUIDATE_CONTENT                                 VARCHAR(256) NULL, #清算内容
REMARK                                            VARCHAR(40) NULL, #备注
CONSTRAINT MSA_LIQUIDATE_OWNER_SUM_PK PRIMARY KEY(ID)   );

#节假日信息表
DROP TABLE IF EXISTS MSA_BASE_HOLIDAY ;
CREATE TABLE MSA_BASE_HOLIDAY(
ID                                                BIGINT default 0 NOT NULL, #ID
HOLIDAY                                           CHAR(8) NOT NULL, #日期
REMARK                                            VARCHAR(256) NULL, #备注
CONSTRAINT MSA_BASE_HOLIDAY_PK PRIMARY KEY(ID,HOLIDAY)   );

#清算主体信息表
DROP TABLE IF EXISTS MSA_BASE_ACCT_INFO ;
CREATE TABLE MSA_BASE_ACCT_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
LIQUIDATION_CYCLE                                 INTEGER default 0 NOT NULL, #结算周期
ENTITY_NAME                                       VARCHAR(80) NOT NULL, #实体名称
BANK_NAME                                         VARCHAR(40) NOT NULL, #开户行名称
ACCOUNT_NAME                                      VARCHAR(80) NOT NULL, #结算账户名称
ACCOUNT_NO                                        VARCHAR(40) NOT NULL, #结算账号
PHONE                                             VARCHAR(20) NOT NULL, #结算账户预留手机号
STATUS                                            CHAR(1) NOT NULL, #状态
DATA_SOURCE                                       CHAR(2) NOT NULL, #所属平台
COMPANY_ID                                        BIGINT default 0 NOT NULL, #归属企业ID
COMPANY_NAME                                      VARCHAR(40) NOT NULL, #企业名称
REMARK                                            VARCHAR(256) NULL, #备注
PROVINCE_ID                                       BIGINT default 0 NOT NULL, #ID
CITY_ID                                           BIGINT default 0 NOT NULL, #ID
CONSTRAINT MSA_BASE_ACCT_INFO_PK PRIMARY KEY(ID)   );

#对账汇总表
DROP TABLE IF EXISTS MSA_BASE_CHECK_SUM ;
CREATE TABLE MSA_BASE_CHECK_SUM(
ID                                                BIGINT default 0 NOT NULL, #ID
SHOULD_PAY_AMT                                    DECIMAL(16,2) default 0 NOT NULL, #应结金额
SHOULD_DATE                                       CHAR(8) NOT NULL, #应结日期
ACCOUNT_AMT                                       DECIMAL(16,2) default 0 NULL, #到账金额
ACCOUNT_DATE                                      CHAR(8) NULL, #到账日期
PAY_NUM                                           INTEGER default 0 NOT NULL, #交易笔数
ACCOUNT_STATUS                                    CHAR(1) NOT NULL, #清算状态
ENTITY_ID                                         BIGINT default 0 NOT NULL, #ID
ENTITY_NAME                                       VARCHAR(40) NULL, #实体名称
ACCOUNT_NO                                        VARCHAR(40) NULL, #结算账户
REMARK                                            VARCHAR(256) NULL, #备注
MCH_ID                                            BIGINT default 0 NULL, #ID
SECT_ID                                           BIGINT default 0 NOT NULL, #小区ID
SECT_NAME                                         VARCHAR(40) NULL, #小区名称
CSP_ID                                            BIGINT default 0 NOT NULL, #公司ID
CSP_NAME                                          VARCHAR(40) NULL, #公司名称
CONSTRAINT MSA_BASE_CHECK_SUM_PK PRIMARY KEY(ID)   );

#对账明细表
DROP TABLE IF EXISTS MSA_BASE_CHECK_DETAIL ;
CREATE TABLE MSA_BASE_CHECK_DETAIL(
ID                                                BIGINT default 0 NOT NULL, #ID
ORDER_ID                                          BIGINT default 0 NOT NULL, #业务订单号
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #交易金额
PAY_METHOD                                        CHAR(2) NOT NULL, #支付方式
PAY_PRODUCT                                       VARCHAR(20) NULL, #支付产品
TRAN_DATE                                         CHAR(8) NOT NULL, #交易日期
TRAN_TIME                                         CHAR(6) NOT NULL, #交易时间
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #费率
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #费率金额
CHANNEL_RATE                                      DECIMAL(5,2) NOT NULL, #渠道费率
CHANNEL_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #渠道费率金额
MCH_NO                                            VARCHAR(20) NULL, #业务商户号
ORIGIN_ORDER_ID                                   BIGINT default 0 NULL, #原业务订单号
ORIGIN_TRAN_DATE                                  CHAR(8) NULL, #原订单交易日期
SECT_NAME                                         VARCHAR(40) NULL, #小区名称
SECT_ID                                           BIGINT default 0 NULL, #小区ID
CSP_NAME                                          VARCHAR(40) NULL, #公司名称
CSP_ID                                            BIGINT default 0 NULL, #公司ID
CHECK_ID                                          BIGINT default 0 NULL, #ID
REMARK                                            VARCHAR(256) NULL, #备注
CONSTRAINT MSA_BASE_CHECK_DETAIL_CHK8 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_BASE_CHECK_DETAIL_CHK10 CHECK(CHANNEL_RATE<=100.00),
CONSTRAINT MSA_BASE_CHECK_DETAIL_PK PRIMARY KEY(ID)   );

#支付订单表
DROP TABLE IF EXISTS MSA_TRADE_PAY_ORDER ;
CREATE TABLE MSA_TRADE_PAY_ORDER(
ID                                                BIGINT default 0 NOT NULL, #支付订单ID
TRAN_STATUS                                       CHAR(2) NOT NULL, #交易状态
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #费率
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #费率金额
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #交易金额
PAY_METHOD                                        CHAR(2) NOT NULL, #支付方式
TRAN_DATE                                         CHAR(8) NOT NULL, #交易日期
TRAN_TIME                                         CHAR(6) NOT NULL, #交易时间
ACCT_DATE                                         CHAR(8) NULL, #记账日期
ACCT_TIME                                         CHAR(6) NULL, #记账时间
NEED_INVOICE                                      CHAR(1) NULL, #是否需要发票
CSP_ID                                            BIGINT default 0 NULL, #物业公司ID
CSP_NAME                                          VARCHAR(40) NULL, #公司名称
SECT_ID                                           BIGINT default 0 NULL, #物业项目ID
SECT_NAME                                         VARCHAR(40) NULL, #项目名称
CARD_TYPE                                         CHAR(1) NULL, #银行卡类型
STAFF_NAME                                        VARCHAR(80) NOT NULL, #员工名称
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #业主承担费率金额
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #外部订单ID
FROM_SYS                                          VARCHAR(20) NULL, #来自平台
ORDER_ATTACH                                      VARCHAR(256) NULL, #订单附加信息
MCH_ID                                            BIGINT default 0 NOT NULL, #商户ID
MCH_NO                                            VARCHAR(40) NOT NULL, #商户号
MCH_NAME                                          VARCHAR(40) NULL, #商户名称
MCH_ABBRE                                         VARCHAR(40) NULL, #商户简称
SECRET                                            VARCHAR(40) NULL, #商户密钥
APPID                                             VARCHAR(20) NULL, #应用ID
PAY_CHANNEL                                       CHAR(2) NOT NULL, #支付渠道
PAY_PRODUCT                                       VARCHAR(20) NULL, #支付产品
ENTITY_ID                                         BIGINT default 0 NOT NULL, #实体ID
ACCOUNT_NAME                                      VARCHAR(40) NULL, #结算账户名称
ACCOUNT_NO                                        VARCHAR(40) NULL, #结算账号
CONSTRAINT MSA_TRADE_PAY_ORDER_CHK3 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_TRADE_PAY_ORDER_PK PRIMARY KEY(ID)   );

#退款订单表
DROP TABLE IF EXISTS MSA_TRADE_REFUND_ORDER ;
CREATE TABLE MSA_TRADE_REFUND_ORDER(
ID                                                BIGINT default 0 NOT NULL, #退款订单ID
TRAN_STATUS                                       CHAR(2) NOT NULL, #退款状态
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #费率
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #费率金额
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #交易金额
PAY_METHOD                                        CHAR(2) NOT NULL, #支付方式
TRAN_DATE                                         CHAR(8) NOT NULL, #交易日期
TRAN_TIME                                         CHAR(6) NOT NULL, #交易时间
ACCT_DATE                                         CHAR(8) NULL, #记账日期
ACCT_TIME                                         CHAR(6) NULL, #记账时间
CSP_ID                                            BIGINT default 0 NULL, #物业公司ID
CSP_NAME                                          VARCHAR(40) NULL, #公司名称
SECT_ID                                           BIGINT default 0 NULL, #物业项目ID
SECT_NAME                                         VARCHAR(40) NULL, #项目名称
CARD_TYPE                                         CHAR(1) NULL, #银行卡类型
OPER_NAME                                         VARCHAR(80) NULL, #操作人
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #业主承担费率金额
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #外部订单ID
FROM_SYS                                          VARCHAR(20) NULL, #来自平台
ORDER_ATTACH                                      VARCHAR(256) NULL, #订单附加信息
ORIGIN_ORDER_ID                                   BIGINT default 0 NOT NULL, #原支付订单号
MCH_ID                                            BIGINT default 0 NOT NULL, #商户ID
MCH_NO                                            VARCHAR(40) NOT NULL, #商户号
MCH_NAME                                          VARCHAR(40) NULL, #商户名称
MCH_ABBRE                                         VARCHAR(40) NULL, #商户简称
SECRET                                            VARCHAR(40) NULL, #商户密钥
APPID                                             VARCHAR(20) NULL, #应用ID
PAY_CHANNEL                                       CHAR(2) NOT NULL, #支付渠道
PAY_PRODUCT                                       VARCHAR(20) NULL, #支付产品
ENTITY_ID                                         BIGINT default 0 NOT NULL, #实体ID
ACCOUNT_NAME                                      VARCHAR(40) NULL, #结算账户名称
ACCOUNT_NO                                        VARCHAR(40) NULL, #结算账号
CONSTRAINT MSA_TRADE_REFUND_ORDER_CHK3 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_TRADE_REFUND_ORDER_PK PRIMARY KEY(ID)   );

#地区信息表
DROP TABLE IF EXISTS MSA_BASE_REGIN_INFO ;
CREATE TABLE MSA_BASE_REGIN_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
REGIN_NAME                                        VARCHAR(40) NOT NULL, #地区名
REGIN_TYPE                                        CHAR(1) NOT NULL, #区域类别
SUPER_ID                                          BIGINT default 0 NOT NULL, #上级ID
CONSTRAINT MSA_BASE_REGIN_INFO_PK PRIMARY KEY(ID)   );

#支付产品信息表
DROP TABLE IF EXISTS MSA_BASE_PRODUCT_INFO ;
CREATE TABLE MSA_BASE_PRODUCT_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
PRODUCT_NAME                                      VARCHAR(40) NOT NULL, #产品名称
CONSTRAINT MSA_BASE_PRODUCT_INFO_PK PRIMARY KEY(ID)   );

#商户产品关系表
DROP TABLE IF EXISTS MSA_RELATE_MCH_PRODUCT ;
CREATE TABLE MSA_RELATE_MCH_PRODUCT(
MCH_ID                                            BIGINT default 0 NOT NULL, #ID
PRODUCT_ID                                        BIGINT default 0 NOT NULL, #ID
CONSTRAINT MSA_RELATE_MCH_PRODUCT_PK PRIMARY KEY(MCH_ID,PRODUCT_ID)   );

#清算对账关系表
DROP TABLE IF EXISTS MSA_RELATE_LIQUIDATE_CHECK ;
CREATE TABLE MSA_RELATE_LIQUIDATE_CHECK(
LIQUIDATE_ID                                      BIGINT default 0 NOT NULL, #ID
CHECK_ID                                          BIGINT default 0 NOT NULL, #ID
CONSTRAINT MSA_RELATE_LIQUIDATE_CHECK_PK PRIMARY KEY(LIQUIDATE_ID,CHECK_ID)   );


