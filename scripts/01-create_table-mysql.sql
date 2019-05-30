#�̻���Ϣ��
DROP TABLE IF EXISTS MSA_BASE_MCHINFO ;
CREATE TABLE MSA_BASE_MCHINFO(
ID                                                BIGINT default 0 NOT NULL, #ID
MCH_NO                                            VARCHAR(40) NULL, #�̻���
MCH_STATUS                                        CHAR(1) NOT NULL, #�̻�״̬
MCH_NAME                                          VARCHAR(40) NOT NULL, #�̻�����
MCH_ABBRE                                         VARCHAR(40) NOT NULL, #�̻����
SECRET                                            VARCHAR(40) NULL, #�̻���Կ
APPID                                             VARCHAR(20) NULL, #Ӧ��ID
METHOD_TYPE                                       CHAR(10) NOT NULL, #��������
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_LIMIT                                     DECIMAL(16,2) default 0 NOT NULL, #��������
PAY_PRODUCT                                       VARCHAR(10) NOT NULL, #֧����Ʒ
LINK_MAN                                          VARCHAR(80) NOT NULL, #��ϵ��
CERT_NO                                           VARCHAR(40) NOT NULL, #֤����
TEL                                               VARCHAR(20) NOT NULL, #��ϵ�绰
EMAIL                                             VARCHAR(20) NOT NULL, #��ϵ������
CUSTOMER_TEL                                      VARCHAR(20) NOT NULL, #�ͷ��绰
MCH_ADDR                                          VARCHAR(128) NULL, #�̻���ַ
INDUSTRY_TYPE                                     VARCHAR(10) NOT NULL, #��ҵ���
REMARK                                            VARCHAR(256) NULL, #��ע
ENTITY_ID                                         CHAR(10) NULL, #ID
CONSTRAINT MSA_BASE_MCHINFO_CHK9 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_BASE_MCHINFO_PK PRIMARY KEY(ID)   );

#�ͻ��̻���ϵ��
DROP TABLE IF EXISTS MSA_RELATE_MCH_CUST ;
CREATE TABLE MSA_RELATE_MCH_CUST(
CUST_ID                                           BIGINT default 0 NOT NULL, #�ͻ�ID
MCH_ID                                            BIGINT default 0 NOT NULL, #ID
CUST_NAME                                         VARCHAR(160) NULL, #�ͻ�����
CUST_ADDR                                         VARCHAR(80) NULL, #�ͻ���ַ
CONSTRAINT MSA_RELATE_MCH_CUST_PK PRIMARY KEY(CUST_ID)   );

#ԭʼ������Ϣ��
DROP TABLE IF EXISTS MSA_BASE_ORIGIN_VERIFY ;
CREATE TABLE MSA_BASE_ORIGIN_VERIFY(
ID                                                BIGINT default 0 NOT NULL, #ID
FILE_CREATE_DATE                                  CHAR(8) NULL, #�ļ���������
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
SYS_MCH_NO                                        VARCHAR(256) NOT NULL, #֧���̻���
MCH_NAME                                          VARCHAR(40) NULL, #�̻�����
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
FEE_AMT                                           DECIMAL(16,2) default 0 NOT NULL, #������
PAY_PRODUCT                                       VARCHAR(20) NULL, #֧����Ʒ
TRAN_TYPE                                         VARCHAR(20) NULL, #��������
ORDER_ID                                          BIGINT default 0 NOT NULL, #ҵ�񶩵���
ORIGIN_ORDER_ID                                   BIGINT default 0 NULL, #ԭҵ�񶩵���
ORIGIN_TRAN_DATE                                  CHAR(8) NULL, #ԭ��������
CHECK_FLAG                                        CHAR(1) NULL, #���˱�ʶ
REMARK                                            VARCHAR(256) NULL, #��ע
CONSTRAINT MSA_BASE_ORIGIN_VERIFY_PK PRIMARY KEY(ID)   );

#����������ϸ��
DROP TABLE IF EXISTS SP_LIQUIDATE_OWNER_DETAIL ;
CREATE TABLE SP_LIQUIDATE_OWNER_DETAIL(
ID                                                BIGINT default 0 NOT NULL, #ID
ORDER_ID                                          BIGINT default 0 NOT NULL, #ҵ�񶩵���
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
PAY_METHOD                                        CHAR(2) NOT NULL, #֧����ʽ
PLAT_CHANLE                                       CHAR(1) NOT NULL, #֧��ƽ̨
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #���ʽ��
CHANNEL_RATE                                      DECIMAL(5,2) NOT NULL, #�ɱ�����
CHANNEL_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #�ɱ����ʽ��
MCH_NO                                            VARCHAR(40) NOT NULL, #�̻���
SECT_ID                                           BIGINT default 0 NULL, #С��ID
SECT_NAME                                         VARCHAR(80) NULL, #С������
CSP_ID                                            BIGINT default 0 NULL, #��˾ID
CSP_NAME                                          VARCHAR(80) NULL, #��˾����
LIQUIDATE_ID                                      BIGINT default 0 NOT NULL, #ID
CONSTRAINT SP_LIQUIDATE_OWNER_DETAIL_CHK8 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT SP_LIQUIDATE_OWNER_DETAIL_CHK10 CHECK(CHANNEL_RATE<=100.00),
CONSTRAINT SP_LIQUIDATE_OWNER_DETAIL_PK PRIMARY KEY(ID)   );

