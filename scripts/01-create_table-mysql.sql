#�̻���Ϣ��
DROP TABLE IF EXISTS MSA_BASE_MCH_INFO ;
CREATE TABLE MSA_BASE_MCH_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
MCH_NO                                            VARCHAR(40) NULL, #�̻���
MCH_STATUS                                        CHAR(1) NOT NULL, #�̻�״̬
MCH_NAME                                          VARCHAR(40) NOT NULL, #�̻�����
MCH_ABBRE                                         VARCHAR(40) NOT NULL, #�̻����
SECRET                                            VARCHAR(40) NULL, #�̻���Կ
APPID                                             VARCHAR(20) NULL, #Ӧ��ID
METHOD_TYPE                                       CHAR(2) NOT NULL, #��������
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_LIMIT                                     DECIMAL(16,2) default 0 NOT NULL, #��������
PAY_CHANNEL                                       CHAR(2) NOT NULL, #֧������
LINK_MAN                                          VARCHAR(80) NOT NULL, #��ϵ��
CERT_NO                                           VARCHAR(40) NOT NULL, #֤����
TEL                                               VARCHAR(20) NOT NULL, #��ϵ�绰
EMAIL                                             VARCHAR(20) NOT NULL, #��ϵ������
CUSTOMER_TEL                                      VARCHAR(20) NOT NULL, #�ͷ��绰
MCH_ADDR                                          VARCHAR(128) NULL, #�̻���ַ
INDUSTRY_TYPE                                     VARCHAR(10) NOT NULL, #��ҵ���
REMARK                                            VARCHAR(256) NULL, #��ע
DATA_SOURCE                                       CHAR(2) NOT NULL, #����ƽ̨
CONSTRAINT MSA_BASE_MCH_INFO_CHK9 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_BASE_MCH_INFO_PK PRIMARY KEY(ID)   );

#�ͻ��̻���ϵ��
DROP TABLE IF EXISTS MSA_RELATE_MCH_CUST ;
CREATE TABLE MSA_RELATE_MCH_CUST(
MCH_ID                                            BIGINT default 0 NOT NULL, #ID
CUST_ID                                           BIGINT default 0 NOT NULL, #�ͻ�ID
COMPANY_ID                                        BIGINT default 0 NULL, #�ͻ�������ҵID
CUST_NAME                                         VARCHAR(160) NULL, #�ͻ�����
ENTITY_ID                                         BIGINT default 0 NULL, #ID
CONSTRAINT MSA_RELATE_MCH_CUST_PK PRIMARY KEY(MCH_ID,CUST_ID)   );

#ԭʼ������Ϣ��
DROP TABLE IF EXISTS MSA_BASE_ORIGIN_RECON_FILE ;
CREATE TABLE MSA_BASE_ORIGIN_RECON_FILE(
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
PAY_CHANNEL                                       CHAR(2) NOT NULL, #֧������
REMARK                                            VARCHAR(256) NULL, #��ע
CONSTRAINT MSA_BASE_ORIGIN_RECON_FILE_PK PRIMARY KEY(ID)   );

#����������ϸ��
DROP TABLE IF EXISTS MSA_LIQUIDATE_OWNER_DETAIL ;
CREATE TABLE MSA_LIQUIDATE_OWNER_DETAIL(
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
CONSTRAINT MSA_LIQUIDATE_OWNER_DETAIL_CHK8 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_LIQUIDATE_OWNER_DETAIL_CHK10 CHECK(CHANNEL_RATE<=100.00),
CONSTRAINT MSA_LIQUIDATE_OWNER_DETAIL_PK PRIMARY KEY(ID)   );

#����������ܱ�
DROP TABLE IF EXISTS MSA_LIQUIDATE_OWNER_SUM ;
CREATE TABLE MSA_LIQUIDATE_OWNER_SUM(
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
LIQUIDATE_COUNT                                   INTEGER default 0 NOT NULL, #�������
OPER_NAME                                         VARCHAR(40) NULL, #������
LIQUIDATE_CONTENT                                 VARCHAR(256) NULL, #��������
REMARK                                            VARCHAR(40) NULL, #��ע
CONSTRAINT MSA_LIQUIDATE_OWNER_SUM_PK PRIMARY KEY(ID)   );

#�ڼ�����Ϣ��
DROP TABLE IF EXISTS MSA_BASE_HOLIDAY ;
CREATE TABLE MSA_BASE_HOLIDAY(
ID                                                BIGINT default 0 NOT NULL, #ID
HOLIDAY                                           CHAR(8) NOT NULL, #����
REMARK                                            VARCHAR(256) NULL, #��ע
CONSTRAINT MSA_BASE_HOLIDAY_PK PRIMARY KEY(ID,HOLIDAY)   );

