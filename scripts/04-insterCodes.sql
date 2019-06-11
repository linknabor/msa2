#　系统的引用值个数:11
#　本引用值的代码个数:11 -------i==1
delete from code_info where ci_sp_class='1';
INSERT INTO CODE_INFO  VALUES ('01', '1', '支付方式', '现金', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('02', '1', '支付方式', '转账', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('03', '1', '支付方式', 'POS（脱机）', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('04', '1', '支付方式', '支付宝', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('05', '1', '支付方式', '财付通', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('06', '1', '支付方式', '微信公众号', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('07', '1', '支付方式', '微信App', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('08', '1', '支付方式', '微信扫码', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('09', '1', '支付方式', '支付宝扫码', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('10', '1', '支付方式', 'POS（联机）', 'PayMethod');
INSERT INTO CODE_INFO  VALUES ('99', '1', '支付方式', '上海物业App', 'PayMethod');
#　本引用值的代码个数:17 -------i==2
delete from code_info where ci_sp_class='2';
INSERT INTO CODE_INFO  VALUES ('0', '2', '支付平台', '微信', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('1', '2', '支付平台', '威富通', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('2', '2', '支付平台', '河马付', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('3', '2', '支付平台', '通联', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('4', '2', '支付平台', '拉卡拉', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('5', '2', '支付平台', '兴业银联刷卡', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('6', '2', '支付平台', '建行银联刷卡', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('7', '2', '支付平台', '嘉兴银行刷卡', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('8', '2', '支付平台', '拉卡拉刷卡', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('9', '2', '支付平台', '建行在线', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('A', '2', '支付平台', '慧兜圈', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('B', '2', '支付平台', '汇付刷卡', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('C', '2', '支付平台', '汇付扫码', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('D', '2', '支付平台', '汇付在线支付', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('E', '2', '支付平台', '杉德刷卡', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('Y', '2', '支付平台', '财付通', 'PlatChannel');
INSERT INTO CODE_INFO  VALUES ('Z', '2', '支付平台', '现金', 'PlatChannel');
#　本引用值的代码个数:2 -------i==3
delete from code_info where ci_sp_class='3';
INSERT INTO CODE_INFO  VALUES ('0', '3', '是否标志', '否', 'IsFlag');
INSERT INTO CODE_INFO  VALUES ('1', '3', '是否标志', '是', 'IsFlag');
#　本引用值的代码个数:5 -------i==4
delete from code_info where ci_sp_class='5';
INSERT INTO CODE_INFO  VALUES ('01', '5', '交易状态', '正在支付中', 'MergerStatus');
INSERT INTO CODE_INFO  VALUES ('02', '5', '交易状态', '已支付', 'MergerStatus');
INSERT INTO CODE_INFO  VALUES ('03', '5', '交易状态', '未支付', 'MergerStatus');
INSERT INTO CODE_INFO  VALUES ('04', '5', '交易状态', '已清算', 'MergerStatus');
INSERT INTO CODE_INFO  VALUES ('05', '5', '交易状态', '已返点', 'MergerStatus');
#　本引用值的代码个数:2 -------i==5
delete from code_info where ci_sp_class='6';
INSERT INTO CODE_INFO  VALUES ('1', '6', '银行卡类型', '借记卡', 'CardType');
INSERT INTO CODE_INFO  VALUES ('2', '6', '银行卡类型', '贷记卡', 'CardType');
#　本引用值的代码个数:5 -------i==6
delete from code_info where ci_sp_class='7';
INSERT INTO CODE_INFO  VALUES ('0', '7', '商户状态', '启用', 'MchStatus');
INSERT INTO CODE_INFO  VALUES ('1', '7', '商户状态', '注销', 'MchStatus');
INSERT INTO CODE_INFO  VALUES ('2', '7', '商户状态', '未启用', 'MchStatus');
INSERT INTO CODE_INFO  VALUES ('3', '7', '商户状态', '待审核', 'MchStatus');
INSERT INTO CODE_INFO  VALUES ('4', '7', '商户状态', '审核失败', 'MchStatus');
#　本引用值的代码个数:6 -------i==7
delete from code_info where ci_sp_class='8';
INSERT INTO CODE_INFO  VALUES ('0', '8', '清算状态', '未清算', 'AccountStatus');
INSERT INTO CODE_INFO  VALUES ('1', '8', '清算状态', '待审核', 'AccountStatus');
INSERT INTO CODE_INFO  VALUES ('2', '8', '清算状态', '清算中', 'AccountStatus');
INSERT INTO CODE_INFO  VALUES ('3', '8', '清算状态', '已清算', 'AccountStatus');
INSERT INTO CODE_INFO  VALUES ('4', '8', '清算状态', '审核驳回', 'AccountStatus');
INSERT INTO CODE_INFO  VALUES ('9', '8', '清算状态', '清算失败', 'AccountStatus');
#　本引用值的代码个数:1 -------i==8
delete from code_info where ci_sp_class='9';
INSERT INTO CODE_INFO  VALUES ('01', '9', '支付渠道', '银联', 'PayChannel');
#　本引用值的代码个数:4 -------i==9
delete from code_info where ci_sp_class='12';
INSERT INTO CODE_INFO  VALUES ('0', '12', '地区类别', '全国', 'ReginType');
INSERT INTO CODE_INFO  VALUES ('1', '12', '地区类别', '省', 'ReginType');
INSERT INTO CODE_INFO  VALUES ('2', '12', '地区类别', '市', 'ReginType');
INSERT INTO CODE_INFO  VALUES ('3', '12', '地区类别', '区', 'ReginType');
#　本引用值的代码个数:3 -------i==10
delete from code_info where ci_sp_class='13';
INSERT INTO CODE_INFO  VALUES ('01', '13', '数据来源', '上海SaaS', 'DataSource');
INSERT INTO CODE_INFO  VALUES ('02', '13', '数据来源', '河北SaaS', 'DataSource');
INSERT INTO CODE_INFO  VALUES ('03', '13', '数据来源', '浙江SaaS', 'DataSource');
#　本引用值的代码个数:2 -------i==11
delete from code_info where ci_sp_class='14';
INSERT INTO CODE_INFO  VALUES ('01', '14', '渠道类型', '微信', 'MethodType');
INSERT INTO CODE_INFO  VALUES ('02', '14', '渠道类型', '支付宝', 'MethodType');