#����������ܱ�
DROP TABLE IF EXISTS SP_LIQUIDATE_OWNER_SUM ;
CREATE TABLE SP_LIQUIDATE_OWNER_SUM(
ID                                                BIGINT default 0 NOT NULL, #ID
BANK_NAME                                         VARCHAR(40) NULL, #����������
ACCT_NAME                                         VARCHAR(40) NULL, #�����˻�����
ACCT_NO                                           VARCHAR(40) NULL, #�����˺�
LINK_MAN                                          VARCHAR(80) NULL, #��ϵ��
PROVINCE                                          VARCHAR(20) NULL, #����������ʡ��
CITY                                              VARCHAR(20) NULL, #���������ڳ���
PHONE                                             VARCHAR(20) NULL, #�����˻�Ԥ���ֻ���
ENTITY_NAME                                       VARCHAR(40) NULL, #ʵ������
LIQUIDATE_DATE                                    CHAR(8) NOT NULL, #��������
LIQUIDATE_TIME                                    CHAR(6) NOT NULL, #����ʱ��
LIQUIDATE_AMT                                     DECIMAL(16,2) default 0 NOT NULL, #������
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
LIQUIDATE_STATUS                                  CHAR(1) NOT NULL, #����״̬
LIQUIDATE_COUNT                                   DECIMAL(10) default 0 NOT NULL, #�������
OPER_NAME                                         VARCHAR(40) NULL, #������
LIQUIDATE_CONTENT                                 VARCHAR(256) NULL, #��������
CONSTRAINT SP_LIQUIDATE_OWNER_SUM_PK PRIMARY KEY(ID)   );

#�ڼ�����Ϣ��
DROP TABLE IF EXISTS MSA_BASE_HOLIDAY ;
CREATE TABLE MSA_BASE_HOLIDAY(
ID                                                BIGINT default 0 NOT NULL, #ID
HOLIDAY                                           CHAR(8) NOT NULL, #����
REMARK                                            VARCHAR(256) NULL, #��ע
CONSTRAINT MSA_BASE_HOLIDAY_PK PRIMARY KEY(ID,HOLIDAY)   );

#�˻�ʵ����Ϣ��
DROP TABLE IF EXISTS MSA_BASE_ACCT_INFO ;
CREATE TABLE MSA_BASE_ACCT_INFO(
ID                                                CHAR(10) NOT NULL, #ID
LIQUIDATION_CYCLE                                 DECIMAL(10) default 0 NOT NULL, #��������
ENTITY_NAME                                       VARCHAR(80) NOT NULL, #ʵ������
BANK_NAME                                         VARCHAR(40) NOT NULL, #����������
ACCOUNT_NAME                                      VARCHAR(80) NOT NULL, #�����˻�����
ACCOUNT_NO                                        VARCHAR(40) NOT NULL, #�����˺�
CUST_NAME                                         VARCHAR(40) NOT NULL, #�ͻ�����(��ҵ)
PROVINCE                                          VARCHAR(20) NOT NULL, #����������ʡ��
PHONE                                             VARCHAR(20) NOT NULL, #�����˻�Ԥ���ֻ���
CITY                                              VARCHAR(20) NOT NULL, #���������ڳ���
STATUS                                            CHAR(1) NOT NULL, #״̬
REMARK                                            VARCHAR(256) NULL, #��ע
CONSTRAINT MSA_BASE_ACCT_INFO_PK PRIMARY KEY(ID)   );