#����������Ϣ��
DROP TABLE IF EXISTS MSA_BASE_ACCT_INFO ;
CREATE TABLE MSA_BASE_ACCT_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
LIQUIDATION_CYCLE                                 INTEGER default 0 NOT NULL, #��������
ENTITY_NAME                                       VARCHAR(80) NOT NULL, #ʵ������
BANK_NAME                                         VARCHAR(40) NOT NULL, #����������
ACCOUNT_NAME                                      VARCHAR(80) NOT NULL, #�����˻�����
ACCOUNT_NO                                        VARCHAR(40) NOT NULL, #�����˺�
PHONE                                             VARCHAR(20) NOT NULL, #�����˻�Ԥ���ֻ���
STATUS                                            CHAR(1) NOT NULL, #״̬
DATA_SOURCE                                       CHAR(2) NOT NULL, #����ƽ̨
COMPANY_ID                                        BIGINT default 0 NOT NULL, #������ҵID
COMPANY_NAME                                      VARCHAR(40) NOT NULL, #��ҵ����
REMARK                                            VARCHAR(256) NULL, #��ע
PROVINCE_ID                                       BIGINT default 0 NOT NULL, #ID
CITY_ID                                           BIGINT default 0 NOT NULL, #ID
CONSTRAINT MSA_BASE_ACCT_INFO_PK PRIMARY KEY(ID)   );

#���˻��ܱ�
DROP TABLE IF EXISTS MSA_BASE_CHECK_SUM ;
CREATE TABLE MSA_BASE_CHECK_SUM(
ID                                                BIGINT default 0 NOT NULL, #ID
SHOULD_PAY_AMT                                    DECIMAL(16,2) default 0 NOT NULL, #Ӧ����
SHOULD_DATE                                       CHAR(8) NOT NULL, #Ӧ������
ACCOUNT_AMT                                       DECIMAL(16,2) default 0 NULL, #���˽��
ACCOUNT_DATE                                      CHAR(8) NULL, #��������
PAY_NUM                                           INTEGER default 0 NOT NULL, #���ױ���
ACCOUNT_STATUS                                    CHAR(1) NOT NULL, #����״̬
ENTITY_ID                                         BIGINT default 0 NOT NULL, #ID
ENTITY_NAME                                       VARCHAR(40) NULL, #ʵ������
ACCOUNT_NO                                        VARCHAR(40) NULL, #�����˻�
REMARK                                            VARCHAR(256) NULL, #��ע
MCH_ID                                            BIGINT default 0 NULL, #ID
SECT_ID                                           BIGINT default 0 NOT NULL, #С��ID
SECT_NAME                                         VARCHAR(40) NULL, #С������
CSP_ID                                            BIGINT default 0 NOT NULL, #��˾ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
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
PAY_METHOD                                        CHAR(2) NOT NULL, #֧����ʽ
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
ACCT_DATE                                         CHAR(8) NULL, #��������
ACCT_TIME                                         CHAR(6) NULL, #����ʱ��
NEED_INVOICE                                      CHAR(1) NULL, #�Ƿ���Ҫ��Ʊ
CSP_ID                                            BIGINT default 0 NULL, #��ҵ��˾ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
SECT_ID                                           BIGINT default 0 NULL, #��ҵ��ĿID
SECT_NAME                                         VARCHAR(40) NULL, #��Ŀ����
CARD_TYPE                                         CHAR(1) NULL, #���п�����
STAFF_NAME                                        VARCHAR(80) NOT NULL, #Ա������
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #ҵ���е����ʽ��
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #�ⲿ����ID
FROM_SYS                                          VARCHAR(20) NULL, #����ƽ̨
ORDER_ATTACH                                      VARCHAR(256) NULL, #����������Ϣ
MCH_ID                                            BIGINT default 0 NOT NULL, #�̻�ID
MCH_NO                                            VARCHAR(40) NOT NULL, #�̻���
MCH_NAME                                          VARCHAR(40) NULL, #�̻�����
MCH_ABBRE                                         VARCHAR(40) NULL, #�̻����
SECRET                                            VARCHAR(40) NULL, #�̻���Կ
APPID                                             VARCHAR(20) NULL, #Ӧ��ID
PAY_CHANNEL                                       CHAR(2) NOT NULL, #֧������
PAY_PRODUCT                                       VARCHAR(20) NULL, #֧����Ʒ
ENTITY_ID                                         BIGINT default 0 NOT NULL, #ʵ��ID
ACCOUNT_NAME                                      VARCHAR(40) NULL, #�����˻�����
ACCOUNT_NO                                        VARCHAR(40) NULL, #�����˺�
CONSTRAINT MSA_TRADE_PAY_ORDER_CHK3 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_TRADE_PAY_ORDER_PK PRIMARY KEY(ID)   );

