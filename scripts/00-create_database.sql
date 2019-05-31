create database msa_trade;
grant all privileges on msa_trade.* to msa_trade@'%' identified by 'msa_trade';
flush PRIVILEGES;