#���˻��ܱ�
DROP TABLE IF EXISTS MSA_BASE_CHECK_SUM ;
CREATE TABLE MSA_BASE_CHECK_SUM(
ID                                                BIGINT default 0 NOT NULL, #ID
SHOULD_PAY_AMT                                    DECIMAL(16,2) default 0 NOT NULL, #Ӧ����
SHOULD_DATE                                       CHAR(8) NOT NULL, #Ӧ������
ACCOUNT_AMT                                       DECIMAL(16,2) default 0 NULL, #���˽��
ACCOUNT_DATE                                      CHAR(8) NULL, #��������
PAY_NUM                                           DECIMAL(10) default 0 NOT NULL, #���ױ���
ACCOUNT_STATUS                                    CHAR(1) NOT NULL, #����״̬
ENTITY_ID                                         CHAR(10) NOT NULL, #ID
ENTITY_NAME                                       VARCHAR(40) NULL, #ʵ������
ACCOUNT_NO                                        VARCHAR(40) NULL, #�����˻�
REMARK                                            VARCHAR(256) NULL, #��ע
MCH_ID                                            BIGINT default 0 NULL, #ID
CONSTRAINT MSA_BASE_CHECK_SUM_PK PRIMARY KEY(ID)   );

#������ϸ��
DROP TABLE IF EXISTS MSA_BASE_CHECK_DETAIL ;
CREATE TABLE MSA_BASE_CHECK_DETAIL(
ID                                                BIGINT default 0 NOT NULL, #ID
ORDER_ID                                          BIGINT default 0 NOT NULL, #ҵ�񶩵���
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
PAY_METHOD                                        CHAR(2) NOT NULL, #֧����ʽ
PAY_PRODUCT                                       VARCHAR(20) NULL, #֧����Ʒ
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #���ʽ��
CHANNEL_RATE                                      DECIMAL(5,2) NOT NULL, #��������
CHANNEL_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #�������ʽ��
MCH_NO                                            VARCHAR(20) NULL, #ҵ���̻���
ORIGIN_ORDER_ID                                   BIGINT default 0 NULL, #ԭҵ�񶩵���
ORIGIN_TRAN_DATE                                  CHAR(8) NULL, #ԭ������������
SECT_NAME                                         VARCHAR(40) NULL, #С������
SECT_ID                                           BIGINT default 0 NULL, #С��ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
CSP_ID                                            BIGINT default 0 NULL, #��˾ID
CHECK_ID                                          BIGINT default 0 NULL, #ID
REMARK                                            VARCHAR(256) NULL, #��ע
CONSTRAINT MSA_BASE_CHECK_DETAIL_CHK8 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_BASE_CHECK_DETAIL_CHK10 CHECK(CHANNEL_RATE<=100.00),
CONSTRAINT MSA_BASE_CHECK_DETAIL_PK PRIMARY KEY(ID)   );

#֧��������
DROP TABLE IF EXISTS MSA_TRADE_PAY_ORDER ;
CREATE TABLE MSA_TRADE_PAY_ORDER(
ID                                                BIGINT default 0 NOT NULL, #֧������ID
TRAN_STATUS                                       CHAR(2) NOT NULL, #����״̬
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #���ʽ��
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
PAY_METHOD                                        CHAR(2) NOT NULL, #֧������
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
ACCT_DATE                                         CHAR(8) NULL, #��������
ACCT_TIME                                         CHAR(6) NULL, #����ʱ��
NEED_INVOICE                                      CHAR(1) NULL, #�Ƿ���Ҫ��Ʊ
CSP_ID                                            BIGINT default 0 NULL, #��ҵ��˾ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
SECT_ID                                           BIGINT default 0 NULL, #��ҵ��ĿID
SECT_NAME                                         VARCHAR(40) NULL, #��Ŀ����
PLAT_CHANNEL                                      CHAR(1) NOT NULL, #֧��ƽ̨
CARD_TYPE                                         CHAR(1) NULL, #���п�����
STAFF_NAME                                        VARCHAR(80) NOT NULL, #Ա������
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #ҵ���е����ʽ��
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #�ⲿ����ID
FROM_SYS                                          VARCHAR(20) NULL, #����ƽ̨
ORDER_ATTACH                                      VARCHAR(256) NULL, #����������Ϣ
CONSTRAINT MSA_TRADE_PAY_ORDER_CHK3 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_TRADE_PAY_ORDER_PK PRIMARY KEY(ID)   );