#�˿����
DROP TABLE IF EXISTS MSA_TRADE_REFUND_ORDER ;
CREATE TABLE MSA_TRADE_REFUND_ORDER(
ID                                                BIGINT default 0 NOT NULL, #�˿��ID
TRAN_STATUS                                       CHAR(2) NOT NULL, #�˿�״̬
CONSULT_RATE                                      DECIMAL(5,2) NOT NULL, #����
CONSULT_AMT                                       DECIMAL(16,2) default 0 NOT NULL, #���ʽ��
TRAN_AMT                                          DECIMAL(16,2) default 0 NOT NULL, #���׽��
PAY_METHOD                                        CHAR(2) NOT NULL, #֧����ʽ
TRAN_DATE                                         CHAR(8) NOT NULL, #��������
TRAN_TIME                                         CHAR(6) NOT NULL, #����ʱ��
ACCT_DATE                                         CHAR(8) NULL, #��������
ACCT_TIME                                         CHAR(6) NULL, #����ʱ��
CSP_ID                                            BIGINT default 0 NULL, #��ҵ��˾ID
CSP_NAME                                          VARCHAR(40) NULL, #��˾����
SECT_ID                                           BIGINT default 0 NULL, #��ҵ��ĿID
SECT_NAME                                         VARCHAR(40) NULL, #��Ŀ����
CARD_TYPE                                         CHAR(1) NULL, #���п�����
OPER_NAME                                         VARCHAR(80) NULL, #������
OWNER_CONSULT_AMT                                 DECIMAL(16,2) default 0 NULL, #ҵ���е����ʽ��
OUTSIDE_ORDER_ID                                  VARCHAR(40) NULL, #�ⲿ����ID
FROM_SYS                                          VARCHAR(20) NULL, #����ƽ̨
ORDER_ATTACH                                      VARCHAR(256) NULL, #����������Ϣ
ORIGIN_ORDER_ID                                   BIGINT default 0 NOT NULL, #ԭ֧��������
MCH_ID                                            BIGINT default 0 NOT NULL, #�̻�ID
MCH_NO                                            VARCHAR(40) NOT NULL, #�̻���
MCH_NAME                                          VARCHAR(40) NULL, #�̻�����
MCH_ABBRE                                         VARCHAR(40) NULL, #�̻����
SECRET                                            VARCHAR(40) NULL, #�̻���Կ
APPID                                             VARCHAR(20) NULL, #Ӧ��ID
PAY_CHANNEL                                       CHAR(2) NOT NULL, #֧������
PAY_PRODUCT                                       VARCHAR(20) NULL, #֧����Ʒ
ENTITY_ID                                         BIGINT default 0 NOT NULL, #ʵ��ID
ACCOUNT_NAME                                      VARCHAR(40) NULL, #�����˻�����
ACCOUNT_NO                                        VARCHAR(40) NULL, #�����˺�
CONSTRAINT MSA_TRADE_REFUND_ORDER_CHK3 CHECK(CONSULT_RATE<=100.00),
CONSTRAINT MSA_TRADE_REFUND_ORDER_PK PRIMARY KEY(ID)   );

#������Ϣ��
DROP TABLE IF EXISTS MSA_BASE_REGIN_INFO ;
CREATE TABLE MSA_BASE_REGIN_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
REGIN_NAME                                        VARCHAR(40) NOT NULL, #������
REGIN_TYPE                                        CHAR(1) NOT NULL, #�������
SUPER_ID                                          BIGINT default 0 NOT NULL, #�ϼ�ID
CONSTRAINT MSA_BASE_REGIN_INFO_PK PRIMARY KEY(ID)   );

#֧����Ʒ��Ϣ��
DROP TABLE IF EXISTS MSA_BASE_PRODUCT_INFO ;
CREATE TABLE MSA_BASE_PRODUCT_INFO(
ID                                                BIGINT default 0 NOT NULL, #ID
PRODUCT_NAME                                      VARCHAR(40) NOT NULL, #��Ʒ����
CONSTRAINT MSA_BASE_PRODUCT_INFO_PK PRIMARY KEY(ID)   );

#�̻���Ʒ��ϵ��
DROP TABLE IF EXISTS MSA_RELATE_MCH_PRODUCT ;
CREATE TABLE MSA_RELATE_MCH_PRODUCT(
MCH_ID                                            BIGINT default 0 NOT NULL, #ID
PRODUCT_ID                                        BIGINT default 0 NOT NULL, #ID
CONSTRAINT MSA_RELATE_MCH_PRODUCT_PK PRIMARY KEY(MCH_ID,PRODUCT_ID)   );

#������˹�ϵ��
DROP TABLE IF EXISTS MSA_RELATE_LIQUIDATE_CHECK ;
CREATE TABLE MSA_RELATE_LIQUIDATE_CHECK(
LIQUIDATE_ID                                      BIGINT default 0 NOT NULL, #ID
CHECK_ID                                          BIGINT default 0 NOT NULL, #ID
CONSTRAINT MSA_RELATE_LIQUIDATE_CHECK_PK PRIMARY KEY(LIQUIDATE_ID,CHECK_ID)   );