#֧�������� ��������
DROP TABLE OPER_MSA_TRADE_PAY_ORDER;
CREATE TABLE OPER_MSA_TRADE_PAY_ORDER(
WORK_ID VARCHAR(38) NOT NULL,
DATA_TYPE CHAR(1) NOT NULL,
ID                                                BIGINT default 0 NOT NULL, #֧������ID
TRAN_STATUS                                       CHAR(2) NOT NULL, #����״̬
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #���ʽ��
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
PAY_METHOD                                        CHAR(2) NOT NULL, #֧������
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
ACCT_DATE                                         CHAR(8) NULL, #��������
ACCT_TIME                                         CHAR(6) NULL, #����ʱ��
NEED_INVOICE                                      CHAR(1) NULL, #�Ƿ���Ҫ��Ʊ
CSP_ID                                            BIGINT default 0 NULL, #��ҵ��˾ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
SECT_ID                                           BIGINT default 0 NULL, #��ҵ��ĿID
SECT_NAME                                         VARCHAR(40) NULL, #��Ŀ����
PLAT_CHANNEL                                      CHAR(1) NOT NULL, #֧��ƽ̨
CARD_TYPE                                         CHAR(1) NULL, #���п�����
STAFF_NAME                                        VARCHAR(80) NOT NULL, #Ա������
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #ҵ���е����ʽ��
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #�ⲿ����ID
FROM_SYS                                          VARCHAR(20) NULL, #����ƽ̨
ORDER_ATTACH                                      VARCHAR(256) NULL, #����������Ϣ
CONSTRAINT OPER_MSA_TRADE_PAY_ORDER_PK PRIMARY KEY(WORK_ID, DATA_TYPE, ID)   );

#֧�������� ��ҵ���
DROP TABLE BIZ_MSA_TRADE_PAY_ORDER;
CREATE TABLE BIZ_MSA_TRADE_PAY_ORDER(
BIZ_ID VARCHAR(38) NOT NULL,
ID                                                BIGINT default 0 NOT NULL, #֧������ID
TRAN_STATUS                                       CHAR(2) NOT NULL, #����״̬
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #���ʽ��
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
PAY_METHOD                                        CHAR(2) NOT NULL, #֧������
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
ACCT_DATE                                         CHAR(8) NULL, #��������
ACCT_TIME                                         CHAR(6) NULL, #����ʱ��
NEED_INVOICE                                      CHAR(1) NULL, #�Ƿ���Ҫ��Ʊ
CSP_ID                                            BIGINT default 0 NULL, #��ҵ��˾ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
SECT_ID                                           BIGINT default 0 NULL, #��ҵ��ĿID
SECT_NAME                                         VARCHAR(40) NULL, #��Ŀ����
PLAT_CHANNEL                                      CHAR(1) NOT NULL, #֧��ƽ̨
CARD_TYPE                                         CHAR(1) NULL, #���п�����
STAFF_NAME                                        VARCHAR(80) NOT NULL, #Ա������
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #ҵ���е����ʽ��
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #�ⲿ����ID
FROM_SYS                                          VARCHAR(20) NULL, #����ƽ̨
ORDER_ATTACH                                      VARCHAR(256) NULL, #����������Ϣ
CONSTRAINT BIZ_MSA_TRADE_PAY_ORDER_PK PRIMARY KEY(BIZ_ID, ID)   );

#�˿����
DROP TABLE IF EXISTS MSA_TRADE_REFUND_ORDER ;
CREATE TABLE MSA_TRADE_REFUND_ORDER(
ID                                                BIGINT default 0 NOT NULL, #�˿��ID
TRAN_STATUS                                       CHAR(2) NOT NULL, #�˿�״̬
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #���ʽ��
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
PAY_METHOD                                        CHAR(2) NOT NULL, #֧������
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
ACCT_DATE                                         CHAR(8) NULL, #��������
ACCT_TIME                                         CHAR(6) NULL, #����ʱ��
CSP_ID                                            BIGINT default 0 NULL, #��ҵ��˾ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
SECT_ID                                           BIGINT default 0 NULL, #��ҵ��ĿID
SECT_NAME                                         VARCHAR(40) NULL, #��Ŀ����
PLAT_CHANNEL                                      CHAR(1) NULL, #֧��ƽ̨
CARD_TYPE                                         CHAR(1) NULL, #���п�����
OPER_NAME                                         VARCHAR(80) NULL, #������
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #ҵ���е����ʽ��
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #�ⲿ����ID
FROM_SYS                                          VARCHAR(20) NULL, #����ƽ̨
ORDER_ATTACH                                      VARCHAR(256) NULL, #����������Ϣ
ORIGIN_ORDER_ID                                   BIGINT default 0 NOT NULL, #ԭ֧��������
CONSTRAINT MSA_TRADE_REFUND_ORDER_CHK3 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_TRADE_REFUND_ORDER_PK PRIMARY KEY(ID)   );

DROP TABLE IF EXISTS CODE_INFO;
CREATE TABLE `code_info` (
  `CI_SP_CODE` varchar(40) NOT NULL,
  `CI_SP_CLASS` varchar(40) NOT NULL,
  `CI_SP_CLASSNAME` varchar(80) NOT NULL,
  `CI_SP_NAME` varchar(256) NOT NULL,
  `CI_SP_REMARK` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`CI_SP_CODE`,`CI_SP_CLASS`)
) ;